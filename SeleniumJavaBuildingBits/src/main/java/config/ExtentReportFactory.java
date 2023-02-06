package config;

import com.aventstack.extentreports.ExtentTest;

public class ExtentReportFactory {

    private static final ExtentReportFactory instance = new ExtentReportFactory();
    ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    //explicit private constructor
    private ExtentReportFactory() {}

    public static ExtentReportFactory getInstance() {
        return instance;
    }

    public ExtentTest getExtentTest() {
        return extentTestThreadLocal.get();
    }

    public void setExtentTest(ExtentTest extentTest) {
        extentTestThreadLocal.set(extentTest);
    }

    public void removeExtentTest() {
        extentTestThreadLocal.remove();
    }
}
