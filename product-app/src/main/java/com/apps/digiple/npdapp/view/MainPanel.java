package com.apps.digiple.npdapp.view;

import com.apps.digiple.npdapp.constant.Constants;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class MainPanel extends GridPane {

	private MenuPanel menuPanel;
	private SmallMenuPanel smallMPanel;
	private ContentPanel contentPanel;
	private double prefHeight;
	private double prefWidth;
	private double minHeight;
	private double minWidth;
	private double maxHeight;
	private double maxWidth;

	public MainPanel(MenuPanel menuPanel, SmallMenuPanel smallMPanel, ContentPanel contentPanel) {
//		TODO: add parameters for multiple MenuPanel and Content panels
		super();
		this.menuPanel = menuPanel;
		this.smallMPanel = smallMPanel;
		this.contentPanel = contentPanel;
	}
	

	public void toggleMenuPanel() {
//		TODO: find better way for changing view
		if (getChildren().contains(menuPanel)) {
			getChildren().remove(menuPanel);
			add(smallMPanel, 0, 0);
		} else if (getChildren().contains(smallMPanel)){
			getChildren().remove(smallMPanel);
			add(menuPanel, 0, 0);
		}
	}


	public void init() {
//		TODO: set dimensions as calculated percentage to match all displays. create class with final values.
		setPrefSize(prefHeight, prefWidth);
		setMinSize(minHeight, minWidth);
		setMaxSize(maxHeight, maxWidth);
		
		setId(Constants.MAINPANEL_NAME);
		
		setAlignment(Pos.TOP_LEFT);
		add(this.menuPanel, 0, 0);
		add(this.contentPanel, 1, 0);
	}
	
	public void loadContentPane(String name){
		this.menuPanel.setBtnSelected(name);
		this.contentPanel.showPane(name);
	}
}
