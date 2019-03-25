package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import bank.BankItem;
import files.Data;
import files.FileLoader;
import gwClasses.Account;
import gwClasses.GW2Character;
import gwClasses.Item;
import gwClasses.Material;
import gwClasses.Price;
import gwClasses.Recipe;
import inventory.Inventory;

public class DataRetriever {
	public static final String materialsURLString = "https://api.guildwars2.com/v2/account/materials";
	public static final String bankURLString = "https://api.guildwars2.com/v2/account/bank";
	public static final String characterURLString = "https://api.guildwars2.com/v2/characters";
	private static final String recipeOutputURLString = "https://api.guildwars2.com/v2/recipes/search?output=";
	private static final String itemURLString = "https://api.guildwars2.com/v2/items?ids=";
	private static final String recipeURLString = "https://api.guildwars2.com/v2/recipes?ids=";
	private static final String coinURLString = "https://api.guildwars2.com/v2/commerce/prices";
	
	private Data data;
	private Gson gson;
	
	public DataRetriever() {
		FileLoader loader = new FileLoader();
		this.data = loader.retreiveData();
		
		//this.data = FileLoader.getData();
		this.gson = new Gson();
	}
	
	public Account getAccountInformation() {
		if(this.data != null) {
			Account account = new Account();
			
			String materials = URLConnectionReader(materialsURLString);
			String bank = URLConnectionReader(bankURLString);
			String characters = URLConnectionReader(characterURLString);
			
			if(characters != null) {
				String[] charactersArray = gson.fromJson(characters, String[].class);
				for(String characterName : charactersArray) {
					String inventory = URLConnectionReader(characterURLString + "/" + characterName + "/inventory");
					
					if(inventory != null) {
						GW2Character c = new GW2Character(characterName);
						c.inventory = (Inventory) gson.fromJson(inventory, Inventory.class);
						account.characters.add(c);
					}
				}
			}
			
			if(materials != null && bank != null) {
				account.materials = (Material[]) gson.fromJson(materials, Material[].class);
				account.bankItems = (BankItem[]) gson.fromJson(bank, BankItem[].class);
			}
			
			return account;
		}
		
		return null;
		
	}
	
	public String getItemNameById(int id) {
		String itemString = URLConnectionReader(itemURLString + id);
		
		if(itemString != null) {
			if(this.isArray(itemString)) {
				Item[] itemArray = gson.fromJson(itemString, Item[].class);
				if(itemArray.length >= 1) {
					return itemArray[0].name;
				}
				return null;
			}
			else {
				Item item = gson.fromJson(itemString, Item.class);
				return item.name;
			}
		}
		
		return null;
	}
	
	public int getPriceById(int id) {
		String priceString = URLConnectionReader(coinURLString + id);
		
		if(priceString != null) {
			if(this.isArray(priceString)) {
				Price[] priceArray = gson.fromJson(priceString, Price[].class);
				if(priceArray.length >= 1) {
					return priceArray[0].sells.unit_price;
				}
				return -1;
			}
			else {
				Price price = gson.fromJson(priceString, Price.class);
				return price.sells.unit_price;
			}
		}
		
		return -1;
	}
	
	public boolean isArray(String json) {		
		return (json.charAt(0) == '[');
	}
	
	public int[] getRecipeList(int id) {
		String recipeList = URLConnectionReader(recipeOutputURLString + id);
		
		if(recipeList != null) {
			return gson.fromJson(recipeList, int[].class);
		}
		
		return null;
	}
	
	public Item getItemById(int id) {
		
		String item = URLConnectionReader(itemURLString + id);
		
		if(item != null) {
			Item[] itemArray = gson.fromJson(item, Item[].class);
			if(itemArray.length >= 1) {
				return itemArray[0];
			}
			return null;
		}
		
		return null;
	}
	
	public Recipe getRecipeById(int id) {
		
		String recipe = URLConnectionReader(recipeURLString + id);
		
		if(recipe != null) {
			Recipe[] recipeArray = gson.fromJson(recipe, Recipe[].class);
			if(recipeArray.length >= 1) {
				return recipeArray[0];
			}
			return null;
		}
		
		return null;
	}
	
	
	private String URLConnectionReader(String url) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + data.getApikey());

		try {
			HttpResponse response = client.execute(request);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			StringBuilder sBuilder = new StringBuilder();
			String line = "";
			
			while((line = reader.readLine()) != null) {
				sBuilder.append(line);
			}
			
			return sBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
