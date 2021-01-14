import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModelIndex extends AbstractTableModel {
    private final List<Noeud> noeuds;
    private final String[] columns;

    public TableModelIndex(ArrayList<Noeud> NoeudsList, String[] columns){
        super();
        // Triage par ordre decroissant selon la fréquence
        NoeudsList.sort(Noeud.trierOrdreDecroissant);
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
        Noeud noeud = noeuds.get(row);
        //voici les cellules pour chaque colonne
        return switch (col) {
            case 0 -> noeud.getNomDuFichier();
            case 1 -> noeud.getMot();
            case 2 -> noeud.getFrequence();
            default -> null;
        };
    }

    // Nom de la colonne
    public String getColumnName(int col) {
        return columns[col] ;
    }

}
