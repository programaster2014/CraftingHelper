package gwClasses;

import java.util.List;

public class Recipe {
	public String type;
	public int output_item_id;
	public int output_item_count;
	public int min_rating;
	public int time_to_craft_ms;
	public List<String> disciplines;
	public List<String> flags;
	public List<Ingredient> ingredients;
	public int id;
	public String chat_link;
}
