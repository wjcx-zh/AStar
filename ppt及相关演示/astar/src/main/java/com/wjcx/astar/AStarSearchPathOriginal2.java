package com.wjcx.astar;




import com.wjcx.astar.model.MapInit;
import com.wjcx.astar.model.Node;
import com.wjcx.astar.model.Position;
import com.wjcx.astar.structure.MinHeap;
import com.wjcx.astar.structure.SBTree;


public class AStarSearchPathOriginal2 implements AStar{
	public final static int ROAD_BAR=0;
	public final static char ROAD=5;
	public final static int ROAD_COST=10;
	//public final static int DIRECTCONTS=ROAD_COST/2;
	//public final static int OBSCONTS=DIRECTCONTS-1;
	MinHeap openTable=null;
	SBTree closeTable=null;
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
		closeTable=new SBTree();
		long s1=System.nanoTime();
		int sH=(int)cal_H(map.getStart().getPosition(), map.getGoal().getPosition(), map.getStart().getPosition(),null);
		System.out.println(System.nanoTime()-s1);
		System.out.println(sH);
		map.getStart().setH(sH);
		map.getStart().cal_F();
		map.getGoal().setG(sH);
		map.getGoal().cal_F();
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
		
	}

	public void stepMoving(MapInit map) {
		// TODO Auto-generated method stub
		while(openTable.size()>0){
			if(isInCloseTable(map.getGoal())){
				move(map.getMaps(),map.getGoal());
				break;
			}
			Node current=openTable.extractMin();
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
		addNeighborsToOpenProcess(map,current,x+1,y,ROAD_COST);
		addNeighborsToOpenProcess(map,current,x,y+1,ROAD_COST);
		addNeighborsToOpenProcess(map,current,x-1,y,ROAD_COST);
		
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
				float h=cal_H(start.getPosition(),end.getPosition(),position,map.getMaps());
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
	
	public float cal_H(Position start,Position goal, Position current,int[][] map) {
		// TODO Auto-generated method stub
		
		
		int distance_cost=Math.abs(goal.getX()-current.getX())+Math.abs(goal.getY()-current.getY());
		
		return distance_cost;
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
