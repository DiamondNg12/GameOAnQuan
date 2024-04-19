package Main;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ScoreBox{
	JLabel scBoxLabel,turnLabel;
	ImageIcon flag;
	int num,team;
	float mul=4;
	boolean[] isStone=new boolean[70];
	MainGame mainGame;
	Font fontArrow=new Font("Times New Romans",Font.PLAIN,(int)(mul*10));
	ScoreBox(MainGame m,int team){
		this.team=team;
		mainGame=m;
		scBoxLabel=new JLabel("0");
		scBoxLabel.setFocusable(false);
		scBoxLabel.setFont(fontArrow);
		mainGame.add(scBoxLabel);
		flag=new ImageIcon("D:\\Java\\GameOAnQuan\\src\\images\\flag.jpg");
		turnLabel=new JLabel(flag);
		turnLabel.setFocusable(false);
		turnLabel.setFont(fontArrow);
		turnLabel.setFocusable(false);
		resize();
		resetStone();
	}
	void resize() {
		mul=4;
		fontArrow=new Font("Times New Romans",Font.PLAIN,(int)(mul*10));
		if(team==0) {
			scBoxLabel.setBounds(
				(int)(300),
				(int)(400),
				(int)(50),
				(int)(50));
			turnLabel.setBounds(
				(int)(400),
				(int)(350),
				flag.getIconWidth(),
				flag.getIconWidth());
		}
		else {
			scBoxLabel.setBounds(
				(int)(350),
				(int)(0),
				(int)(50),
				(int)(50));
			turnLabel.setBounds(
				(int)(400),
				(int)(0),
				flag.getIconWidth(),
				flag.getIconHeight());
		}
		scBoxLabel.setFont(fontArrow);
	}
	int getNum(){
		return num;
	}
	void resetStone() {
		for(int i=0;i<70;i++) isStone[i]=false;
	}
	void change(int n){
		num=n;
		scBoxLabel.setText(Integer.toString(n));
	}
	void setTurn(boolean turn) {
		if (turn==true) mainGame.add(turnLabel);
		else mainGame.remove(turnLabel);
	}
}
