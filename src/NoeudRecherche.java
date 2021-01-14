import java.util.Comparator;

public class NoeudRecherche {
    private final String nomDuFichier;
    private final int score;

    public NoeudRecherche(String nomDuFichier, int score){
        this.nomDuFichier = nomDuFichier;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getNomDuFichier() {
        return nomDuFichier;
    }

    /* Comparator pour trier la list par score en ordre décroissant */
    public static Comparator<NoeudRecherche> trierOrdreDecroissant = (s1, s2) -> {
        int score1 = s1.getScore();
        int score2 = s2.getScore();

        /* retourne par ordre décroissant */
        return score2 - score1;
    };

    @Override
    public String toString() {
        return "[ nomDuFichier=" + nomDuFichier + ", score=" + score + "]";
    }
}
