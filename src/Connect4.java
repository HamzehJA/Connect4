import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Connect4 implements ActionListener {
	
	
	private int buttonRow[]; 
	private Connect4GUI board;
	
	Connect4() {	
		this.board = new Connect4GUI();
		setButtonRow();	
	}
	
	
	
	private void setButtonRow() {
		
		this.buttonRow = new int[42];
		int count = 0;
		for (int i = 1; i < board.getButtons().length; i++) {
			if ((i % 7) == 0 && i != 0) {
				count++;
			}
			buttonRow[i] = count;
		}
		
		board.getFrame().add(board.getButtonPanel(), BorderLayout.CENTER);
		for (int i = 0; i < 42; i++) {
			board.getButtons()[i] = new JButton(board.getChipFrame());
			board.getButtons()[i].addActionListener(this);
			board.getButtonPanel().add(board.getButtons()[i]);
		}

	}

	
	//Places Chip onto board
	private void placeChip(JButton button, boolean previous) {
		if (chipOrder(button) == false) {
			return;
		}
		if (button.getIcon() != board.getChipFrame()) {
			return;
		}
		if (board.isPress() == true) {
			button.setIcon(board.getrChip());
			board.setPress(false);
			 
		}
		else {
			button.setIcon(board.getyChip());
			board.setPress(true);
	
		}
	}
	
	
	
	private boolean chipOrder(JButton button) {
		int button_spot = checkChipPosition(button);
		if (button_spot >= 35) {
			return true;
		}
		
		if (board.getButtons()[button_spot + 7].getIcon() == board.getChipFrame()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
		placeChip(button, board.isPress());
	
		if (checkForWin(button) == true) {
			winningScreen(button);
		}
	}
	
	private int checkChipPosition(JButton button) {
		int button_spot = 0;
		for (int i = 0; i < board.getButtons().length; i++) {
			if (button == board.getButtons()[i]) {
				button_spot = i;
			}
		}
		return button_spot;
	}
	
	
	private void setXonChips (int[] buttonPos) {
		
		for (int i = 0; i < 4; i ++) {
			board.getButtons()[buttonPos[i]].setText("X");
			board.getButtons()[buttonPos[i]].setFont(new Font("Arial", Font.PLAIN, 100));
			board.getButtons()[buttonPos[i]].setForeground(new Color(0, 185, 255));
			board.getButtons()[buttonPos[i]].setHorizontalTextPosition(JButton.CENTER);
		}
	}
	
	//Checks horizontal 4
	private boolean horizontalcheck(int pos) {
		try {
			for (int j = 0; j < board.getButtons().length; j++) {
				if ( board.getButtons()[j].getIcon() != board.getChipFrame()) {
					int count = 0;
					pos = j;
					int buttonPos[] = new int[4];
					for (int i = 0; i < 4; i++) {
						buttonPos[i] = pos;
						if (buttonRow[pos] != buttonRow[pos + 1]) {
							break;
						}
						if (count >= 3) {
							setXonChips(buttonPos);
							return true;
						}
						if ( board.getButtons()[pos].getIcon() ==  board.getButtons()[pos + 1].getIcon()) {
							count++;
						}
						pos = pos + 1;
					}
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			
		}
		return false;
	}
	
	
	//Checks right diagonal 4
	private boolean downRightDiagonal(int pos) {
		for (int j = 0; j < board.getButtons().length; j++) {
			try {
				if (board.getButtons()[j].getIcon() != board.getChipFrame()) {
					int count = 0;
					pos = j;
					int buttonPos[] = new int[4];
					for (int i = 0; i < 4; i++) {
						buttonPos[i] = pos;
						if (count >= 3 ) {
							setXonChips(buttonPos);
							return true;
						}
						if (board.getButtons()[pos].getIcon() == board.getButtons()[pos + 8].getIcon()  && buttonRow[pos] != buttonRow[pos + 8]) {
							count++;
						}
						pos = pos + 8;
						}
					}
			}
			catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		return false;
	}
	
	//Checks left diagonal 4 
	private boolean downLeftDiagonal(int pos) {
		for (int j = 0; j < board.getButtons().length; j++) {
			try {
				if (board.getButtons()[j].getIcon() != board.getChipFrame()) {
					int count = 0;
					pos = j;
					int buttonPos[] = new int[4];
					for (int i = 0; i < 4; i++) {
						buttonPos[i] = pos;
						if (count >= 3 && buttonPos.length == 4) {
							setXonChips(buttonPos);
							return true;
						}
						if (board.getButtons()[pos].getIcon() == board.getButtons()[pos + 6].getIcon() && buttonRow[pos] != buttonRow[pos + 6]) {
							count++;
						}
						pos = pos + 6;
					}
				}
			}
			catch (ArrayIndexOutOfBoundsException e) {
				continue;
			} 
		}
		return false;
	}
	
	//Checks vertical 4
	private boolean verticalDownCheck(int pos) {
		try {
		if (pos <= 20 && board.getButtons()[pos].getIcon() != board.getChipFrame()) {
			int count = 0;
			int buttonPos[] = new int[4];
			for (int i = 0; i < 4; i++) {
				buttonPos[i] = pos;
				if (count >= 3) {
					setXonChips(buttonPos);
					return true;
				}
				if (board.getButtons()[pos].getIcon() == board.getButtons()[pos + 7].getIcon()) {
					count++;
				}
			pos = pos + 7;
			}
		}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			
		}
		return false;
	}
	
		
	private boolean checkForWin(JButton button) {
		int pos = checkChipPosition(button);
		if (button.getIcon() == board.getChipFrame()) {
			return false;
		}
		 
		if (horizontalcheck(pos) || downRightDiagonal(pos) || downLeftDiagonal(pos) || verticalDownCheck(pos)) {
			disableButtons();
			return true;
		}
		return false;
	}
	
	
	private void winningScreen(JButton button) {
		
		if (button.getIcon() == board.getrChip()) {
			board.getLabel().setText("RED TEAM WINS !");
			board.getLabel().setFont(new Font("Arial", Font.PLAIN, 100));
			board.getLabel().setForeground(new Color(0, 185, 255));
			board.getLabel().setBackground(new Color(255, 0, 0));
		}
		else {
			board.getLabel().setText("YELLOW TEAM WINS !");
			board.getLabel().setFont(new Font("Arial", Font.PLAIN, 100));
			board.getLabel().setForeground(new Color(0, 185, 255));
		}
	}
	
	
	private void disableButtons() {
		for (int i = 0; i < board.getButtons().length; i++) {
			board.getButtons()[i].removeActionListener(this);
		}
	}
}
	