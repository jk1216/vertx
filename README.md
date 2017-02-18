Les sources java sont dans javaCestCoolPD !

Operationnel :

	     vertx run Dispatcher.java -cluster -cp ./javafaker-0.12.jar
	     - Vertx Dispatcher
	       - Envoi une image (aléatoire) et un text faker sur le bus

	     vertx run Translator.java -cluster -cp ./gson-2.8.0.jar
	     - Vertx Translator
	       - recoit le texte
	       - traduction EN -> FR OK

	     vertx run Resize.java -cluster
	     - Vertx Resize
	       - recoit l'image sous forme de buffer
	       - change le format de l'image en 100x100 et 400x400
