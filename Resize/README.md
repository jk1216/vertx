- Vertx Resize
  - reçoit l'image sous forme de buffer
  - change le format de l'image en 100x100 et 400x400
  - envoie les 3 images à la verticle Database
					     
Pour lancer la verticle seule:

		vertx run Resize.java -cluster
					