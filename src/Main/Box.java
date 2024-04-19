package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Box implements ActionListener{
	private JButton boxBtn, lArrow, rArrow;
	private JLabel numLabel=new JLabel("5");
	private int	num=5,
				pos;
	boolean[] isStone=new boolean[70];
	private float mul=4;
	final int	NUM_LABEL_SIZE=14;
	private static Font	fontArrow;
	private GamePlay game_play;
	private MainGame main_game;
	Box(MainGame main_game,GamePlay game_play, int pos){
		this.main_game=main_game;
		this.game_play = game_play;
		fontArrow=new Font("Times New Romans",Font.PLAIN,(int)(10*3));
		this.pos=pos;
		resetStone();
		create();
		createArrow();
		resize();
		main_game.add(boxBtn);
		
	}
	void create() { //pos = 0~11
		boxBtn=new JButton();
		boxBtn.setFocusable(false);
		setOutlook(boxBtn);
		boxBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
//				System.out.println(pos);
				game_play.removeAllArrow();
				int team=main_game.current_team;
				if(team==0&&pos>5&&pos<11&&num>0) showArrow();
				if(team==1&&pos<6&&num>0) showArrow();
			}
		});
		numLabel.setFont(fontArrow);
		main_game.add(numLabel);
		main_game.add(boxBtn);
	}
	void createArrow() {
		int row;
		if(pos<6) row=1;
			else row=0;
		rArrow=new JButton("\u25B6");
		lArrow=new JButton("\u25C0");
		
		ActionListener r=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int chieu=(row==0)?-1:1;
//				System.out.println(pos);
				removeArrow();
				game_play.move(pos,chieu,false);
			}
		};
		ActionListener l=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int chieu=(row==0)?1:-1;
				removeArrow();
				game_play.move(pos,chieu,false);
			}
		};
		setOutlook(rArrow);
		setOutlook(lArrow);
		rArrow.addActionListener(r);
		lArrow.addActionListener(l);
		main_game.add(rArrow);
		main_game.add(lArrow);
		rArrow.setVisible(false);
		lArrow.setVisible(false);
	}
	void resize() {
		int col,row;
		mul=4;
		fontArrow=new Font("Times New Romans",Font.PLAIN,(int)(10*mul));
		setOutlook(boxBtn);
		setOutlook(rArrow);
		setOutlook(lArrow);
		numLabel.setFont(fontArrow);
		if(pos<6) col=pos+1;
			else if(pos<11) col=11-pos;
				else col=0;
		if(pos<6) row=0;
			else row=1;
		if(col!=0&&col!=6) {
			boxBtn.setBounds(
					(int)(game_play.x + (col - 0.5)*game_play.x_diff + 10),
					(int)(game_play.y + (row)*game_play.y_diff),
					(int)(game_play.x_diff-10),
					(int)(game_play.y_diff-10));
		}
			
		if(col!=0&&col!=6) {
			numLabel.setBounds(
					(int)(game_play.x + (col)*game_play.x_diff),
					(int)(game_play.y + (row*2 - 0.5)*game_play.y_diff),
					(int)(game_play.x_diff),
					(int)(game_play.y_diff));
		}
		else {
			numLabel.setText("10");
			this.num = 10;
			if(col == 0) {
				numLabel.setBounds(
						(int)(game_play.x + (col - 0.5)*game_play.x_diff),
						(int)(game_play.y + game_play.y_diff*1.25),
						(int)(NUM_LABEL_SIZE*mul),
						(int)(NUM_LABEL_SIZE*mul));
			} else if(col == 6) {
				numLabel.setBounds(
						(int)(game_play.x + (col)*game_play.x_diff),
						(int)(game_play.y + game_play.y_diff*1.25),
						(int)(50),
						(int)(60));
			}
		}
			
		rArrow.setBounds(
			(int)(game_play.x + (col + 0.4) * game_play.x_diff),
			(int)(game_play.y + (game_play.y_diff * (row + 0.25))),
			(int)(60),
			(int)(80));
		lArrow.setBounds(
			(int)(game_play.x + (col - 0.75) * game_play.x_diff),
			(int)(game_play.y + (game_play.y_diff * (row + 0.25))),
			(int)(60),
			(int)(80));
	}
	void change(int n){
		num=n;
		numLabel.setText(Integer.toString(num));
	}

	void showArrow() {
		rArrow.setVisible(true);
		lArrow.setVisible(true);
		main_game.repaint();
	}
	void removeArrow() {
		rArrow.setVisible(false);
		lArrow.setVisible(false);
		main_game.repaint();
	}
	void resetStone() {
		for(int i=0;i<70;i++) isStone[i]=false;
		if(pos<5) 
			for(int i=0;i<5;i++) isStone[5*pos+i]=true;
		else if(pos==5)
			for(int i=0;i<10;i++) isStone[25+i]=true;
		else if(pos<11)
			for(int i=0;i<5;i++) isStone[5*(pos+1)+i]=true;
		else 
			for(int i=0;i<10;i++) isStone[60+i]=true;
			
	}
	void setOutlook(JButton b){
		b.setFocusPainted(false);
		b.setFont(fontArrow);
		
		b.setMargin(new Insets(0,0,0,0));
		b.setHorizontalAlignment(SwingConstants.LEFT);
		b.setVerticalAlignment(SwingConstants.TOP);
		
		//bug màu nút.
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
	}
	int getNum() {
		return num;
	}
	void setColor(Color c) {
		boxBtn.setForeground(c);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
