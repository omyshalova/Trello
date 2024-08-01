package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import manager.TakeScreenShot;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import pages.HomePage;

public class LoginTests extends ApplicationManager {

    UserDTO user = UserDTO.builder()
            .email("omyshalova@gmail.com")
            .password("Qw1235813.")
            .build();

    @Test
    public void loginTest(){

        HomePage  homePage = new HomePage(getDriver());
        homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user);

        TakeScreenShot.takeScreenShot((TakesScreenshot) getDriver());
    }
}
