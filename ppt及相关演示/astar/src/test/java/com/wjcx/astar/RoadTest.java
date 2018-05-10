package com.wjcx.astar;

import org.junit.Test;

import com.wjcx.astar.model.MapInit;
import com.wjcx.astar.model.Node;
import com.wjcx.astar.model.Position;

public class RoadTest {
	MapInit map;
	AStarSearchPath asp;
	AStarSearchPathOriginal aspo;
	@Test
	public void testAStar(){
		map=new MapInit();
		map.getMaps()[4][2]=2;
		map.getMaps()[18][26]=5;
		map.MapInfo();
		
		map.getMaps()[4][2]=1;
		map.getMaps()[18][26]=1;
		
		map.setStart(new Node(0,0,null,new Position(4,2)));
		map.setGoal(new Node(0,0,null,new Position(18,26)));
		System.out.println(map.getStart().getF()+" "+map.getStart().getG()+" "+map.getStart().getH());
		System.out.println(map.getGoal().getF()+" "+map.getGoal().getG()+" "+map.getGoal().getH());

		
		asp=new AStarSearchPath();
		asp.pathSearching(map);
		System.out.println(map.getStart().getF()+" "+map.getStart().getG()+" "+map.getStart().getH());
		System.out.println(map.getGoal().getF()+" "+map.getGoal().getG()+" "+map.getGoal().getH());

		map.getMaps()[4][2]=2;
		map.getMaps()[18][26]=5;
		map.MapInfo();
		System.out.println("+++++++++++++++++++");
	}
	
	@Test
	public void testAStarO(){
		map=new MapInit();
		map.getMaps()[4][2]=2;
		map.getMaps()[18][26]=5;
		map.MapInfo();
		
		map.getMaps()[4][2]=1;
		map.getMaps()[18][26]=1;
		
		map.setStart(new Node(0,0,null,new Position(4,2)));
		map.setGoal(new Node(0,0,null,new Position(18,26)));
		System.out.println(map.getStart().getF()+" "+map.getStart().getG()+" "+map.getStart().getH());
		System.out.println(map.getGoal().getF()+" "+map.getGoal().getG()+" "+map.getGoal().getH());

		
		aspo=new AStarSearchPathOriginal();
		aspo.pathSearching(map);
		System.out.println(map.getStart().getF()+" "+map.getStart().getG()+" "+map.getStart().getH());
		System.out.println(map.getGoal().getF()+" "+map.getGoal().getG()+" "+map.getGoal().getH());

		map.getMaps()[4][2]=2;
		map.getMaps()[18][26]=5;
		map.MapInfo();
		System.out.println("+++++++++++++++++++");
	}
	
	@Test
	public void testStepMoving(){
		testAStarO();
		aspo.stepMoving(map);
	}
	/*
	@Test
	public void testAddNeighbors(){
		testAStar();
		//asp.pathSearching(map);
	}
	
	@Test
	public void isInCloseTable1(){
		
	}
	
	
	
	@Test
	public void isInCloseTable2(){
		testAStar();
		System.out.println(asp.isInCloseTable(0, 1));
		System.out.println(asp.isInCloseTable(0, 2));
		System.out.println(asp.isInCloseTable(1, 0));
		System.out.println(asp.isInCloseTable(1, 1));
		System.out.println(asp.isInCloseTable(1, 2));
		System.out.println(asp.isInCloseTable(2, 0));
		System.out.println(asp.isInCloseTable(2, 1));
		System.out.println(asp.isInCloseTable(2, 2));
		
	}
	
	@Test 
	public void testCanNodeReach(){
		testAStar();
		System.out.println(asp.canNodeReach(map, 0, 1));
		System.out.println(asp.canNodeReach(map, 0, 2));
		System.out.println(asp.canNodeReach(map, 1, 0));
		System.out.println(asp.canNodeReach(map, 1, 1));
		System.out.println(asp.canNodeReach(map, 1, 2));
		System.out.println(asp.canNodeReach(map, 2, 0));
		System.out.println(asp.canNodeReach(map, 2, 1));
	
	}
	
	@Test 
	public void testIsEndNode(){
		testAStar();
		System.out.println(asp.isEndNode(map.getGoal().getPosition(),new Position( 0, 1)));
		System.out.println(asp.isEndNode(map.getGoal().getPosition(),new Position( 0, 1)));
		System.out.println(asp.isEndNode(map.getGoal().getPosition(),new Position( 0, 1)));
		System.out.println(asp.isEndNode(map.getGoal().getPosition(),new Position( 0, 1)));
		System.out.println(asp.isEndNode(map.getGoal().getPosition(),new Position( 0, 1)));
		System.out.println(asp.isEndNode(map.getGoal().getPosition(),new Position( 0, 1)));
		System.out.println(asp.isEndNode(map.getGoal().getPosition(),new Position( 0, 1)));
	
	}*/
}
