package totaler;

import gwClasses.Requirement;

public class TotalerRow {
	public String name;
	public int required;
	public int have;
	public String completed;
	public int gold;
	public int silver;
	public int copper;
	
	public TotalerRow(Requirement r){
		this.name = r.materialName;
		this.required = r.number;
		
		if(r.coin != -1) {
			int coin = r.coin;

			//copper logic
			this.copper = coin%100;
			
			//silver logic
			this.silver = ((coin - this.copper)%10000)/100;
			
			//gold logic
			this.gold = (coin - this.copper - this.silver)/10000;
			
		}
	}
}
