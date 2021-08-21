/*
 * To change this license header, choose License Headers in Project SmProperties.
 * 
 * 
 */

package supply.medium.utility;

/**
 *
 * @author Intel
 */
public class SmProperties {
    
    /*************folder path and url for files and images*******************/
    public static String folderPath="";
    
    public static final String localUrl="http://localhost:8084/SMlatest/cropData/";
    public static final String serverUrl="http://supplymedium.in:8080/SMlatest/cropData/";
//    public static final String serverUrl="http://8288033280.in/SMlatest/cropData/";
    public static final String pathUrl=localUrl;

    public static final String urlPath=pathUrl;    
    public static final String emailLocalUrl="http://localhost:8084/SMlatest/";
    public static final String emailServerUrl="http://supplymedium.in:8080/SMlatest/";
//    public static final String emailServerUrl="http://8288033280.in/SMlatest/";
    public static final String emailUrl=emailLocalUrl;
    
    /**************email static parameters********************************************/
    public static final String emailFrom="noreply@supplymedium.com";
    public static final String emailHost="mail.supplymedium.com";
//    public static final String emailHost="smtpout.secureserver.net";
    public static final String emailUsername="noreply";
    public static final String emailPassword="SupplyMedium!1";

    public static final String extraGb="USD$0.5 for every 100MB";
    public static final int transactionVolume=10000;
    public static final String plan[]={"Basic","Premium","Advanced","Unlimited"};
    public static final String price[]={"Free","USD$99/month","USD$299/month","USD$499/month"};
    public static final String storage[]={"100MB total storage of company","25GB total storage of company","100GB total storage for company","Unlimited storage of company"};
    public static final String user[]={"2","25","10","Unlimited"};
    
    public static final String pdfFooterMessage="\n\nSupplyMedium is a quick and smart way to get connected with small business network. This document is generated via www.supplymedium.com\n"
                                + "All Rights Reserved. © 2015 Supply Medium Inc, Ohio. USA. Supply Chain Management MarketPlace - Redefining Social Commerce!\n"
                                + "If you are having problems / suggestions / ideas, please contact a member of our support staff at info@supplymedium.com";
}
