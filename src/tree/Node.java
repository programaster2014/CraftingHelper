package tree;

import java.util.ArrayList;

import gwClasses.Item;

public class Node {
	private Item data;
	private int coin;
	private int count;
	private int totalCount;
	private ArrayList<Node> children;
	
	public Node(Item data, int count, int parentCount, int coin) {
		this.data = data;
		this.count = count;
		this.totalCount = this.count * parentCount;
		this.children = new ArrayList<Node>();
		this.coin = coin;
	}
	
	public int getCoin() {
		return this.coin;
	}
	
	public void addChild(Node child) {
		this.children.add(child);
	}
	
	public Item getData() {
		return this.data;
	}
	
	public ArrayList<Node> getChildren(){
		return this.children;
	}
	
	public boolean isValidNode() {
		return (this.data != null);
	}
	
	public int getCount() {
		return this.count;
	}
	
	public int getTotalCount() {
		return this.totalCount;
	}
}
