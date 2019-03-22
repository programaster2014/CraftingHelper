package main;

import gwClasses.Account;
import network.DataRetriever;
import recipes.BaseRecipe;
import recipes.ZehtukaReaver;
import recipes.ZehtukaSpire;
import totaler.Totaler;
import tree.GWTree;

public class Main {

	public static final String materialsURLString = "https://api.guildwars2.com/v2/account/materials";
	public static final String bankURLString = "https://api.guildwars2.com/v2/account/bank";
	public static final String characterURLString = "https://api.guildwars2.com/v2/characters";
	
	
	public static void main(String[] args){
		DataRetriever ret = new DataRetriever();
		Account account = ret.getAccountInformation();
		Totaler totaler = new Totaler(account);
		
		
		Gui gui = new Gui();
		System.out.println("Preparing......");
		BaseRecipe recipe = new ZehtukaReaver(ret);
		recipe.generateRecipe();
		
		gui.createGui(totaler, recipe, ret);
		
		/*while(true) {
			System.out.println("WAITING..............");
		}*/

	}
}
