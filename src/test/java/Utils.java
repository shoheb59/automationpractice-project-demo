import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


import javax.rmi.CORBA.Util;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    String fileName = "./src/test/resources/customer.json";
    public String email;
    public String password;

    //variable declare hobe for Webdriver

    WebDriver driver;
    public Utils (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void saveData(String email, String password) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader(fileName));
        JSONObject customerObject = new JSONObject();

        customerObject.put("email",email);
        customerObject.put("password", password );

        JSONArray jsonArray = (JSONArray) object;
        jsonArray.add(customerObject);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();

    }
    public void readData(int pos) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject customerObj  = (JSONObject) jsonArray.get(pos);

        String email  = (String) customerObj.get("email");
        String password = (String) customerObj.get("password");

        setEmail(email);
        setPassword(password);
    }

    public int getLastUser() throws IOException, ParseException {
        //index return hobe that;s y INT use krlm

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        return  jsonArray.size();
    }

    public static int generateRandomID(int min, int max)
    {
        int id = (int) (Math.random()*(max - min) + min);
        return id;
    }

    //Screeshot + Driver lagbe so put driver method with page factory in upper code.

    public void takeScreenShot() throws IOException {
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath = "./src/test/resources/screenshots/"+time + ".png";
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshotFile, DestFile);
    }


}
