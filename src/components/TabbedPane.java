package components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import recipes.BaseRecipe;
import totaler.TotalerRow;

public class TabbedPane extends JPanel{
	private static final long serialVersionUID = 3221095184039438004L;

	private HashMap<String, LabelManager> labelTable;
	private BaseRecipe recipe;
	private HashMap<Integer, JComponent> orderedTabs;
	private JTabbedPane tabpane;
	
	public TabbedPane(HashMap<String, ArrayList<TotalerRow>> table, BaseRecipe recipe){
		super(new GridLayout(1, 1));
		this.recipe = recipe;
		labelTable = new HashMap<>();
		orderedTabs = new HashMap<>();
		tabpane = new JTabbedPane();
		tabpane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		for(Integer tabCounter = 1; tabCounter <= this.recipe.order.size(); tabCounter++){
			this.buildLabelTable(table.get(this.recipe.order.get(tabCounter)), this.recipe.order.get(tabCounter));
			orderedTabs.put(tabCounter, makeTab(this.recipe.order.get(tabCounter)));
			

			tabpane.add(this.recipe.order.get(tabCounter), orderedTabs.get(tabCounter));
		}
		
		add(tabpane, c);
		tabpane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	
	public HashMap<String, LabelManager> getLabelTable(){
		return labelTable;
	}
	
	public void buildLabelTable(ArrayList<TotalerRow> table, String tabName) {
		labelTable.put(tabName, new LabelManager());
		for(TotalerRow row : table){
			boolean red = false;
			if(!row.completed.equals("Done")) {
				red = true;
			}
			
			labelTable.get(tabName).materialNames.add(createButton(row.name));
			labelTable.get(tabName).requiredLabels.put(row.name, createLabel(Integer.toString(row.required), false));
			labelTable.get(tabName).haveLabels.put(row.name, createLabel(Integer.toString(row.have), false));
			labelTable.get(tabName).needLabels.put(row.name, createLabel(row.completed, red));
		}
	}
	
	public JComponent makeTab(String tabName){
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.25;
		c.weighty = 1.0;
		
		c.gridx = 0;
		c.gridy = 0;
		JLabel name = createLabel("Material Name", false);
		name.setForeground(Color.BLUE);
		panel.add(name,  c);
		
		c.gridx = 1;
		c.gridy = 0;
		JLabel req = createLabel("Req.", false);
		req.setForeground(Color.BLUE);
		panel.add(req, c);
		
		c.gridx = 2;
		c.gridy = 0;
		JLabel have = createLabel("Have", false);
		have.setForeground(Color.BLUE);
		panel.add(have, c);
		
		c.gridx = 3;
		c.gridy = 0;
		JLabel need = createLabel("Need", false);
		need.setForeground(Color.BLUE);
		panel.add(need, c);
		
        for(JButton button : labelTable.get(tabName).materialNames) {
        	String materialName = button.getText();

			c.gridx = 0;
			c.gridy++;
			panel.add(button, c);
			
			c.gridx++;
			panel.add(labelTable.get(tabName).requiredLabels.get(materialName), c);
			
			c.gridx++;
			panel.add(labelTable.get(tabName).haveLabels.get(materialName), c);
			
			c.gridx++;
			panel.add(labelTable.get(tabName).needLabels.get(materialName), c);
        }
		
        return panel; 
	}
	
	public JButton createButton(String s){
		JButton button = new JButton();
		button.setText(s);
		button.setHorizontalAlignment(JLabel.CENTER);
		button.addActionListener(new GwLinkListener(s));
		button.setBorderPainted(false);
	    button.setOpaque(false);
	
		return button;
	}
	
	public JLabel createLabel(String s, boolean isRed){
		JLabel label = new JLabel(s);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		if(isRed) {
			label.setForeground(Color.RED);
		}
		
		return label;
	}
	
}