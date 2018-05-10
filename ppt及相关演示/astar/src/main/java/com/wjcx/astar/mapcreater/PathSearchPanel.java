package com.wjcx.astar.mapcreater;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.wjcx.astar.control.AStarController;

public class PathSearchPanel extends JPanel{
	
	private static final long serialVersionUID = 1028297194888662364L;
	private JComboBox<String> jcb;
	private Vector<String> v;
	private JButton start;
	private JButton goal;
	private JButton ok;
	//private JButton clear;
	private String astar="3";
	private AStarController controller=AStarController.getInstance();
	public PathSearchPanel(){
		init();
		this.setLayout(null);
		jcb.setBounds(5, 0, 150, 50);
		start.setBounds(5, 60, 100, 30);
		goal.setBounds(110, 60, 100, 30);
		ok.setBounds(5, 100, 100, 30);
		//clear.setBounds(100, 60, 80, 30);
		
		jcb.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent ie) {
				// TODO Auto-generated method stub
				controller.setAstar(controller.getFactory().getAStarChoose(3));
				if(ie.getStateChange()==ItemEvent.SELECTED){
					astar=((String)ie.getItem());
					System.out.println(astar);
					if(astar.endsWith("3")){
						controller.setAstar(controller.getFactory().getAStarChoose(3));
					}else if(astar.endsWith("2")){
						
						controller.setAstar(controller.getFactory().getAStarChoose(2));

					}else{
						controller.setAstar(controller.getFactory().getAStarChoose(1));

					}
				}
			}
			
		});
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				if(ae.getSource()==ok){
					//if(Integer.parseInt(astar)==3){
						controller.pathSearch();
					//}
				}
			}
			
		});
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				if(ae.getSource().equals(start)){
						/*System.out.println(ae.getSource());
						System.out.println(start);
						System.out.println(ae.getSource()==start);
						System.out.println(ae.getSource()==goal);
						System.out.println(ae.getSource().equals(start));
						System.out.println(ae.getSource().equals(goal));*/
						controller.setCanSetStart(true);
					
				}
			}
			
		});
		goal.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				if(ae.getSource()==goal){
					//System.out.println(ae.getSource());
						controller.setCansetGoal(true);;
					
				}
			}
			
		});
		this.add(jcb);
		this.add(start);
		this.add(goal);
		this.add(ok);
		//this.add(clear);
	}
	
	private void init() {
		// TODO Auto-generated method stub
		v=new Vector<String>();
		v.add("A* 3");
		v.add("A* 2");
		v.add("A* 1");
		//this.setLayout(null);
		jcb=new JComboBox<String>(v);
		jcb.setBorder(BorderFactory.createTitledBorder("path search choose:"));
		jcb.setLocation(0,0);
		jcb.setPreferredSize(new Dimension(150,50));
		ok=new JButton("CONFIRM");
		start=new JButton("SETSTART");
		goal=new JButton("SETGOAL");
		//clear=new JButton("CLEAR");
		
	}
	
}
