package com.apps.digiple.npdapp.impl.enhanced;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.util.StringConverter;

public class EnhancedStringConverter extends StringConverter<LocalDate> {

	DateTimeFormatter formatter = null;
	
	public EnhancedStringConverter(String format) {
		super();
		this.formatter = DateTimeFormatter.ofPattern(format);
	}

	@Override
	public String toString(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return formatter.format(localDate);
	}
	
	@Override
	public LocalDate fromString(String text) {
		if (text == null || text.trim().isEmpty()) {
			return null;
		}
		return LocalDate.parse(text, formatter);
	}

}
