package components;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class GwLinkListener implements ActionListener{

	private URL url;
	
	public GwLinkListener(String name){
		super();
		try {
			this.url = new URL("https://wiki.guildwars2.com/wiki/" + name.replace(" ", "_"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Desktop.isDesktopSupported()) {
		      try {
		        Desktop.getDesktop().browse(url.toURI());
		      } 
		      catch (IOException | URISyntaxException e) {
		    	  /* TODO: error handling */ 
		      }
		} 
		else { 
			/* TODO: error handling */ 
		}
	}

}
