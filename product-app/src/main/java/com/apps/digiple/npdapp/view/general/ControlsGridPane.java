package com.apps.digiple.npdapp.view.general;

import com.apps.digiple.npdapp.view.CustButton;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControlsGridPane extends GridPane {

	public ControlsGridPane(String controlsName, String... names) {
		super();
		setId(controlsName);
		setStyle("-fx-background-color: #C1C0C2;");
		setHgap(10);
		addAllNodes(names);
	}

	private void addAllNodes(String[] names) {
//		TODO: Add multiple rows of buttons
		for (int i = 0; i < names.length; i=i+2) {
			Button btn = CustButton.getCustButton(names[i], names[i+1]);
			add(btn, i, 1);
		}
	}

	

}
