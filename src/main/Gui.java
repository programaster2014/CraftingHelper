package main;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import components.LabelManager;
import components.TabbedPane;
import network.DataRetriever;
import recipes.BaseRecipe;
import totaler.Totaler;
import totaler.TotalerRow;

public class Gui {

	private TabbedPane tabbedPane;
	
	public void createGui(Totaler totaler, BaseRecipe recipe, DataRetriever ret){
		JFrame frame = new JFrame(recipe.name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 700);

		tabbedPane = new TabbedPane(totaler, recipe, ret);
		frame.add(tabbedPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public HashMap<String, LabelManager> getLabelTable(){
		return this.tabbedPane.getLabelTable();
	}
	
	
}
