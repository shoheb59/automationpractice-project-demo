import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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



    @Test (priority = 2, enabled = true)
    public void userLogin() throws InterruptedException, IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://automationpractice.com");
        Utils utils = new Utils(driver);
        utils.readData(utils.getLastUser()-1);
        String name = loginPage.doLogin(utils.getEmail(), utils.getPassword());

        //SoftAssertion
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(name.contains("Hasnat Shoheb"));
        //System.out.println(name);

        //IF we want to match value
        //Assert.assertEquals(name, "Hasnat shoheb");

        //If we want to match boolean value
        //Assert.assertTrue(true, name);

        //Assert.assertTrue(name.contains("Hasnat shoheb"), String.valueOf(true));

        //signout Button display assertion
        softAssert.assertEquals(loginPage.btnSignOut.isDisplayed(),true);

        //Logout
        Thread.sleep(5000);
        loginPage.btnSignOut.click();
    }


    //Negative Test Case
    @Test(priority = 3, enabled = true)
    public void doLoginWithValidmailInvalidPass()
    {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://automationpractice.com");
        String ErroMsg = loginPage.doLoginWithValidEmailandInvalidPassword("hasnat.shoheb@test.com","123");
        Assert.assertTrue(ErroMsg.contains("Invalid password"));
        

    }

    @Test(priority = 4, enabled = true)
    public void doLoginWithInvalidmail()
    {
        LoginPage loginPage = new LoginPage(driver);
        //driver.get("http://automationpractice.com");
        String InvalidEmailErrMsg = loginPage.doLoginWithInvalidMail("j");
        //For Screenshot purpose, msg is made worng
        Assert.assertTrue(InvalidEmailErrMsg.contains("habijabi."));
    }



}
