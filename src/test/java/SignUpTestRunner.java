import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class SignUpTestRunner extends SetUp  {
    @Test(priority = 1  )
    public  void userRegistrtion() throws IOException, ParseException {
        driver.get("http://automationpractice.com");
        LoginPage loginPage = new LoginPage(driver);
        int id = Utils.generateRandomID(10, 1000);
        String email = "user"+id+"@test.com";
        String password = "password"+id;
        loginPage.doReg(email,password);
        //Assert.assertEquals(loginPage.signOut.isDisplayed(),true);

        Utils utils = new Utils(driver);
        utils.saveData(email,password);
        loginPage.btnSignOut.click();



    }
}
