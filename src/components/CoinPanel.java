package components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

public class CoinPanel extends JPanel{
	private static final long serialVersionUID = 3435911246459340024L;

	private String gold;
	private String silver;
	private String copper;
	
	public CoinPanel(String gold, String silver, String copper){
		super(new GridBagLayout());
		this.gold = gold;
		this.silver = silver;
		this.copper = copper;
	}
	
	public CoinPanel(int total) {
		int n_copper;
		int n_silver;
		int n_gold;
		
		
		//copper logic
		n_copper = total%100;
		this.copper = Integer.toString(n_copper);
		
		//silver logic
		n_silver = ((total - n_copper)%10000)/100;
		this.silver = Integer.toString(n_silver);
		
		//gold logic
		n_gold = (total - n_copper - n_silver)/10000;
		this.gold = Integer.toString(n_gold);
	}
	
	public void build() {
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0.25;
		c.weighty = 1.0;
		
		c.gridx = 0;
		c.gridy = 0;
		if(isBound()) {
			this.add(createLabel("N/A"), c);
		}
		else {			
			this.add(createLabel(gold, "gold"), c);
			
			c.gridx++;
			this.add(createImageLabel("gold"), c);
			
			c.gridx++;
			this.add(createLabel(silver, "silver"), c);
			
			c.gridx++;
			this.add(createImageLabel("silver"), c);
			
			c.gridx++;
			this.add(createLabel(copper, "copper"), c);
			
			c.gridx++;
			this.add(createImageLabel("copper"), c);
		}
		
		
	}
	
	public boolean isBound() {
		return (this.gold.equals("0") && this.silver.equals("0") && this.copper.equals("0"));
	}
	
	public JLabel createLabel(String s, String type){
		JLabel label;
		
		if(s.equals("silver") || s.equals("copper")) {
			label = s.length() == 1 ? new JLabel(" " + s) : new JLabel(s);
		}
		else {
			label = new JLabel(s);
		}
		label.setHorizontalAlignment(JLabel.CENTER);
		
		return label;
	}
	
	public JLabel createLabel(String s){
		JLabel label = new JLabel(s);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		return label;
	}
	
	public JLabel createImageLabel(String type) {
		URL url;
		switch(type) {
			case "gold":
				url = Main.class.getResource("/resources/Gold_coin.png");
				break;
			case "silver":
				url = Main.class.getResource("/resources/Silver_coin.png");
				break;
			case "copper":
				url = Main.class.getResource("/resources/Copper_coin.png");
				break;
			default:
				url = null;
			
		}
		
		JLabel label;
		if(url != null) {
			ImageIcon icon = new ImageIcon(url);			
			label = new JLabel(icon);
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		return new JLabel(type);
	}
}
