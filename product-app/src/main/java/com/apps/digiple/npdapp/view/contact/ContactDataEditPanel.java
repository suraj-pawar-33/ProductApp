package com.apps.digiple.npdapp.view.contact;

import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.dbdriver.DBHelper;
import com.apps.digiple.npdapp.models.AddressModel;
import com.apps.digiple.npdapp.models.ContactModel;
import com.apps.digiple.npdapp.models.ItemModel;
import com.apps.digiple.npdapp.view.AddressDataEditPanel;
import com.apps.digiple.npdapp.view.CustLabel;
import com.apps.digiple.npdapp.view.DataEditPanel;
import com.apps.digiple.npdapp.view.TablePanel;
import com.mysql.cj.util.StringUtils;

import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ContactDataEditPanel extends DataEditPanel {
	private static final Logger LOGGER = Logger.getLogger(ContactDataEditPanel.class);
	private TextField nameText;
	private ChoiceBox<String> brNameText;
	private TextField shortNameText;
	private AddressDataEditPanel addEdit;
	private HashMap<String, Integer> bankMap = new HashMap<>();
	
	public ContactDataEditPanel(ContactAddDataEditPanel addEdit, TablePanel tablePanel) {
		super(tablePanel);
		this.addEdit = addEdit;
		add(getBankPane(), 0, 0);
		add(addEdit.getAddressPane(), 1, 0);
	}

	public String getNameText() {
		return nameText.getText();
	}
	

	public int getSelectedBankKey() {
		return bankMap.get(getBrNameText());
	}
	
	
	public String getBrNameText() {
		return brNameText.getSelectionModel().getSelectedItem();
	}

	public String getShortNameText() {
		return shortNameText.getText();
	}
	
	public void setNameText(String value) {
		nameText.setText(value);
	}

	public void setBrNameText(String value) {
		bankMap.forEach((k,v) -> {
			if (v.equals(value)) {
				brNameText.getSelectionModel().select(k);
			}
		});
	}

	public void setShortNameText(String value) {
		shortNameText.setText(value);
	}

	
	
	protected void clearAllText(){
		nameText.clear();
		brNameText.getSelectionModel().clearSelection();
		shortNameText.clear();
		addEdit.clearAllText();
		showSaveView();
	}
	
	private GridPane getBankPane() {
		GridPane bankPanel = new GridPane();
		bankPanel.setVgap(5);
		bankPanel.setHgap(5);
		
		Text bankLabel = CustLabel.CONTACT.getBoldLebel();
		
		Text nameLabel = CustLabel.CONT_NAME.getLebel();
		nameText = getTextField();

		Text branchLable = CustLabel.CONT_BRANCH.getLebel();
		brNameText = getChoiceBox();

		Text shortNameLabel = CustLabel.CONT_BK_SHNAME.getLebel();
		shortNameText = getTextField();
		
		// Arranging all the nodes in the grid
		bankPanel.add(bankLabel, 1, 0);
		
		bankPanel.add(nameLabel, 0, 1);
		bankPanel.add(nameText, 1, 1);

		bankPanel.add(branchLable, 0, 2);
		bankPanel.add(brNameText, 1, 2);

		bankPanel.add(shortNameLabel, 0, 3);
		bankPanel.add(shortNameText, 1, 3);
		return bankPanel;		
	}

	@Override
	protected EventHandler<MouseEvent> updateEvent() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if (notValidAddress()) {
					showErrAlert("Data Error!",
							"Address > Line 1, city, zip and state is required");
					return;
				} else {
					try {
						DBHelper.updateContactQuery(getCurrentContact());
						tablePanel.updateRow(getCurrentContact());
						showConfAlert("Success", "Contact data is updated " + getObjectKey());
						LOGGER.info("Contact data is updated KEY:" + getObjectKey());
					} catch (Exception e) {
						showErrAlert("Error", "Bank data is not updated.\nReason : " + e.getLocalizedMessage());
						LOGGER.error("Contact data is not updated. KEY:" + getObjectKey() + " Reason : " + e.getLocalizedMessage());
					}
				}
			}
		};
	}



	@Override
	protected EventHandler<MouseEvent> saveEvent(){
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if (notValidBankData()) {
					showErrAlert("Data Error!", "Contact > Name, branch Name is required");
					return;
				} else if (notValidAddress()) {
					showErrAlert("Data Error!",
							"Address > Line 1, city, zip and state is required");
					return;
				} else {
					int bankKey = 0;
					try {
						bankKey = DBHelper.saveContactQuery(getCurrentContact());
						tablePanel.addNewRow(bankKey, getCurrentContact());
						showConfAlert("Success", "New Contact is Added " + bankKey);
						LOGGER.info("New bank is Added KEY:" + bankKey);
					} catch (Exception e) {
						showErrAlert("Error", "Contact is not saved.//n Reason : " + e.getLocalizedMessage());
						LOGGER.error("Contact is not saved.  KEY:" + bankKey + " Reason : " + e.getLocalizedMessage());
					}
				}
			}
		};
	}

	

	protected boolean notValidBankData() {
		return StringUtils.isNullOrEmpty(nameText.getText()) || StringUtils.isNullOrEmpty(getBrNameText());
	}

	protected boolean notValidAddress() {
		return StringUtils.isNullOrEmpty(addEdit.getLine1Text().getText())
				|| !StringUtils.isStrictlyNumeric(addEdit.getZipText().getText()) || !addEdit.isCitySelected()
				|| !addEdit.isStateSelected();
	}
	
	protected ContactModel getCurrentContact() {
		return new ContactModel(getObjectKey(), nameText.getText(), getSelectedBankKey(), getCurrentAddress());
	}
	
	protected AddressModel getCurrentAddress() {
		 AddressModel address = new AddressModel(addEdit.getObjectKey(), getObjectKey());
		address.setLine1(addEdit.getLine1Text().getText());
		address.setLine2(addEdit.getLine2Text().getText());
		address.setLine3(addEdit.getLine3Text().getText());
		address.setCity(addEdit.getSelectedCity());
		address.setZip(Integer.parseInt(addEdit.getZipText().getText()));
		address.setState(addEdit.getSelectedState());
		address.setCountry(addEdit.getSelectedCountry());
		address.setOther(addEdit.getOtherText().getText());
		return address;
	}

	@Override
	protected EventHandler<MouseEvent> deleteEvent() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {

				try {
					DBHelper.deleteBankQuery(getObjectKey());
				} catch (Exception e) {
					showErrAlert("Error", "Contact is not deleted.//n Reason : " + e.getLocalizedMessage());
					LOGGER.error("Contact is not deleted. KEY:" + getObjectKey()+ ", Reason : " + e.getLocalizedMessage());
				}
				showConfAlert("Success", "Contact is deleted " + getObjectKey());
				LOGGER.info("Contact is deleted KEY:" + getObjectKey());
				clearAllText();
				tablePanel.deleteRow();
			}

		};
	}
	

	private String[] getBankNameList() {
		String[] arr = bankMap.keySet().toArray(new String[0]);
		Arrays.sort(arr);
		return arr;
	}

	@Override
	public void loadDropdownList() {
		this.bankMap = getBankMap();
		brNameText.getItems().clear();
		brNameText.getItems().addAll(getBankNameList());
	}

	@Override
	public void printReport(ItemModel itemModel) {
		// TODO Auto-generated method stub
	}

	
}
