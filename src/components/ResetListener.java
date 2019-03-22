package components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetListener implements ActionListener{
	private TabbedPane tabbedPane;
	
	public ResetListener(TabbedPane tabbedPane){
		super();
		this.tabbedPane = tabbedPane;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.tabbedPane.rebuildTable();
	}
}
