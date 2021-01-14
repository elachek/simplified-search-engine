import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;

public class TableModelIndexInverse extends AbstractTableModel {
    private final ArrayList<NoeudIndexInverse> noeuds;
    private final String[] columns ;

    public TableModelIndexInverse(ArrayList<NoeudIndexInverse> noeuds, String[] columns){
        super();
        //trier la liste en ordre alphabétique
        Collections.sort(noeuds);
        this.noeuds = noeuds ;
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
        NoeudIndexInverse noeud = noeuds.get(row);
            if(col == 0){
                //si l'index est 0 alors on affiche le nom unique
                return noeud.getMot();
            } else if( col % 2 == 0){
                //si l'index est par on affiche alors la valeur de la fréquence
                return noeud.getFrequenceDisponibleCourant(col);
            }else{
                //sinon on affinche la valeur de fichier
                return noeud.getFichierDisponibleCourant(col);
            }
    }

    // Nom de la colonne
    public String getColumnName(int col) {
        return columns[col] ;
    }

}
