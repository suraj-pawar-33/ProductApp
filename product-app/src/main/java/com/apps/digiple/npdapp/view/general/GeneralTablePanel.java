package com.apps.digiple.npdapp.view.general;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.impl.GeneralDataProcessor;
import com.apps.digiple.npdapp.models.FieldInfo;
import com.apps.digiple.npdapp.models.GeneralModel;
import com.apps.digiple.npdapp.models.ItemModel;
import com.apps.digiple.npdapp.table.view.CustTableView;
import com.apps.digiple.npdapp.view.CustButton;
import com.apps.digiple.npdapp.view.CustLabel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class GeneralTablePanel extends GridPane {
	private static final Logger LOGGER = Logger.getLogger(GeneralTablePanel.class);

	protected CustTableView<GeneralModel> table;
	protected ObservableList<? extends ItemModel> filteredData = FXCollections.observableArrayList();
	private TextField field;
	private Button btn;
	private Button printBtn;
	private GridPane filPane;

	private String dbTableName;

	private FieldInfo[] fieldInfoList;
	
	public GeneralTablePanel(String dbTableName, FieldInfo... fieldInfoList) {
		this.dbTableName = dbTableName;
		this.fieldInfoList = fieldInfoList;
		
		setMinSize(500, 300);
		setPrefSize(2000, 1800);
		setAlignment(Pos.CENTER);
		setVgap(5);
		setHgap(5);
		table = new CustTableView<GeneralModel>("DataTable");
		table.setId("DataTable");
        table.focusedProperty().addListener(getFocusListener());
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadColumns(fieldInfoList);
		this.btn = createReloadBtn();
		this.printBtn = createPrintBtn();
		this.filPane = getFilterPanel();
		add(filPane, 0, 0);
		add(getVbox(table), 0, 1);
	}
	
	public String getDbTableName() {
		return dbTableName;
	}

	public void setDbTableName(String dbTableName) {
		this.dbTableName = dbTableName;
	}

	public FieldInfo[] getFieldInfoList() {
		return fieldInfoList;
	}

	public void setFieldInfoList(FieldInfo[] fieldInfoList) {
		this.fieldInfoList = fieldInfoList;
	}

	public void refresh(ObservableList<GeneralModel> data) {
		table.setGenItems(data);
	}

	

	@SuppressWarnings("unchecked")
	protected void loadColumns(FieldInfo... fieldInfoList) {
		for (int i = 0; i < fieldInfoList.length; i++) {
			TableColumn<GeneralModel, String> keyCol = getTableColumn(fieldInfoList[i].getLableText(), fieldInfoList[i].getPropertyName(), String.class, GeneralModel.class);
			((TableView<GeneralModel>) table).getColumns().add(keyCol);
		}
        table.setEditable(true);
        table.setPrefSize(1800, 1800);
        table.setMinSize(500, 300);
 
	}
	
	@SuppressWarnings("unchecked")
	protected ObservableList<GeneralModel> loadFilteredData(String str) {
//		TODO : filter using the db data. add search btn
		ObservableList<GeneralModel> newData = FXCollections.observableArrayList();
		LOGGER.error("loadFilteredData not ready...");
		return newData;
	}

	private ChangeListener<? super Boolean> getFocusListener() {
		return new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if (arg2.booleanValue()) {
					showPrintBtn(true);
				} 
			}
		};
	}

	private GridPane getFilterPanel() {
		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setPadding(new Insets(0, 0, 0, 10));
		pane.add(createSearchBox(), 0, 0);
		pane.add(btn, 1, 0);
		return pane;
	}
	
	public TableView<? extends ItemModel> getTable() {
		return table;
	}


	protected void filterData (String str) {
		filteredData.clear();
		filteredData = loadFilteredData(str);
		refreshFildata();
	}
	
	public void disableSearchField(){
		field.disableProperty().set(true);
	}

	public void setPrintButtonDisabled(boolean disable){
		printBtn.setDisable(disable);
	}
	
	public void enableSearchField(){
		field.disableProperty().set(false);
	}
	
	private TextField createSearchBox() {
		field = new TextField();
		field.setId("search_field");
		field.setPrefSize(300, 30);
		field.setMinSize(50, 25);
		field.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
				filterData(newValue);
			}

		});
		return field;
	}
	
	private Button createReloadBtn() {
		Button button = CustButton.getCustButton("ReloadBtn", "");
		button.setShape(new Circle(15));
		button.setPrefSize(20, 20);
		button.setMinSize(10, 10);
		return button;
	}
	
	private Button createPrintBtn() {
		Button button = CustButton.getCustButton("PrintBtn", "Print");
		button.setPrefSize(100, 20);
		button.setMinSize(10, 10);
		button.setPadding(new Insets(0));
		return button;
	}

	protected VBox getVbox(Node node) {
		final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(0, 10, 10, 10));
        vbox.getChildren().addAll(node);
		return vbox;
	}


	public boolean isSelected() {
		return !table.getSelectionModel().isEmpty();
	}
	
	public int getSelectedIndex() {
		return table.getSelectionModel().getSelectedIndex();
	}
	
	public ItemModel getSelectedItem() {
		return table.getSelectionModel().getSelectedItem();
	}

	protected <K, T> TableColumn<K, T> getTableColumn(String string, String string2, Class<T> clazz, Class<K> clazz2) {
		TableColumn<K, T> col = new TableColumn<K, T>(string);
        col.setMinWidth(100);
        col.setId(string2);
        col.setResizable(true);
        col.setEditable(true);
        col.setCellValueFactory(
                new PropertyValueFactory<K, T>(string2));
		return col;
	}
	
	protected void showAlert(AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(this.getScene().getWindow());
		alert.show();
	}

	public void refreshFildata() {
		table.setGenItems((ObservableList<GeneralModel>) filteredData);
	}
	
	public void setPlaceHolder(String msg) {
		table.setPlaceholder(CustLabel.createLabel(msg));
	}

	public void addNewRow(int key, GeneralModel model) {
		model.setKey(key);
		table.getItems().add(0,  model);
	}
	
	public void deleteRow() {
		table.getItems().remove(table.getSelectionModel().getSelectedIndex());
	}
	public void updateRow(GeneralModel model) {
		ObservableList<GeneralModel> items = ((TableView<GeneralModel>) table).getItems();
		for (Iterator<?> iterator = items.iterator(); iterator.hasNext();) {
			GeneralModel itemModel = (GeneralModel) iterator.next();
			if (itemModel.getKey() == model.getKey()) {
				((TableView<GeneralModel>) table).getItems().remove(itemModel);
				((TableView<GeneralModel>) table).getItems().add(0,  model);
				return;
			}
		}
	}

	public void showPrintBtn(boolean value) {
		if (!this.filPane.getChildren().contains(printBtn) && value) {
			this.filPane.add(printBtn, 2, 0);
		} else if (this.filPane.getChildren().contains(printBtn) && !value) {
			this.filPane.getChildren().remove(printBtn);
		}
	}

	public void setEventHandler(GeneralDataProcessor generalDataProcessor) {
		field.setOnMouseClicked(generalDataProcessor);
		btn.setOnMouseClicked(generalDataProcessor);
		printBtn.setOnMouseClicked(generalDataProcessor);
	}

}
