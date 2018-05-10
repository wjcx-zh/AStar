package com.wjcx.astar;

import com.wjcx.astar.model.MapInit;
import com.wjcx.astar.model.Position;

public interface AStar {
	
	public float cal_H(Position start,Position goal, Position current,int[][] map);
	
	public void pathSearching(MapInit map);
	
	public long getStart() ;

	public void setStart(long start) ;

	public long getEnd() ;

	public void setEnd(long end) ;

	public int getSearch_nodes() ;

	public void setSearch_nodes(int search_nodes) ;

	public int getRoad_nodes();

	public void setRoad_nodes(int road_nodes);
	public int getTotal_nodes() ;

	public void setTotal_nodes(int total_nodes) ;
}
