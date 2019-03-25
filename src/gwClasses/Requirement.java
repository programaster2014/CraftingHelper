package gwClasses;

public class Requirement {
	public String materialName;
	public int number;
	public int coin;
	
	public Requirement(String name, int number, int coin){
		this.materialName = name;
		this.number = number;
		this.coin = coin;
	}
	
	public Requirement(String name, int number){
		this.materialName = name;
		this.number = number;
		this.coin = -1;
	}
}
