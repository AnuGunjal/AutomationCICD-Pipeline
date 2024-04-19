package automationTest.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject()
	{

		//ExtentReports , ExtentSparkReporter
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter sp = new ExtentSparkReporter(path);
		sp.config().setReportName("Web Automation Suite Results");
		sp.config().setDocumentTitle("Automation Results");
		
		ExtentReports  er = new ExtentReports();
		er.attachReporter(sp);
		er.setSystemInfo("Tester", "Kunal");
		return er;
	}

}
