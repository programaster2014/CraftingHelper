package tree;

import java.util.ArrayList;

import gwClasses.Item;

public class Node {
	private Item data;
	private int count;
	private int totalCount;
	private ArrayList<Node> children;
	
	public Node(Item data, int count, int parentCount) {
		this.data = data;
		this.count = count;
		this.totalCount = this.count * parentCount;
		this.children = new ArrayList<Node>();
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
