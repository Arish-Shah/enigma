package enigma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Compile {
	
	protected static final HashMap<String, String> Object = null;
	protected static final HashMap<String, String> HashMap = null;
	private JDialog dialog;
	private JPanel panel;
	private JTextArea InputArea, OutputArea;
	
	private HashMap<String, String> keyMap;
	
	private JButton encryptButton, decryptButton, cancelButton;
	
	public Compile(HashMap<String, String> keyMap) {
		
		try {		
			this.keyMap = keyMap;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(dialog, "Internal Error Occured!", "Error", JOptionPane.WARNING_MESSAGE);
		}
		
		dialog = new JDialog();
		dialog.setBounds(375, 160, 570, 375);
		dialog.setVisible(true);
		dialog.setTitle("Enigma Compiler");
		
		//Setting up the panel
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		dialog.getContentPane().add(panel);
		
		set();	
		
	}
	
	
	/**
	 * creating text areas
	 * @param args
	 */
	public void set() {
		
		//Input TextArea
		InputArea = new JTextArea();
		InputArea.setBounds(10, 34, 387, 130);
		setTA(InputArea);
		
		JLabel iLabel = new JLabel("Input Message:");
		iLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		iLabel.setForeground(Color.LIGHT_GRAY);
		iLabel.setBounds(10, 9, 300, 20);
		panel.add(iLabel);
		panel.add(InputArea);
		InputArea.requestDefaultFocus();
		
		
		//Output textArea
		OutputArea = new JTextArea();		
		OutputArea.setBounds(10, 197, 387, 130);
		setTA(OutputArea);
		
		JLabel oLabel = new JLabel("Output Message:");
		oLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oLabel.setForeground(Color.LIGHT_GRAY);
		oLabel.setBounds(10, 170, 300, 20);
		panel.add(oLabel);
		panel.add(OutputArea);
		
		//Adding the buttons
		encryptButton = new JButton("Encrypt");		
		encryptButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {		
					convert();
				}
				catch(Exception exp) {
					JOptionPane.showMessageDialog(dialog, "Internal Error Occured!", "Error", JOptionPane.WARNING_MESSAGE);
					dialog.dispose();
				}			
			}
		});
		setButton(encryptButton);		
		encryptButton.setBounds(418, 16, 114, 25);		
		panel.add(encryptButton);
		
		decryptButton = new JButton("Decrypt");
		decryptButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {		
					convert();
				}
				catch(Exception exp) {
					JOptionPane.showMessageDialog(dialog, "Internal Error Occured!", "Error", JOptionPane.WARNING_MESSAGE);
					dialog.dispose();
				}				
			}
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
		
		setButton(decryptButton);
		decryptButton.setBounds(418, 52, 114, 25);
		
		setButton(cancelButton);
		cancelButton.setBounds(418, 116, 114, 25);
		panel.add(decryptButton);
		panel.add(cancelButton);
		
		
		JLabel rollLabel = new JLabel("Build 889790");
		rollLabel.setForeground(Color.LIGHT_GRAY);
		rollLabel.setBounds(489, 300, 55, 25);
		rollLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(rollLabel);
	}
	
	//Design of JButton
	public void setButton(JButton button) {
		
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setForeground(Color.LIGHT_GRAY);
		button.setFocusPainted(false);
		button.setBackground(Color.DARK_GRAY);
		button.setPreferredSize(new Dimension(52, 52));
		button.setHorizontalAlignment(JTextField.CENTER);

		button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		
	}
	
	
	
	
	public void setTA(JTextArea textArea) {
		
		textArea.setBackground(new Color(50, 50, 50));
		textArea.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY), new EmptyBorder(5, 5, 0, 0)));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setCaretColor(Color.LIGHT_GRAY);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setForeground(Color.WHITE);
		
	}
	
	
	/**
	 * Encrypt and Decrypt method
	 * @param args
	 */
	public void convert() {
		
		String[] inp = InputArea.getText().toUpperCase().split("");
		String out = "";
		
		for(int i = 0; i < inp.length; i++) {
			
			if(keyMap.get(inp[i]) != null) {
				out += keyMap.get(inp[i]);
			}
			else {
				out += inp[i];
			}
			
		}
		
		OutputArea.setText(out);
		InputArea.requestFocus();
		
	}
	
	
	
	
	//The main method
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new Compile(HashMap);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}