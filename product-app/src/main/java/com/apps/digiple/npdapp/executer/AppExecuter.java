package com.apps.digiple.npdapp.executer;

import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.dbdriver.DBHelper;
import com.apps.digiple.npdapp.view.AppWindow;

public class AppExecuter {
	private static final Logger LOGGER = Logger.getLogger(AppExecuter.class);
	public static void main(String[] args) {
		
		LOGGER.info("NPD APP Started.");
		DBHelper.createConnection(args);
		try {
			new AppWindow().launchApp(args);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage(), e);
		}
		DBHelper.closeCon();
		LOGGER.info("NPD APP Closed.");
	}
}
