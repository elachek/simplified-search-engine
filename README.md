# simplified-search-engine

### Compilation
The main class is Main.java

### Goals
Practice with java the following concepts and techniques: class organization, use of lists, sorting, file, and graphical interface.


### Problem
```
This system must index a set of texts (documents), build an index structure and use it to find the texts corresponding to a user request. These are the basic functions typically used in a search engine.
The search engine must do two processes: Indexing documents in offline mode and process a user request online.
```

### Program details
```
All tables have the possibility of being sorted manually by the user
I decided to work with ArrayLists, because it was easier for me to populate the corresponding arrays.
Each table has its own node class since the table columns and values are different.
    
For the index, the associated node is Node.java
For the reverse index, the associated node is NodeIndexInverse.java
For research, the associated node is NodeRecherche.java
```


