package com.apps.digiple.npdapp.impl.printer;

import java.io.File;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class JasperReportCompile {
	private static final Logger LOGGER = Logger.getLogger(JasperReportCompile.class);

	public static void main(String[] args) {
		run(args[0], args[1]);
	}

	private static void run(String sourceFileName, String destFileName) {
		LOGGER.info("Compiling Report Design : " + sourceFileName);
		try {
			File sourcefile = new File(sourceFileName);
			File destfile = new File(destFileName);
			JasperCompileManager.compileReportToFile(sourcefile.getAbsolutePath(), destfile.getAbsolutePath());
		} catch (JRException e) {
			LOGGER.error(e);
		}
		LOGGER.info("SUCCESS : " + destFileName);
	}
}