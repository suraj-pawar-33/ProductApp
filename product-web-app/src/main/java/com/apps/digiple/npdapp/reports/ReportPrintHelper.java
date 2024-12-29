package com.apps.digiple.npdapp.reports;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportPrintHelper {
	public static final String JASPER_DIR = "resources/jasper/";

	public static void generate(Map<String, Object> params, Collection<JasperProduct> productList, String destFilePath ) {
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productList);
		File sourceFile = new File(JASPER_DIR + "ProductDetailReport.jasper");
		try {
			JasperPrint printFileName = JasperFillManager.fillReport(sourceFile.getAbsolutePath(), params, dataSource);
			if (printFileName != null) {
				JasperExportManager.exportReportToPdfFile(printFileName, destFilePath);
			}
		} catch (JRException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
