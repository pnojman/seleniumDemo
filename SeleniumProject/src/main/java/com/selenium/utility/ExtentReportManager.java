/**
 * 
 */
package com.selenium.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author przem
 *
 */
public class ExtentReportManager {

	public static ExtentReports extent;
	public static ExtentTest test;

	public static void setExtentReport() {

		ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");

		extent = new ExtentReports();
		extent.attachReporter(spark);

		spark.config().setDocumentTitle("Automation report");
		spark.config().setReportName("Test automation demo report");
		spark.config().setTheme(Theme.STANDARD);

	}

	public static void endReport() {

		extent.flush();

	}

}
