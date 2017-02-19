Chaque verticle est dans son dossier respectif. Par exemple, la verticle de traduction se trouve dans "./Translate/".
NB: Chaque sous-dossier possède un README.

Pour lancer le projet, il faut avant tout avoir les images docker "vertx/vertx3" et "vertx/vertx3-exec" que l'on obtient de la manière suivante:

        docker pull vertx/vertx3
        docker pull vertx/vertx3-exec

** Ajouter la procédure de lancement du projet ici

Il suffit de lancer les verticles avec "-cluster' pour que celles-ci fonctionnent sur un même réseau (entre plusieurs machines, virtuelles ou non).

Problèmes rencontrés:

	  - Documentation fiable, mais peu d'aide quant à la mise en place / démonstration
	  - Peu d'activité sur internet (forum, stackoverflow, etc.)
	  - Implémentation Javascript peu fonctionnelle (bcp de méthodes ne sont simplement pas appelées car elles n'existent pas / n'ont pas étaient implémentées)
	  - Mettre en place la Base de donnée.

Solutions apportées:

	  -
	  -
	  -
	  - Utilisation de "Database as a service" (Heroku PostgreSQL) pour ne pas avoir à s'occuper de la mise en place de la BDD.

Les membres du groupe pour le TP1 sont Luc Georges, Nicolas Ovejero et Viven Pradelles.
