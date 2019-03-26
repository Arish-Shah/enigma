package enigma;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Enigma	{

	//Getting the mouse Coordinates
	int mX, mY;

	private JFrame frame;
	private JPanel panel;

	private JTextField key;
	private JButton resetButton;
	private JButton setButton;
	private JButton defaultButton;
	
	private JLabel[] label;

	private JButton[] button;

	private JButton compileButton;
	
	private HashMap<String, String> keyMap;
	
	public Enigma() {
		
		//Setting up the JFrame
		frame = new JFrame("Enigma");
		frame.setBounds(350, 70, 620, 565);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Setting up the JPanel
		panel = new JPanel();
		panel.setBounds(0, 0, 620, 565);
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		
		frame.getContentPane().add(panel);
		
		setTitleBar();
		setKey();
		setQwerty();
		setKeyboard();
		setBottomBar();
		
		//Random value for first time usage
		Random random = new Random();
		String temp = String.valueOf(random.nextInt(9999) + 1);
		key.setText(temp);
		JOptionPane.showMessageDialog(frame, "Default Encryption key has been set.", "Enigma", JOptionPane.INFORMATION_MESSAGE);
		setEnc();
	}
	
	
	
	/**
	 * The Glowing Method
	 */
	private void glow(String s) {
	
		String buttonText = keyMap.get(s).toString();
		
		for(int i = 0; i < label.length; i++) {
			String labelText = label[i].getText();
			
			if(buttonText.equals(labelText)) {
				
				label[i].setBackground(Color.ORANGE);
				label[i].setForeground(Color.BLACK);
				
			}
		}
		
	}	
	
	/**
	 * Setting up the Encryption Key
	 */
	public void setEnc() {
		
		String s = key.getText();
		
		try {
			int k = Integer.parseInt(s);
			
			keyMap = new HashMap<String, String>();
			String[] in = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
			String[] out = null;
			
			//Dividing the number by 6 and setting encryption values based on remainder
			switch(k%6) {
			
				case 0 : String[] zero_i = {"Z", "Y", "X", "W", "V", "U", "T", "S", "R", "Q", "P", "O", "N", "M", "L", "K", "J", "I", "H", "G", "F", "E", "D", "C", "B", "A"};
						 out = zero_i;
						 break;
				case 1 : String[] one_i = {"C", "D", "A", "B", "G", "H", "E", "F", "K", "L", "I", "J", "O", "P", "M", "N", "S", "T", "Q", "R", "W", "X", "U", "V", "Z", "Y"};
						 out = one_i;
						 break;
				case 2 : String[] two_i = {"Q", "W", "E", "R", "C", "T", "Y", "U", "I", "O", "P", "S", "Z", "X", "J", "K", "A", "D", "L", "F", "H", "V", "B", "N", "G", "M"};
						 out = two_i;
						 break;
				case 3 : String[] three_i = {"I", "S", "H", "Q", "P", "K", "O", "C", "A", "N", "F", "U", "X", "J", "G", "E", "D", "W", "B", "Z", "L", "Y", "R", "M", "V", "T"};
						 out = three_i;
						 break;
				case 4 : String[] four_i = {"I", "S", "H", "Q", "P", "K", "O", "C", "A", "N", "F", "U", "X", "J", "G", "E", "D", "W", "B", "Z", "L", "Y", "R", "M", "V", "T"};
						 out = four_i;
						 break;
				case 5 : String[] five_i = {"F", "U", "C", "K", "T", "A", "H", "G", "I", "S", "D", "M", "L", "O", "N", "R", "Z", "P", "J", "E", "B", "Y", "X", "W", "V", "Q"};
						 out = five_i;
						 break;
				default : String[] def_i = {"P", "E", "W", "D", "B", "I", "K", "Z", "F", "X", "G", "O", "S", "Y", "L", "A", "U", "T", "M", "R", "Q", "V", "C", "J", "N", "H"};
						  out = def_i;
						  break;
			}
			
			for(int i = 0; i < in.length; i++) {
				keyMap.put(in[i], out[i]);
			}
			
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Please enter an Integer value!", "Error", JOptionPane.WARNING_MESSAGE);
			key.setText("");
			key.setEnabled(true);
			key.requestFocus();
			resetButton.setVisible(false);
			setButton.setVisible(true);
		}
		
	}
	
	
	
	
	/**
	 * Setting the BottomBar
	 */
	public void setBottomBar() {
		
		JPanel bottom = new JPanel();
		bottom.setBounds(1, 504, 618, 60);
		bottom.setBackground(Color.DARK_GRAY);
		bottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		bottom.setLayout(null);
		
		//The Compile Button
		compileButton = new JButton("Compile Keys");
		compileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Compile(keyMap);
			}
		});
		compileButton.setBackground(Color.DARK_GRAY);
		compileButton.setForeground(Color.LIGHT_GRAY);
		compileButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		compileButton.setFocusPainted(false);
		compileButton.setBounds(490, 16, 120, 28);
		bottom.add(compileButton);
		
		panel.add(bottom);
		
	}
	
	
	
	/**
	 * Setting up the KeyBoard
	 */
	public void setKeyboard() {
		
		JPanel keyboardPanel = new JPanel();
		keyboardPanel.setBounds(1, 285, 618, 200);
		keyboardPanel.setBackground(Color.DARK_GRAY);
		keyboardPanel.setLayout(null);
		
		//The three temporary Panels
		JPanel tempPanel_1 = new JPanel();
		JPanel tempPanel_2 = new JPanel();
		JPanel tempPanel_3 = new JPanel();
		tempPanel_1.setBounds(20, 25, 580, 60);
		tempPanel_1.setBorder(null);
		tempPanel_1.setBackground(Color.DARK_GRAY);
		tempPanel_2.setBounds(20, 80, 580, 60);
		tempPanel_2.setBorder(null);
		tempPanel_2.setBackground(Color.DARK_GRAY);
		tempPanel_3.setBounds(20, 135, 580, 60);
		tempPanel_3.setBorder(null);
		tempPanel_3.setBackground(Color.DARK_GRAY);
		
		//Setting up the buttons
		String buttonText[] = {"Q", "W", "E", "R", "T", "Z", "U", "I", "O", "A", "S", "D", "F", "G", "H", "J", "K", "P", "Y", "X", "C", "V", "B", "N", "M", "L"};
		button = new JButton[26];
		
		for(int i = 0; i < button.length; i++) {
			
			button[i] = new JButton(buttonText[i]);
			setButton(button[i]);
			
			//Adding after checking the panel to which it belongs
			if(i >=0 && i<=8)
				tempPanel_1.add(button[i]);
			else if(i > 8 && i <= 16)
				tempPanel_2.add(button[i]);
			else
				tempPanel_3.add(button[i]);
			
			
			//Adding mouseClick Listener
			button[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Object o = e.getSource();
					JButton b = null;
					
					String buttonText = "";
					
					if(o instanceof JButton)
						b = (JButton)o;
					
					if(b != null)
						buttonText = b.getText();
					
					glow(buttonText);
				}
				@Override
				public void mouseReleased(MouseEvent e) {				
					
					for(int i = 0; i < label.length; i++) {
						label[i].setBackground(new Color(50, 50, 50));
						label[i].setForeground(Color.WHITE);
					}
						
				}
			});
		}
		
		
		keyboardPanel.add(tempPanel_1);
		keyboardPanel.add(tempPanel_2);
		keyboardPanel.add(tempPanel_3);
		panel.add(keyboardPanel);
		
	}
	
	
	
	/**
	 * Setting up the QwertyPanel
	 */
	public void setQwerty() {
		
		JPanel qwertyPanel = new JPanel();
		
		CompoundBorder outer = new CompoundBorder(new EmptyBorder(0, 10, 0, 10), BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		qwertyPanel.setBorder(new CompoundBorder(outer, new EmptyBorder(10, 0, 0, 0)));
		
		qwertyPanel.setBounds(1, 90, 618, 200);
		qwertyPanel.setBackground(Color.DARK_GRAY);
		qwertyPanel.setLayout(null);
		
		
		//Temporary Panels for labels in lines
		JPanel tempPanel_1 = new JPanel();
		JPanel tempPanel_2 = new JPanel();
		JPanel tempPanel_3 = new JPanel();
		tempPanel_1.setBounds(20, 15, 580, 60);
		tempPanel_1.setBorder(null);
		tempPanel_1.setBackground(Color.DARK_GRAY);
		tempPanel_2.setBounds(20, 70, 580, 60);
		tempPanel_2.setBorder(null);
		tempPanel_2.setBackground(Color.DARK_GRAY);
		tempPanel_3.setBounds(20, 125, 580, 60);
		tempPanel_3.setBorder(null);
		tempPanel_3.setBackground(Color.DARK_GRAY);
		
		
		//Setting up the QWERTY JLabels
		label = new JLabel[26];
		String labelText[] = {"Q", "W", "E", "R", "T", "Z", "U", "I", "O", "A", "S", "D", "F", "G", "H", "J", "K", "P", "Y", "X", "C", "V", "B", "N", "M", "L"};
		
		for(int i = 0; i < label.length; i++) {
			
			label[i] = new JLabel(labelText[i]);
			setLabel(label[i]);
			
			//Adding after checking the panel to which it belongs
			if(i >=0 && i<=8)
				tempPanel_1.add(label[i]);
			else if(i > 8 && i <= 16)
				tempPanel_2.add(label[i]);
			else
				tempPanel_3.add(label[i]);
		}
		
		qwertyPanel.add(tempPanel_1);
		qwertyPanel.add(tempPanel_2);
		qwertyPanel.add(tempPanel_3);
				
		
		panel.add(qwertyPanel);
		
	}
	
	
	/**
	 * Setting up the Key Setter Panel
	 */
	public void setKey() {		
		
		JPanel keyPanel = new JPanel();
		keyPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
		keyPanel.setBounds(0, 40, 620, 50);
		keyPanel.setBackground(Color.DARK_GRAY);
		keyPanel.setLayout(null);		
		
		//The Key Label
		JLabel keyLabel = new JLabel("Key:");
		keyLabel.setForeground(Color.LIGHT_GRAY);
		keyLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		keyLabel.setBounds(10, 14, 70, 20);
		keyPanel.add(keyLabel);
		
		
		//The Key entering box
		key = new JTextField();
		key.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				key.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.ORANGE), new EmptyBorder(0, 5, 5, 0)));
			}
			@Override
			public void focusLost(FocusEvent e) {
				key.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY), new EmptyBorder(0, 5, 5, 0)));
				setEnc();
				
				//Fixing Bugs
				for(int i = 0; i < label.length; i++) {
					label[i].setBackground(new Color(50, 50, 50));
					label[i].setForeground(Color.WHITE);
				}
			}
		});
		key.setBackground(Color.DARK_GRAY);
		key.setCaretColor(Color.LIGHT_GRAY);
		key.setForeground(Color.WHITE);
		key.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY), new EmptyBorder(0, 5, 5, 0)));
		key.setFont(new Font("Tahoma", Font.PLAIN, 14));
		key.setBounds(40, 16, 60, 20);
		key.setEnabled(false);
		keyPanel.add(key);
		
		
		//The Reset Button
		resetButton = new JButton("Reset");
		resetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 key.requestFocus();
				 key.setText("");
				 key.setEnabled(true);
				 resetButton.setVisible(false);
				 setButton.setVisible(true);
			}
		});		
		resetButton.setFocusPainted(false);
		resetButton.setBackground(Color.DARK_GRAY);
		resetButton.setForeground(Color.LIGHT_GRAY);
		resetButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		resetButton.setBounds(110, 14, 70, 23);
		resetButton.setVerticalAlignment(JButton.CENTER);
		keyPanel.add(resetButton);
		
		
		//The Set Button
		setButton = new JButton("Set");
		setButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setButton.setVisible(false);
				resetButton.setVisible(true);
				key.setEnabled(false);
				setEnc();
			}
		});
		setButton.setFocusPainted(false);
		setButton.setBackground(Color.DARK_GRAY);
		setButton.setForeground(Color.LIGHT_GRAY);
		setButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		setButton.setBounds(110, 14, 70, 23);
		setButton.setVerticalAlignment(JButton.CENTER);
		setButton.setVisible(false);
		keyPanel.add(setButton);
		
		//The Default Button
		defaultButton = new JButton("Default");
		defaultButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Random random = new Random();
				int n = random.nextInt(99999) + 1;
				String s = String.valueOf(n);
				key.setText(s);
				setEnc();
			}
		});
		defaultButton.setFocusPainted(false);
		defaultButton.setBackground(Color.DARK_GRAY);
		defaultButton.setForeground(Color.LIGHT_GRAY);
		defaultButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		defaultButton.setBounds(190, 14, 70, 23);
		defaultButton.setVerticalAlignment(JButton.CENTER);
		keyPanel.add(defaultButton);
		
		
		
		panel.add(keyPanel);
		
		
	}
	
	
	
	/**
	 * 
	 * Setting up the titleBar
	 */
	private void setTitleBar() {
		
		JPanel topBar = new JPanel();
		topBar.setBounds(0, 0, 620, 40);
		topBar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		topBar.setLayout(null);
		topBar.setBackground(Color.DARK_GRAY);
		
		//topBar moving
		topBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {				
				mX = e.getX();
				mY = e.getY();		
				}
		});
		
		topBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {				
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();				
				frame.setBounds(x - mX, y - mY, 620, 565);
			}
		});
		
		//The Enigma Text
		JLabel iconLabel = new JLabel("Enigma");
		iconLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		iconLabel.setForeground(Color.ORANGE);
		iconLabel.setBounds(10, 8, 100, 22);
		topBar.add(iconLabel);
		
		//About this Project Text
		JLabel about = new JLabel("About this Project");
		about.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new About();
			}
			public void mousePressed(MouseEvent e) {
				about.setForeground(Color.white);
			}
			public void mouseReleased(MouseEvent e) {
				about.setForeground(Color.LIGHT_GRAY);
			}

		});
		about.setFont(new Font("Tahoma", Font.PLAIN, 11));
		about.setForeground(Color.LIGHT_GRAY);
		about.setBounds(440, 10, 90, 20);
		about.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		topBar.add(about);
		
		
		//The Minimize Button
				JButton minimize = new JButton("—");
				minimize.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						frame.setExtendedState(JFrame.ICONIFIED);
					}
				});
				minimize.setBounds(540, 0, 40, 40);
				minimize.setFont(new Font("Tahoma", Font.BOLD, 15));
				minimize.setBackground(Color.DARK_GRAY);
				minimize.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
				minimize.setForeground(Color.LIGHT_GRAY);
				minimize.setFocusPainted(false);
				minimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				topBar.add(minimize);
				
		
		
		//The Close Button
		JButton close = new JButton("×");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				frame.dispose();
			}
		});
		close.setBounds(580, 0, 40, 40);
		close.setFont(new Font("Tahoma", Font.BOLD, 15));
		close.setBackground(Color.DARK_GRAY);
		close.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black));
		close.setForeground(Color.LIGHT_GRAY);
		close.setFocusPainted(false);
		close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		topBar.add(close);
		
		
		panel.add(topBar);
	
	}
	
	
	
	
	//Design of JButton
	public void setButton(JButton button) {
		
		Font myFont = new Font("Tahoma", Font.PLAIN, 20);
		
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBackground(new Color(20, 20, 20));
		button.setPreferredSize(new Dimension(52, 52));
		button.setFont(myFont);
		button.setHorizontalAlignment(JTextField.CENTER);
		
		CompoundBorder outer = new CompoundBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY), BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		button.setBorder(outer);
		
	}
	
	
	//Design of the JLabel
	public void setLabel(JLabel label) {
		
		Font font = new Font("Tahoma", Font.PLAIN, 20);
		
		label.setPreferredSize(new Dimension(50, 50));
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
		CompoundBorder outer = new CompoundBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY), BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 10, 10)));
		label.setBorder(outer);
		label.setBackground(new Color(50, 50, 50));
		
	}
	
	
	
	//The main method
	public static void main(String[] args) { 		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Enigma();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}