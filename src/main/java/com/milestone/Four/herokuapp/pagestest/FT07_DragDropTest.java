package herokuapp.pagestest;


import herokuapp.basetest.BaseTest;
import utility.DriverFactory;
import herokuapp.pages.DragDropPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FT07_DragDropTest extends BaseTest {

    @Test
    public void verifyDragAndDrop() {

        // Navigate to Drag and Drop page
        DriverFactory.getDriver().findElement(By.linkText("Drag and Drop")).click();

        DragDropPage page = new DragDropPage(DriverFactory.getDriver());

        // Perform drag and drop
        page.dragAtoB();

        // Validate the swap
        String colATitle = page.getColumnATitle();
        Assert.assertTrue(colATitle.equals("B"), 
                "Expected Column A to contain 'B' after drag-drop!");

        String colBTitle = page.getColumnBTitle();
        Assert.assertTrue(colBTitle.equals("A"),
                "Expected Column B to contain 'A' after drag-drop!");
    }
}
