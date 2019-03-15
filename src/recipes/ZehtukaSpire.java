package recipes;

import java.util.HashMap;

import gwClasses.Requirement;
import network.DataRetriever;

public class ZehtukaSpire extends BaseRecipe {
	
	//public HashMap<Integer, Requirement> zojjaSpire;
	//public HashMap<Integer, Requirement> mordantInscription;
	public HashMap<Integer, Requirement> statChange;
	
	
	public ZehtukaSpire(DataRetriever ret){
		super(ret);
		this.name = "Zehtuka's Spire";
		
		/*
		zojjaSpire = new HashMap<>();
		zojjaSpire.put(19725, new Requirement("Ancient Wood Log", 30));
		zojjaSpire.put(46752, new Requirement("Augur's Stone", 1));
		zojjaSpire.put(46733, new Requirement("Dragonite Ore", 500));
		zojjaSpire.put(19722, new Requirement("Elder Wood Log", 1350));
		zojjaSpire.put(46735, new Requirement("Empyreal Fragment", 500));
		zojjaSpire.put(46681, new Requirement("Glob of Dark Matter", 10));
		zojjaSpire.put(19721, new Requirement("Glob of Ectoplasm", 17));
		zojjaSpire.put(19724, new Requirement("Hard Wood Log", 540));
		zojjaSpire.put(19699, new Requirement("Iron Ore", 270));
		zojjaSpire.put(19750, new Requirement("Lump of Coal", 30));
		zojjaSpire.put(19924, new Requirement("Lump of Primordium", 60));
		zojjaSpire.put(19700, new Requirement("Mithril Ore", 300));
		zojjaSpire.put(19925, new Requirement("Obsidian Shard", 30));
		zojjaSpire.put(19701, new Requirement("Orichalcum Ore", 30));
		zojjaSpire.put(46731, new Requirement("Pile of Bloodstone Dust", 500));
		zojjaSpire.put(24277, new Requirement("Pile of Crystalline Dust", 20));
		zojjaSpire.put(19702, new Requirement("Platinum Ore", 120));
		zojjaSpire.put(19727, new Requirement("Seasoned Wood Log", 270));
		zojjaSpire.put(19726, new Requirement("Soft Wood Log", 360));
		zojjaSpire.put(46747, new Requirement("Thermocatalytic Reagent", 370));
		zojjaSpire.put(24295, new Requirement("Vial of Powerful Blood", 5));
		
		
		mordantInscription = new HashMap<>();
		mordantInscription.put(19701, new Requirement("Orichalcum Ore", 30));
		mordantInscription.put(19725, new Requirement("Ancient Wood Log", 30));
		mordantInscription.put(19721, new Requirement("Glob of Ectoplasm", 5));
		mordantInscription.put(24330, new Requirement("Crystal Lodestone", 3));
		mordantInscription.put(83103, new Requirement("Eye of Kormir", 30));
		mordantInscription.put(83284, new Requirement("Ley-Infused Sand", 9));
		mordantInscription.put(83757, new Requirement("Congealed Putrescence", 60));
		*/
		
		statChange = new HashMap<>();
		statChange.put(83974, new Requirement("Mordant Inscription", 1));
		statChange.put(69953, new Requirement("Anthology of Heroes", 1));
		statChange.put(19721, new Requirement("Glob of Ectoplasm", 5));
		statChange.put(46773, new Requirement("Zojja's Spire", 1));
		
		
		//this.recipes.put("Zojja's Spire", zojjaSpire);
		//this.recipes.put("Mordrant Inscription", mordantInscription);
		//this.recipes.put("Stat Change", statChange);
		
		this.order.put(1, 46773);
		this.order.put(2, 83974);
		this.order.put(3, -999);
	}
}

