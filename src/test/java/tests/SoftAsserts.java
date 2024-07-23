package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void test(){
        System.out.println("=======================");
        softAssert.assertTrue(false, "message assert true");
        System.out.println("+++++++++++++++++++++++");
        softAssert.assertEquals(1,1, "message assert equals");
        softAssert.assertAll();
    }
}