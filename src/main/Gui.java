package main;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import components.LabelManager;
import components.TabbedPane;
import recipes.BaseRecipe;
import totaler.TotalerRow;

public class Gui {

	private TabbedPane tabbedPane;
	
	public void createGui(HashMap<String, ArrayList<TotalerRow>> table, BaseRecipe recipe){
		JFrame frame = new JFrame(recipe.name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 700);

		tabbedPane = new TabbedPane(table, recipe);
		frame.add(tabbedPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public HashMap<String, LabelManager> getLabelTable(){
		return this.tabbedPane.getLabelTable();
	}
	
	
}
