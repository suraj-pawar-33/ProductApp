package com.apps.digiple.npdapp.table.view;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class CustTableView<GeneralModel> extends TableView<GeneralModel> {
	
	public CustTableView(String name) {
		super();
		setId(name);
	}

	public void setGenItems(ObservableList<GeneralModel> data) {
		super.setItems(data);
	}
}
