package recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JProgressBar;

import gwClasses.Requirement;
import network.DataRetriever;
import thread.TreeCaller;
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
		for(Integer count = 1; count <= order.size(); count++) {
			if(!(order.get(count) == -999)) {
				GWTree tree = new GWTree(order.get(count), ret);
				tree.build();
				recipeNames.put(count, ret.getItemNameById(order.get(count)));
				recipes.put(tree.getItemName(), tree.baseElements);
			}
		}
	}
	
	public void generateRecipe(JProgressBar progress) {
		int step = 80 / order.size();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		ArrayList<TreeCaller> gwTreeList = new ArrayList<>();
		
		for(Integer counter = 1; counter <= order.size(); counter++) {
			if(!(order.get(counter) == -999)) {
				gwTreeList.add(new TreeCaller(counter, order.get(counter), ret));				
			}
		}
		
		try {
			List<Future<GWTree>> futures = executor.invokeAll(gwTreeList);
			
			for(Future<GWTree> future : futures) {
				GWTree tree = future.get();
				recipeNames.put(tree.Count, tree.getItemName());
				recipes.put(tree.getItemName(), tree.baseElements);
				progress.setValue(progress.getValue() + step);
			}
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();

	}
}
