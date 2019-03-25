package components;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;

public class LabelManager {
	public ArrayList<JButton> materialNames;
	
	//material name, jlabel
	public HashMap<String, JLabel> requiredLabels;
	public HashMap<String, JLabel> haveLabels;
	public HashMap<String, JLabel> needLabels;
	public HashMap<String, JLabel> goldLabels;
	public HashMap<String, JLabel> silverLabels;
	public HashMap<String, JLabel> copperLabels;
	
	public LabelManager() {
		materialNames = new ArrayList<>();
		requiredLabels = new HashMap<>();
		haveLabels = new HashMap<>();
		needLabels = new HashMap<>();
		goldLabels = new HashMap<>();
		silverLabels = new HashMap<>();
		copperLabels = new HashMap<>();
	}
}
