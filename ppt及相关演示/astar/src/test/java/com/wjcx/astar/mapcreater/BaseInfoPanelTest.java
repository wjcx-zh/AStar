package com.wjcx.astar.mapcreater;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

public class BaseInfoPanelTest {

	@Test
	public void test() {
		JFrame f=new JFrame();
		JPanel base=new BaseInfoPanel();
		JPanel node=new NodeInfoPanel();
		JPanel mapCntrol=new MapControlPane();
		JPanel pathPanel=new PathSearchPanel();
		JPanel processPanel=new ProcessPanel();
		f.setLayout(null);
		base.setBounds(5, 5, 200, 200);
		node.setBounds(5, 200, 200, 100);
		mapCntrol.setBounds(0, 300, 200, 100);
		pathPanel.setBounds(0, 400, 200, 100);
		processPanel.setBounds(0, 600, 200, 300);
		f.add(base);
		f.add(node);
		f.add(mapCntrol);
		f.add(pathPanel);
		f.add(processPanel);
		f.setBounds(50, 50, 200, 700);
		f.setVisible(true);
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
