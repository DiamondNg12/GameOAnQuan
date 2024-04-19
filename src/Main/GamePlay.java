package Main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JOptionPane;

public class GamePlay {
	double x = 87.25, y = 135, x_diff = 77, y_diff = 95;
	
	MainGame main_game;
	ScoreBox[] scBox=new ScoreBox[2];
	Stone[] stones=new Stone[70];
	Box[] box=new Box[12];
	
	public GamePlay(MainGame main_game) {
		this.main_game = main_game;
	}
	
	public void init() {
		
		//init elements for the first time start.
		for(int i=0;i<12;i++) box[i]=new Box(main_game,this,i);
		scBox[0]=new ScoreBox(main_game,0);
		scBox[1]=new ScoreBox(main_game,1);
		int count=0;
		for(int i=0;i<12;i++) {
			if(i!=5&&i!=11) {
				int row = 0;
				if (i > 5 && i < 11) {
					row = 1;
				}
				for(int j=0;j<5;j++) {
					if(row == 0) {
						stones[count]=new Stone(this.main_game, x + ((i + 1) * this.x_diff), y + (this.y_diff * row));
						
					} else if (row == 1) {
						stones[count]=new Stone(this.main_game, x + ((11 - i) * this.x_diff), y + (this.y_diff * row));
					}
					count++;
				}
			}
			else {
				int col = 1;
				if (i == 11) {
					col = 0;
				}
				for(int j=0;j<10;j++) {
					stones[count]=new Stone(this.main_game, x + col * 6 * this.x_diff, y + (this.y_diff * 0.5));
					count++;
				}
			}
		}
		scBox[main_game.current_team].setTurn(true);
	}
	public void removeAllArrow() {
		for(int i=0;i<12;i++) box[i].removeArrow();
	}
	public void move(int pos,int direction, boolean isEaten){
		//main function to move stones.
		if(pos==5||pos==11) main_game.nextTurn();
		else if (box[pos].getNum()>0){
			if(!isEaten){
				int num=box[pos].getNum();
				box[pos].setColor(Color.BLUE);
				for(int i=1;i<=num;i++) {
					int temp=calNewPos(pos,i*direction);
					box[pos].change(box[pos].getNum()-1);
					box[temp].change(box[temp].getNum()+1);
					int j=0;
					while(box[pos].isStone[j]==false)
						j++;
					box[pos].isStone[j]=false;
					box[temp].isStone[j]=true;
					stones[j].move(temp);
				}
				int vtSau=calNewPos(pos,(num+1)*direction);
				move(vtSau,direction,false);
			}else main_game.nextTurn();
		}else{
			if(box[calNewPos(pos,direction)].getNum()>0){
				kill(main_game.current_team,calNewPos(pos,direction));
				move(calNewPos(pos,direction*2),direction,true);
			}else main_game.nextTurn();
		}
	}
	int calNewPos(int src,int step){
//		System.out.println(src + ":" + step + "");
		return (src+step+1200)%12;
	}
	void kill(int team,int pos){
		int 
			temp=scBox[team].getNum(),
			boxTemp=box[pos].getNum();
		for(int i=0;i<boxTemp;i++) {
			int j=0;
			while(box[pos].isStone[j]==false)
				j++;
			box[pos].isStone[j]=false;
			scBox[team].isStone[j]=true;
			box[pos].change(box[pos].getNum()-1);
			stones[j].move(team-2);
			scBox[team].change(1+scBox[team].getNum());
		}
	}
	void nextTurn() {
		int result=check(main_game.current_team);
		if(result==-1) {
			int winTeam;
			if(scBox[0].getNum()>scBox[1].getNum()) winTeam=0;
			else if(scBox[0].getNum()<scBox[1].getNum()) winTeam=1;
			else winTeam=-1;
			victory(winTeam);
		}
		if(result==0) victory(1-main_game.current_team);
		if(result==1) spread(main_game.current_team);
		if(main_game.level != 0) {
			main_game.current_team = 1 - main_game.current_team;
		}
		scBox[main_game.current_team].setTurn(true);
		scBox[1-main_game.current_team].setTurn(false);
	}
	void victory(int team) {
		if(main_game.level == 0) {
			if(team != -1) JOptionPane.showConfirmDialog(main_game,"Người chơi "+(team+1)+" thắng","Game Over",JOptionPane.DEFAULT_OPTION);
			else 
				JOptionPane.showConfirmDialog(main_game,"Hoà","Game over",1);
		} else {
			if(team==1) JOptionPane.showConfirmDialog(main_game,"Máy thắng","Game Over",JOptionPane.DEFAULT_OPTION);
			else if(team == 0) {
				JOptionPane.showConfirmDialog(main_game,"Người chơi thắng","Game Over",JOptionPane.DEFAULT_OPTION);
			}else 
				JOptionPane.showConfirmDialog(main_game,"Hoà","Game over",1);
		}
		CardLayout cl = (CardLayout) (main_game.main.main_panel.getLayout());
		if(main_game.level != 0) {
	        cl.show(main_game.main.main_panel, "level");
	        
		}else {
	        cl.show(main_game.main.main_panel, "game_mode");
		}
		int componentCount = main_game.main.main_panel.getComponentCount();

		// Iterate over the components to find the one with the specified name
		for (int i = 0; i < componentCount; i++) {
		    Component component = main_game.main.main_panel.getComponent(i);
		    if (component.getName() != null && component.getName().equals("main_game")) {
		        // Remove the component from the CardLayout
		        cl.removeLayoutComponent(component);
		        // Revalidate and repaint the container to reflect the changes
		        main_game.main.main_panel.revalidate();
		        main_game.main.main_panel.repaint();
		        break; // Exit loop since we found and removed the component
		    }
		}
		
//		main_game.newGame();
	}
	void spread(int team) {
		//spread stones when there're no stone on table.
		if(team==0)
			for(int i=6;i<11;i++) boxSpread(team,i);
		else 
			for(int i=0;i<5;i++) boxSpread(team,i);
	}
	void boxSpread(int team, int pos) {
		//sub function spread to a box.
		int j=0;
		scBox[team].change(scBox[team].getNum()-1);
		while(!scBox[team].isStone[j])
			j++;
		
		stones[j].move(pos);
		scBox[team].isStone[j]=false;
		box[pos].change(1);
		box[pos].isStone[j]=true;
	}
	public int check(int team) {
		//check if there is a win situation or out of stones in current team.
		if(box[5].getNum()==0&&box[11].getNum()==0) return -1;
		if(scBox[team].getNum()<5&&total(team)==0) 	return 0;
		if(scBox[team].getNum()>5&&total(team)==0) 	return 1;
		return 2;
	}
	int total(int team) {
		//get the total of stones on normal box.
		if(team==0)	
			return box[6].getNum()+box[7].getNum()+box[8].getNum()+box[9].getNum()+box[10].getNum();
		else 
			return box[0].getNum()+box[1].getNum()+box[2].getNum()+box[3].getNum()+box[4].getNum();
	}
}
