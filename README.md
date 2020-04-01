# astonSpringCine

Cinema printanier

Instructions :

Le but de ce devoir est de creer une API REST avec spring Boot pour gérer un cinema.

Le rendu du devoir devra ce faire sur un git public.


Creer un projet spring avec:
choix 1
spring web
Spring data mongo
(option) Lombok
choix 2 (+2)
Spring reactive web
Spring data mongo Reactive
(option) Lombok
Les models ( /4)
voir cinema-entity.png
L'api ( /5)
Creer une api REST
L'on doit avoir des CRUD pour les sceances, les films et les clients
L'on doit pouvoir ajouter un client a une seance
L'on doit pouvoir savoir combien a rapporter un film
L'on doit pouvoir savoir combien a rapporter un seance
L'on doit pouvoir savoir s'il reste de la place a une seance
L'on doit pouvoir trouver une seance entre deux plages horraire
L'on doit pouvoir trouver les seances en fonction des films
Le code metier ( /5)
Le prix de la seance depend de l'age du client et du type de seance
prix de base: 10euros
remise enfant (-10 ans): -4 euros
remise etudient : -2 euros
seance 3D: +3 euros
seance IMAX: + 6 euros
seance 4DX: + 8 euros
Le nombre de client dans une seance ne peut pas etre superieur au nombre de place de la salle
Un Client ne peut pas aller voir un film s'il na pas l'age.
Les bonus
Les Status Http doivent etre correcte. (+0.5)
Pouvoir rechercher les seances en fonction: (+1)
du genre de film
de plage horraire
en fonction d'une restriction d'age
le type de seances
Ajouter la possibilité de noté les films et d'ajouter des commentaires (+1)
Ajouter la possibilité de trier les films lors des differentes recherches par ordre de notation (+0.5)
Chercher les films en fonctions de leurs noms (+1)
Pour avoir 20, le code doit etre PARFAIT!