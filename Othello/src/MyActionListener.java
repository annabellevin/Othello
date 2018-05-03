import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class MyActionListener implements ActionListener {
	
	private int x;
	private int y;
	private Board board;
	
	 public MyActionListener(int x, int y, Board board) {
		// TODO Auto-generated constructor stub
		 this.x = x;
		 this.y = y;
		 this.board = board;
	}
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ArrayList<JButton> toFlip = new ArrayList<>();
		boolean movesPossible = false;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if (board.buttons[i][j].getBackground().equals(Board.GREEN)){
					toFlip = new ArrayList<>();
					for(int k = 0; k < 8; k++) {
						toFlip.addAll(getFlipped(i,j,k,board.getCurrentPlayer()));
					}
					if(toFlip.size() != 0) {
						movesPossible = true;
					}
					} 
		}
		} if (!movesPossible) {
			System.out.println("No moves possible!");
			board.noMovesConsecutive++;
			if (board.noMovesConsecutive == 2) {
				System.out.println("Game Over");
				System.out.println("Winner is " + board.getWinner());
				System.exit(0);
			}
			board.setCurrentPlayer((board.getCurrentPlayer().equals(Board.BLACK))? Board.WHITE: Board.BLACK);
			return;
		}
		board.noMovesConsecutive = 0;
		JButton button = (JButton)e.getSource();
		if (button.getBackground().equals(Board.BLACK)){
			System.out.println("BLACK");
			button.setEnabled(false);
		}
		if (button.getBackground().equals(Board.WHITE)){
			System.out.println("WHITE");
			button.setEnabled(false);
		}
		if (button.getBackground().equals(Board.GREEN)){
			System.out.println("GREEN");
			toFlip = new ArrayList<>();
			for(int i = 0; i < 8; i++) {
				toFlip.addAll(getFlipped(x,y,i,board.getCurrentPlayer()));
			}
			if(toFlip.size() == 0) {
				System.out.println("You can't play there");
				return;
			}
			for(JButton b : toFlip) {
				b.setBackground(board.getCurrentPlayer());
			}
			board.buttons[x][y].setBackground(board.getCurrentPlayer());
			board.setCurrentPlayer((board.getCurrentPlayer().equals(Board.BLACK))? Board.WHITE: Board.BLACK);
		}
	}
	
	
	private ArrayList<JButton> getFlipped(int x, int y, int direction, Color color){
		Color other = (color.equals(Board.WHITE))? Board.BLACK: Board.WHITE;
		ArrayList<JButton> toFlip = new ArrayList<>();
		do {
			switch(direction) {
			case 0: 
				x++;
				break;
			case 1:
				x--;
				break;
			case 2:
				y++;
				break;
			case 3:
				y--;
				break;
			case 4:
				x++;
				y++;
				break;
			case 5:
				x--;
				y++;
				break;
			case 6:
				y--;
				x++;
				break;
			case 7:
				y--;
				x--;
				break;
			}
			if(!(x < 8 && y < 8 && x >= 0 && y >= 0)) break;
			if(board.buttons[x][y].getBackground().equals(other)) {
				toFlip.add(board.buttons[x][y]);
			} else if(board.buttons[x][y].getBackground().equals(Board.GREEN)){
				return new ArrayList<>();
			} else {
				return toFlip;
			}
		} while(x < 8 && y < 8 && x >= 0 && y >= 0); 
		return new ArrayList<>();
	}
	
}
