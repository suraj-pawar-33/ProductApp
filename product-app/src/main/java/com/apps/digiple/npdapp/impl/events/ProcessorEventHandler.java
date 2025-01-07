package com.apps.digiple.npdapp.impl.events;

import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.constant.Constants;
import com.apps.digiple.npdapp.impl.GeneralDataProcessor;
import com.apps.digiple.npdapp.models.FieldInfo;
import com.apps.digiple.npdapp.view.general.ControlsGridPane;
import com.apps.digiple.npdapp.view.general.FieldGridPane;
import com.apps.digiple.npdapp.view.general.GeneralDataEditPanel;
import com.apps.digiple.npdapp.view.general.GeneralTablePanel;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class ProcessorEventHandler implements EventHandler<MouseEvent> {
	
	private static final Logger LOGGER = Logger.getLogger(GeneralDataEditPanel.class);
	
	private GeneralDataProcessor processor;

	protected GeneralTablePanel tablePanel;

	protected FieldGridPane fieldGridPane;

	protected GeneralDataEditPanel editPanel;

	public ProcessorEventHandler(GeneralDataEditPanel editPanel, GeneralTablePanel tablePanel) {
		this.tablePanel = tablePanel;
		this.fieldGridPane = editPanel.getfieldGridPane();
		this.editPanel = editPanel;
	}

	public ProcessorEventHandler(String dbTableName, FieldInfo[] fieldInfoList) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
			Node btn = (Node) event.getSource();
			switch (btn.getId()) {
			case Constants.EDITPANEL_SAVE_BTN:
				processor.save();
				break;
			case Constants.EDITPANEL_UPDATE_BTN:
				processor.update();
				break;
			case Constants.EDITPANEL_DELETE_BTN:
				processor.delete();
				break;
			case Constants.EDITPANEL_CLEAR_BTN:
				processor.clear();
				break;
			case "DataTable":
				processor.handleRowSelection();
				break;
			case "ReloadBtn":
				processor.handleReload();
				break;
			case "PrintBtn":
				processor.handlePrint();
				break;

			default:
				LOGGER.error(btn.getId() + " Clicked");
				break;
			}

		}

	}

}
