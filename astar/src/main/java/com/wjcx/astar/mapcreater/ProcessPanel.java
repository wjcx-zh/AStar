package com.wjcx.astar.mapcreater;



import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.wjcx.astar.control.AStarController;
import com.wjcx.astar.model.MapInit;
import com.wjcx.astar.structure.SBTree;

public class ProcessPanel extends JPanel{
	
	
	private static final long serialVersionUID = 4728158473417914106L;
	private JButton move;
	private JButton clear;
	
	public ProcessPanel(){
		init();
		this.setLayout(null);
		
		move.setBounds(5, 5, 100, 30);
		clear.setBounds(110, 5, 100, 30);
		move.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent mae) {
				// TODO Auto-generated method stub
				int[][] road=AStarController.getInstance().getMap().getMaps();
				
				Graphics g=AStarController.getInstance().getCVPG();
				for(int rr=0;rr<road.length;rr++){
					for(int rc=0;rc<road[0].length;rc++){
						System.out.print(road[rr][rc]+" ");
						if(road[rr][rc]==5){
							g.drawImage(MapConfig.icon5.getImage(),rc*30 ,rr*30, MapConfig.ELEWIDTH, MapConfig.ELEHEIGHT, null);
						}
					}
					System.out.println();
				}
				AStarController.getInstance().analyseData();
			}
			
		});
		
		clear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MapInit mi=AStarController.getInstance().getMap();
				int[][] m=mi.getMaps();
				Graphics g=AStarController.getInstance().getCVPG();
				for(int i=0;i<m.length;i++){
					for(int j=0;j<m[0].length;j++){
						if(m[i][j]==5/*||m[i][j]==2||m[i][j]==4*/){
							m[i][j]=1;
							
							g.drawImage(MapConfig.icon1.getImage(),j*30 ,i*30, MapConfig.ELEWIDTH, MapConfig.ELEHEIGHT, null);
						}
					}
				}
				//mi.setGoal(null);
				//mi.setStart(null);
				
				AStarController.getInstance().clearData();
				AStarController.getInstance().analyseData();
			}
			
		});
		
		this.add(move);
		this.add(clear);
	}
	private void init() {
		// TODO Auto-generated method stub
		
		move=new JButton("SEARCH");
		clear=new JButton("CLEAR");
		/*
		JButton[] btns={start,goal,move,clear};
		for(JButton btn:btns){
			btn.setFont(new Font("arial",Font.PLAIN,12));
		}*/
	}
}
