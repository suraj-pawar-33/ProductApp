package com.apps.digiple.npdapp.view.nodes;

import com.apps.digiple.npdapp.impl.events.MenuEventHandler;
import com.apps.digiple.npdapp.view.CustFontColor;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class CtMenuButton extends Button {
//	TODO : Create Abstract ct button class
	private boolean isSelected;

	public CtMenuButton(String btnName, String btnText, MenuEventHandler eventHandler) {
		super(btnText);
		setId(btnName);
		setOnMouseClicked(eventHandler);
		setOnMouseEntered(eventHandler);
		setOnMouseExited(eventHandler);
		init();
	}

	private void init() {
		setBackground(CustFontColor.BUTTON_BG.getBackGround());
		setFont(CustFontColor.getNormalFont());
		setTextFill(CustFontColor.WHITE.getPaint());
		setBorder(null);
		setPrefHeight(30);
		setPrefWidth(1000);
		setMaxWidth(600);
	}

	public void setMouseEntered() {
		if (!isSelected()) {
			setFont(CustFontColor.getBoldFont());
			setBackground(CustFontColor.BUTTON_H_BG.getBackGround());
		}
	}

	public void setMouseExited() {
		if (!isSelected()) {
			setFont(CustFontColor.getNormalFont());
			setBackground(CustFontColor.BUTTON_BG.getBackGround());
		}
	}

	public void setSelected() {
		this.setTextFill(CustFontColor.BLACK.getPaint());
		this.setFont(CustFontColor.getBoldFont());
		this.isSelected = true;
		this.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));

	}

	public void setUnselected() {
		this.setTextFill(CustFontColor.WHITE.getPaint());
		this.setFont(CustFontColor.getNormalFont());
		this.isSelected = false;
		this.setBackground(new Background(new BackgroundFill(Color.rgb(50, 72, 168), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public boolean isSelected() {
		return isSelected;
	}


}
