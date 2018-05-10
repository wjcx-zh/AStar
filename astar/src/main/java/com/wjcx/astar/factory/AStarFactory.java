package com.wjcx.astar.factory;

import com.wjcx.astar.AStar;
import com.wjcx.astar.AStarSearchPath;
import com.wjcx.astar.AStarSearchPath2;
import com.wjcx.astar.AStarSearchPathOriginal;
import com.wjcx.astar.AStarSearchPathOriginal2;
import com.wjcx.astar.AStarSearchPathOriginal3;
import com.wjcx.astar.AStarSearchPathWithCos;
import com.wjcx.astar.AStarSearchPathWithCos2;

public class AStarFactory {
	public AStar getAStarChoose(int choose){
		if(choose==3){
			return new AStarSearchPath();
		}else if(choose==2){
			return new AStarSearchPathWithCos();
		}else{
			return new AStarSearchPathOriginal();
		}
	}
}
