package com.apps.digiple.npdapp.view;

import com.apps.digiple.npdapp.view.nodes.CtMenuButton;
import com.apps.digiple.npdapp.view.nodes.SliderButton;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MenuPanel extends AnchorPane {
	private CtMenuButton[] menuButton;

	public MenuPanel( SliderButton slidButton, CtMenuButton... menuButton) {
		super();
		this.menuButton = menuButton;
		init(slidButton, menuButton);
	}

	private GridPane loadButtonPane(CtMenuButton... menuButton) {
		GridPane pane = new GridPane();
		AnchorPane.setLeftAnchor(pane, 0.0);
		AnchorPane.setRightAnchor(pane, 0.0);
		AnchorPane.setTopAnchor(pane, 200.0);
		
		for (int i = 0; i < menuButton.length; i++) {
			CtMenuButton ctMenuButton = menuButton[i];
			pane.add(ctMenuButton, 0, i);
		}
		return pane;
	}

	private Button loadResizeBtn(Button slidButton) {
		AnchorPane.setTopAnchor(slidButton, 10.0);
		AnchorPane.setRightAnchor(slidButton, 10.0);
		return slidButton;
	}

	public void init(Button slidButton, CtMenuButton... menuButton){
		setMinSize(200, 500);
		setPrefSize(300, 1000);
		setId("menuPanel");

		getChildren().addAll(loadResizeBtn(slidButton), loadButtonPane(menuButton));
		setBackground(CustFontColor.MENU_BG.getBackGround());
	}

	public void setBtnSelected(String name) {
		for (CtMenuButton ctMenuButton : menuButton) {
			if (!ctMenuButton.getId().equals(name)) {
				ctMenuButton.setUnselected();
			}
		}
	}
}
