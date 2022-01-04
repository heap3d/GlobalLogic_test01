package Task01.e1a;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Exercise1aClass {
    final int TEST_PREPARATION_FAIL = -1;
    final int TEST_SUCCESS = 0;
    final int MAIN_PAGE_FAIL = 1;
    final int MAIN_MENU_FAIL = 2;
    final int DESKTOP_PAGE_FAIL = 3;
    final int SET_PAGE_SIZE_FAIL = 4;
    final int RESULT_PAGE_SIZE_FAIL = 5;
    final int SET_SORT_FAIL = 6;
    final int RESULT_SORT_FAIL = 7;
    final int ADD_TO_CART_FAIL = 8;
    String WEBDRIVER = "webdriver.chrome.driver";
    String WEBDRIVER_PATH = "c:\\chromedriver_win32\\chromedriver.exe";

    public int mainPage() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/";
        webDriver.get(url);
        String expectedTitle = "Demo Web Shop";
        String actualTitle = webDriver.getTitle();
        if (!expectedTitle.equals(actualTitle)) exitCode = MAIN_PAGE_FAIL;  // return error code - unable to open main page

        webDriver.quit();
        return exitCode;
    }

    public int mainMenu() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/";
        webDriver.get(url);
        try {
            // get main menu
            String computersSelector = "ul.top-menu > li:nth-child(2)";
            WebElement menuComp = webDriver.findElement(By.cssSelector(computersSelector));
            // hover over menu item
            Actions action = new Actions(webDriver);
            action.moveToElement(menuComp).perform();
            // click on menu item
            String desktopsSelector = "ul.top-menu > li:nth-child(2) > ul > li:nth-child(1) > a";
            WebElement menuDesktops = webDriver.findElement(By.cssSelector(desktopsSelector));
            menuDesktops.click();
            String expectedTitle = "Demo Web Shop. Desktops";
            String actualTitle = webDriver.getTitle();
            if (!expectedTitle.equals(actualTitle))
                exitCode = DESKTOP_PAGE_FAIL;  // return error code - unable open Desktops page
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            exitCode = MAIN_MENU_FAIL;  // fail to click on menu Computers > Desktops
        }

        webDriver.quit();
        return exitCode;
    }

    public int pageSize() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/";
        webDriver.get(url);
        //test preparation
        try {
            // get main menu
            String computersSelector = "ul.top-menu > li:nth-child(2)";
            WebElement menuComp = webDriver.findElement(By.cssSelector(computersSelector));
            // hover over menu item
            Actions action = new Actions(webDriver);
            action.moveToElement(menuComp).perform();
            // click on menu item
            String desktopsSelector = "ul.top-menu > li:nth-child(2) > ul > li:nth-child(1) > a";
            WebElement menuDesktops = webDriver.findElement(By.cssSelector(desktopsSelector));
            menuDesktops.click();
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            webDriver.quit();
            exitCode = TEST_PREPARATION_FAIL;
            return exitCode;
        }

        try {
            // get Display dropbox
            WebElement pageSize = webDriver.findElement(By.cssSelector("#products-pagesize"));
            // select "4"
            Select selectPageSize = new Select(pageSize);
            selectPageSize.selectByVisibleText("4");
            // get product grid
            WebElement productGrid = webDriver.findElement(By.cssSelector("div.product-grid"));
            List<WebElement> elements = productGrid.findElements(By.cssSelector("div.item-box"));
            // check result product grid
            int expectedPageSize = 4;
            int actualPageSize = elements.size();
            if (expectedPageSize != actualPageSize) exitCode = RESULT_PAGE_SIZE_FAIL;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            exitCode = SET_PAGE_SIZE_FAIL;  // fail to click Display 4 per page
        }

        webDriver.quit();
        return exitCode;
    }

    public int sortPage() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/";
        webDriver.get(url);
        // test preparation
        try {
            // get main menu
            String computersSelector = "ul.top-menu > li:nth-child(2)";
            WebElement menuComp = webDriver.findElement(By.cssSelector(computersSelector));
            // hover over menu item
            Actions action = new Actions(webDriver);
            action.moveToElement(menuComp).perform();
            // click on menu item
            String desktopsSelector = "ul.top-menu > li:nth-child(2) > ul > li:nth-child(1) > a";
            WebElement menuDesktops = webDriver.findElement(By.cssSelector(desktopsSelector));
            menuDesktops.click();
            // get Display dropbox
            WebElement pageSize = webDriver.findElement(By.cssSelector("#products-pagesize"));
            // select "4"
            Select selectPageSize = new Select(pageSize);
            selectPageSize.selectByVisibleText("4");
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            webDriver.quit();
            exitCode = TEST_PREPARATION_FAIL;
            return exitCode;
        }
        try {
            // get sort dropbox
            WebElement sortBy = webDriver.findElement(By.cssSelector("#products-orderby"));
            // select sort method
            Select selectSortBy = new Select(sortBy);
            selectSortBy.selectByVisibleText("Price: High to Low");
            String priceSelector = "div.product-grid > div:nth-child(1) > div > div.details > div.add-info > div.prices > span";
            WebElement itemPrice = webDriver.findElement(By.cssSelector(priceSelector));
            String expected = "1800.00";
            String actual = itemPrice.getText();
            if (!expected.equals(actual)) exitCode = RESULT_SORT_FAIL;

            String addToCartSelector = "div.product-grid > div:nth-child(1) > div > div.details > div.add-info > div.buttons > input";
            WebElement firstItemCart = webDriver.findElement(By.cssSelector(addToCartSelector));
            firstItemCart.click();
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            exitCode = SET_SORT_FAIL;
        }

        webDriver.quit();
        return exitCode;
    }

    public int addToCart() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/";
        webDriver.get(url);
        try {
            // get main menu
            String computersSelector = "ul.top-menu > li:nth-child(2)";
            WebElement menuComp = webDriver.findElement(By.cssSelector(computersSelector));
            // hover over menu item
            Actions action = new Actions(webDriver);
            action.moveToElement(menuComp).perform();
            // click on menu item
            String desktopsSelector = "ul.top-menu > li:nth-child(2) > ul > li:nth-child(1) > a";
            WebElement menuDesktops = webDriver.findElement(By.cssSelector(desktopsSelector));
            menuDesktops.click();
            // get Display dropbox
            WebElement pageSize = webDriver.findElement(By.cssSelector("#products-pagesize"));
            // select "4"
            Select selectPageSize = new Select(pageSize);
            selectPageSize.selectByVisibleText("4");
            // get sort dropbox
            WebElement sortBy = webDriver.findElement(By.cssSelector("#products-orderby"));
            // select sort method
            Select selectSortBy = new Select(sortBy);
            selectSortBy.selectByVisibleText("Price: High to Low");
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            webDriver.quit();
            exitCode = TEST_PREPARATION_FAIL;
            return exitCode;
        }

        try {
            String addToCartSelector = "div.product-grid > div:nth-child(1) > div > div.details > div.add-info > div.buttons > input";
            WebElement firstItemCart = webDriver.findElement(By.cssSelector(addToCartSelector));
            firstItemCart.click();
            String cartQtySelector = "#topcartlink > a > span.cart-qty";
            String expected = "(1)";
            String actual = webDriver.findElement(By.cssSelector(cartQtySelector)).getText();
            if (!expected.equals(actual)) exitCode = ADD_TO_CART_FAIL;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            exitCode = ADD_TO_CART_FAIL;
        }
        
        webDriver.quit();
        return exitCode;
    }
}
