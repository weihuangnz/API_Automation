import org.apache.commons.io.FileUtils;
import java.util.Random;

//receives input parameters delimited by ','.
//String [] params = Parameters.split(",");
//String benefit = params[0];
String [] extensions = Parameters.split(",");

//get project path.
String path = vars.get("PROJECT_HOME") + vars.get("PAYLOAD_FOLDER");
String benefit_pickup = vars.get("BENEFIT");
String [] payloads;
String payloadFile = "";

if (benefit_pickup.isEmpty()) {
    //pick up a payload randomly from the Payload folder.
    Collection files = FileUtils.listFiles(new File(path), extensions, true);
    payloads = files.toArray();
    payloadFile = payloads[(int)(Math.random() * files.size())].getAbsolutePath();
}
else {
    payloadFile = path + benefit_pickup + ".json";
}

benefit_pickup = payloadFile.substring(payloadFile.lastIndexOf("\\") + 1).replaceFirst("[.][^.]+$", "");
vars.put("BENEFIT_PICKUP", benefit_pickup);
vars.put("PAYLOAD_FILE", payloadFile);

//get all files shown.
/*
for (File file : files) {
    log.info(file.getAbsolutePath());               
}
*/