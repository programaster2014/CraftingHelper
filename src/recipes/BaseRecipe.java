package recipes;

import java.util.HashMap;
import javax.swing.JProgressBar;

import gwClasses.Requirement;
import network.DataRetriever;
import tree.GWTree;

public class BaseRecipe {
	public String name;
	public int id;
	private DataRetriever ret;
	public HashMap<Integer, Requirement> recipe;
	
	public BaseRecipe(DataRetriever ret, int id){
		this.ret = ret;
		this.id = id;
		recipe = new HashMap<>();
	}
	
	public void generateRecipe() {
		GWTree tree = new GWTree(id, ret);
		tree.build();
	}
	
	public void generateRecipe(JProgressBar progress) {	
		GWTree tree = new GWTree(id, ret);
		tree.build();
		this.recipe = tree.baseElements;
	}
}
