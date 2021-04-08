default: connectX/Connect4Game.java \
	connectX/IGameBoard.java \
	connectX/GameBoard.java
	javac connectX/Connect4Game.java

run: connectX/*.class
	java connectX.Connect4Game

clean:
	rm -f connectX/*.class 
