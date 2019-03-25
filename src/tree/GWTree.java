package tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import gwClasses.Ingredient;
import gwClasses.Item;
import gwClasses.Recipe;
import gwClasses.Requirement;
import network.DataRetriever;

public class GWTree {
	public Integer Count;
	private Node root;
	private Node current;
	public DataRetriever ret;
	private TreeMap<String, Integer > table;
	public HashMap<Integer, Requirement> baseElements;
	
	public GWTree(int id, DataRetriever ret) {
		Item item = ret.getItemById(id);
		this.root = new Node(item, 1, 1, 0);
		this.current = this.root;
		this.ret = ret;
		this.table = new TreeMap<>();
		this.baseElements = new HashMap<>();
	}
	
	public void reset() {
		this.current = this.root;
	}
	
	public String getItemName() {
		return this.root.getData().name;
	}
	
	public void build() {
		if(this.root.isValidNode()) {
			Stack<Node> stack = new Stack<>();
			stack.push(this.root);
			
			do {
				this.current = stack.pop();
				int[] recipes = ret.getRecipeList(this.current.getData().id);
				if(recipes != null) {
					if(recipes.length > 0) {
						Recipe recipe = ret.getRecipeById(recipes[0]);
						if(recipe != null && !recipe.ingredients.isEmpty()) {		
							for(Ingredient ingredient : recipe.ingredients) {
								Item item = ret.getItemById(ingredient.item_id);
								int coin = 0;
								if(!item.flags.contains("AccountBound")) {
									coin = ret.getPriceById(ingredient.item_id);									
								}
								if(item != null) {
									Node node = new Node(item, ingredient.count, this.current.getTotalCount(), coin);
									this.current.addChild(node);
									stack.push(node);
								}
							}
						}					
					}
				}
			}while(!stack.empty());
			
			this.buildBaseElements(this.root);
		}
		else {
			System.out.println("Root is not valid node");
		}
	}
	
	public void printTree(Node node) {
		System.out.println(node.getCount() + "x " + node.getData().name);
		for(Node child : node.getChildren()) {
			this.printTree(child);
		}
	}
	
	public void getBaseElements(Node node) {
		
		if(node.getChildren().isEmpty()) {
			table.put(node.getData().name, table.getOrDefault(node.getData().name, 0) + node.getTotalCount());
		}
		for(Node child : node.getChildren()) {
			this.getBaseElements(child);
		}
	}
	
	public void buildBaseElements(Node node) {
		if(node.getChildren().isEmpty()) {
			Requirement reqDefault = new Requirement(node.getData().name, 0, node.getCoin());
			int updatedCount = baseElements.getOrDefault(node.getData().id, reqDefault).number + node.getTotalCount();
			this.baseElements.put(node.getData().id, new Requirement(node.getData().name, updatedCount, node.getCoin()));
		}
		for(Node child : node.getChildren()) {
			this.buildBaseElements(child);
		}
	}
	
	public void printBaseElements() {
		Set<?> set = table.entrySet();
		Iterator<?> i = set.iterator();
		while(i.hasNext()) {
	        @SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry)i.next();
	        System.out.println(me.getValue() + "x " + me.getKey());
	    }
	}
}
