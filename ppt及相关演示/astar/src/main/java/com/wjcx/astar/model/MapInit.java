package com.wjcx.astar.model;

public class MapInit {
	private int[][] maps;
	private int width=20;
	private int height=30;
	private Node start;
	private Node goal;
	private float obs_rate=0.2f;
	public MapInit(){
		//MakeMap(width,height,obs_rate);
	}
	
	public void MakeMap(int  w,int h,float obs_rate) {
		// TODO Auto-generated method stub
		maps=new int[w][h];
		for(int i=0;i<w;i++){
			
			for(int j=0;j<h;j++){
				maps[i][j]=1;
			}
		}
		makeObstacles(obs_rate);
	}
	
	private void makeObstacles(float obs_rate) {
		// TODO Auto-generated method stub
		int obs_num=(int)(width*height*obs_rate);
		for(int i=0;i<obs_num;i++){
			int obs_i=(int)(Math.random()*width);
			int obs_j=(int)(Math.random()*height);
			maps[obs_i][obs_j]=0;
		}
	}

	public void MapInfo(){
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				System.out.print(maps[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("map_width:"+getWidth()+"\nmap_height:"+getHeight());
	}
	public MapInit(int[][] maps,int width,int height,Node start,Node goal){
		this.maps=maps;
		this.width=width;
		this.height=height;
		this.start=start;
		this.goal=goal;
	}
	
	public int[][] getMaps() {
		return maps;
	}
	public void setMaps(int[][] maps) {
		this.maps = maps;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Node getStart() {
		return start;
	}
	public void setStart(Node start) {
		this.start = start;
	}
	public Node getGoal() {
		return goal;
	}
	public void setGoal(Node goal) {
		this.goal = goal;
	}

	public float getObs_rate() {
		return obs_rate;
	}

	public void setObs_rate(float obs_rate) {
		this.obs_rate = obs_rate;
	}
	
}
