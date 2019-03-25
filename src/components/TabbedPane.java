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

import gwClasses.Account;
import network.DataRetriever;
import recipes.BaseRecipe;
import totaler.Totaler;
import totaler.TotalerRow;

public class TabbedPane extends JPanel{
	private static final long serialVersionUID = 3221095184039438004L;
	public static String goldIcon = "https://wiki.guildwars2.com/images/d/d1/Gold_coin.png";
	public static String silverIcon = "https://wiki.guildwars2.com/images/3/3c/Silver_coin.png";
	public static String copperIcon = "https://wiki.guildwars2.com/images/e/eb/Copper_coin.png";

	private HashMap<String, LabelManager> labelTable;
	public BaseRecipe recipe;
	private Totaler totaler;
	private DataRetriever ret;
	
	public TabbedPane(Totaler totaler, BaseRecipe recipe, DataRetriever ret){
		super(new GridLayout(1, 1));
		this.recipe = recipe;
		this.totaler = totaler;
		this.ret = ret;
		labelTable = new HashMap<>();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		rebuildTable();
	}
	
	public void rebuildTable() {
		Account account = this.ret.getAccountInformation();
		this.totaler = new Totaler(account);
		ArrayList<TotalerRow> table = this.totaler.calculate(recipe);
		this.removeAll();
		

		this.buildLabelTable(table, recipe.name);
		this.build(this.recipe.name);
	}
	
	public void rebuildTable(Account account) {
		this.totaler = new Totaler(account);
		ArrayList<TotalerRow> table = this.totaler.calculate(recipe);
		this.removeAll();
		
		this.buildLabelTable(table, recipe.name);
		this.build(this.recipe.name);
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
			labelTable.get(tabName).requiredLabels.put(row.name, createLabel(Integer.toString(row.required)));
			labelTable.get(tabName).haveLabels.put(row.name, createLabel(Integer.toString(row.have)));
			labelTable.get(tabName).needLabels.put(row.name, createLabel(row.completed, red));
			labelTable.get(tabName).goldLabels.put(row.name, createLabel(Integer.toString(row.gold)));
			labelTable.get(tabName).silverLabels.put(row.name, createLabel(Integer.toString(row.silver)));
			labelTable.get(tabName).copperLabels.put(row.name, createLabel(Integer.toString(row.copper)));
		}
	}
	
	public void build(String tabName) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.25;
		c.weighty = 1.0;
		
		c.gridx = 0;
		c.gridy = 0;
		JLabel name = createLabel("Material Name");
		name.setForeground(Color.BLUE);
		this.add(name,  c);
		
		c.gridx = 1;
		c.gridy = 0;
		JLabel req = createLabel("Req.");
		req.setForeground(Color.BLUE);
		this.add(req, c);
		
		c.gridx = 2;
		c.gridy = 0;
		JLabel have = createLabel("Have");
		have.setForeground(Color.BLUE);
		this.add(have, c);
		
		c.gridx = 3;
		c.gridy = 0;
		JLabel need = createLabel("Need");
		need.setForeground(Color.BLUE);
		this.add(need, c);
		
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 3;
		JLabel coin = createLabel("Cost");
		coin.setForeground(Color.BLUE);
		this.add(coin, c);
		
		c.gridwidth = 1;
        for(JButton button : labelTable.get(tabName).materialNames) {
        	String materialName = button.getText();

			c.gridx = 0;
			c.gridy++;
			this.add(button, c);
			
			c.gridx++;
			this.add(labelTable.get(tabName).requiredLabels.get(materialName), c);
			
			c.gridx++;
			this.add(labelTable.get(tabName).haveLabels.get(materialName), c);
			
			c.gridx++;
			this.add(labelTable.get(tabName).needLabels.get(materialName), c);
			
			c.gridx++;
			CoinPanel coinPanel = new CoinPanel(
					labelTable.get(tabName).goldLabels.get(materialName).getText(),
					labelTable.get(tabName).silverLabels.get(materialName).getText(),
					labelTable.get(tabName).copperLabels.get(materialName).getText()
			);
			coinPanel.build();
			this.add(coinPanel, c);
        }
        
        c.gridy++;
        CoinPanel coinPanel = new CoinPanel(this.totaler.totalCost);
        coinPanel.build();
        this.add(coinPanel, c); 
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
		JLabel name = createLabel("Material Name");
		name.setForeground(Color.BLUE);
		panel.add(name,  c);
		
		c.gridx = 1;
		c.gridy = 0;
		JLabel req = createLabel("Req.");
		req.setForeground(Color.BLUE);
		panel.add(req, c);
		
		c.gridx = 2;
		c.gridy = 0;
		JLabel have = createLabel("Have");
		have.setForeground(Color.BLUE);
		panel.add(have, c);
		
		c.gridx = 3;
		c.gridy = 0;
		JLabel need = createLabel("Need");
		need.setForeground(Color.BLUE);
		panel.add(need, c);
		
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 3;
		JLabel coin = createLabel("Cost");
		coin.setForeground(Color.BLUE);
		panel.add(coin, c);
		
		c.gridwidth = 1;
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
			
			c.gridx++;
			CoinPanel coinPanel = new CoinPanel(
					labelTable.get(tabName).goldLabels.get(materialName).getText(),
					labelTable.get(tabName).silverLabels.get(materialName).getText(),
					labelTable.get(tabName).copperLabels.get(materialName).getText()
			);
			coinPanel.build();
			panel.add(coinPanel, c);
        }
        
        c.gridy++;
        CoinPanel coinPanel = new CoinPanel(this.totaler.totalCost);
        coinPanel.build();
        panel.add(coinPanel, c);        
		
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
	
	public JLabel createLabel(String s){
		JLabel label = new JLabel(s);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		return label;
	}	
}
