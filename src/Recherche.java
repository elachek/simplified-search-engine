import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Recherche {
    private final File file;
    private final ArrayList<String> listeDeMotsAChercher;
    private final ArrayList<String> listeDeMotsUniqueDuFichier = new ArrayList<>();
    private int score = 0;

    public Recherche(File file, ArrayList<String> listeDeMotsAChercher){
        this.file = file;
        this.listeDeMotsAChercher = listeDeMotsAChercher;

    }

    public ArrayList<NoeudRecherche> track() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));
        String ligne;// stockage de le ligne en cours de lecture
        while((ligne = reader.readLine())!= null) {
            String[] spliter = ligne.split("[\\p{Punct}\\s]+");
            listeDeMotsUniqueDuFichier.addAll(Arrays.asList(spliter));
        }

        ArrayList<NoeudRecherche> valuesRechercheCol = new ArrayList<>();

        if(listeDeMotsUniqueDuFichier.containsAll(listeDeMotsAChercher)){
            for(String mot : listeDeMotsAChercher){
                trouverApparition(mot);
            }
            NoeudRecherche n = new NoeudRecherche(file.getName(), score);
            valuesRechercheCol.add(n);
        }

        return valuesRechercheCol;
    }

    public void trouverApparition(String mot){
        //Determiner la frequence des mots uniques que l'utilisateur à entrée
        int nombreApparition = 0;
        for(String uniq: this.listeDeMotsUniqueDuFichier) {
            if(uniq.equals(mot)){
                nombreApparition++;
            }
        }
        score += nombreApparition;
    }

}
