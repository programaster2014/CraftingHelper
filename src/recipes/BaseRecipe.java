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
	
	public BaseRecipe(DataRetriever ret){
		this.ret = ret;
		
		recipes = new HashMap<>();
		order = new HashMap<>();
	}
	
	public void generateRecipe() {
		System.out.println("Starting Build");
		for(Integer count = 1; count < order.size(); count++) {
			if(!(order.get(count) == -999)) {
				GWTree tree = new GWTree(order.get(count), ret);
				tree.build();
				
				recipes.put(tree.getItemName(), tree.baseElements);
			}
		}
		
		for(Integer id : recipes.get("Mordant Inscription").keySet()) {
			HashMap<Integer, Requirement> current = recipes.get("Mordant Inscription");
			System.out.println(current.get(id).materialName + "  " + current.get(id).number);
		}
	}
}
