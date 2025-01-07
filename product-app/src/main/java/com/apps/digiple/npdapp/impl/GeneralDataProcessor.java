package com.apps.digiple.npdapp.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.constant.Constants;
import com.apps.digiple.npdapp.dbdriver.DatabaseDriver;
import com.apps.digiple.npdapp.impl.events.ProcessorEventHandler;
import com.apps.digiple.npdapp.models.FieldInfo;
import com.apps.digiple.npdapp.models.GeneralModel;
import com.apps.digiple.npdapp.models.ItemModel;
import com.apps.digiple.npdapp.services.ServiceFactory;
import com.apps.digiple.npdapp.view.general.ControlsGridPane;
import com.apps.digiple.npdapp.view.general.FieldGridPane;
import com.apps.digiple.npdapp.view.general.GeneralDataEditPanel;
import com.apps.digiple.npdapp.view.general.GeneralTablePanel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GeneralDataProcessor extends ProcessorEventHandler {
	private static final Logger LOGGER = Logger.getLogger(GeneralDataProcessor.class);


	private ObservableList<GeneralModel> data = FXCollections.observableArrayList();
	protected int objectKey;

	private ControlsGridPane saveButtons;

	private ControlsGridPane updateButtons;

	public GeneralDataProcessor( String dbTableName, FieldInfo... fieldInfoList) {
		super(dbTableName, fieldInfoList);
		tablePanel = new GeneralTablePanel(dbTableName, fieldInfoList);
		fieldGridPane = new FieldGridPane(fieldInfoList);
		saveButtons = new ControlsGridPane("SaveButtons", Constants.EDITPANEL_SAVE_BTN, "Save",
				Constants.EDITPANEL_CLEAR_BTN, "Clear");
		updateButtons = new ControlsGridPane("UpdateButtons", Constants.EDITPANEL_UPDATE_BTN, "Update",
				Constants.EDITPANEL_CLEAR_BTN, "Clear", Constants.EDITPANEL_DELETE_BTN, "Delete");
		editPanel = new GeneralDataEditPanel(fieldGridPane, saveButtons, updateButtons);
	}

	public GeneralDataProcessor(GeneralDataEditPanel editPanel, GeneralTablePanel tablePanel) {
		super(editPanel, tablePanel);
		super.tablePanel.setEventHandler(this);
		for ( ControlsGridPane controlsGridPane : super.editPanel.getControlsGridPanes() ) {
			controlsGridPane.getChildren().forEach(node -> {
				node.setOnMouseClicked(this);
			});
		}
	}
	
	public void loadDBData() {
		data.clear();
		try {
			
			DatabaseDriver driver = ServiceFactory.getdbInstance();
			List<GeneralModel> list = driver.selectGeneralQuery(tablePanel.getDbTableName(), tablePanel.getFieldInfoList());
			for (GeneralModel generalModel : list) {
				data.add(generalModel);
			}

		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	public void loadRow(ItemModel row) {
		loadTextFields((GeneralModel) row);
	}

	public GeneralTablePanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(GeneralTablePanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public GeneralDataEditPanel getEditPanel() {
		return editPanel;
	}

	public void setEditPanel(GeneralDataEditPanel editPanel) {
		this.editPanel = (GeneralDataEditPanel) editPanel;
	}

	private void loadTextFields(GeneralModel GeneralModel) {

	}

	public ObservableList<GeneralModel> getData() {
		return this.data;
	}

	public GridPane getPanel() {
		GridPane pane = new GridPane();
		pane.add(editPanel, 0, 0);
		pane.add(tablePanel, 0, 1);
		return pane;
	}


	public void refreshView() {
		// loadDBData();
		tablePanel.refresh(data);
	}



	public void clear() {
		// TODO Auto-generated method stub
		LOGGER.info("Clear");
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		LOGGER.info("Delete");
		
	}

	public void update() {
		// TODO Auto-generated method stub
		LOGGER.info("Update");
		
	}

	public void save() {
		// TODO Auto-generated method stub
		LOGGER.info("Save");
	}

	public void handleRowSelection() {
		if (tablePanel.isSelected()) {
			loadRow(tablePanel.getSelectedItem());
			editPanel.showUpdateView();
		}
		LOGGER.info("Selecting");
	}

	public void handleReload() {
		loadDBData();
		tablePanel.refresh(data);
		tablePanel.showPrintBtn(false);
		editPanel.loadDropdownList();
		LOGGER.info("Reload");
	}

	public void handlePrint() {
		if (tablePanel.isSelected()) {
			editPanel.printReport(tablePanel.getSelectedItem());
		}
		LOGGER.info("Printing");
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
			Node btn = (Node) event.getSource();
			switch (btn.getId()) {
			case Constants.EDITPANEL_SAVE_BTN:
				save();
				break;
			case Constants.EDITPANEL_UPDATE_BTN:
				update();
				break;
			case Constants.EDITPANEL_DELETE_BTN:
				delete();
				break;
			case Constants.EDITPANEL_CLEAR_BTN:
				clear();
				break;
			case "DataTable":
				handleRowSelection();
				break;
			case "ReloadBtn":
				handleReload();
				break;
			case "PrintBtn":
				handlePrint();
				break;

			default:
				LOGGER.error(btn.getId() + " Clicked");
				break;
			}

		}

	}


}
