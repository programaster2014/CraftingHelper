package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import components.LabelManager;
import components.TabbedPane;
import network.DataRetriever;
import recipes.BaseRecipe;
import totaler.Totaler;

public class Gui {	
	private TabbedPane tabbedPane;
	private JFrame frame;
	private JLabel loading;
	
	private JProgressBar progressBar;
	
	public Gui() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550, 700);
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	public void rebuildTable() {
		tabbedPane.rebuildTable();
	}
	
	public void createGui(Totaler totaler, BaseRecipe recipe, DataRetriever ret){		
		frame.setTitle(recipe.name);
		frame.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		tabbedPane = new TabbedPane(totaler, recipe, ret);
		frame.remove(progressBar);
		frame.remove(loading);
		frame.add(tabbedPane, c);
		frame.setVisible(true);
	}
	
	public void createGui() {
		frame.setLayout(new GridBagLayout());
		
		progressBar = new JProgressBar();
		progressBar.setVisible(true);
		//progressBar.setIndeterminate(true);
		progressBar.setValue(0);
		
		GridBagConstraints c = new GridBagConstraints();
		loading = new JLabel("Loading...");
		c.gridx = 0;
		c.gridy = 0;
		frame.add(loading, c);
		c.gridy = 1;
		frame.add(progressBar, c);
		frame.setVisible(true);
	}
	
	public HashMap<String, LabelManager> getLabelTable(){
		return this.tabbedPane.getLabelTable();
	}
	
	public void updateProgress(int progress) {
		if(progressBar.isVisible()) {
			progressBar.setValue(progress);
		}
	}
	
	
}
