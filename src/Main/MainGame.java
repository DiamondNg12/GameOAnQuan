package Main;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGame extends JPanel{
	
	Main main;
	BufferedImage bg;
	CardLayout cl;
	
	GamePlay game_play;
	int current_team = 0;
	int stone_in_sBox = 5;
	int stone_in_bBox = 10;
	
	public MainGame(Main main) {
		this.main = main;
		makeComponent();
		
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, 640, 480, null);
	}
	
	public void makeComponent() {
		
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.setBounds(0, 0, 71, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (main.main_panel.getLayout());
				cl.show(main.main_panel, "level");
			}
		});
		setLayout(null);
		add(btnNewButton);
		
		URL path = this.getClass().getResource("/images/game_background.jpg");
		try {
			bg = ImageIO.read(new File(path.toString().replace("file:/", "")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(false);
	}
	
}
