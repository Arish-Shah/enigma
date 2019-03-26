package enigma;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;

public class LoadingScreen extends JWindow {
	private ImageIcon img;
	private JLabel imgLabel;
	LoadingScreen(){
		img = new ImageIcon(getClass().getResource("a.jpg"));
		imgLabel = new JLabel(img);
		add(imgLabel);
	}
	public void ProgressBar(){
		JProgressBar jb = new JProgressBar(0,100);
		
		//Font for JProgressBar
		Font font = new Font("Tahoma", Font.PLAIN, 11);
		
		int i = 0;
		jb.setBounds(0,274,450,15);
		jb.setStringPainted(true);
		jb.setForeground(new Color(46,46,46));
		jb.setBorder(null);
		jb.setBackground(new Color(230,230,230));
		jb.setFont(font);
		add(jb);
		
		while(i <= 100)
		{
			jb.setValue(i);
			i=i+5;
			try{
				Thread.sleep(400);
				if(i == 20)
					jb.setString("Copying First Run files...");
				if(i == 40)
					
					jb.setString("Initializing Java API...");
				if(i == 60)
					jb.setString("Loading Cipher Keys...");
				if(i == 100){
					jb.setString("Building Workspace...");
					Thread.sleep(1000);
					setVisible(false);
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
			catch(Exception e){
				e.printStackTrace();
			}
			
		}

	}
	
	public static void main(String[] args){
		LoadingScreen ls = new LoadingScreen();
		ls.setBounds(440,190,450,290);
		//ls.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, Color.DARK_GRAY));
		ls.setVisible(true);
		ls.ProgressBar();
	}
	
	}
