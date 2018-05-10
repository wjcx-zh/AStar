package com.wjcx.astar.mapcreater;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NodeInfoPanel extends JPanel{
	
	private static final long serialVersionUID = -2502194753308097780L;
	private JLabel total_node_num=new JLabel("total_node",JLabel.LEFT);
	private JLabel search_node_num=new JLabel("search_node",JLabel.LEFT);
	private JLabel road_node_num=new JLabel("road_node",JLabel.LEFT);
	private static JLabel lab1=new JLabel();
	private static JLabel lab2=new JLabel();
	private static JLabel lab3=new JLabel();
	private JLabel[] labs={total_node_num,search_node_num,road_node_num};
	public NodeInfoPanel(){
		total_node_num.setBounds(5, 10, 100, 20);
		search_node_num.setBounds(5, 40, 100, 20);
		road_node_num.setBounds(5, 70, 100, 20);
		lab1.setBounds(100, 10, 80, 20);
		lab2.setBounds(100, 40, 80, 20);
		lab3.setBounds(100, 70, 80, 20);
		for(JLabel lab:labs){
			lab.setFont(new Font("arial",Font.PLAIN,16));
		}
		this.setLayout(null);
		this.add(total_node_num);
		this.add(search_node_num);
		this.add(road_node_num);
		this.add(lab1);
		this.add(lab2);
		this.add(lab3);
		lab1.setText("0");
		lab2.setText("0");
		lab3.setText("0");
	}
	public static void setInfo(String info1,String info2,String info3){
		lab1.setText(info1);
		lab2.setText(info2);
		lab3.setText(info3);
	}
}
