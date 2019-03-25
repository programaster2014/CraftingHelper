package thread;

import java.util.concurrent.Callable;

import network.DataRetriever;
import tree.GWTree;

public class TreeCaller implements Callable<GWTree>{
	private Integer orderID;
	private DataRetriever ret;
	
	public TreeCaller(Integer orderID, DataRetriever ret) {
		this.orderID = orderID;
		this.ret = ret;
	}
	
	@Override
	public GWTree call() throws Exception {
		GWTree tree = new GWTree(orderID, ret);
		tree.build();
		return tree;
	}

}
