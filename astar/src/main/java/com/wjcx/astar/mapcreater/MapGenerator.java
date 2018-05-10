package com.wjcx.astar.mapcreater;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.wjcx.astar.control.AStarController;

public class MapGenerator implements MapConfig{
	
	private static JPanel canvasPanel;
	private static JScrollPane jsp=new JScrollPane();
	private static JFrame frame;
	/*private static Graphics g;*/
	private static AStarController controller=AStarController.getInstance();
	public MapGenerator(){
		init();
		create(null);
	}
	public MapGenerator(int[][] map){
		init();
		create(map);
	}
	
	private void init(){
		frame=new JFrame();
		//canvasPanel=(map==null?new NonePane():new CanvasPanel(map));
		//JPanel infoPanel=new JPanel();
		
		JPanel panel=new JPanel();
		JPanel basePanel=new BaseInfoPanel();
		JPanel nodePanel=new NodeInfoPanel();
		JPanel mapCtrlPanel=new MapControlPane();
		JPanel pathPanel=new PathSearchPanel();
		JPanel processPanel=new ProcessPanel();
		jsp.setBounds(0, 0,900,600);
		jsp.setPreferredSize(new Dimension(900,600));
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setLayout(null);
		panel.setBounds(920, 0, 250, 640);
		basePanel.setBounds(5, 10, 250, 120);
		nodePanel.setBounds(5, 140, 250, 100);
		mapCtrlPanel.setBounds(5, 260, 250, 100);
		pathPanel.setBounds(5, 380, 250, 150);
		processPanel.setBounds(5, 550, 250, 80);
		panel.add(basePanel);
		panel.add(nodePanel);
		panel.add(mapCtrlPanel);
		panel.add(pathPanel);
		panel.add(processPanel);
		frame.setSize(1200,650);
		frame.setLayout(null);
		frame.setLocation(50, 10);
		frame.setVisible(true);
		frame.getContentPane().add(jsp);
		frame.getContentPane().add(panel);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(1);
			}
		});
	}
	
	public static CanvasPanel create(int[][] map){
		canvasPanel=(map==null?new NonePane():new CanvasPanel(map));
		canvasPanel.setLocation(5, 5);
		canvasPanel.setSize(MAPHEIGHT, MAPWIDTH);
		canvasPanel.setPreferredSize(new Dimension(MAPHEIGHT,MAPWIDTH));
		
		if(canvasPanel instanceof CanvasPanel){
			canvasPanel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me){
					int choose=me.getButton();
					Graphics g=canvasPanel.getGraphics();
					//System.out.println("1"+g);
					if(choose==MouseEvent.BUTTON1){
						//System.out.println(me.getX()+","+me.getY());
						int x=me.getX()/30;
						int y=me.getY()/30;
						if(map[y][x]==1&&controller.isCanSetStart()){
							map[y][x]=2;
							controller.MapStartNode(y, x);
							g.drawImage(MapConfig.icon3.getImage(),x*30 ,y*30, MapConfig.ELEWIDTH, MapConfig.ELEHEIGHT, null);
							//System.out.println(g);
							controller.setCanSetStart(false);
						}else if(map[y][x]==1&&controller.isCansetGoal()){
							map[y][x]=4;
							controller.MapGoalNode(y, x);
							g.drawImage(MapConfig.icon4.getImage(),x*30 ,y*30, MapConfig.ELEWIDTH, MapConfig.ELEHEIGHT, null);
							//System.out.println(g);
							controller.setCansetGoal(false);
						}
					}else if(choose==MouseEvent.BUTTON3){
						int x=me.getX()/30;
						int y=me.getY()/30;
						//System.out.println(x);
						if(map[y][x]==2){
							map[y][x]=1;
							g.drawImage(MapConfig.icon1.getImage(),x*30 ,y*30, MapConfig.ELEWIDTH, MapConfig.ELEHEIGHT, null);
							controller.getMap().setStart(null);
							controller.setCanSetStart(false);
							
						}else if(map[y][x]==4){
							map[y][x]=1;
							g.drawImage(MapConfig.icon1.getImage(),x*30 ,y*30, MapConfig.ELEWIDTH, MapConfig.ELEHEIGHT, null);
							controller.getMap().setGoal(null);
							controller.setCansetGoal(false);
						}
					}
				}
			});
		}
		jsp.getViewport().add(canvasPanel);
		frame.getContentPane().add(jsp);
		if(canvasPanel instanceof CanvasPanel){
			return (CanvasPanel)canvasPanel;
		}
		return null;
	}
	/*
	private static void draw(int x,int y,String info,Graphics gg){
		//System.out.println(gg);
		if(info.equals("start")){
			gg.drawImage(MapConfig.icon3.getImage(),x*30 ,y*30, MapConfig.ELEWIDTH, MapConfig.ELEHEIGHT, null);
		}else{
			gg.drawImage(MapConfig.icon4.getImage(),x*30 ,y*30, MapConfig.ELEWIDTH, MapConfig.ELEHEIGHT, null);
		}
		
	}
	*/
}
