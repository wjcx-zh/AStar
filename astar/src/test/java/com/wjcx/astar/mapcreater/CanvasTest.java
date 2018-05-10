package com.wjcx.astar.mapcreater;

import org.junit.Test;

import com.wjcx.astar.model.MapInit;

public class CanvasTest {
	/*public static void main(String[] args){
		//MapGenerator map=new MapGenerator();
		//map.init();
	}*/
	
	@Test
	public void testCanvas(){
		MapInit map=new MapInit();
		new MapGenerator(map.getMaps());
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
