package gwClasses;

import java.util.ArrayList;

import bank.BankItem;

public class Account {
	public  ArrayList<GW2Character> characters;
	
	public Material[] materials;
	public BankItem[] bankItems;
	
	public Account(){
		characters = new ArrayList<>();
	}
}
