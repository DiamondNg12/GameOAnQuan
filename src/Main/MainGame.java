package Main;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainGame extends JPanel{
	
	Main main;
	BufferedImage bg;
	CardLayout cl;
	
	GamePlay game_play;
	
	
	int current_team = 0;
//	int stone_in_sBox = 5;
//	int stone_in_bBox = 10;
	public int speed=1;
	int level;
	
	public MainGame(Main main, int level) {
		this.main = main;
		this.level = level;
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.setBounds(0, 0, 83, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl = (CardLayout) (main.main_panel.getLayout());
				if(level != 0) {
			        cl.show(main.main_panel, "level");
			        
				}else {
			        cl.show(main.main_panel, "game_mode");
				}
				int componentCount = main.main_panel.getComponentCount();

				// Iterate over the components to find the one with the specified name
				for (int i = 0; i < componentCount; i++) {
				    Component component = main.main_panel.getComponent(i);
				    if (component.getName() != null && component.getName().equals("main_game")) {
				        // Remove the component from the CardLayout
				        cl.removeLayoutComponent(component);
				        // Revalidate and repaint the container to reflect the changes
				        main.main_panel.revalidate();
				        main.main_panel.repaint();
				        break; // Exit loop since we found and removed the component
				    }
				}
			}
		});
		setLayout(null);
		add(btnNewButton);
		
		
		game_play = new GamePlay(this);
		game_play.init();
		
		URL path = this.getClass().getResource("/images/game_background.jpg");
		try {
			bg = ImageIO.read(new File(path.toString().replace("file:/", "")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void nextTurn() {
		if(level != 0 && current_team == 0) {
			current_team=1-current_team;
			this.game_play.scBox[current_team].setTurn(true);
			this.game_play.scBox[1-current_team].setTurn(false);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int result=game_play.check(current_team);
			if(result==-1) {
				int winTeam;
				if(game_play.scBox[0].getNum()>game_play.scBox[1].getNum()) winTeam=0;
				else if(game_play.scBox[0].getNum()<game_play.scBox[1].getNum()) winTeam=1;
				else winTeam=-1;
				game_play.victory(winTeam);
			}
			if(result==0) game_play.victory(1-current_team);
			if(result==1) game_play.spread(current_team);
			Boolean next = false;
			while (!next) {
				Random r = new Random();
				int box_no = Math.abs(r.nextInt() % 5);
				if(this.game_play.box[box_no].getNum() == 0) {
					continue;
				}
				int right = r.nextInt() % 2 == 0 ? 1 : -1;
				game_play.move(box_no, right, false);
				
				next = true;
			}
		}else {
			current_team=1-current_team;
		}
		
		game_play.nextTurn();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, 640, 480, null);
	}
	
	
}
