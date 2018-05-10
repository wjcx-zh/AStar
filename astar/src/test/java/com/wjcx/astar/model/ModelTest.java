package com.wjcx.astar.model;

import org.junit.Before;
import org.junit.Test;

public class ModelTest {
	MapInit maps;
	@Before
	public void before(){
		maps=new MapInit();
	}
	
	@Test
	public void testMap(){
		maps.MapInfo();
	}
	
	@Test
	public void testHeap(){
		
	}
}
