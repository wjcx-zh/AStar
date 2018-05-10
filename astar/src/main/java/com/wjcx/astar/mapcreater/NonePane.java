package com.wjcx.astar.mapcreater;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NonePane extends JPanel{

	private static final long serialVersionUID = 8993333369974984135L;
	private JLabel info=new JLabel("This is an empty map :)");
	public NonePane(){
		info.setFont(new Font("arial",Font.BOLD,50));
		info.setBounds(50, 250, 600, 80);
		this.setLayout(null);
		this.setSize(900, 600);
		this.add(info);
	}
}
