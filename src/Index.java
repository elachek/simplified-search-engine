/*
    Nom: Mohamed-Farouk El Achek
    Matricule:  20055590
*/

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Index {

    private final String nomDuFichier;
    private final ArrayList<Noeud> iList = new ArrayList<>();
    private final ArrayList<String> listeDesMots = new ArrayList<>();

    public Index(String nomDuFichier){
        this.nomDuFichier = nomDuFichier;
    }

    public ArrayList<Noeud> lireFichier(String pathFichier) throws IOException {
        /*
         * methode de lecture de fichiers
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathFichier), StandardCharsets.ISO_8859_1));
        // creation du buffer de lecture

        String ligne;// stockage de le ligne en cours de lecture
        while((ligne = reader.readLine())!= null) {
            decoupageDesMots(ligne);
        }

        ArrayList<String> listeDeMotUnique = (ArrayList<String>) this.listeDesMots.stream().distinct().collect(Collectors.toList());

        //Determiner la frequence de tous les mots uniques
        for(String uniq: listeDeMotUnique) {
            Noeud n = new Noeud(uniq);
            int nombreApparition = trouverApparition(uniq);
            n.ajouterFrequence(nombreApparition);
            n.ajouterNomDuFichier(this.nomDuFichier);
            this.iList.add(n);
        }

        //Triage par ordre croissant (index normal)
//        Collections.sort(this.iList, Noeud.trierOrdreCroissant);
        return this.iList;
    }

    public int trouverApparition(String uniq){
        int nombreApparition = 0;
        for(String mot: this.listeDesMots){
            if(uniq.equals(mot)){
                nombreApparition++;
            }
        }
        return nombreApparition;
    }

    public void decoupageDesMots(String ligne){
        String[] spliter = ligne.split("[\\p{Punct}\\s]+");
        this.listeDesMots.addAll(Arrays.asList(spliter));
    }

}
