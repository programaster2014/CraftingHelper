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
	}
}
