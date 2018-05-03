import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Board extends JPanel  {
	
	JButton[][] buttons;
	
	public static Color GREEN = Color.GREEN.darker();
	public static Color WHITE = Color.WHITE;
	public static Color BLACK = Color.BLACK;
	private Color currentPlayer = BLACK;
	private ArrayList<Consumer<Color>> colors;
	int noMovesConsecutive = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2195379760914381351L;
	
	public Board() {
		// TODO Auto-generated constructor stub
		// creates an 8*8 board
		buttons = new JButton[8][8];
		colors = new ArrayList<>();
		setLayout(new GridLayout(8, 8));
		for(int i=0; i < buttons.length; i ++) {
			for(int j=0; j < buttons.length; j ++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setBackground(GREEN);
				add(buttons[i][j]);
				buttons[i][j].addActionListener(new MyActionListener(i,j, this));
			}
		}
		buttons[3][3].setBackground(WHITE);
		buttons[3][4].setBackground(BLACK);
		buttons[4][4].setBackground(WHITE);
		buttons[4][3].setBackground(BLACK);
		buttons[3][3].setEnabled(false);
		buttons[3][4].setEnabled(false);
		buttons[4][4].setEnabled(false);
		buttons[4][3].setEnabled(false);
	}

	public void addPlayerChangeListener(Consumer<Color> color){
		colors.add(color);
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(Color newColor) {
		if(!newColor.equals(currentPlayer)) {
			for (Consumer<Color> col : colors) {
				col.accept(newColor);
			}
		}
		currentPlayer = newColor;
	}
	
	String getWinner() {
		int black = 0;
		int white = 0;
		int green = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(buttons[i][j].getBackground().equals(BLACK)) {
					black++;
				}else if(buttons[i][j].getBackground().equals(WHITE)){
					white++;
				}else {
					green++;
				}
			}
		}
		if(black > white) {
			return "BLACK";
		}
		else if(white > black) {
			return "WHITE";
		}

		return "TIE";
	}
	
	
}
