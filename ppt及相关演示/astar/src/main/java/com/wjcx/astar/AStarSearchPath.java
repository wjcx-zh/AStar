package com.wjcx.astar;

import com.wjcx.astar.model.MapInit;
import com.wjcx.astar.model.Node;
import com.wjcx.astar.model.Position;
import com.wjcx.astar.model.Vector;
import com.wjcx.astar.structure.MinHeap;
import com.wjcx.astar.structure.SBTree;

public class AStarSearchPath implements AStar{
	public final static int ROAD_BAR=0;
	public final static char ROAD=5;
	public final static int ROAD_COST=10;
	public final static int DIRECTCONTS=ROAD_COST;
	public final static int OBSCONTS=ROAD_COST/10*1;
	private MinHeap openTable=null;
	private SBTree closeTable=null;
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
		openTable=new MinHeap(map.getHeight()*map.getWidth());
		//System.out.println(openTable.size());
		closeTable=new SBTree();
		
		Position start=map.getStart().getPosition();
		Position goal=map.getGoal().getPosition();
		//long s1=System.nanoTime();
		int sH=(Math.abs(goal.getX()-start.getX())+Math.abs(goal.getY()-start.getY()))*ROAD_COST;
		//System.out.println(System.nanoTime()-s1);
		System.out.println(sH);
		map.getStart().setH(sH);
		map.getStart().cal_F();
		map.getGoal().setG(sH);
		map.getGoal().cal_F();
		//System.out.println(map.getStart().getPosition().positionInfo());
		openTable.insert(map.getStart());
		stepMoving(map);
		
		end=System.nanoTime();
		
		if(openTable.size()>0){
			total_nodes+=openTable.size();
		}
		if(closeTable.root().getSize()>0){
			total_nodes+=closeTable.root().getSize();
			search_nodes+=closeTable.root().getSize();
		}
		//closeTable.midRootSearch(closeTable.root());
		System.out.println(openTable.size());
		System.out.println(closeTable.root().getSize());
	}

	public void stepMoving(MapInit map) {
		// TODO Auto-generated method stub
		while(openTable.size()>0){
			if(isInCloseTable(map.getGoal())){
				move(map.getMaps(),map.getGoal());
				break;
			}
			Node current=openTable.extractMin();
			/*
			System.out.println("**********");
			System.out.println(current.getPosition().positionInfo()+">>> "+current.getF());
			System.out.println("**********");
			*/
			closeTable.insert(current);
			addNeighborsToOpenTable(map,current);
			
		}
		
	}

	public boolean isInCloseTable(Node goal) {
		
		Node node=closeTable.search(goal);
		if(node==null){
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isInCloseTable(int x,int y) {
		
		if(closeTable.search(new Position(x,y))!=null){
			return true;
		}
		return false;
	}

	public void addNeighborsToOpenTable(MapInit map, Node current) {
		// TODO Auto-generated method stub
		int x=current.getPosition().getX();
		int y=current.getPosition().getY();
		addNeighborsToOpenProcess(map,current,x,y-1,ROAD_COST);
		addNeighborsToOpenProcess(map,current,x,y+1,ROAD_COST);
		addNeighborsToOpenProcess(map,current,x+1,y,ROAD_COST);
		addNeighborsToOpenProcess(map,current,x-1,y,ROAD_COST);
		
	}

	public void addNeighborsToOpenProcess(MapInit map, Node current, int x, int y, int roadCost) {
		// TODO Auto-generated method stub
		if(canNodeReach(map,x,y)&&!isInCloseTable(x,y)){
			//System.out.println(x+","+y+" canReach && not in CloseTable");
			//Node start=map.getStart();
			Node end=map.getGoal();
			Position position=new Position(x,y);
			/*
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			int g=current.getG()+roadCost;
			Object[] obj=searchInOpenTable(position);
			Node child=(obj==null?null:(Node)obj[1]);
			int index=(obj==null?-1:(Integer)obj[0]);
			if(obj==null){
				/*start*/
				float h=cal_H(current.getPosition(),end.getPosition(),position,map.getMaps());
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
				/*System.out.println("=================");
				System.out.println(child.getF());
				System.out.println("=================");*/
				openTable.insert(child);
				
			}else if(child.getG()>g){
				child.setG(g);
				child.setPrefix(current);
				child.cal_F();
				openTable.update(child, index);
				
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
		float obs_rate=line_through(current,goal,map);
		float direction_cost=threaten_tri(start,goal,current);
		int distance_cost=(Math.abs(goal.getX()-current.getX())+Math.abs(goal.getY()-current.getY()))*ROAD_COST;
		float total_cost=(distance_cost+direction_cost+obs_rate*OBSCONTS);
		return total_cost;
	}

	public float line_through(Position current, Position goal,int[][] map) {
		// TODO Auto-generated method stub
		if(!current.equals(goal)){
			int step=0;
			int obs=0;
			if((goal.getX()-current.getX()==0)){
				
				int x=current.getX();
				int y1=current.getY()<goal.getY()?current.getY():goal.getY();
				int y2=current.getY()<goal.getY()?goal.getY():current.getY();
			
				for(int y=y1;y<y2;y++){
					if(map[x][y]==ROAD_BAR){
						obs++;
					}
					step++;
				}
			}else{
				float k=(goal.getY()-current.getY())/(goal.getX()-current.getX());
				float b=current.getY()-current.getX()*k;
				int x1=current.getX()<goal.getX()?current.getX():goal.getX();
				int x2=current.getX()<goal.getX()?goal.getX():current.getX();
				for(int x=x1;x<x2;x++){
					int y=Math.round(x*k+b);
					if(map[x][y]==ROAD_BAR){
						obs++;
					}
					step++;
				}
				//System.out.println(((float)obs)/(step+1));
				
			}
			return ((float)obs)/(step+1);
		}
		return 0;
	}

	public float threaten_tri(Position start, Position goal, Position current) {
		// TODO Auto-generated method stub
		Vector v1=makeVector(start,goal);
		Vector v2=makeVector(start,current);
		int multiply=Vector.vectorMultiply(v1, v2);
		double v1Norm=v1.vectorNorm();
		double v2Norm=v2.vectorNorm();
		float cost=(float)((1-(multiply/(v1Norm*v2Norm)))*DIRECTCONTS);
		//System.out.println(cost);
		return cost;
	}

	public Vector makeVector(Position start, Position current) {
		// TODO Auto-generated method stub
		int vx=current.getX()-start.getX();
		int vy=current.getY()-start.getY();
		return new Vector(vx,vy);
	}

	public Object[] searchInOpenTable(Position position) {
		// TODO Auto-generated method stub
		int index=-1;
		Object[] obj=new Object[2];
		if(position==null||openTable.isEmpty()){
			return null;
		}
		for(int i=0;i<openTable.size();i++){
			Node node=openTable.heap()[i];
			index++;
			if(node.getPosition().equals(position)){
				obj[0]=index;
				obj[1]=node;
				return obj;
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
			//System.out.println(end.getF());
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
