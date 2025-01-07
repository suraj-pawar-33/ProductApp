package com.apps.digiple.npdapp.dbdriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.apps.digiple.npdapp.models.FieldInfo;
import com.apps.digiple.npdapp.models.GeneralModel;

public class DatabaseDriver {
	private JdbcTemplate jdbcTemplate;

	public DatabaseDriver() {
		this.jdbcTemplate = new JdbcTemplate();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void execute() {
		List<String> list = getJdbcTemplate().queryForList("show tables", String.class);
		for (String string : list) {
			System.out.println(string);
		}
	}

	public List<GeneralModel> selectGeneralQuery(String dbTableName, FieldInfo[] fieldInfoList) {

		PreparedStatementCreator psc = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String str = "Select * FROM " + dbTableName;
				return con.prepareStatement(str);
			}
		};
		
		ResultSetExtractor<List<GeneralModel>> rse = new ResultSetExtractor<List<GeneralModel>>() {

			@Override
			public List<GeneralModel> extractData(ResultSet result) throws SQLException, DataAccessException {

				String[] stringProps = new String[fieldInfoList.length];
				List<GeneralModel> list = new ArrayList<>();
				
				while (result.next()) {
					int key = Integer.valueOf(result.getString(fieldInfoList[0].getColumnName()));
					for (int i = 0; i < stringProps.length; i++) {
						
						FieldInfo fieldInfo = fieldInfoList[i];
						stringProps[i] = result.getString(fieldInfo.getColumnName());
					}
					list.add(new GeneralModel(key, stringProps));
				}
				return list;
			}
		};
		return getJdbcTemplate().query(psc, rse);
	}

}
