package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Dashboard;
import pages.Login;
import pages.Profile;
import pages.SearchConnections;
import properties.DriverInitialization;
import utilities.ReadWriteExcel;
import utilities.SelUtils;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        DriverInitialization initiateDriver = new DriverInitialization();
        WebDriver driver = initiateDriver.getDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 35L);

        Login loginPage = new Login(driver, wait);
        loginPage.loginToLinkedin();

        Dashboard dash = new Dashboard(driver, wait);
        //dash.waitDashboardToLoad();

        ArrayList<String> friendFirstNames = new ArrayList<>();
        ArrayList<String> friendLastNames = new ArrayList<>();
        ArrayList<String> friendUrls = new ArrayList<>();
        ReadWriteExcel.fillInArrayListsFromExcel(friendFirstNames, friendLastNames, friendUrls);

        for(String url : friendUrls) {
            int indexOf = friendUrls.indexOf(url);
            Profile profile = new Profile(driver, wait);
            try { Thread.sleep(5000); } catch(Exception ignored){}
            profile.openProfileUrl(url);
            if(SelUtils.ifElementExists(driver, "a[data-control-name=url_card_action_click_highlights_shared_connections]"))
                profile.openMutualConnections();
            else {
                ReadWriteExcel.writeDateToInputNoConnections(url);
                continue;
            }

            SearchConnections connections = new SearchConnections(driver, wait);

            ArrayList<String> urls = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();

            try {
                connections.fillProfileNamesAndUrls(urls, names);
            } catch (NullPointerException e) { e.printStackTrace(); }

              try {
                  ReadWriteExcel.writeData(friendFirstNames.get(indexOf), friendLastNames.get(indexOf), url, names, urls);
              } catch (NullPointerException e) { e.printStackTrace(); }


        }

        driver.quit();


    }
}
