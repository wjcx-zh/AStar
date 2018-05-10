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

public class MapControlPane extends JPanel{
	
	private static final long serialVersionUID = 5587964709357722342L;
	private JComboBox<String> jcb;
	private Vector<String> v;
	private JButton generate;
	private String obs_rate="0.2";
	private AStarController controller=AStarController.getInstance();
	public MapControlPane(){
		init();
		this.setLayout(null);
		jcb.setBounds(5, 0, 150, 50);
		jcb.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				obs_rate=(String)e.getItem();
				//System.out.println(obs_rate);
				controller.MapInitControl((Float.parseFloat(obs_rate.substring(0, 2)))/100);
				if(e.getStateChange()==ItemEvent.SELECTED){
					 obs_rate=(String)e.getItem();
					//System.out.println(obs_rate);
					controller.MapInitControl((Float.parseFloat(obs_rate.substring(0, 2)))/100);
				}
		
			}
		
		});
		generate.setBounds(5, 60, 90, 30);
		generate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(ae.getSource()==generate){
					//System.out.println("ok");
					Float f=(Float.parseFloat(obs_rate.substring(0, 2)))/100;
					BaseInfoPanel.setRate(f.toString());
					controller.MapGenerate();
				}
			}
		});
		this.add(jcb);
		this.add(generate);
	}

	private void init() {
		// TODO Auto-generated method stub
		v=new Vector<String>();
		v.add("20%");
		v.add("30%");
		v.add("40%");
		jcb=new JComboBox<String>(v);
		jcb.setBorder(BorderFactory.createTitledBorder("obstacle rate choose:"));
		jcb.setLocation(0,0);
		jcb.setPreferredSize(new Dimension(150,50));
	
		generate=new JButton("GENERTE");
		
	}

	
	
}
