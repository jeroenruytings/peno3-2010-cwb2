package be.kuleuven.cw.peno3;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.ariadne.config.PropertiesManager;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;


public class WebServiceLoader {

	public static void main(String[] args) throws IOException {

		PropertiesManager.getInstance().init("config/config.properties");

		final String baseUri = "http://localhost:9876/";
		final Map<String, String> initParams = new HashMap<String, String>();

		initParams.put("com.sun.jersey.config.property.packages", "be.kuleuven.cw.peno3");

		System.out.println("Starting grizzly server...");
		SelectorThread threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);
		
		System.out.println("Available at : " + baseUri);
		
		System.in.read();
		threadSelector.stopEndpoint();
		System.exit(0);
	}    
}
