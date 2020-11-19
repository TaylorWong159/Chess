package pieces;

import java.util.ArrayList;
import java.util.HashSet;

import chess.PlayerColor;
import javafx.scene.image.Image;
import main.Main;

public abstract class Piece {
	protected PlayerColor color;
	protected HashSet<Integer> invalidMoves;
	protected Image img;
	public boolean moved = false;
	
	public Piece(PlayerColor color) {
		this.color = color;
		invalidMoves = new HashSet<Integer>();
		for (int i = 0; i < 20; i++) invalidMoves.add(i);
		for (int i = 100; i < 120; i++) invalidMoves.add(i);
		for (int i = 20; i < 100; i += 10) invalidMoves.add(i);
		for (int i = 29; i < 109; i += 10) invalidMoves.add(i);
	}
	
	public Image getImage() {
		return img;
	};
	
	public boolean checkMove(int index) {
		Piece testPiece = Main.board.getPiece(index);
		if (!invalidMoves.contains(index) && (testPiece == null || testPiece.color != color)) return true;
		return false;
	}
	
	public boolean checkMoveBlocked(int index) {
		Piece testPiece = Main.board.getPiece(index);
		if (!invalidMoves.contains(index) && testPiece == null) return true;
		return false;
	}
	
	protected void generateMoves(ArrayList<Move> out, int index, int modifier) {
		int test = index + modifier;
		while(!invalidMoves.contains(test)) {
			Piece testPiece = Main.board.getPiece(test);
			if (testPiece != null && testPiece.color == color) break;
			out.add(new Move(index, test));
			if (testPiece != null) break;
			test += modifier;
		}
	}
	
	public abstract ArrayList<Move> getMoves(int index);
	
}
