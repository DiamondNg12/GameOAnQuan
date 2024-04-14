package Main;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;


public class Main{
	
	JFrame window;
	JPanel main_panel;
	BeginPanel begin_panel;
	GameMode game_mode;
	Level level;
	Rule rule;
	Music music;
	
	public Main() {
		this.window = new JFrame("Ô ăn quan");
		begin_panel = new BeginPanel(this);
		game_mode = new GameMode(this);
		level = new Level(this);
		rule = new Rule(this);
		music = new Music(this);
		
		window.setSize(640, 480);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_panel = new JPanel(new CardLayout());
		window.getContentPane().add(main_panel, BorderLayout.CENTER);
		main_panel.add(begin_panel, "begin_panel");
		main_panel.add(game_mode, "game_mode");
		main_panel.add(level, "level");
		main_panel.add(rule, "rule");
		main_panel.add(music, "music");
		window.setVisible(true);
	}
	
	public void endGame() {
		this.window.dispose();;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
