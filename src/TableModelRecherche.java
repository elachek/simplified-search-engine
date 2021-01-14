/*
    Nom: Mohamed-Farouk El Achek
    Matricule:  20055590
*/

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelRecherche extends AbstractTableModel {
    private final List<NoeudRecherche> noeuds;
    private final String[] columns;

    public TableModelRecherche(List<NoeudRecherche> NoeudsList, String[] columns){
        super();
        //triage par ordre décroissant selon le score du NoeudRecherche
        NoeudsList.sort(NoeudRecherche.trierOrdreDecroissant);
        this.noeuds = NoeudsList ;
        this.columns = columns;
    }

    // Nombre de colonnes du tableau
    public int getColumnCount() {
        return columns.length ;
    }

    // Nombre de lignes du tableau
    public int getRowCount() {
        return noeuds.size();
    }

    // L'objet qui doit "render" pour chaque cellule
    public Object getValueAt(int row, int col) {
        NoeudRecherche noeud = noeuds.get(row);
        return switch (col) {
            case 0 -> noeud.getNomDuFichier();
            case 1 -> noeud.getScore();
            default -> null;
        };
    }

    // Nom de la colonne
    public String getColumnName(int col) {
        return columns[col] ;
    }

}