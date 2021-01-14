import java.util.ArrayList;

public class NoeudIndexInverse implements Comparable<NoeudIndexInverse> {
    //mot unique
    private final String mot;
    //liste des noms de fichiers et de leurs fréquences
    //exemple: [ "1.txt" , "0" , "2.txt", "4", .... ]
    private final ArrayList<String> otherElements;

    public NoeudIndexInverse(ArrayList<String> elements){
        this.mot = elements.get(0);
        this.otherElements = elements;
    }

    public String getMot() {
        return mot;
    }

    //Ces deux fonctions permettent de générer le tableau TableModelIndexInverse
    public String getFichierDisponibleCourant(int i){
        return otherElements.get(i);
    }
    public String getFrequenceDisponibleCourant(int i){
        return otherElements.get(i);
    }

    //Triage par ordre alphabétique du mot unique
    @Override
    public int compareTo(NoeudIndexInverse other) {
        return mot.compareTo(other.mot);
    }
}

