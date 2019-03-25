package totaler;

import java.util.ArrayList;
import bank.BankItem;
import gwClasses.Account;
import gwClasses.Bag;
import gwClasses.Material;
import inventory.InventoryItem;
import recipes.BaseRecipe;

public class Totaler {
	public Account account;
	public int totalCost;
	
	public Totaler(Account account){
		this.account = account;
		this.totalCost = 0;
	}
	
	public ArrayList<TotalerRow> calculate(BaseRecipe recipe){
		ArrayList<TotalerRow> tables = new ArrayList<>();
		
		for(Integer materialID : recipe.recipe.keySet()){
			TotalerRow row = new TotalerRow(recipe.recipe.get(materialID));
			row.have = calculateTotalAmount(materialID);
			
			if(row.have >= row.required){
				row.completed = "Done";
				removeAmount(materialID, row.required);
			}
			else{
				row.completed = Integer.toString(row.required - row.have);
				totalCost += (recipe.recipe.get(materialID).coin * (row.required - row.have));
			}

			tables.add(row);
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
}
