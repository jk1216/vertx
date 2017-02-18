Les sources sont dans le dossier "./tp/".

    	    cd tp/

	    vertx run Dispatcher.java -cluster -cp ./javafaker-0.12.jar
	     - Vertx Dispatcher
	       - Envoi une image (aléatoire) et un text faker sur le bus

	     vertx run Translator.java -cluster -cp ./gson-2.8.0.jar
	     - Vertx Translator
	       - recoit le texte
	       - traduction EN -> FR OK

	     vertx run Resize.java -cluster
	     - Vertx Resize
	       - reçoit l'image sous forme de buffer
	       - change le format de l'image en 100x100 et 400x400
	       - envoie les 3 images à la verticle Database

	     vertx run Database.js -cluster
	     - Vertx Database
	       - reçoit les image de la verticle de resize
	       - reçoit le texte de la verticle de traduction

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
