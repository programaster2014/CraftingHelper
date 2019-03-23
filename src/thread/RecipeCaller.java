package thread;

import java.util.concurrent.Callable;

import network.DataRetriever;
import recipes.BaseRecipe;
import recipes.RecipeFactory;

public class RecipeCaller implements Callable<BaseRecipe>{
	
	private BaseRecipe recipe;
	
	public RecipeCaller(DataRetriever ret, Integer id) {
		this.recipe = RecipeFactory.createInstance(id, ret);
	}
	
	@Override
	public BaseRecipe call() throws Exception {
		recipe.generateRecipe();
		return recipe;
	}

}
