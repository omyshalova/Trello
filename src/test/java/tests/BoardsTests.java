package tests;

import dto.BoardDTO;
import dto.UserDTO;
import manager.ApplicationManager;
import manager.TestNGListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.PersonalBoardPage;

import java.lang.reflect.Method;
import java.util.Random;

@Listeners(TestNGListener.class)

public class BoardsTests extends ApplicationManager {
    UserDTO user = UserDTO.builder()
            .email("omyshalova@gmail.com")
            .password("Qw1235813.")
            .build();

    BoardsPage boardsPage = new BoardsPage(getDriver());


    @BeforeMethod
    public void loginBeforeBoards() {
        HomePage homePage = new HomePage(getDriver());
        boardsPage = homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user);
    }

    @Test
    public void createBoardPositive(Method method) {
        int i = new Random().nextInt(1000);
        BoardDTO board = BoardDTO.builder()
                .boardTitle("OMysh-" + i)
                .build();
        logger.info("{} starts with board title --> {}", method.getName(), board.getBoardTitle());
        Assert.assertTrue(
                boardsPage
                        .typeBoardTitle(board)
                        .clickBtnCreateSubmitPositive()
                        .isTextInElementPresent_nameBoard(board.getBoardTitle())) ;
    }

    @Test()
    public void createBoardNegative_emptyBoardTitle() {
        BoardDTO board = BoardDTO.builder()
                .boardTitle("  ")
                .build();
        Assert.assertFalse(
                boardsPage
                        .typeBoardTitle(board)
                        .clickBtnCreateSubmitNegative()
                        .isElementClickable_btnCreateSubmit(), "element is clickable");
    }

//    @Test()
//    public void createBoardNegative_emptyBoardTitle_Alex() {
//        BoardDTO board = BoardDTO.builder()
//                .boardTitle("  ")
//                .build();
//        HomePage homePage = new HomePage(getDriver());
//        Assert.assertFalse(homePage.clickBtnLogin()
//                .typeEmail(user)
//                .typePassword(user)
//                .typeBoardTitle(board)
//                .clickBtnCreateSubmitNegative()
//                .isElementClickable_btnCreateSubmit(), "element is clickable")
//        ;
//    }

    @Test
    public void deleteBoardPositiveTest() {
        int i = new Random().nextInt(1000);
        BoardDTO board = BoardDTO.builder()
                .boardTitle("OMysh-" + i)
                .build();
        PersonalBoardPage personalBoardPage = boardsPage
                .typeBoardTitle(board)
                .clickBtnCreateSubmitPositive();
        if (personalBoardPage.isTextInElementPresent_nameBoard(board.getBoardTitle())) {
            Assert.assertTrue(personalBoardPage.deleteBoard(board)
                    .isTextPopUpPresent());
        } else {
            Assert.fail("board isn't create");
        }
    }
}