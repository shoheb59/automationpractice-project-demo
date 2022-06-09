import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends SetUp {
//    @Test
//    public void userReg()
//    {
//        driver.get("http://automationpractice.com");
//        LoginPage loginPage = new LoginPage(driver);
//        int id = (int) (Math.random()*(1000-1)+1);
//        String email = "user"+id+"@test.com";
//        loginPage.doReg(email);


    @Test(priority = 1)
    public  void userRegistration() throws IOException, ParseException {
        driver.get("http://automationpractice.com");
        LoginPage loginPage = new LoginPage(driver);
        int id = Utils.generateRandomID(10, 1000);
        String email = "user"+id+"@test.com";
        String password = "password"+id;
        loginPage.doReg(email,password);
        //Assert.assertEquals(loginPage.signOut.isDisplayed(),true);

        Utils utils = new Utils(driver);
        utils.saveData(email,password);



    }
    @Test (priority = 2)
    public void userLogin() throws InterruptedException, IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://automationpractice.com");
        Utils utils = new Utils(driver);
        utils.readData(utils.getLastUser()-1);
        String name = loginPage.doLogin(utils.getEmail(), utils.getPassword());
        Assert.assertTrue(name.contains("Hasnat Shoheb"));
        //System.out.println(name);

        //IF we want to match value
        //Assert.assertEquals(name, "Hasnat shoheb");

        //If we want to match boolean value
        //Assert.assertTrue(true, name);

        //Assert.assertTrue(name.contains("Hasnat shoheb"), String.valueOf(true));

        //signout Button display assertion
        Assert.assertEquals(loginPage.signOut.isDisplayed(),true);

        //Logout
        Thread.sleep(5000);
        loginPage.signOut.click();
    }


    //Negative Test Case
    @Test(priority = 3)
    public void doLoginWithValidmailInvalidPass()
    {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://automationpractice.com");
        String ErroMsg = loginPage.doLoginWithValidEmailandInvalidPassword("hasnat.shoheb@test.com","123");
        Assert.assertTrue(ErroMsg.contains("Invalid password"));
        

    }

    @Test(priority = 4)
    public void doLoginWithInvalidmail()
    {
        LoginPage loginPage = new LoginPage(driver);
        //driver.get("http://automationpractice.com");
        String InvalidEmailErrMsg = loginPage.doLoginWithInvalidMail("j");
        //For Screenshot purpose, msg is made worng
        Assert.assertTrue(InvalidEmailErrMsg.contains("habijabi."));
    }



}
