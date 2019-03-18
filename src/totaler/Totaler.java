package totaler;

import java.util.ArrayList;
import java.util.HashMap;

import bank.BankItem;
import gwClasses.Account;
import gwClasses.Bag;
import gwClasses.Material;
import inventory.InventoryItem;
import recipes.BaseRecipe;

public class Totaler {
	public Account account;
	
	public Totaler(Account account){
		this.account = account;
	}
	
	public HashMap<String, ArrayList<TotalerRow>> calculate(BaseRecipe recipe){
		HashMap<String, ArrayList<TotalerRow>> tables = new HashMap<>();
		
		for(Integer counter = 1; counter <= recipe.order.size(); counter++){
			String recipeName = recipe.recipeNames.get(counter);
			tables.put(recipeName, new ArrayList<>());
			for(Integer materialID : recipe.recipes.get(recipeName).keySet()){
				TotalerRow row = new TotalerRow(recipe.recipes.get(recipeName).get(materialID));
				row.have = calculateTotalAmount(materialID);
				
				if(row.have >= row.required){
					row.completed = "Done";
					removeAmount(materialID, row.required);
				}
				else{
					row.completed = Integer.toString(row.required - row.have);
				}

				tables.get(recipeName).add(row);
			}
		}
		return tables;
	}
	
	private void removeAmount(Integer id, int amount){
		int number = new Integer(amount);
		
		//inventory
		if(number > 0){
			for(gwClasses.GW2Character c : account.characters){
				for(Bag b : c.inventory.bags){
					for(InventoryItem i : b.inventory){
						if(i != null){
							if(i.id == id && number > 0){
								if(i.count >= number){
									i.count -= number;
									number = 0;
								}
								else{
									number -= i.count;
									i.count = 0;
								}
							}
						}
					}
				}
			}
		}
		
		
		//bank
		if(number > 0){
			for(BankItem b : account.bankItems){
				if(b != null){
					if(b.id == id && number > 0){
						if(b.count >= number){
							b.count -= number;
							number = 0;
						}
						else{
							number -= b.count;
							b.count = 0;
						}
					}
				}
			}
		}
		
		
		//Material Bank
		if(number > 0){
			for(Material m : account.materials){
				if(m.id == id && number > 0){
					if(m.count >= number){
						m.count -= number;
						number = 0;
					}
					else{
						number -= m.count;
						m.count = 0;
					}
				}
			}
		}
		
	}
	
	private int calculateTotalAmount(Integer id){
		int number = 0;
		//inventory
		for(gwClasses.GW2Character c : account.characters){
			for(Bag b : c.inventory.bags){
				for(InventoryItem i : b.inventory){
					if(i != null){
						if(i.id == id){
							number += i.count;
						}
					}
				}
			}
		}
		
		//bank	
		for(BankItem b : account.bankItems){
			if(b != null){
				if(b.id == id){
					number += b.count;
				}
			}
		}
		
		//Material Bank
		for(Material m : account.materials){
			if(m.id == id){
				number += m.count;
			}
		}
		
		
		return number;
	}


	@SuppressWarnings("unused")
	private void printTable(HashMap<String, ArrayList<TotalerRow>> tables, BaseRecipe recipe){
		int longestName = "Material Name".length();
		int longestRequirement = "Req.".length();
		int longestInventory = "Have".length();
		int longestCompleted = "Done".length();
		for(String recipeName : tables.keySet()){
			for(TotalerRow row : tables.get(recipeName)){
				longestName = row.name.length() > longestName ? row.name.length() : longestName;
				longestRequirement = Integer.toString(row.required).length() > longestRequirement ? Integer.toString(row.required).length() : longestRequirement;
				longestInventory = Integer.toString(row.have).length() > longestInventory ? Integer.toString(row.have).length() : longestInventory;
			}
		}
				
		for(int counter = 1; counter <= recipe.order.size(); counter++){
			String recipeName = recipe.recipeNames.get((Integer)counter);
			
			System.out.println(calculateDashes(longestName) + recipeName + calculateDashes(longestName));
			System.out.println(calculateSpaces("Material Name", longestName) + calculateSpacesRightAlign("Req.", longestRequirement) + calculateSpacesRightAlign("Have", longestInventory) + calculateSpacesRightAlign("Need", longestInventory));
			System.out.println(calculateDashes(longestName) + calculateDashes(recipeName) + calculateDashes(longestName));
			for(TotalerRow row : tables.get(recipeName)){
				StringBuilder sb = new StringBuilder();
				sb.append(calculateSpaces(row.name, longestName));
				sb.append(calculateSpacesRightAlign(Integer.toString(row.required), longestRequirement));
				sb.append(calculateSpacesRightAlign(Integer.toString(row.have), longestInventory));
				sb.append(calculateSpacesRightAlign(row.completed, longestCompleted));
				System.out.println(sb.toString());
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}		
	}
	
	private String calculateSpaces(String name, int longest){		
		StringBuilder sb = new StringBuilder(name + "   ");
		
		if(name.length() < longest){
			int difference = longest - name.length();
			for(int counter = 0; counter < difference; counter++){
				sb.append(" ");
			}
		}
		sb.append("|");
		return sb.toString();
		
	}
	
	private String calculateSpacesRightAlign(String s, int longest){
		StringBuilder sb = new StringBuilder("   ");
		
		if(s.length() < longest){
			int difference = longest - s.length();
			for(int counter = 0; counter < difference; counter++){
				sb.append(" ");
			}
		}
		sb.append( s + "   |");
		return sb.toString();
	}
	
	private String calculateDashes(int length){
		StringBuilder sb = new StringBuilder();
		for(int count = 0; count < length; count++){
			sb.append("-");
		}
		
		return sb.toString();
	}
	
	private String calculateDashes(String s){
		StringBuilder sb = new StringBuilder();
		for(int count = 0; count < s.length(); count++){
			sb.append("-");
		}
		
		return sb.toString();
	}

}
