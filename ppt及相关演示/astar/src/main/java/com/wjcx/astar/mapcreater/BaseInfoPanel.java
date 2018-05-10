package com.wjcx.astar.mapcreater;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BaseInfoPanel extends JPanel{
	
	private static final long serialVersionUID = 4037056998847221230L;
	private JLabel obs_label=new JLabel("obstacle_rate",JLabel.LEFT);
	private JLabel start=new JLabel("start_time",JLabel.LEFT);
	private JLabel end=new JLabel("end_time",JLabel.LEFT);
	private JLabel time_cost=new JLabel("time_cost",JLabel.LEFT);
	private static JLabel lab1=new JLabel();
	private static JLabel lab2=new JLabel();
	private static JLabel lab3=new JLabel();
	private static JLabel lab4=new JLabel();
	public BaseInfoPanel(){
		obs_label.setBounds(5, 10, 80, 30);
		start.setBounds(5, 40, 80, 30);
		end.setBounds(5, 70, 80, 30);
		time_cost.setBounds(5, 100, 80, 30);
		/**/
		lab1.setBounds(100, 10, 80, 30);
		lab2.setBounds(100, 40, 80, 30);
		lab3.setBounds(100, 70, 80, 30);
		lab4.setBounds(100, 100, 80, 30);
		this.setLayout(null);
		this.add(obs_label);
		this.add(start);
		this.add(end);
		this.add(time_cost);
		this.add(lab1);
		this.add(lab2);
		this.add(lab3);
		this.add(lab4);
		/**/
		lab1.setText("0.0");
		lab2.setText("0");
		lab3.setText("0");
		lab4.setText("0");
		
	}
	public static void setRate(String rate){
		lab1.setText(rate);
		
	}
	
	public static void setTimeInfo(String start,String end){
		Long cost=Long.parseLong(end)-Long.parseLong(start);
		
		lab2.setText(start);
		lab3.setText(end);
		lab4.setText(cost.toString());
		
	}
}
