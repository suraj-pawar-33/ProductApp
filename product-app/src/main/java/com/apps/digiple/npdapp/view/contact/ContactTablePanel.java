package com.apps.digiple.npdapp.view.contact;

import java.util.Iterator;

import com.apps.digiple.npdapp.models.ContactModel;
import com.apps.digiple.npdapp.models.ItemModel;
import com.apps.digiple.npdapp.view.TablePanel;
import com.mysql.cj.util.StringUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ContactTablePanel extends TablePanel {

	
	public ContactTablePanel(ObservableList<ContactModel> data, EventHandler<MouseEvent> eventHandler) {
		super(eventHandler, new TableView<ContactModel>());
		this.data =  data;
	}

	@SuppressWarnings("unchecked")
	protected void loadColumns() {
 
        table.setEditable(true);
 
        TableColumn<ContactModel, Long> keyCol = getTableColumn("Contact Key", "contactKey", Long.class, ContactModel.class);
        TableColumn<ContactModel, String> bankNameCol = getTableColumn("Contact Name", "contactName", String.class, ContactModel.class);
        TableColumn<ContactModel, String> shNameCol = getTableColumn("Short Name", "shortName", String.class, ContactModel.class);
        TableColumn<ContactModel, String> brNameCol = getTableColumn("Branch Name","branchName", String.class, ContactModel.class);
        TableColumn<ContactModel, String> addressCol = getTableColumn("Address", "address", String.class, ContactModel.class);

        ((TableView<ContactModel>) table).setItems((ObservableList<ContactModel>) data);
        ((TableView<ContactModel>) table).getColumns().addAll(keyCol, bankNameCol, shNameCol, brNameCol, addressCol);
        table.setPrefSize(1800, 1800);
        table.setMinSize(500, 300);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected ObservableList<ContactModel> loadFilteredData(String str) {
		ObservableList<ContactModel> newData = FXCollections.observableArrayList();
		if (StringUtils.isNullOrEmpty(str) || StringUtils.isEmptyOrWhitespaceOnly(str)) {
			newData.addAll((ObservableList<ContactModel>) data);
			return newData;
		}
		if (StringUtils.isStrictlyNumeric(str)) {
			for (Iterator<? extends ItemModel> iterator = data.iterator(); iterator.hasNext();) {
				ContactModel ContactModel = (ContactModel) iterator.next();
				if (String.valueOf(ContactModel.address().getZip()).contains(str)
						|| String.valueOf(ContactModel.getBankKey()).contains(str) ) {
					newData.add(ContactModel);
				}
			}
		} else {
			for (Iterator<? extends ItemModel> iterator = data.iterator(); iterator.hasNext();) {
				ContactModel ContactModel = (ContactModel) iterator.next();
				String lowerStr = str.toLowerCase();
				if ((!StringUtils.isNullOrEmpty(ContactModel.getContactName()) 
						&& ContactModel.getContactName().toLowerCase().contains(lowerStr))
						|| (!StringUtils.isNullOrEmpty(ContactModel.getAddress())
						&& ContactModel.getAddress().toLowerCase().contains(lowerStr))
						 ) {
					newData.add(ContactModel);
				}
			}
		}
		return newData;
	}
	

}
