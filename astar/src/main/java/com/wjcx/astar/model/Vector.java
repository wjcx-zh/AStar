package com.wjcx.astar.model;

public class Vector {
	private int vx;
	private int vy;
	
	public Vector(int vx,int vy){
		this.vx=vx;
		this.vy=vy;
	}
	
	public static int vectorMultiply(Vector v1,Vector v2){
		if(v1==null||v2==null){
			return -1;
		}
		return v1.vx*v2.vx+v1.vy*v2.vy;
	}
	
	public double vectorNorm(){
		return Math.sqrt(this.vx*this.vx+this.vy*this.vy);
	}
	
	public int getVx() {
		return vx;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public int getVy() {
		return vy;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	
}
