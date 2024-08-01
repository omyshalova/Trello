package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import manager.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProfileAndVisibility;

public class ProfileTests extends ApplicationManager {
    UserDTO user = UserDTO.builder()
            .email("omyshalova@gmail.com")
            .password("Qw1235813.")
            .build();

    ProfileAndVisibility profileAndVisibility;

    @BeforeMethod
    public void loginBeforeBoProfile() {
        HomePage homePage = new HomePage(getDriver());
        profileAndVisibility = homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user)
                .goToProfileAndVisibility();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void changeProfilePhotoPositiveTest(){
        Assert.assertTrue(
                profileAndVisibility.changeAvatar("qa_yellow.png").isTextInElementPresent_AvatarAdded()
        );
    }

    @Test
    public void changeProfilePhotoNegativeTest_WrongFileFormat(){
        Assert.assertTrue(
                profileAndVisibility
                        .changeAvatar("log-240731-18.45.54.log")
                        .isTextInElementPresent_WrongFileFormat()
        );
    }

}
