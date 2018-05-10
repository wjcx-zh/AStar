package com.wjcx.astar.mapcreater;

import java.awt.Graphics;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel{
	
	private static final long serialVersionUID = -5787216559053926704L;
	private int[][] map;
	private int mapWidth;
	private int mapHeight;
	
	
	
	public CanvasPanel(int[][] map){
		init(map);
	}
	
	private void init(int[][] map) {
		// TODO Auto-generated method stub
		this.map=map;
		this.mapWidth=map.length;
		this.mapHeight=map[0].length;
	}

	public void paint(Graphics g){
		for(int i=0;i<mapWidth;i++){
			for(int j=0;j<mapHeight;j++){
				if(map[i][j]==1){
					g.drawImage(MapConfig.icon1.getImage(),getDrawX(j) ,getDrawY(i), MapConfig.ELEWIDTH, MapConfig.ELEHEIGHT, null);
				}else{
					g.drawImage(MapConfig.icon2.getImage(),getDrawX(j) ,getDrawY(i), MapConfig.ELEWIDTH, MapConfig.ELEHEIGHT, null);
				}
			}
		}
	}
	
	public int getDrawX(int i){
		int x=0;
		x=i*MapConfig.ELEWIDTH;
		return x;
	}
	
	public int getDrawY(int i){
		int y=0;
		y=i*MapConfig.ELEHEIGHT;
		return y;
	}
}
