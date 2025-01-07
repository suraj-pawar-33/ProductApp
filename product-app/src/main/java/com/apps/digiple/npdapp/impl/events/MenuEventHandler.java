package com.apps.digiple.npdapp.impl.events;

import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.constant.Constants;
import com.apps.digiple.npdapp.view.MainPanel;
import com.apps.digiple.npdapp.view.nodes.CtMenuButton;
import com.apps.digiple.npdapp.view.nodes.SliderButton;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MenuEventHandler implements EventHandler<MouseEvent> {
	private static final Logger LOGGER = Logger.getLogger(MenuEventHandler.class);

	@Override
	public void handle(MouseEvent event) {
		if (event.getSource() instanceof CtMenuButton) {
			CtMenuButton btn = (CtMenuButton) event.getSource();
			if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
				btn.setSelected();
				MainPanel mainPanel = (MainPanel) btn.getScene().lookup("#" + Constants.MAINPANEL_NAME);
				mainPanel.loadContentPane(btn.getId());
			} else if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
				btn.setMouseEntered();
			} else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
				btn.setMouseExited();
			}
		} else if (event.getSource() instanceof SliderButton) {
			SliderButton btn = (SliderButton) event.getSource();
			if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
				MainPanel mainPanel = (MainPanel) btn.getScene().lookup("#" + Constants.MAINPANEL_NAME);
				mainPanel.toggleMenuPanel();
			} else if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
				btn.setMouseEntered();
			} else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
				btn.setMouseExited();
			}
		}
	}

}
