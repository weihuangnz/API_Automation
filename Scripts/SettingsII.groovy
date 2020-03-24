/****************************************************************************************************************
This file is used for defining customized variables.
This file should be INCLUDED in your customized settings.
e.g. 
evaluate(new File(vars.get("PROJECT_HOME") + "/Scripts/Settings.groovy"))
****************************************************************************************************************/
if (vars.get("INIT_DONE") == "Yes") return;

import org.apache.commons.io.FileUtils;

//_______________Settings_________________
vars.put("SMOKE_TEST", "Yes")//Value: Yes/No
vars.put("PAYLOAD_PATH", "\\Payload_Samples\\SovHub\\");
vars.put("PAYLOAD_FOLDER", "Pricing");
//vars.put("PAYLOAD_FOLDER", "Pricing, Projection");//, delimited folders for multi-products.
//----------------------------------------

//get lives from property.
//vars.put("MAX_LIVES", props.get("maxLives"));
vars.put("PRODUCT_PRICE_VERSION", "11.0.0");
vars.put("BENEFIT", "");


/****************** ASB Network *********************
vars.put("TOKEN_DOMAIN", "sso.qa.sovcloud.sovereign.co.nz");
vars.put("TOKEN_PATH", "/ms_oauth/oauth2/endpoints/APIserviceprofile/tokens");
vars.put("URL_TOKEN","https://sso.qa.sovcloud.sovereign.co.nz/ms_oauth/oauth2/endpoints/APIserviceprofile/tokens");
vars.put("TOKEN_USER","d8e3649f9e3d4ed1b8adb540e9f8c913");
vars.put("TOKEN_PASSWORD","XPJNTyxIwuACGO");
vars.put("DOMAIN","api.qa.sovcloud.sovereign.co.nz");
vars.put("PATH","/quote/v1/calculate/premium");
vars.put("SCOPE","SOV.Client.Products");
****************************************************/

/****************** AIA Network *********************/
vars.put("TOKEN_DOMAIN", "sso.zaicloud.aia.co.nz");
vars.put("TOKEN_PATH", "/ms_oauth/oauth2/endpoints/APIserviceprofile/tokens");
vars.put("URL_TOKEN","https://sso.qa.sovcloud.sovereign.co.nz/ms_oauth/oauth2/endpoints/APIserviceprofile/tokens");
vars.put("TOKEN_USER","3c54b00892dd44d587c4198155cfe5f0");
vars.put("TOKEN_PASSWORD","9XEoHFeAMr");
vars.put("DOMAIN","api.zaicloud.aia.co.nz");
vars.put("PATH","/quote/v1/calculate/premium");
vars.put("SCOPE","SOV.Client.Products");
/****************************************************/


//_______________Payload Data Generater_________________
vars.put("RANDOM_AGE", "\${__Random(18,55)}");
vars.put("RANDOM_SUM_ASSURED", "\${__Random(200000,10000000)}");

//________________Payload Paths Ready___________________
//------------------------------------------------------
String payloadPath = vars.get("PAYLOAD_PATH");
//receives input parameters delimited by ','.
//String [] params = Parameters.split(",");
//String benefit = params[0];
String [] extensions = Parameters.split(",");
String payloadFolder = vars.get("PAYLOAD_FOLDER");
String[] benefitTypes = payloadFolder.split(",");
String fullPayloadPath = vars.get("PROJECT_HOME") + payloadPath; //e.g. ...\Payload_Samples\SovHub\

String fixedBenefit = vars.get("BENEFIT");
String [] payloadFiles = [];//payload body files.
String [] payloadHeads = [];
String [] payloadTails = [];
if (fixedBenefit.isEmpty()) {
    //pick up a payload randomly from the Payload folder including its sub-folders.
    for (String benefitType : benefitTypes) {
        Collection files = FileUtils.listFiles(new File(fullPayloadPath + benefitType.trim()), extensions, true);
        payloadFiles += files.toArray();
    }

} else {
    payloadFiles += fullPayloadPath + payloadFolder + "\\" + fixedBenefit + ".json";
}

vars.put("PAYLOAD_FILES", payloadFiles.join(","));

String headFileName = "_head.txt";
String tailFileName = "_tail.txt";
String tempHead = fullPayloadPath + headFileName;
String tempTail = fullPayloadPath + tailFileName;

for (String plFile : payloadFiles) {
    String plPath = plFile.substring(0, plFile.lastIndexOf("\\") + 1);
    tempHead = plPath + headFileName;
    tempTail = plPath + tailFileName;
    File _headFile = new File(tempHead);
    if (!_headFile.exists())  {
        tempHead = fullPayloadPath + headFileName;
    }
    payloadHeads += tempHead;

    File _tailFile = new File(tempTail);
    if (!_tailFile.exists())  {
        tempTail = fullPayloadPath + tailFileName;
    }
    payloadTails += tempTail;
}

vars.put("PAYLOAD_HEADS", payloadHeads.join(","));
vars.put("PAYLOAD_TAILS", payloadTails.join(","));
/*
log.info("**************");
log.info(vars.get("PAYLOAD_FILES"));
log.info("**************");
log.info(vars.get("PAYLOAD_HEADS"));
log.info("**************");
log.info(vars.get("PAYLOAD_TAILS"));
*/
//Create Error-Logs Directory
//boolean success = (new File())
vars.put("INIT_DONE", "Yes");
log.info("Initializing variables done!");