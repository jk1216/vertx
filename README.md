Les sources java sont dans javaCestCoolPD !
pour lancer il faut faire un vertx run fichier.java -cluster -cp ./javafaker-0.12.jar

Operationnel :

	     - Vertx Dispatcher
	       - Envoi une image (aléatoire) et un text faker sur le bus

	     - Vertx Translator
	       - recoit le texte
	       - pour traduire j'ai trouvé l'api google mais j'ai eu la flème de l'essayer

	     - Vertx Resize
	       - recoit l'image sous forme de buffer
	       - change le format de l'image en 100x100 et 400x400 