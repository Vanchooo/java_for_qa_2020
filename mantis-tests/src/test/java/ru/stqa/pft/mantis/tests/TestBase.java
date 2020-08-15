package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.*;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;


import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        //app.ftp().upload(new File("scr/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        //app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();

    }

    public static void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public static boolean isIssueOpen(int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData data = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(id));
        String status = data.getStatus().getName();

        if(status.equals("resolved")){
            return false;
        } else return true;
    }

    public static MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().
                getMantisConnectPort(new URL("http://localhost/mantisbt-2.24.2/api/soap/mantisconnect.php"));
    }



}
