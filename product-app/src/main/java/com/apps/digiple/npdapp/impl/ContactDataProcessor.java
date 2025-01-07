package com.apps.digiple.npdapp.impl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.dbdriver.DBHelper;
import com.apps.digiple.npdapp.models.AddressModel;
import com.apps.digiple.npdapp.models.ContactModel;
import com.apps.digiple.npdapp.models.ItemModel;
import com.apps.digiple.npdapp.view.DataEditPanel;
import com.apps.digiple.npdapp.view.TablePanel;
import com.apps.digiple.npdapp.view.contact.ContactAddDataEditPanel;
import com.apps.digiple.npdapp.view.contact.ContactDataEditPanel;
import com.apps.digiple.npdapp.view.contact.ContactTablePanel;
import com.mysql.cj.util.StringUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;

public class ContactDataProcessor extends AbstractDataProcessor {
	private static final Logger LOGGER = Logger.getLogger(ContactDataProcessor.class);
	private ObservableList<ContactModel> data = FXCollections.observableArrayList();;
	protected int objectKey;
	AddressDataProcessor addressProcessor;

	public ContactDataProcessor() {
		this.tablePanel = new ContactTablePanel(data, getMouseEventHandler());
		addressProcessor = new AddressDataProcessor(new ContactAddDataEditPanel());
		this.editPanel = new ContactDataEditPanel((ContactAddDataEditPanel)addressProcessor.getEditPanel(), this.tablePanel);
	}


	@Override
	public void loadRow(ItemModel row) {
		loadTextFields((ContactModel) row);
	}

	public void loadDBData() {
		data.clear();
		try {
			ResultSet rs = DBHelper.selectContactQuery();
			while (rs.next()) {
				AddressModel address = new AddressModel(Integer.valueOf(rs.getString(12)), Integer.valueOf(rs.getString(1)));
				address.setLine1(rs.getString(4));
				address.setLine2(rs.getString(5));
				address.setLine3(rs.getString(6));
				address.setCity(rs.getString(7));
				address.setZip(Integer.parseInt(rs.getString(8) != null ? rs.getString(8) : "0"));
				address.setState(rs.getString(9));
				address.setCountry(rs.getString(10));
				address.setOther(rs.getString(11));
				data.add(new ContactModel(rs.getInt(1), rs.getString(2), Integer.valueOf(rs.getString(3)), address));
			}
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	public TablePanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(TablePanel tablePanel) {
		this.tablePanel = (ContactTablePanel) tablePanel;
	}

	public DataEditPanel getEditPanel() {
		return editPanel;
	}

	public void setEditPanel(DataEditPanel editPanel) {
		this.editPanel = (ContactDataEditPanel) editPanel;
	}

	private void loadTextFields(ContactModel contactModel) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(contactModel.getClass(), Object.class);
			PropertyDescriptor[] property = beanInfo.getPropertyDescriptors();
			Map<String, String> properties = BeanUtils.describe(contactModel);
			editPanel.setObjectKey(contactModel.getBankKey());
			editPanel.showUpdateView();
			addressProcessor.loadAddresTextFields(contactModel.address());
			for (int i = 0; i < property.length; i++) {
				if (StringUtils.isNullOrEmpty(properties.get(property[i].getDisplayName()))) {
					continue;
				}
				switch (property[i].getDisplayName()) {
				case ContactModel.CONTACTNAME:
					((ContactDataEditPanel) editPanel).setNameText(properties.get(property[i].getDisplayName()));
					break;
				case ContactModel.BANKKEY:
					((ContactDataEditPanel) editPanel).setBrNameText(properties.get(property[i].getDisplayName()));
					break;
				default:
					break;
				}
			}

		} catch (IntrospectionException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			LOGGER.error("loadTextFields >" +e.getLocalizedMessage());
		}
	}

	@Override
	public ObservableList<ContactModel> getData() {
		return this.data;
	}

	public GridPane getPanel() {
		GridPane pane = new GridPane();
		pane.add(editPanel, 0, 0);
		pane.add(tablePanel, 0, 1);
		return pane;
	}


	


}
