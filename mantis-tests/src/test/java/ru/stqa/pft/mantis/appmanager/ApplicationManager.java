package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    public WebDriver wd;

    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private LoginHelper loginHelper;
    private UsersControlHelper usersControlHelper;
    private SoapHelper soapHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    }

    public void stop() {
        if(wd != null){
            wd.quit();
        }

    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }


    public RegistrationHelper registration() {
        if (registrationHelper == null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public LoginHelper login() {
        if (loginHelper == null){
            loginHelper = new LoginHelper(this);
        }
        return loginHelper;
    }



//    public UsersControlHelper usersControl() {
//        return usersControlHelper;
//    }

  public UsersControlHelper usersControl() {
       if (usersControlHelper == null){
           usersControlHelper = new UsersControlHelper(this);
       }
        return usersControlHelper;
    }



    public FtpHelper ftp(){
        if (ftp == null) {
            ftp =  new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.CHROME)){
                wd = new ChromeDriver();
            }else if (browser.equals(BrowserType.FIREFOX)){
                wd = new FirefoxDriver();
            }else if (browser.equals(BrowserType.IE)){
                wd = new InternetExplorerDriver();
            }

            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public MailHelper mail(){
        if(mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public SoapHelper soap() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}
