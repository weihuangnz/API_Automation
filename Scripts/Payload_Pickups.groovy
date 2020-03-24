String [] payloadFiles = vars.get("PAYLOAD_FILES").split(",");
String [] payloadHeads = vars.get("PAYLOAD_HEADS").split(",");
String [] payloadTails = vars.get("PAYLOAD_TAILS").split(",");

String payload = "";
String bodyFile = "";
String headFile = "";
String tailFile = "";

String smokeTest = vars.get("SMOKE_TEST");
int index = 0;
if (smokeTest.equals("Yes")) {
    if (vars.get("SMOKE_INDEX") != null) {
        index = Integer.parseInt(vars.get("SMOKE_INDEX"));
        if(index < payloadFiles.size()) {
            index++;
        }
        if(index >= payloadFiles.size()) {
            index = 0;
        }
    }
    vars.put("SMOKE_INDEX", index.toString());
    
} else {
    index = (int)(Math.random() * payloadFiles.size());
}

headFile = payloadHeads[index];
bodyFile = payloadFiles[index];
tailFile = payloadTails[index];

File _headFile = new File(headFile);
File _bodyFile = new File(bodyFile);
File _tailFile = new File(tailFile);

payload = _headFile.text + _bodyFile.text;

String strMaxLives = vars.get("MAX_LIVES");
//strMaxLives = "8";
int maxLives = 1;//default value.
String requestLable = "";
if (strMaxLives != null){
    maxLives = Integer.parseInt(strMaxLives);
}
if (maxLives > 1) {
    //When it is multi-lives (2+):
    //minimum lives is 2.
    int loops = (int)(Math.random() * (maxLives - 1)) + 1;
    requestLable = "["  + (loops + 1).toString() +"-Lives] ";
    while (loops > 0) {
        payload += _bodyFile.text;
        loops--;
    }
}
payload += _tailFile.text;

String randomPaymentFrequency() {
    String[] paymentFrequency = ["Weekly", "Fortnightly", "Monthly", "Yearly"];
    return paymentFrequency[(int)(Math.random() * paymentFrequency.size())];
}

vars.put("RANDOM_PAYMENT_FREQUENCY", randomPaymentFrequency());

String benefit_pickup = bodyFile.substring(bodyFile.lastIndexOf("\\", bodyFile.lastIndexOf("\\") - 1) + 1, bodyFile.lastIndexOf(".")).replace("\\", " - ");

vars.put("BENEFIT_PICKUP", requestLable + benefit_pickup);
vars.put("PAYLOAD", payload);
