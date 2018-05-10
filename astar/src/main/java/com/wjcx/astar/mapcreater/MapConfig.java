package com.wjcx.astar.mapcreater;

import javax.swing.ImageIcon;

public interface MapConfig {
	int ELEWIDTH=30;
	int ELEHEIGHT=30;
	
	int MAPWIDTH=600;
	int MAPHEIGHT=900;
	
	//SAVE
	String path="D:/map1.txt";
	//image
	ImageIcon icon1=new ImageIcon("C:\\Users\\WJCX\\Desktop\\sea.png");
	ImageIcon icon2=new ImageIcon("C:\\Users\\WJCX\\Desktop\\stone2.png");
	ImageIcon icon3=new ImageIcon("C:\\Users\\WJCX\\Desktop\\start.png");
	ImageIcon icon4=new ImageIcon("C:\\Users\\WJCX\\Desktop\\goal.png");
	ImageIcon icon5=new ImageIcon("C:\\Users\\WJCX\\Desktop\\road.png");
}
