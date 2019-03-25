package main;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import files.FileLoader;
import gwClasses.Account;
import network.DataRetriever;
import recipes.BaseRecipe;
import recipes.RecipeFactory;
import thread.AccountCaller;
import thread.RecipeCaller;
import totaler.Totaler;

public class Main {

	public static final String materialsURLString = "https://api.guildwars2.com/v2/account/materials";
	public static final String bankURLString = "https://api.guildwars2.com/v2/account/bank";
	public static final String characterURLString = "https://api.guildwars2.com/v2/characters";
	
	
	public static void main(String[] args){
	
		Gui gui = new Gui();
		gui.createGui();
		
		DataRetriever ret = new DataRetriever();
		BaseRecipe recipe = RecipeFactory.createInstance(46759, ret);

		System.out.println("Preparing Executors");
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		AccountCaller a_caller = new AccountCaller(ret);
		System.out.println("Launching Account Future");
		Future<Account> a_future = executor.submit(a_caller);
		
		
		Totaler totaler;
		try {
			System.out.println("Getting Account and Creating Totaler");
			totaler = new Totaler(a_future.get());
			gui.updateProgress(10);
			System.out.println("Getting Recipe From Future");
			recipe.generateRecipe(gui.getProgressBar());
			
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Something went wrong with the futures.");
			totaler = null;
			recipe = null;
			e.printStackTrace();
		}
		
		if(totaler != null && recipe != null) {
			System.out.println("Populating Gui");
			gui.updateProgress(99);
			gui.createGui(totaler, recipe, ret);
			System.out.println("Done");
			
			while(true) {
				try {
					Thread.sleep(300000);
					gui.rebuildTable();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
