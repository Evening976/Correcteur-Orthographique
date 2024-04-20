# Correcteur Orthographique

### Groupe 3 : LEFEVRE Quentin & SLAYBI Christopher

Dans le cadre de ce travail pratique, l'objectif est de mettre en œuvre un système de correction automatique de mots à l'aide de trigrammes. La fonction principale, correct, tente de corriger un mot lorsque il n'apparait pas tel qu'il est écrit dans le dictionnaire.

L'analyse de la complexité de la fonction correct s'articule autour de plusieurs étapes clés :

- Création de trigrammes (`createTrigram` et `processDictionnary`): 
        Chaque mot est converti en une série de trigrammes. Cette opération a une complexité linéaire en fonction de la longueur du mot.

- Recherche de trigrammes communs (`findCommonTrigramList`): 
        Les trigrammes du mot à corriger sont comparés à une HashMap des mots associés à ces trigrammes. 

- Calcul des Occurrences et Sélection de Mots Proches (`calculateOccurrences` & `selectWordsWithMaxTrigrams` puis `selectClosestWords`): 
        À partir des trigrammes communs, des occurrences sont comptées pour sélectionner les mots similaires, on utilise cette première maniere de trier. 
        Ensuite, parmis ces mots, on utilisera la distance Levenshtein pour identifier les corrections potentielles.

La complexité totale de la fonction correct dépend principalement de la taille du dictionnaire, de la longueur du mot à corriger et de la façon dont les trigrammes sont associés aux mots du dictionnaire. Dans le pire cas, la complexité peut être linéaire par rapport à la taille du dictionnaire et à la longueur du mot, avec des facteurs constants dépendant des opérations sur les trigrammes et du calcul du nombre d'occurences des trigrammes.

La complexité de la fonction **_correct(mot)_** semble donc etre O(M * L * D) où D est la taille du dictionnaire, L la longueur du mot et M le nombre maximal de mots associés à un trigramme.

En utilisant la liste de mots fautés donnée dans les ressources du TP, on arrive à proposer une correction de toute la liste en moins de 4 secondes (3795ms).