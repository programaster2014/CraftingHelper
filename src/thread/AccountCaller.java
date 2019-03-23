package thread;

import java.util.concurrent.Callable;

import gwClasses.Account;
import network.DataRetriever;

public class AccountCaller implements Callable<Account>{
	private DataRetriever ret;	
	
	public AccountCaller(DataRetriever ret) {
		this.ret = ret;
	}
	
	@Override
	public Account call() throws Exception {
		return ret.getAccountInformation();
	}

}
