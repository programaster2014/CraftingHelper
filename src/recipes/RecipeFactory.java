package recipes;

import network.DataRetriever;

public class RecipeFactory {
	
	public static BaseRecipe createInstance(Integer id, DataRetriever ret) {
		switch(id) {
			case 46759:
				return new ZehtukaReaver(ret);
			case 46773:
				return new ZehtukaSpire(ret);
			default:
				return null;
		}
	}
}
