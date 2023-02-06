package config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

public class ExtentReportConfig {

    private static final ExtentReportConfig instance = new ExtentReportConfig();
    ThreadLocal<ExtentReports> extentReportsThreadLocal = new ThreadLocal<>();
    ThreadLocal<String> reportPath = new ThreadLocal<>();
    private final Random rng = new Random(Instant.now().getEpochSecond());

    public static ExtentReportConfig getInstance() {
        return instance;
    }

    public ExtentReports getExtentReports() {
        if (!(extentReportsThreadLocal.get() == null)) {
            return extentReportsThreadLocal.get();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        Date date = new Date();
        String formattedDateTime = simpleDateFormat.format(date);

        reportPath.set(System.getProperty("user.dir") + "/target/reports/Regression_Report_" + getRng() + "_" + formattedDateTime + ".html");

        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath.get());
        ExtentReports extentReportTemplate = new ExtentReports();
        extentReportTemplate.attachReporter(extentSparkReporter);

        extentSparkReporter.config().setTheme(Theme.DARK);

        extentReportsThreadLocal.set(extentReportTemplate);
        return extentReportsThreadLocal.get();
    }

    public synchronized int getRng() {
        return rng.nextInt();
    }

    public ExtentReports getInstanceOfReport(){
        return extentReportsThreadLocal.get();
    }
}
