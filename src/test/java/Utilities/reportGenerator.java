package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class reportGenerator {

    private static ExtentReports extent;

    /**
     * Returns the singleton ExtentReports instance.
     * Creates it on first call with Spark reporter configuration.
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir")
                    + "/test-output/ExtentReport.html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Demo Web Shop - Automation Report");
            sparkReporter.config().setReportName("Selenium TestNG Extent Report");
            sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Application", "Demo Web Shop");
            extent.setSystemInfo("URL", "https://demowebshop.tricentis.com/");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Tester", "Shreya");
        }
        return extent;
    }
}
