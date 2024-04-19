package Main;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Stone extends JLabel{
//	private double x = 87.25, y = 185, x_diff = 77.75, y_diff = 95;
	private final int BASE_SPEED=200;
	private int pos_x, pos_y;
	MainGame main_game;
	Random r=new Random();
	int mul = 4;
	
	public Stone(MainGame main_game,double pos_x, double pos_y){
		this.main_game = main_game;
		this.pos_x = (int)pos_x;
		this.pos_y = (int)pos_y;
		
		setText("\u2B24");
		setForeground(Color.getHSBColor(
				r.nextFloat()*255,
				r.nextFloat()*255,
				r.nextFloat()*255));
		resize();		
		main_game.add(this);
	}
	
	public void resize() {
		double temp = r.nextFloat() * 100;
		int int_temp = (int)temp;
		int value_x = (int_temp / 10);
		int value_y = (int_temp % 10);
		int xX, xY;
		if (temp <= 25) {
			xX = 1;
			xY = 1;
		} else if(temp <=50) {
			xX = 1;
			xY = -1;
		} else if(temp <=75) {
			xX = -1;
			xY = 1;
		} else {
			xX = -1;
			xY = -1;
		}
		setBounds(this.pos_x + 3*(xX * value_x), this.pos_y + 3*(xY * value_y), 100,100);
		setFont(new Font("Times New Romans",Font.PLAIN,(int)(10*2)));
	}
	public void move(int pos) {
		int 
			newX=calcX(pos),
			newY=calcY(pos);
		int speed=main_game.speed;
		int time=(pos>=0)? BASE_SPEED/speed:BASE_SPEED/10/speed;
		for(int i=0;i<=time;i++) {
			try {
				setLocation(
					pos_x+(int)((newX-pos_x)*i/time),
					pos_y+(int)((newY-pos_y)*i/time));
				Thread.sleep(1);
				main_game.paintImmediately(main_game.getVisibleRect());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pos_x=newX;
		pos_y=newY;
	}
	int calcX(int pos) {
		double temp = r.nextFloat() * 100;
		int int_temp = (int)temp;
		int value_x = (int_temp / 10);
		int xX;
		if(temp <=50) {
			xX = 1;
		} else {
			xX = -1;
		}
		int col=calcCol(pos);
		if(pos!=5&&pos!=11&&pos>=0)	
			return (int)(main_game.game_play.x + main_game.game_play.x_diff * col + 3*(xX * value_x));
		else 
			return (int)(main_game.game_play.x + col*main_game.game_play.x_diff + 3*(xX * value_x));
	}
	int calcY(int pos) {
		double temp = r.nextFloat() * 100;
		int int_temp = (int)temp;
		int value_y = (int_temp % 10);
		int xY;
		if (temp <= 25) {
			xY = 1;
		} else if(temp <=50) {
			xY = -1;
		} else if(temp <=75) {
			xY = 1;
		} else {
			xY = -1;
		}
		int row=calcRow(pos);
		if(pos!=5&&pos!=11&&pos>=0)	
			return (int)(main_game.game_play.y + row*main_game.game_play.y_diff + 2*(xY * value_y));
		if(pos>=0) 
			return (int)(main_game.game_play.y + 0.5*main_game.game_play.y_diff + 3*(xY * value_y));
		if(pos==-2) 
			return (int)(350 + 1*(xY * value_y));
			return (int)(10 + 1*(xY * value_y));//pos==-1
	}
	int calcCol(int pos) {
		if(pos<0) 	return 3; 
		if(pos<6) 	return(pos+1);
		if(pos<11) 	return(11-pos);
			 		return 0;
	}
	int calcRow(int pos) {
		if(pos<6) 	return 0;
			else 	return 1;
	}
}
