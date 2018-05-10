package com.wjcx.astar.model;

public class Position implements Comparable<Position>{
	private int x;
	private int y;
	
	public Position(){}
	public Position(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public String positionInfo(){
		return "("+this.x+","+this.y+")";
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	@Override
	public int compareTo(Position o) {
		// TODO Auto-generated method stub
		if(this.x*245+this.y<o.getX()*245+o.getY()){
			return -1;
		}else if(this.x*245+this.y>o.getX()*245+o.getY()){
			return 1;
		}
		return 0;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		if(this!=null&&obj!=null&&obj.getClass()==this.getClass()){
			Position current=(Position)obj;
			return x==current.getX()&&y==current.getY();
		}
		
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.x*245+this.y%29;
	}
	
	
}
