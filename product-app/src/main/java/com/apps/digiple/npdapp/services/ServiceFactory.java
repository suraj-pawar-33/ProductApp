package com.apps.digiple.npdapp.services;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.apps.digiple.npdapp.constant.Constants;
import com.apps.digiple.npdapp.dbdriver.DatabaseDriver;
import com.apps.digiple.npdapp.impl.GeneralDataProcessor;
import com.apps.digiple.npdapp.view.DataEditPanel;
import com.apps.digiple.npdapp.view.MainPanel;

public class ServiceFactory {

	public static MainPanel getMainPanelInstance() {
		Resource res = new ClassPathResource(Constants.MAINPANEL_XML);
		BeanFactory fact = new XmlBeanFactory(res);

		MainPanel panel = (MainPanel) fact.getBean("mainPanel");
		panel.init();
		return panel;
	}
	
	public static DataEditPanel getEditPaneInstance() {
		Resource res = new ClassPathResource(Constants.EDITPANE_XML);
		BeanFactory fact = new XmlBeanFactory(res);

		DataEditPanel panel = (DataEditPanel) fact.getBean("generalDataEditPanel");
		return panel;
	}
	
	public static GeneralDataProcessor getGenProcessorInstance() {
		Resource res = new ClassPathResource(Constants.EDITPANE_XML);
		BeanFactory fact = new XmlBeanFactory(res);

		GeneralDataProcessor panel = (GeneralDataProcessor) fact.getBean("generalDataProcessor");
		return panel;
	}
	
	public static DatabaseDriver getdbInstance() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(Constants.DB_CONN_XML);  
		DatabaseDriver jdbc = (DatabaseDriver) ctx.getBean("dbDriver");
		return jdbc;
	}
	
	public static JdbcTemplate getJdbcTemplate() {
		Resource res = new ClassPathResource(Constants.DB_CONN_XML);
		BeanFactory fact = new XmlBeanFactory(res);
		JdbcTemplate jdbc = (JdbcTemplate) fact.getBean("jdbcTemplate");
		return jdbc;
	}

}
