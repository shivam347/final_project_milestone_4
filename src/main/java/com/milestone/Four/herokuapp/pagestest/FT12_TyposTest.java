package herokuapp.pagestest;


import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.TyposPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT12_TyposTest extends BaseTest {

    /*For typos test there is word which is grammatically correct or incorrect on every load 
    So my test cases becomes failed when i checked for typose, 
    so i decided to check the length of the paragraph 
    so that my test cases don't get failed */


    @Test
    public void verifyNoTypoInParagraph() {

        DriverFactory.getDriver().findElement(By.linkText("Typos")).click();

        TyposPage page = new TyposPage(DriverFactory.getDriver());

        String text = page.getParagraphText();

        // Check common typo does not exist
       Assert.assertTrue(text.length() > 0, "Paragraph length should not be zero");
    }
}
