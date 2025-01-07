package com.apps.digiple.npdapp.impl.printer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.models.ItemModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PrintReportHelper {
	private static final Logger LOGGER = Logger.getLogger(PrintReportHelper.class);
	
    public static final String JASPER_DIR = "resources/jasper/";
	public static void generate(HashMap<String, Object> params, ArrayList<? extends ItemModel> dataList, String fileName) {
		String printFileName = null;
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

		try {
			File sourcefile = new File(JASPER_DIR + fileName);
			printFileName = JasperFillManager.fillReportToFile(sourcefile.getAbsolutePath(), params,
					beanColDataSource);
			if (printFileName != null) {
				boolean result = JasperPrintManager.printReport(printFileName, true);
				LOGGER.info((result ? "Printed ": "Not Printed '") + fileName + "' KEY = " + dataList.get(0).getKey());
			}
		} catch (JRException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}
	}