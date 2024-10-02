import Utility.BaseDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Transperfect extends BaseDriver {
    String fisrtNameStr="Johnny";
    long phoneRondom= ThreadLocalRandom.current().nextLong(100000000L,1000000000L);

    @Test
    public void transperfect(){
        //Go to www.transperfect.com

        driver.get("https://www.transperfect.com/");
        wait.until(ExpectedConditions.urlToBe("https://www.transperfect.com/"));

        //Click on Industries in the top navigation bar

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Industries")));
        WebElement industriesBtn=driver.findElement(By.partialLinkText("Industries"));
        wait.until(ExpectedConditions.elementToBeClickable(industriesBtn));
        industriesBtn.click();

        //Click on Retail & E-commerce

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='t-page-cards-item-content'])[2]")));
        WebElement retailBtn=driver.findElement(By.xpath("(//div[@class='t-page-cards-item-content'])[2]"));
        js.executeScript("arguments[0].scrollIntoView(false);", retailBtn);
        actions.click(retailBtn).perform();

        //Wait 5 seconds

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Scroll down/move the screen until Client Stories are visible

        wait.until(ExpectedConditions.urlToBe("https://www.transperfect.com/industries/retail-and-ecommerce"));
        WebElement clientStroies=driver.findElement(By.xpath("//div[text()='Client Stories']"));
        actions.scrollToElement(clientStroies).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Client Stories']")));

        //Click on the search engine icon in the top navigation bar

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='t-search-link']")));
        WebElement searchBtn=driver.findElement(By.cssSelector("[class='t-search-link']"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        searchBtn.click();

        //Enter text "translation" in the Search text... textbox

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='search_api_fulltext']")));
        WebElement searchBox=driver.findElement(By.cssSelector("[name='search_api_fulltext']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='search_api_fulltext']")));
        searchBox.sendKeys("translation");
        wait.until(ExpectedConditions.textToBePresentInElementValue(searchBox,"translation"));

        //Delete the text you just entered

        searchBox.sendKeys(Keys.CONTROL+"a",Keys.DELETE);

        //Enter "quote" in the Search text... textbox

        searchBox.sendKeys("quote"+Keys.ENTER);
        wait.until(ExpectedConditions.textToBePresentInElementValue(searchBox,"quote"));

        //Wait for the "Request a Free Quote" search result to appear

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='field-content'])[6]")));

        //Click on Request a Free Quote

        WebElement quoteItem=driver.findElement(By.xpath("(//span[@class='field-content'])[6]"));
        wait.until(ExpectedConditions.elementToBeClickable(quoteItem));
        quoteItem.click();

        //Hover the mouse button over Website Localization to cause the popup with the description to appear

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@class='form-check-label'])[2]")));
        WebElement hoverWebsiteLocalization=driver.findElement(By.xpath("(//label[@class='form-check-label'])[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(hoverWebsiteLocalization));
        actions.moveToElement(hoverWebsiteLocalization).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id^='tooltipster']")));
        WebElement webFormDescription=driver.findElement(By.cssSelector("[id^='tooltipster']"));
        Assert.assertTrue(webFormDescription.getText().contains("International SEO/SEM"));

        //Tick the boxes for Translation Services and Legal Services

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[1]")));
        WebElement selectedTranslationServices=driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(selectedTranslationServices));
        selectedTranslationServices.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[7]")));
        WebElement selectedLegalServices=driver.findElement(By.xpath("(//input[@type='checkbox'])[7]"));
        wait.until(ExpectedConditions.elementToBeClickable(selectedLegalServices));
        selectedLegalServices.click();

        //Enter text into First Name text box

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("first_name")));
        WebElement firstName=driver.findElement(By.name("first_name"));
        firstName.sendKeys(fisrtNameStr);
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("first_name"),fisrtNameStr));

        //Generate a random number and enter it into Telephone text box

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone_work")));
        WebElement phoneNumber=driver.findElement(By.name("phone_work"));
        phoneNumber.sendKeys("+905"+phoneRondom);

        //Take a screenshot and save it to your desktop

        LocalDateTime localDateTime=LocalDateTime.now();
        DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm.ss");
        File fileInMemory = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String desktop=System.getProperty("user.home")+"/Desktop";
        try {
            FileUtils.copyFile(fileInMemory,new File(desktop+"\\screenShot"+localDateTime.format(formater)+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Change the website language from English to Italian

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-controls='links-wrapper']")));
        WebElement language=driver.findElement(By.cssSelector("[aria-controls='links-wrapper']"));
        actions.moveToElement(language).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='it']")));
        WebElement italianoLanguage=driver.findElement(By.xpath("//li[@class='it']"));
        italianoLanguage.click();

        //Open the Solutions (Soluzioni) page in a new tab

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href='/it/solutions'])[1]")));
        String soluzioniLink = driver.findElement(By.xpath("(//a[@href='/it/solutions'])[1]")).getAttribute("href");
        js.executeScript("window.open(arguments[0])", soluzioniLink);

        //Switch to new opened TAB

        String seluzionHandle = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(seluzionHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        //Close the browser

        driver.quit();
    }
}
