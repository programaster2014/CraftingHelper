package main;

import network.DataRetriever;
import recipes.BaseRecipe;
import recipes.ZehtukaSpire;
import totaler.Totaler;
import tree.GWTree;

public class Main {

	public static final String materialsURLString = "https://api.guildwars2.com/v2/account/materials";
	public static final String bankURLString = "https://api.guildwars2.com/v2/account/bank";
	public static final String characterURLString = "https://api.guildwars2.com/v2/characters";
	
	
	public static void main(String[] args){
		DataRetriever ret = new DataRetriever();
		
		GWTree tree = new GWTree(46773, ret);
		tree.build();
		
		Gui gui = new Gui();
		BaseRecipe recipe = new ZehtukaSpire(ret);
		Totaler totaler = new Totaler(ret.getAccountInformation());
		gui.createGui(totaler.calculate(recipe), recipe);

	}
}
