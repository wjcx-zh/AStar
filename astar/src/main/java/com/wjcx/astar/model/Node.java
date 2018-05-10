package com.wjcx.astar.model;

public class Node{
	private Position position;
	private float f;
	private int g;
	private float h;
	private int size;
	
	private Node parent,left,right,prefix;
	
	public Node(){
		
	}
	
	public Node(int g,float h,Node prefix,Position position){
		this.g=g;
		this.h=h;
		this.prefix=prefix;
		this.position=position;
		//cal_F();
	}
	
	public void cal_F(){
		f=g+h;
	}
	
	public void setPosition(int x,int y){
		this.position.setX(x);
		this.position.setY(y);
	}
	
	public void setParent(Node parent){
		this.parent=parent;
	}
	
	public void setLeft(Node left){
		this.left=left;
	}
	
	public void setRight(Node right){
		this.right=right;
	}
	
	public void setPrefix(Node prefix){
		this.prefix=prefix;
	}
	
	public void setF(float f){
		this.f=f;
	}
	
	public void setG(int g){
		this.g=g;
	}
	
	public void setH(float h){
		this.h=h;
	}
	
	public void setSize(int size){
		this.size=size;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public float getF(){
		return this.f;
	}
	
	public int getG(){
		return this.g;
	}
	
	public float getH(){
		return this.h;
	}
	
	public Node getLeft(){
		return this.left;
	}
	
	public Node getRight(){
		return this.right;
	}
	public Node getParent(){
		return this.parent;
	}
	
	public Node getPrefix(){
		return this.prefix;
	}
	
	public Position getPosition(){
		return this.position;
	}
	
	public void destroy(){
		this.g=0;
		this.h=0f;
		this.prefix=null;
		//this.position=null;
		this.f=0f;
		this.left=null;
		this.right=null;
		this.parent=null;
		this.size=0;
	}
}
