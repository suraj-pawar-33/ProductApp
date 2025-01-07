package com.apps.digiple.npdapp.view;


import com.apps.digiple.npdapp.view.nodes.SliderButton;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class SmallMenuPanel extends AnchorPane {
//	TODO : Create Abstract ct class

	public SmallMenuPanel(SliderButton slidButton) {
		super();
		init(slidButton);
	}

	private Button loadResizeBtn(SliderButton slidButton) {
		AnchorPane.setTopAnchor(slidButton, 10.0);
		AnchorPane.setRightAnchor(slidButton, 5.0);
		return slidButton;
	}

	public void init(SliderButton slidButton){
		setMinSize(50, 500);
		setPrefSize(60, 1200);
		setId("smallMenuPanel");

		getChildren().add(loadResizeBtn(slidButton));
		setBackground(CustFontColor.MENU_BG.getBackGround());
	}


}
