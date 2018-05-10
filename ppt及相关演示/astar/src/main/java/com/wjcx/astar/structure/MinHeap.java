package com.wjcx.astar.structure;

import com.wjcx.astar.model.Node;

public class MinHeap{
	private Node[] heap;
	private int maxSize;
	private int currentSize;

	public MinHeap(int maxSize){
		this.maxSize=maxSize;
		heap=new Node[maxSize];
		currentSize=0;
	}
	
	public Node[] heap(){
		return heap;
	}
	
	private void moveDown(Node[] nodes,int first,int last){
		int tempIndex=2*first+1;
		Node tempNode=nodes[first];
		
		while(tempIndex<=last){
			if(tempIndex<last&&((int)(nodes[tempIndex].getF()*10000))>((int)(nodes[tempIndex+1].getF()*10000))){
				tempIndex++;
			}
			if(((int)(tempNode.getF()*10000))<=((int)(nodes[tempIndex].getF()*10000))){
				break;
			}else{
				nodes[first]=nodes[tempIndex];
				first=tempIndex;
				tempIndex=2*tempIndex+1;
				
			}
		}
		nodes[first]=tempNode;
	}
	
	private void moveUp(int index){
		int start=index;
		int tempIndex=(index-1)/2;
		Node tempNode=heap[start];
		
		while(start>0){
			if(((int)(heap[tempIndex].getF()*10000))<=((int)(tempNode.getF()*10000))){
				break;
			}else{
				heap[start]=heap[tempIndex];
				start=tempIndex;
				tempIndex=(tempIndex-1)/2;
			}
			heap[start]=tempNode;
		}
	}
	
	public void update(Node node,int location){
		heap[location]=node;
		moveUp(location);
	}
	
	public void insert(Node node){
		if(isMaximum()){
			
		}else{
			heap[currentSize]=node;
			//currentSize++;
			moveUp(currentSize);
			currentSize++;
		}
	}
	
	public Node extractMin(){
		if(isEmpty()){
			return null;
		}
		Node min=heap[0];
		heap[0]=heap[currentSize-1];
		currentSize--;
		moveDown(heap,0,currentSize-1);
		return min;
	}
	
	public boolean isEmpty(){
		return currentSize==0;
	}
	
	public boolean isMaximum(){
		return currentSize==maxSize;
	}
	
	public int size(){
		return currentSize==-1?0:currentSize;
	}
	
	public void clear(){
		heap=null;
		currentSize=0;
	}
}
