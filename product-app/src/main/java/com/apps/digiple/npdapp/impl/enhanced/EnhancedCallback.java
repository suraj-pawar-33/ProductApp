package com.apps.digiple.npdapp.impl.enhanced;

import java.time.LocalDate;
import java.time.MonthDay;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

public class EnhancedCallback implements Callback<DatePicker, DateCell> {

	@Override
	public DateCell call(DatePicker arg0) {
		return new DateCell(){
			@Override
			public void updateItem(LocalDate item, boolean empty){
				super.updateItem(item, empty);
				if (MonthDay.from(item).equals(MonthDay.of(9, 25))) {
					setVisible(false);
					setDisabled(true);
					setOpacity(0);
				}
			}
		};
	}

}
