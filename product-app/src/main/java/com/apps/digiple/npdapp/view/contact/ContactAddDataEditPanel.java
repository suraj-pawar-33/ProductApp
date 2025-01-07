package com.apps.digiple.npdapp.view.contact;

import com.apps.digiple.npdapp.view.AddressDataEditPanel;
import com.apps.digiple.npdapp.view.CustLabel;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ContactAddDataEditPanel extends  AddressDataEditPanel{
	
	public ContactAddDataEditPanel() {
		super();
		add(getAddressPane(), 0, 0);
	}

	public GridPane getAddressPane() {
		GridPane addressPanel = new GridPane();
		addressPanel.setVgap(5);
		addressPanel.setHgap(5);

		Text addressLabel = CustLabel.CONT_ADDRESS.getBoldLebel();

		Text line1Label = CustLabel.CONT_LINE1.getLebel();
		line1Text = getTextField();

		Text line2Label = CustLabel.CONT_LINE2.getLebel();
		line2Text = getTextField();

		Text line3Label = CustLabel.CONT_LINE3.getLebel();
		line3Text = getTextField();

		Text CityLabel = CustLabel.CONT_CITY.getLebel();
		cityChoiceBox = getChoiceBox();
		cityChoiceBox.getItems().addAll("Aurangabad", "Ahamadanagar", "Chennai", "Delhi", "Mumbai", "Pune");

		Text zipLabel = CustLabel.CONT_ZIP.getLebel();
		zipText = getTextField();

		Text StateLabel = CustLabel.CONT_STATE.getLebel();
		stateChoiceBox = getChoiceBox();
		stateChoiceBox.getItems().addAll("Maharashtra", "Panjab", "Goa", "Gujarat", "UP", "MP", "Rajastan");

		Text ConLabel = CustLabel.CONT_CONT.getLebel();
		conChoiceBox = getChoiceBox();
		conChoiceBox.getItems().addAll("India", "Germany", "UK", "US");

		Text otherLabel = CustLabel.CONT_OTHER.getLebel();
		otherText = getTextField();

		addressPanel.add(addressLabel, 3, 0);

		addressPanel.add(line1Label, 2, 1);
		addressPanel.add(line1Text, 3, 1);

		addressPanel.add(line2Label, 2, 2);
		addressPanel.add(line2Text, 3, 2);

		addressPanel.add(line3Label, 2, 3);
		addressPanel.add(line3Text, 3, 3);

		addressPanel.add(CityLabel, 2, 4);
		addressPanel.add(cityChoiceBox, 3, 4);

		addressPanel.add(zipLabel, 2, 5);
		addressPanel.add(zipText, 3, 5);

		addressPanel.add(StateLabel, 2, 6);
		addressPanel.add(stateChoiceBox, 3, 6);

		addressPanel.add(ConLabel, 2, 7);
		addressPanel.add(conChoiceBox, 3, 7);

		addressPanel.add(otherLabel, 2, 8);
		addressPanel.add(otherText, 3, 8);

		return addressPanel;
	}

}
