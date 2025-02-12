import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Connect4GUI  {
	
	private JFrame frame = new JFrame();
	private JFrame frameWin = new JFrame();
	private JPanel buttonPanel = new JPanel();
	private JButton[] buttons = new JButton[42];
	private boolean press = true;
	private ImageIcon chipFrame = new ImageIcon("src/Icons/buttoneFrame.png");
	private ImageIcon rChip = new ImageIcon("src/Icons/RedbuttonFrame.png");
	private ImageIcon yChip = new ImageIcon("src/Icons/YbuttonFrame.png");
	private JLabel label = new JLabel();
	
	Connect4GUI() {
		
		ImageIcon icon = new ImageIcon("src/Icons/Connect4.1.png");
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
		
	}


	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	public JFrame getFrameWin() {
		return frameWin;
	}


	public void setFrameWin(JFrame frameWin) {
		this.frameWin = frameWin;
	}


	public JPanel getButtonPanel() {
		return buttonPanel;
	}


	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}


	public JButton[] getButtons() {
		return buttons;
	}


	public void setButtons(JButton[] buttons) {
		this.buttons = buttons;
	}


	public boolean isPress() {
		return press;
	}


	public void setPress(boolean press) {
		this.press = press;
	}


	public ImageIcon getChipFrame() {
		return chipFrame;
	}


	public void setChipFrame(ImageIcon chipFrame) {
		this.chipFrame = chipFrame;
	}


	public ImageIcon getrChip() {
		return rChip;
	}


	public void setrChip(ImageIcon rChip) {
		this.rChip = rChip;
	}


	public ImageIcon getyChip() {
		return yChip;
	}


	public void setyChip(ImageIcon yChip) {
		this.yChip = yChip;
	}


	public JLabel getLabel() {
		return label;
	}


	public void setLabel(JLabel label) {
		this.label = label;
	}

}
