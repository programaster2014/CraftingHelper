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
		
		
		Gui gui = new Gui();
		System.out.println("Preparing......");
		BaseRecipe recipe = new ZehtukaSpire(ret);
		recipe.generateRecipe();
		
		Totaler totaler = new Totaler(ret.getAccountInformation());
		gui.createGui(totaler.calculate(recipe), recipe);

	}
}
