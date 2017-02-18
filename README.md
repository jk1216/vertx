Les sources sont dans le dossier tp.

	     vertx run Dispatcher.java -cluster -cp ./javafaker-0.12.jar
	     - Vertx Dispatcher
	       - Envoi une image (aléatoire) et un text faker sur le bus

	     vertx run Resize.java -cluster
	     - Vertx Resize
	       - reçoit l'image sous forme de buffer
	       - change le format de l'image en 100x100 et 400x400
	       - envoie les 3 images à la verticle Database

	     vertx run Translator.js -cluster
	     - Vertx Translator
	       - reçoit le texte depuis Dispatcher

	     vertx run Database.js -cluster
	     - Vertx Database
	       - reçoit les image de la verticle de resize
	       - reçoit le texte de la verticle de traduction

Il suffit de lancer les verticles avec "-cluster' pour que celles-ci fonctionnent sur un même réseau (entre plusieurs machines, virtuelles ou non).
