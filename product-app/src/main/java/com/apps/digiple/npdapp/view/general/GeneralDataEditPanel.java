package com.apps.digiple.npdapp.view.general;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.constant.Constants;
import com.apps.digiple.npdapp.dbdriver.DBHelper;
import com.apps.digiple.npdapp.models.ItemModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;

public class GeneralDataEditPanel extends GridPane {

	private static final Logger LOGGER = Logger.getLogger(GeneralDataEditPanel.class);
	protected int objectKey;
	private GridPane saveBtnPane;
	private GridPane updateBtnPane;
	private Dialog<ButtonType> infoAlert;
	private Alert errAlert;
	private Alert confAlert;
	private FieldGridPane fieldGridPane;
	private ControlsGridPane[] controlsGridPanes;


	public GeneralDataEditPanel(FieldGridPane fieldGridPane, ControlsGridPane... controlsGridPanes) {
		this.fieldGridPane = fieldGridPane;
		this.controlsGridPanes = controlsGridPanes;
		setMinSize(500, 300);
		setPrefSize(2000, 1800);
		setVgap(20);
		setHgap(20);
		setPadding(new Insets(20.0));
		setAlignment(Pos.CENTER_LEFT);
		setStyle("-fx-background-color: #C0C0C2;");
		//setStyle("-fx-background-color: BEIGE;");
		startLoaderThread();
		add(fieldGridPane, 0, 0);
		if (controlsGridPanes != null && controlsGridPanes[0] !=null ) {
			add(controlsGridPanes[0], 0, 2, 2, 1);
		}
	}
	
	public ControlsGridPane[] getControlsGridPanes() {
		return controlsGridPanes;
	}

	public void setControlsGridPanes(ControlsGridPane[] controlsGridPanes) {
		this.controlsGridPanes = controlsGridPanes;
	}

	public void loadDropdownList(){
//		TODO: Add prefilled LODDING value
//		this.proTypeMap = getProTypeMap();
	}


	protected void clearAllText() {
//		TODO: Clear all fields
		showSaveView();
	}

	public void printReport(ItemModel itemModel) {
	}

	private void startLoaderThread() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					loadDropdownList();					
				} catch (Exception e) {
					LOGGER.info("ERROR : loading dropdown list > " + this.getClass().getSimpleName() + " : " + e.getLocalizedMessage());
				}
			}
		});
		t.start();
	}

	public int getObjectKey() {
		return objectKey;
	}

	public void setObjectKey(int objectKey) {
		this.objectKey = objectKey;
	}

	public void showUpdateView() {
		if (getChildren().contains(saveBtnPane)) {
			getChildren().remove(saveBtnPane);
		}
		if (!getChildren().contains(updateBtnPane)) {
			add(updateBtnPane, 0, 2, 2, 1);
		}
	}

	public void showSaveView() {
		if (getChildren().contains(updateBtnPane)) {
			getChildren().remove(updateBtnPane);
		}
		if (!getChildren().contains(saveBtnPane)) {
			add(saveBtnPane,0, 2, 2, 1);
		}
	}

	
	public LocalDate getLocalDate(String text) {
		return LocalDate.parse(text, DateTimeFormatter.ofPattern(Constants.FULL_DATE_FORMAT));
	}

	public void showErrAlert(String title, String message) {
		errAlert.setTitle(title);
		errAlert.setHeaderText(null);
		errAlert.setContentText(message);
		if (errAlert.getOwner() != null) {
			errAlert.initOwner(this.getScene().getWindow());
		}
		errAlert.show();
	}
	
	public void showInfoAlert(String title, String message) {
		infoAlert.setTitle(title);
		infoAlert.setHeaderText(null);
		infoAlert.setContentText(message);
		if (infoAlert.getOwner() != null) {
			infoAlert.initOwner(this.getScene().getWindow());
		}
		infoAlert.show();
	}
	
	public void showConfAlert(String title, String message) {
		confAlert.setTitle(title);
		confAlert.setHeaderText(null);
		confAlert.setContentText(message);
		if (confAlert.getOwner() != null) {
			confAlert.initOwner(this.getScene().getWindow());
		}
		confAlert.show();
	}

	protected ChoiceBox<String> getChoiceBox() {
		ChoiceBox<String> choiceBox = new ChoiceBox<String>();
		choiceBox.setPrefSize(300, 30);
		choiceBox.setMinSize(100, 20);
		return choiceBox;
	}


	
	protected static HashMap<String, Integer> getStatusMap(){
		HashMap<String, Integer> map = new HashMap<>();
		try {
			ResultSet rs = DBHelper.getStatusList();
			while (rs.next()) {
				map.put(rs.getString(2), rs.getInt(1));
			}
			return map;
		} catch (Exception e) {
			map.put(e.getLocalizedMessage(), 0);
		}
		return map;
	}

	protected static HashMap<String, Integer> getOrderTypeMap(){
		HashMap<String, Integer> map = new HashMap<>();
		try {
			ResultSet rs = DBHelper.getOrderType();
			while (rs.next()) {
				map.put( rs.getString(2), rs.getInt(1));
			} 
		} catch (Exception e) {
			map.put(e.getLocalizedMessage(), 0);
		}
		return map;
	}

	protected static HashMap<String, Integer> getBankMap(){
		HashMap<String, Integer> map = new HashMap<>();
		try {
			ResultSet rs = DBHelper.getBankNameList();
			while (rs.next()) {
				map.put( rs.getString(2) + " - " + rs.getString(3) , rs.getInt(1));
			}
		} catch (Exception e) {
			map.put(e.getLocalizedMessage(), 0);
		}
		return map;
	}
	
	protected static HashMap<String, Integer> getProTypeMap(){
		HashMap<String, Integer> map = new HashMap<>();
		try {
			ResultSet rs = DBHelper.getProductType();
			while (rs.next()) {
				map.put( rs.getString(2), rs.getInt(1));
			}
		} catch (Exception e) {
			map.put(e.getLocalizedMessage(), 0);
		}
		return map;
	}

	public FieldGridPane getfieldGridPane() {
		return fieldGridPane;
	}

}
