package com.wjcx.astar.structure;



import com.wjcx.astar.model.Node;
import com.wjcx.astar.model.Position;

public class SBTree{
	private Node root;
	
	public Node root(){
		return this.root;
	}
	
	public SBTree(){
		root=null;
	}
	
	public SBTree(Node node){
		root=node;
		root.setSize(1);
		root.setParent(null);
		root.setLeft(null);
		root.setRight(null);
	}
	
	public void insert(Node node){
		if(root==null){
			root=node;
		}else{
			Node current=root;
			Node parent=null;
			int result=0;
			while(current!=null){
				parent=current;
				result=node.getPosition().compareTo(current.getPosition());
				if(result>0){
					current=current.getRight();
				}else{
					current=current.getLeft();
				}
			}
			node.setSize(1);
			node.setParent(parent);
			node.setLeft(null);
			node.setRight(null);
			if(result>0){
				parent.setRight(node);
			}else{
				parent.setLeft(node);
			}
			
			ancestorSizeIncrement(node);
			
			maintain(node);
		}
	}

	private void ancestorSizeIncrement(Node node) {
		// TODO Auto-generated method stub
		Node current=node;
		while(current.getParent()!=null){
			int parentSize=current.getParent().getSize();
			current.getParent().setSize(parentSize+1);
			current=current.getParent();
		}
	}
	
	/*private void ancestorSizeDecrement(Node node){
		Node current=node;
		while(current.getParent()!=null){
			int parentSize=current.getParent().getSize();
			current.getParent().setSize(parentSize-1);
			current=current.getParent();
		}
	}*/
	
	private boolean nodeFlag(Node node){
		Node current=node;
		while(current!=null){
			if(current==root.getLeft()){
				return false;
			}else if(current==root.getRight()){
				return true;
			}
			current=current.getParent();
		}
		return false;
	}
	
	private void maintain(Node node){
		if(root==node.getParent()){
			return ;
		}else{
			maintainProcess(root,nodeFlag(node));
		}
	}
	
	private void maintainProcess(Node node,boolean flag){
		if(node!=null){
			if(!flag&&node.getLeft()!=null){
				if((node.getRight()==null&&node.getLeft().getLeft()!=null)||(node.getLeft().getLeft()!=null&&node.getLeft().getLeft().getSize()>node.getRight().getSize())){
					right_rotate(node);
				}else if((node.getRight()==null&&node.getLeft().getRight()!=null)||(node.getLeft().getRight()!=null&&node.getLeft().getRight().getSize()>node.getRight().getSize())){
					left_rotate(node.getLeft());
					right_rotate(node);
				}else{
					return ;
				}
				maintainProcess(node.getLeft(),false);
				maintainProcess(node.getRight(),true);
				maintainProcess(node,false);
				maintainProcess(node,true);
			}else if(flag&&node.getRight()!=null){
				if((node.getLeft()==null&&node.getRight().getLeft()!=null)||(node.getRight().getLeft()!=null&&node.getRight().getLeft().getSize()>node.getLeft().getSize())){
					right_rotate(node.getRight());
					left_rotate(node);
				}else if((node.getLeft()==null&&node.getRight().getRight()!=null)||(node.getRight().getRight()!=null&&node.getRight().getRight().getSize()>node.getLeft().getSize())){
					left_rotate(node);
				}else{
					return ;
				}
				maintainProcess(node.getLeft(),false);
				maintainProcess(node.getRight(),true);
				maintainProcess(node,false);
				maintainProcess(node,true);
			}
		}
		
	}
	
	public Node search(Node node){
		Node current=root;
		if(root==null){
			return null;
		}else{
			int result=0;
			while(current!=null){
				result=node.getPosition().compareTo(current.getPosition());
				if(result>0){
					current=current.getRight();
				}else if(result<0){
					current=current.getLeft();
				}else{
					if(node.getPosition().equals(current.getPosition())){
						return current;
					}
					current=current.getLeft();
				}
			}
		}
		return null;
	}
	
	public Node search(Position position){
		Node current=root;
		if(root==null){
			return null;
		}else{
			int result=0;
			while(current!=null){
				result=position.compareTo(current.getPosition());
				if(result>0){
					current=current.getRight();
				}else if(result<0){
					current=current.getLeft();
				}else{
					if(position.equals(current.getPosition())){
						return current;
					}
					current=current.getLeft();
				}
			}
		}
		return null;
	}
	
	private void right_rotate(Node x){
		Node y=x.getLeft();
		y.setParent(x.getParent());
		if(x.getParent()!=null){
			if(x==x.getParent().getLeft()){
				x.getParent().setLeft(y);
			}else{
				x.getParent().setRight(y);
			}
		}
		x.setLeft(y.getRight());
		if(y.getRight()!=null){
			y.getRight().setParent(x);
		}
		y.setRight(x);
		x.setParent(y);
		y.setSize(x.getSize());
		x.setSize(y.getLeft()==null?y.getSize()-0-1:y.getSize()-y.getLeft().getSize()-1);
		
		if(root==x){
			root=y;
		}
		
	}
	
	private void left_rotate(Node x){
		Node y=x.getRight();
		y.setParent(x.getParent());
		if(x.getParent()!=null){
			if(x==x.getParent().getLeft()){
				x.getParent().setLeft(y);
			}else{
				x.getParent().setRight(y);
			}
		}
		x.setRight(y.getLeft());
		if(y.getLeft()!=null){
			y.getLeft().setParent(x);
		}
		
		y.setLeft(x);
		x.setParent(y);
		
		y.setSize(x.getSize());
		x.setSize(y.getRight()==null?y.getSize()-0-1:y.getSize()-y.getRight().getSize()-1);
		
		if(root==x){
			root=y;
		}
	}
	
	public void midRootSearch(Node node){
		if(node!=null){
			midRootSearch(node.getLeft());
			System.out.print(node.getPosition().positionInfo());
			midRootSearch(node.getRight());
		}
		System.out.println();
	}
}
