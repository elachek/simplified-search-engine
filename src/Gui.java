import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Gui implements ActionListener {
    //path
    private static String directory;
    //liste des fichiers
    private File[] files;
    //input pour la fonction de recherche
    private JTextField textFieldSearch;

    public Gui(String directory) throws IOException {
        Gui.directory = directory;

        JFrame frame = new JFrame("Interface graphique");
        //position du frame sur l'écran
        frame.setLocation(250, 100);
        //dimension du frame
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        // Gestion des tabs et attribution des vues pour chaque tab
        // chaque tab appelle une fonction qui génère un tableau avec les valeurs associées.
        JTabbedPane tabbedPane = new JTabbedPane();
        Component panelTab = affichage("index");
        tabbedPane.addTab("Affichage index", null, panelTab, null);
        tabbedPane.setSelectedIndex(0);
        Component panelTab2 = affichage("index inverse");
        tabbedPane.addTab("Affichage index inverse", null, panelTab2, null);
        Component panelTab3 = recherche();
        tabbedPane.addTab("Fonction de recherche", null, panelTab3, null);
        panel.add(tabbedPane);

        Container con = frame.getContentPane();
        con.setLayout(new BorderLayout());
        con.add(panel, BorderLayout.PAGE_START);

        frame.setVisible(true);
    }

    public JPanel recherche(){
        JPanel panelRecherche = new JPanel();
        panelRecherche.setBounds(40,80,200,200);

        //textfield pour que l'utilisateur puisse effectuer la recherche
        textFieldSearch = new JTextField("", 20);
        textFieldSearch.setBounds(50,100,80,30);

        //button activant l'apelle de la fonction de recherche
        JButton search = new JButton("Rechercher");
        search.setBounds(100,100,80,30);
        search.addActionListener(this);

        panelRecherche.add(textFieldSearch);
        panelRecherche.add(search);

        return panelRecherche;
    }

    public JPanel affichage(String type) throws IOException {
        JPanel panelAffichage = new JPanel(new BorderLayout());
        panelAffichage.setPreferredSize(new Dimension(700, 500));

        //Détermine tous les fichiers du document ressources.
        //Triage qui est effectué en ordre alphabétique
        files = new File(directory).listFiles();
        assert files != null;
        Arrays.sort(files);


        //Génère le tableau pour le tab index
        if(type.equals("index")) {
            //Recoit les valeurs du tableau index
            ArrayList<Noeud> valuesAffichageIndex = showFilesIndex(files);
            TableModel model1 = new TableModelIndex(valuesAffichageIndex, new String[]{"Fichier", "Mots trouvés", "Fréquences"});
            JTable table1 = new JTable(model1);
            //Active le sorting "de base" sur tous les colonnes
            table1.setAutoCreateRowSorter(true);
            table1.setBounds(30, 40, 700, 450);
            JScrollPane sp1 = new JScrollPane(table1);
            panelAffichage.add(sp1);
        }else {
            //Génère le tableau pour le tab index inverse
            //Ici, je détermine le nombre de fichier et j'ajoute pour chaque fichier une colonne
            //"Fichier" et "Fréquence" pour cela soit plus clair à visualiser dans le tableau
            String[] columns = new String[(files.length * 2) -1];
            columns[0] = "Mots";
            for (int i = 1; i < columns.length; i++) {
                if(i % 2 == 0){
                    columns[i] = "Fréquence";
                }else{
                    columns[i] = "Fichier";
                }
            }
            //Recoit les valeurs du tableau index inverse
            ArrayList<NoeudIndexInverse> valuesAffichageIndexInverse = showFilesIndexInverse(files);
            TableModel model2 = new TableModelIndexInverse(valuesAffichageIndexInverse, columns);
            JTable table2 = new JTable(model2);
            //Active le sorting "de base" sur tous les colonnes
            table2.setAutoCreateRowSorter(true);
            table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table2.setBounds(30, 40, 700, 450);
            JScrollPane sp2 = new JScrollPane(table2);
            panelAffichage.add(sp2);
        }

        return panelAffichage;
    }

    public static ArrayList<Noeud> showFilesIndex(File[] files) throws IOException {
        //fonction permettant de boucler sur tous les fichiers excepté .DS_Store
        //et j'ajoute les valeurs dans une ArrayList<Noeud>
        ArrayList<Noeud> values = new ArrayList<>();

        for (File file : files) {
                if(!file.getName().equals(".DS_Store")){
                        Index index = new Index(file.getName());
                        values.addAll(index.lireFichier(directory + file.getName()));
                }
        }
        return values;
    }

    public static ArrayList<NoeudIndexInverse> showFilesIndexInverse(File[] files) throws IOException {
        //fonction permettant de boucler sur tous les fichiers excepté .DS_Store.
        //j'ajoute dans une ArrayList<String> tous les mots uniques de tous les fichiers
        //et ensuite je calcule toutes les fréquences de chacun des mots uniques.
        //je retourne une NoeudIndexInverse qui a pour attribut le mot en question et
        //tous les noms de fichiers et leurs fréquences.
        ArrayList<String> AllWordsFromAllFiles = new ArrayList<>();

        for (File file : files) {
                if(!file.getName().equals(".DS_Store")){
                        IndexInverse indexInverse = new IndexInverse(directory);
                        AllWordsFromAllFiles.addAll(indexInverse.getAllWordFromAllFiles(file));
                }
        }
        IndexInverse indexInverse = new IndexInverse(directory);
        return indexInverse.calculateFrequenceForAllWords(AllWordsFromAllFiles);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Lorsque l'utilisateur clique sur le bouton Rechercher, la fonction de recherche s'active
       if(((JButton)e.getSource()).getText().equals("Rechercher")){
           //Une arraylist de tous les mots que l'utilisateur à entré.
           ArrayList<String> listeDeMotsAChercher = new ArrayList<>(Arrays.asList(textFieldSearch.getText().split(" ")));
           ArrayList<NoeudRecherche> valuesRecherche = new ArrayList<>();
           //Boucle sur tous les fichier pour avec la listeDeMotsAChercher
           for (File file : files) {
               if(!file.getName().equals(".DS_Store")){
                   Recherche rechercheMot = new Recherche(file, listeDeMotsAChercher);
                   try {
                       valuesRecherche.addAll(rechercheMot.track());
                   } catch (IOException ioException) {
                       ioException.printStackTrace();
                   }
               }
           }

           //Nouveau frame s'ouvre avec le tableau et les deux colonnes suivantes "nomDuFichier" et "score"
           JFrame frameRecherche = new JFrame();
           frameRecherche.setLocation(450, 100);
           JPanel panelRecherche = new JPanel();

           TableModel model3 = new TableModelRecherche(valuesRecherche, new String[]{"Nom du Fichier", "Score"});
           JTable table3 = new JTable(model3);
           //Active le sorting "de base" sur tous les colonnes
           table3.setAutoCreateRowSorter(true);
           JScrollPane sp2 = new JScrollPane(table3);

           panelRecherche.add(sp2);
           frameRecherche.add(panelRecherche);
           frameRecherche.setVisible(true);
           frameRecherche.pack();

       }
    }
}
