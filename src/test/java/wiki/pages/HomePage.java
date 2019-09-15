package wiki.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class HomePage {

    public WebDriver driver;
    public String pageTitle ="Wikipedia";


    public void confirmAtHomePage(WebDriver driver){
        Assert.assertTrue(driver.getTitle().contains(pageTitle));
    }

    public void enterSearchTextAndSumbit(String searchFor, WebDriver driver){
       WebElement searchTextBox = driver.findElement(By.xpath("//*[@id='searchInput']"));
       searchTextBox.click();
       searchTextBox.sendKeys(searchFor);
       WebElement submitButton = driver.findElement(By.className("searchButton"));
       submitButton.click();

    }

    public void selectMatchedRecord(String selectText, WebDriver driver){
       String textXpath = "//a[@title=\'".concat(selectText).concat("\']");
       WebElement suggestedSearchText = driver.findElement(By.xpath(textXpath));
       suggestedSearchText.click();

    }
}
