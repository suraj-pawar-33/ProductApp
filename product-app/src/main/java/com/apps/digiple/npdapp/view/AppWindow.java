package com.apps.digiple.npdapp.view;

import com.apps.digiple.npdapp.services.ServiceFactory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppWindow extends Application{

	@Override
	public void start(Stage stage) {
		
		MainPanel panel = ServiceFactory.getMainPanelInstance();
		Scene scene = new Scene(panel);

		stage.setTitle("Product Data");
		stage.setScene(scene);

		stage.show();
	}
	
	public void launchApp(String args[]){
		launch(args);
	}
	    
	


}
