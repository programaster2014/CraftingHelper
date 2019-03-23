package thread;

import java.util.concurrent.Callable;

import network.DataRetriever;
import tree.GWTree;

public class TreeCaller implements Callable<GWTree>{

	private Integer count;
	private Integer orderID;
	private DataRetriever ret;
	
	public TreeCaller(Integer count, Integer orderID, DataRetriever ret) {
		this.orderID = orderID;
		this.ret = ret;
		this.count = count;
	}
	
	@Override
	public GWTree call() throws Exception {
		GWTree tree = new GWTree(orderID, ret);
		tree.Count = count;
		tree.build();
		return tree;
	}

}
