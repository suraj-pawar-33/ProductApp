package com.apps.digiple.npdapp.view.nodes;

import com.apps.digiple.npdapp.impl.events.MenuEventHandler;
import com.apps.digiple.npdapp.view.CustFontColor;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class SliderButton extends Button {
//	TODO : Create Abstract ct button class
	private boolean isSelected;

	public SliderButton(String btnName, String btnText, MenuEventHandler eventHandler) {
		super("<>");
		setId(btnName);
		setOnMouseClicked(eventHandler);
		setOnMouseEntered(eventHandler);
		setOnMouseExited(eventHandler);
		init();
	}

	private void init() {
		this.setBackground(CustFontColor.BUTTON_BG.getBackGround());
		this.setFont(CustFontColor.getNormalFont());
		this.setTextFill(CustFontColor.WHITE.getPaint());
		this.setBorder(null);
		this.setPrefHeight(40);
		this.setPrefWidth(40);
		this.setMinWidth(20);
		this.setPadding(new Insets(2));
	}

	public void setMouseEntered() {
		setFont(CustFontColor.getBoldFont());
		setBackground(CustFontColor.BUTTON_H_BG.getBackGround());
	}

	public void setMouseExited() {
		setFont(CustFontColor.getNormalFont());
		setBackground(CustFontColor.BUTTON_BG.getBackGround());
	}
}
