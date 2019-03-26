package enigma;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class About {
	
	protected static final HashMap<String, String> Object = null;
	protected static final HashMap<String, String> HashMap = null;
	private JDialog dialog;
	private JPanel panel;
	private JLabel imgLabel;
	private ImageIcon img;
	private JLabel enigma;
	private JLabel project;
	private JLabel developers;
	private JLabel doc;
	private String COPYRIGHT = "\u00a9";
	private JLabel copy;
	private JLabel create;
	public About() {
		
		dialog = new JDialog();
		dialog.setBounds(375, 160, 570, 375);
		dialog.setVisible(true);
		dialog.setTitle("About Enigma");
		
		//Setting up the panel
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		dialog.getContentPane().add(panel);
		
		set();
		
	}
	
	
	
	
	/**
	 	The set Method
	 **/
	public void set() {
		
		//icon
		img = new ImageIcon(getClass().getResource("a1.png"));
		imgLabel = new JLabel(img);
		imgLabel.setBounds(20,20,100,100);
		imgLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
		panel.add(imgLabel);
		//ENIGMA label
		enigma = new JLabel();
		enigma.setText("ENIGMA implementation (Encrptor/Decryptor)");
		enigma.setBounds(130,-24,400,100);
		enigma.setFont(new Font("Tahoma", Font.BOLD, 13));
		enigma.setForeground(Color.WHITE);
		panel.add(enigma);
		//about the porject label
		project = new JLabel();
		project.setText("<html>This Mini project is a two way Encryptor <br /> Developed by the students of 2nd year CSE MJCET Hyderabad </html>");
		project.setBounds(130,13,800,100);
		project.setForeground(Color.WHITE);
		project.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(project);
		//names of the Developers
		developers = new JLabel();
		developers.setText("<html> 1.ARISH RAHIL SHAH <br /> 2.MOHAMMED ISHAQ <br >3.MOHAMED ABDUL HASEEB </html>");
		developers.setBounds(160,65,800,100);
		developers.setForeground(Color.WHITE);
		developers.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(developers);
		//documentation and source
		doc = new JLabel();
		doc.setText("<html>Documentation and Source <br>is present at : http://www.github.com/Enigma</html>");
		doc.setForeground(Color.WHITE);
		doc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		doc.setBounds(130,160,400,100);
		panel.add(doc);
		//copyright
		copy = new JLabel(COPYRIGHT + " Copyright 2018 - H.A.I ENIGMA");
		copy.setBounds(130,200,400,100);
		copy.setForeground(Color.WHITE);
		copy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(copy);
		//created using
		create = new JLabel("Created in Java Using Eclipse IDE ");
		create.setBounds(130,120,400,100);
		create.setForeground(Color.WHITE);
		create.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(create);
	}
	
	
		
	
	//The main method
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new About();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}