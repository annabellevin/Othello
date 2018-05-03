import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame root = new JFrame("Black Starts!"); 
		root.setSize(new Dimension(500,500));
		Board board = new Board();
		board.addPlayerChangeListener(color -> {
			root.setTitle((color.equals(Board.BLACK))? "Black's turn": "White's turn");
		});
		
		root.add(board);
		root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		root.setVisible(true);
	}

}
