package com.wjcx.astar.structure;

import org.junit.Before;
import org.junit.Test;

import com.wjcx.astar.model.Node;
import com.wjcx.astar.model.Position;

public class MyTest {
	MinHeap openTable;
	SBTree closeTable;
	@Before
	public  void before(){
		System.out.println("initial....");
		openTable=new MinHeap(20);
		closeTable=new SBTree();
	}
	
	@Test
	public void testMinHeapInsert(){
		openTable.insert(new Node(2,4.0F,null,null));
		openTable.insert(new Node(1,0,null,null));
		openTable.insert(new Node(7,2.000001F,null,null));
		openTable.insert(new Node(5,1.3F,null,null));
		openTable.insert(new Node(2,2.01F,null,null));
		System.out.println("*****************************");
		for(int i=0;i<openTable.size();i++){
			System.out.print(openTable.heap()[i].getF()+" ");
		}
		System.out.println();
	}
	
	@Test
	public void testMinHeapExtractMin(){
		testMinHeapInsert();
		openTable.insert(new Node(0,0,null,null));
		while(openTable.size()!=0){
			System.out.println(openTable.extractMin().getF());
			System.out.println("*****************************");
			for(int i=0;i<openTable.size();i++){
				System.out.print(openTable.heap()[i].getF()+" ");
			}
			System.out.println();
			System.out.println("=================================");
		}
	}
	
	@Test
	public void testMinHeapUpdate(){
		testMinHeapInsert();
		Node node=openTable.heap()[3];
		node.setG(-1);
		node.setH(0);
		node.cal_F();
		openTable.update(node, 3);
		System.out.println("*****************************");
		for(int i=0;i<openTable.size();i++){
			System.out.print(openTable.heap()[i].getF()+" ");
		}
		System.out.println();
	}
	
	@Test
	public void testSBTreeInsert(){
		Node root=new Node(1,3,null,new Position(0,1));
		closeTable.insert(root);
		closeTable.midRootSearch(closeTable.root());
		System.out.println("=========================");
		Node node1=new Node(2,1,null,new Position(3,2));
		Node node2=new Node(3,0,null,new Position(2,2));
		Node node3=new Node(4,2,null,new Position(4,0));
		closeTable.insert(node1);
		closeTable.insert(node2);
		closeTable.insert(node3);
		closeTable.midRootSearch(closeTable.root());
		System.out.println();
		System.out.println("=========================");
	}
	
	@Test
	public void testSBTreeSearch(){
		testSBTreeInsert();
		Node node=closeTable.root().getLeft();
		
		System.out.println(closeTable.search(node).getPosition().positionInfo());
		
	}
}
