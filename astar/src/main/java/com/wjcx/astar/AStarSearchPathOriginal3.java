package com.wjcx.astar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wjcx.astar.model.MapInit;
import com.wjcx.astar.model.Node;
import com.wjcx.astar.model.Position;


public class AStarSearchPathOriginal3 implements AStar{
	public final static int ROAD_BAR=0;
	public final static char ROAD=5;
	public final static int ITALIC_COST=14;
	public final static int HORIZONTAL_COST=10;
	//public final static int DIRECTCONTS=HORIZONTAL_COST/2;
	//public final static int OBSCONTS=DIRECTCONTS-1;
	List<Node> openTable=null;
	List<Node> closeTable=null;
	private long start=0L;
	private long end=0L;
	private int search_nodes=0;
	private int road_nodes=0;
	private int total_nodes=0;
	
	public void pathSearching(MapInit map){
		if(map==null)
			return ;
		
		map.getStart().destroy();
		map.getGoal().destroy();
		start=System.nanoTime();
		
		openTable=new ArrayList<Node>();
		closeTable=new ArrayList<Node>();
		Position startp=map.getStart().getPosition();
		Position goal=map.getGoal().getPosition();
		long s1=System.nanoTime();
		int sH=(int)(Math.sqrt(Math.pow(Math.abs(goal.getX()-startp.getX()),2)+Math.pow(Math.abs(goal.getY()-startp.getY()),2)))*ITALIC_COST;
		System.out.println(System.nanoTime()-s1);
		System.out.println(sH);
		map.getStart().setH(sH);
		map.getStart().cal_F();
		map.getGoal().setG(sH);
		map.getGoal().cal_F();
		openTable.add(map.getStart());
		stepMoving(map);
		
		end=System.nanoTime();
		
		if(openTable.size()>0){
			total_nodes+=openTable.size();
		}
		if(closeTable.size()>0){
			total_nodes+=closeTable.size();
			search_nodes+=closeTable.size();
		}
		
	}

	public void stepMoving(MapInit map) {
		// TODO Auto-generated method stub
		while(openTable.size()>0){
			if(isInCloseTable(map.getGoal())){
				move(map.getMaps(),map.getGoal());
				break;
			}
			Node current=extractMin(openTable);
			openTable.remove(current);
			closeTable.add(current);
			addNeighborsToOpenTable(map,current);
			
		}
		
	}
	
	private Node extractMin(List<Node> open){
		Node min=null;
		if(open.size()==0){
			return null;
		}else{
			min=open.get(0);
			Iterator<Node> iterator=open.iterator();
			while(iterator.hasNext()){
				Node current=iterator.next();
				if(((int)(min.getF()*10000))>((int)(current.getF()*10000))){
					min=current;
				}
			}
			return min;
		
		}
	}
	
	public boolean isInCloseTable(Node goal) {
		
		Node node=search(goal,closeTable);
		if(node==null){
			return false;
		}else {
			return true;
		}
	}
	
	public Node search(Node node,List<Node> list){
		//Node newNode=null;
		if(node==null||list==null){
			return null;
		}
		Iterator<Node> iterator=list.iterator();
		while(iterator.hasNext()){
			Node n=iterator.next();
			if(node.equals(n)){
				return n;
			}
		}
		return null;
	}
	
	public boolean isInCloseTable(int x,int y) {
		
		if(search(new Position(x,y),closeTable)!=null){
			return true;
		}
		return false;
	}
	
	public Node search(Position position,List<Node> list){
		if(position==null||list==null){
			return null;
		}
		Iterator<Node> iterator=list.iterator();
		while(iterator.hasNext()){
			Node n=iterator.next();
			if(position.equals(n.getPosition())){
				return n;
			}
		}
		return null;
	}
	
	public void addNeighborsToOpenTable(MapInit map, Node current) {
		// TODO Auto-generated method stub
		int x=current.getPosition().getX();
		int y=current.getPosition().getY();
		addNeighborsToOpenProcess(map,current,x,y-1,HORIZONTAL_COST);
		addNeighborsToOpenProcess(map,current,x+1,y,HORIZONTAL_COST);
		addNeighborsToOpenProcess(map,current,x,y+1,HORIZONTAL_COST);
		addNeighborsToOpenProcess(map,current,x-1,y,HORIZONTAL_COST);
		addNeighborsToOpenProcess(map,current,x-1,y-1,ITALIC_COST);
		addNeighborsToOpenProcess(map,current,x-1,y+1,ITALIC_COST);
		addNeighborsToOpenProcess(map,current,x+1,y-1,ITALIC_COST);
		addNeighborsToOpenProcess(map,current,x+1,y+1,ITALIC_COST);
	}

	public void addNeighborsToOpenProcess(MapInit map, Node current, int x, int y, int roadCost) {
		// TODO Auto-generated method stub
		if(canNodeReach(map,x,y)&&!isInCloseTable(x,y)){
			//System.out.println(x+","+y+" canReach && not in CloseTable");
			Node start=map.getStart();
			Node end=map.getGoal();
			Position position=new Position(x,y);
			/*
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			int g=current.getG()+roadCost;
			Node child=searchInOpenTable(position);
			
			if(child==null){
				float h=cal_H(start.getPosition(),end.getPosition(),position,null);
				if(isEndNode(end.getPosition(),position)){
					child=end;
					child.setPrefix(current);
					child.setG(g);
					child.setH(h);
					child.cal_F();
				}else{
					child=new Node(g,h,current,position);
					child.cal_F();
				}
				openTable.add(child);
				
			}else if(child.getG()>g){
				child.setG(g);
				child.setPrefix(current);
				child.cal_F();
				//openTable.update(child, index);
				
			}
		}
	}

	public boolean canNodeReach(MapInit map, int x, int y) {
		// TODO Auto-generated method stub
		if(x<0||x>=map.getWidth()||y<0||y>=map.getHeight()){
			return false;
		}else if(map.getMaps()[x][y]!=ROAD_BAR){
			return true;
		}
		return false;
	}

	public boolean isEndNode(Position end, Position position) {
		// TODO Auto-generated method stub
		return position!=null&&end.equals(position);
	}

	public float cal_H(Position start,Position goal, Position current,int[][] map) {
		// TODO Auto-generated method stub
		
		
		float distance_cost=(float)(Math.sqrt(Math.pow(Math.abs(goal.getX()-current.getX()),2)+Math.pow(Math.abs(goal.getY()-current.getY()),2)))*ITALIC_COST;
		
		return distance_cost;
	}


	public Node searchInOpenTable(Position position) {
		// TODO Auto-generated method stub
		
	
		if(position==null||openTable.isEmpty()){
			return null;
		}
		for(int i=0;i<openTable.size();i++){
			Node node=openTable.get(i);
			
			if(node.getPosition().equals(position)){
				
				return node;
			}
			
		}
		return null;
	}

	public void move(int[][] maps, Node end) {
		// TODO Auto-generated method stub
		if(end==null||maps==null){
			return;
		}
		end=end.getPrefix();
		while(end!=null&&end.getPrefix()!=null){
			Position position=end.getPosition();
			maps[position.getX()][position.getY()]=ROAD;
			end=end.getPrefix();
			road_nodes++;
		}
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public int getSearch_nodes() {
		return search_nodes;
	}

	public void setSearch_nodes(int search_nodes) {
		this.search_nodes = search_nodes;
	}

	public int getRoad_nodes() {
		return road_nodes;
	}

	public void setRoad_nodes(int road_nodes) {
		this.road_nodes = road_nodes;
	}

	public int getTotal_nodes() {
		return total_nodes;
	}

	public void setTotal_nodes(int total_nodes) {
		this.total_nodes = total_nodes;
	}
	
	
}
