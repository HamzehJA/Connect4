import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Connect4 implements ActionListener {
	JFrame frame = new JFrame();
	JFrame frameWin = new JFrame();
	JPanel buttonPanel = new JPanel();
	private JButton[] buttons = new JButton[42];
	private int buttonRow[] = new int[42];
	private boolean press = true;
	private ImageIcon chipFrame = new ImageIcon("buttoneFrame.png");
	private ImageIcon rChip = new ImageIcon("RedbuttonFrame.png");
	private ImageIcon yChip = new ImageIcon("YbuttonFrame.png");
	JLabel label = new JLabel();
	
	
	
	Connect4() {
		
		//Creating a label and image icon
		ImageIcon icon = new ImageIcon("Connect4.1.png");
		label.setIcon(icon);
		label.setOpaque(true);
		label.setBackground(new Color(234, 214, 21));
		label.setHorizontalAlignment(JLabel.CENTER);

		
		//Adding a button Panel 
		buttonPanel.setBackground(new Color(150, 150, 150));
		buttonPanel.setLayout(new GridLayout(6,7));
		buttonPanel.setVisible(true);
		buttonPanel.setSize(100, 100);
		buttonPanel.setBounds(400, 400, 1400, 1400);
		
		//Creating Frame
		frame.setVisible(true);
		frame.setSize(1600, 1600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Connect 4");
		frame.setLayout(new BorderLayout());
		frame.getContentPane().setBackground(Color.gray);
		
		frame.add(label, BorderLayout.PAGE_START);
		
		//Button Panel
		frame.add(buttonPanel, BorderLayout.CENTER);
		for (int i = 0; i < 42; i++) {
			buttons[i] = new JButton(chipFrame);
			buttons[i].addActionListener(this);
			buttonPanel.add(buttons[i]);
		}
		//Creating array to represent button row on the grid
		int count = 0;
		for (int i = 1; i < buttons.length; i++) {
			if ((i % 7) == 0 && i != 0) {
				count++;
			}
			buttonRow[i] = count;
		}
		
		
	}
	//Places Chip onto board
	public void placeChip(JButton button, boolean previous) {
		if (chipOrder(button) == false) {
			return;
		}
		if (button.getIcon() != chipFrame) {
			return;
		}
		if (press == true) {
			button.setIcon(rChip);
			press = false;
		}
		else {
		button.setIcon(yChip);;
		press = true;
		}
	}
	
	public boolean chipOrder(JButton button) {
		int button_spot = checkChipPosition(button);
		if (button_spot >= 35) {
			return true;
		}
		
		if (buttons[button_spot + 7].getIcon() == chipFrame) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean checkForWin(JButton button) {
		int pos = checkChipPosition(button);
		if (button.getIcon() == chipFrame) {
			return false;
		}
		 
		if (horizontalcheck(pos) || downRightDiagonal(pos) || downLeftDiagonal(pos) || verticalDownCheck(pos)) {
			disableButtons();
			return true;
		}
		return false;
	}
	
	//Checks horizontal wins
	private boolean horizontalcheck(int pos) {
		try {
			for (int j = 0; j < buttons.length; j++) {
				if (buttons[j].getIcon() != chipFrame) {
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
						if (buttons[pos].getIcon() == buttons[pos + 1].getIcon()) {
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
	//Checks right diagonal wins
	private boolean downRightDiagonal(int pos) {
		for (int j = 0; j < buttons.length; j++) {
			try {
				if (buttons[j].getIcon() != chipFrame) {
					int count = 0;
					pos = j;
					int buttonPos[] = new int[4];
					for (int i = 0; i < 4; i++) {
						buttonPos[i] = pos;
						if (count >= 3 ) {
							setXonChips(buttonPos);
							return true;
						}
						if (buttons[pos].getIcon() == buttons[pos + 8].getIcon()  && buttonRow[pos] != buttonRow[pos + 8]) {
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
	
	//Checks left diagonal wins 
	private boolean downLeftDiagonal(int pos) {
		for (int j = 0; j < buttons.length; j++) {
			try {
				if (buttons[j].getIcon() != chipFrame) {
					int count = 0;
					pos = j;
					int buttonPos[] = new int[4];
					for (int i = 0; i < 4; i++) {
						buttonPos[i] = pos;
						if (count >= 3 && buttonPos.length == 4) {
							setXonChips(buttonPos);
							return true;
						}
						if (buttons[pos].getIcon() == buttons[pos + 6].getIcon() && buttonRow[pos] != buttonRow[pos + 6]) {
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
	
	//Checks vertical wins
	private boolean verticalDownCheck(int pos) {
		try {
		if (pos <= 20 && buttons[pos].getIcon() != chipFrame) {
			int count = 0;
			int buttonPos[] = new int[4];
			for (int i = 0; i < 4; i++) {
				buttonPos[i] = pos;
				if (count >= 3) {
					setXonChips(buttonPos);
					return true;
				}
				if (buttons[pos].getIcon() == buttons[pos + 7].getIcon()) {
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
		placeChip(button, press);
		if (checkForWin(button) == true) {
			winningScreen(button);
		}
	}
	
	private int checkChipPosition(JButton button) {
		int button_spot = 0;
		for (int i = 0; i < buttons.length; i++) {
			if (button == buttons[i]) {
				button_spot = i;
			}
		}
		return button_spot;
	}
	
	private void winningScreen(JButton button) {
		
		if (button.getIcon() == rChip) {
			label.setText("RED TEAM WINS !");
			label.setFont(new Font("Arial", Font.PLAIN, 100));
			label.setForeground(new Color(0, 185, 255));
			label.setBackground(new Color(255, 0, 0));
		}
		else {
			label.setText("YELLOW TEAM WINS !");
			label.setFont(new Font("Arial", Font.PLAIN, 100));
			label.setForeground(new Color(0, 185, 255));
		}
	}
	private void setXonChips (int[] buttonPos) {
		
		for (int i = 0; i < 4; i ++) {
			buttons[buttonPos[i]].setText("X");
			buttons[buttonPos[i]].setFont(new Font("Arial", Font.PLAIN, 100));
			buttons[buttonPos[i]].setForeground(new Color(0, 185, 255));
			buttons[buttonPos[i]].setHorizontalTextPosition(JButton.CENTER);
		}
	}
	
	public void disableButtons() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].removeActionListener(this);
		}
	}
}
	
