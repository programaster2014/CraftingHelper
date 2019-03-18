package recipes;

import java.util.HashMap;

import gwClasses.Requirement;
import network.DataRetriever;
import tree.GWTree;

public class BaseRecipe {
	public String name;
	private DataRetriever ret;
	public HashMap<String, HashMap<Integer, Requirement>> recipes;
	public HashMap<Integer, Integer> order;
	public HashMap<Integer, String> recipeNames;
	
	public BaseRecipe(DataRetriever ret){
		this.ret = ret;
		
		recipes = new HashMap<>();
		order = new HashMap<>();
		recipeNames = new HashMap<>();
	}
	
	public void generateRecipe() {
		System.out.println("Starting Build");
		for(Integer count = 1; count <= order.size(); count++) {
			if(!(order.get(count) == -999)) {
				GWTree tree = new GWTree(order.get(count), ret);
				tree.build();
				recipeNames.put(count, ret.getItemNameById(order.get(count)));
				recipes.put(tree.getItemName(), tree.baseElements);
			}
		}
	}
}
