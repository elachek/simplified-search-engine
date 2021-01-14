import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class IndexInverse {

    private final String directory;
    private final ArrayList<String> listeDesMots = new ArrayList<>();

    public IndexInverse(String directory){
        this.directory = directory;
    }

    public ArrayList<String> showFilesIndexInverse(String wordToTrack) throws IOException {
        File[] files = new File(this.directory).listFiles();
        assert files != null;
        Arrays.sort(files);
        //triage des noms des fichiers par ordre alphabétique

        ArrayList<String> result = new ArrayList<>();
        result.add(wordToTrack);

        //Boucle sur tous les fichiers pour déterminer le nombre d'apparition du mot à cherche.
        //Et calcule le nombre d'apparition du mot dans le fichier
        for (File file : files) {
                if(!file.getName().equals(".DS_Store")){
                    result.add(file.getName());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));
                    String ligne;// stockage de le ligne en cours de lecture
                    ArrayList<String> texteDuFichier = new ArrayList<>();
                    while ((ligne = reader.readLine()) != null) {
                        String[] spliter = ligne.split("[\\p{Punct}\\s]+");
                        texteDuFichier.addAll(Arrays.asList(spliter));
                    }
                    int nombreApparition = 0;

                    if(!texteDuFichier.isEmpty()){
                        for(String chaqueMotDuFichier : texteDuFichier){
                            if(wordToTrack.equals(chaqueMotDuFichier)){
                                nombreApparition++;
                            }
                        }
                    }

                    result.add("" + nombreApparition);

                }
        }
        return result;
    }

    //fonction permettant d'avoir tous les mots uniques des fichiers
    public ArrayList<String> getAllWordFromAllFiles(File fichier) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fichier), StandardCharsets.ISO_8859_1));

        String ligne;// stockage de le ligne en cours de lecture
        while ((ligne = reader.readLine()) != null) {
            decoupageDesMots(ligne);
        }
        return this.listeDesMots;
    }

    //calcule la fréquence de tous les mots
    public ArrayList<NoeudIndexInverse> calculateFrequenceForAllWords(ArrayList<String> list) throws IOException {
        list = (ArrayList<String>) list.stream().distinct().collect(Collectors.toList());
        ArrayList<NoeudIndexInverse> listFinalDeIndexInverse = new ArrayList<>();
        //boucle sur la liste des mots uniques pour savoir combien il y a de mot dans chaque fichier
        //retourne une array comme suit ["mot1", "fichier1", "frequence fichier1", "fichier2", "frequence fichier2"]
        for(String mot : list){
            NoeudIndexInverse n = new NoeudIndexInverse(showFilesIndexInverse(mot));
            listFinalDeIndexInverse.add(n);
        }
        return listFinalDeIndexInverse;
    }


    public void decoupageDesMots(String ligne){
        String[] spliter = ligne.split("[\\p{Punct}\\s]+");
        this.listeDesMots.addAll(Arrays.asList(spliter));
    }
}
