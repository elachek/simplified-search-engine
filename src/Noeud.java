/*
    Nom: Mohamed-Farouk El Achek
    Matricule:  20055590
*/

import java.util.Comparator;

public class Noeud {
    private int frequence;
    private final String mot;
    private String nomDuFichier;

    public Noeud(String mot){
        this.mot = mot;
        this.frequence = 1;
    }

    public String getMot(){
        return this.mot;
    }

    public int getFrequence(){
        return this.frequence;
    }

    public String getNomDuFichier() {
        return this.nomDuFichier;
    }

    public void ajouterNomDuFichier(String nomDuFichier) {
        this.nomDuFichier = nomDuFichier;
    }

    public void ajouterFrequence(int x){
        this.frequence *= x;
    }


    /* Comparator pour trier la list par ordre croissant */
    public static Comparator<Noeud> trierOrdreCroissant = (s1, s2) -> {
        int frequence1 = s1.getFrequence();
        int frequence2 = s2.getFrequence();

        /* retourne par ordre croissant */
        return frequence1 - frequence2;
    };

    /* Comparator pour trier la list par ordre décroissant */
    public static Comparator<Noeud> trierOrdreDecroissant = (s1, s2) -> {
        int frequence1 = s1.getFrequence();
        int frequence2 = s2.getFrequence();

        /* retourne par ordre décroissant */
        return frequence2 - frequence1;
    };

    @Override
    public String toString() {
        return "[ nomDuFichier=" + nomDuFichier + ", mot=" + mot + ", frequence=" + frequence + "]";
    }
}
