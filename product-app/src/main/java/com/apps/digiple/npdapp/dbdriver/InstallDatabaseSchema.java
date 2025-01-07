package com.apps.digiple.npdapp.dbdriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.constant.Constants;

public class InstallDatabaseSchema {
	private static final Logger LOGGER = Logger.getLogger(InstallDatabaseSchema.class);
	
	public static void main(String[] args) {
		DBHelper.createConnection(args);
		try {
			if (DBHelper.con !=null) {
				createTables(args[4]);
			} else {
				throw new RuntimeException(DBHelper.OFFLINE);
			}
		} catch (IOException e) {
			LOGGER.error(e.getLocalizedMessage(), e);
		}
		DBHelper.closeCon();
	}

	private static void createTables(String fileName) throws IOException {
		Reader fileReader = null;
		try {
			ScriptRunner sr = new ScriptRunner(DBHelper.con);
			File file = new File(Constants.SQL_PATH + fileName);
			fileReader = new FileReader(file);
			LOGGER.info("Reading File > " + file.getPath());
			Reader reader = new BufferedReader(fileReader);
			sr.runScript(reader);
			LOGGER.info("Database Schema created Successfully");
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage(), e);
		}finally {
			if (fileReader != null) {
				fileReader.close();
			}
		}
	}
}
