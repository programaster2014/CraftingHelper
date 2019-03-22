package recipes;

import java.util.HashMap;

import gwClasses.Requirement;
import network.DataRetriever;

public class ZehtukaReaver extends BaseRecipe {
public HashMap<Integer, Requirement> statChange;
	
	
	public ZehtukaReaver(DataRetriever ret){
		super(ret);
		this.name = "Zehtuka's Spire";

		statChange = new HashMap<>();
		statChange.put(83974, new Requirement("Mordant Inscription", 1));
		statChange.put(69953, new Requirement("Anthology of Heroes", 1));
		statChange.put(19721, new Requirement("Glob of Ectoplasm", 5));
		statChange.put(46773, new Requirement("Zojja's Reaver", 1));
		
		this.recipes.put("Stat Change", statChange);
		
		this.recipeNames.put(3, "Stat Change" );
		
		this.order.put(1, 46759);
		this.order.put(2, 83974);
		this.order.put(3, -999);
	}
}
