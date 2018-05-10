package com.wjcx.astar.control;

import java.awt.Graphics;

import com.wjcx.astar.AStar;
import com.wjcx.astar.AStarSearchPath;
import com.wjcx.astar.AStarSearchPath2;
import com.wjcx.astar.AStarSearchPathOriginal;
import com.wjcx.astar.AStarSearchPathWithCos;
import com.wjcx.astar.factory.AStarFactory;
import com.wjcx.astar.mapcreater.BaseInfoPanel;
import com.wjcx.astar.mapcreater.CanvasPanel;
import com.wjcx.astar.mapcreater.MapGenerator;
import com.wjcx.astar.mapcreater.NodeInfoPanel;
import com.wjcx.astar.model.MapInit;
import com.wjcx.astar.model.Node;
import com.wjcx.astar.model.Position;

public class AStarController {
	private MapInit map=new MapInit();
	private AStarFactory factory=new AStarFactory();
	private AStar astar=new AStarSearchPath();
	//private AStarSearchPathWithCos astar2=new AStarSearchPathWithCos();
	//private AStarSearchPathOriginal astar1=new AStarSearchPathOriginal();
	private static AStarController aStarController=new AStarController();
	private boolean canSetStart=false;
	private boolean cansetGoal=false;
	private CanvasPanel cvp;
	private AStarController(){}
	
	public void MapInitControl(float obs_rate){
		map.setObs_rate(obs_rate);
		//map.MakeMap(map.getWidth(), map.getHeight(), obs_rate);
	}
	
	public void MapGenerate(){
		map.MakeMap(map.getWidth(), map.getHeight(), map.getObs_rate());
		cvp=MapGenerator.create(map.getMaps());
	}
	
	public void MapStartNode(int x,int y){
		Node start=new Node(0,0,null,new Position(x,y));
		map.setStart(start);
	}
	
	public void MapGoalNode(int x,int y){
		Node goal=new Node(0,0,null,new Position(x,y));
		map.setGoal(goal);
	}
	
	public void analyseData(){
		BaseInfoPanel.setRate(((Float)map.getObs_rate()).toString());
		BaseInfoPanel.setTimeInfo(((Long)astar.getStart()).toString(), ((Long)astar.getEnd()).toString());
		NodeInfoPanel.setInfo(((Integer)astar.getTotal_nodes()).toString(),((Integer)astar.getSearch_nodes()).toString(),astar.getSearch_nodes()==0?"0": ((Integer)(astar.getRoad_nodes()+2)).toString());
	}
	
	public void clearData(){
		astar.setRoad_nodes(0);
		astar.setSearch_nodes(0);
		astar.setTotal_nodes(0);
		astar.setStart(0);
		astar.setEnd(0);
	}
	
	public void updateMap(){
		
	}
	
	public void pathSearch(){
		System.out.println(astar.getClass());
		astar.pathSearching(map);
	}
	
	public Graphics getCVPG(){
		return cvp.getGraphics();
	}
	
	public static AStarController getInstance(){
		return aStarController;
	}
	public MapInit getMap() {
		return map;
	}

	public void setMap(MapInit map) {
		this.map = map;
	}

	public boolean isCanSetStart() {
		return canSetStart;
	}

	public void setCanSetStart(boolean canSetStart) {
		this.canSetStart = canSetStart;
	}

	public boolean isCansetGoal() {
		return cansetGoal;
	}

	public void setCansetGoal(boolean cansetGoal) {
		this.cansetGoal = cansetGoal;
	}

	public AStarFactory getFactory() {
		return factory;
	}

	public void setFactory(AStarFactory factory) {
		this.factory = factory;
	}

	public AStar getAstar() {
		return astar;
	}

	public void setAstar(AStar astar) {
		this.astar = astar;
	}

	
	
}
