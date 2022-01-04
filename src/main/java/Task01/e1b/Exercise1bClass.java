package Task01.e1b;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Exercise1bClass {
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
    final int SET_CPU_FAIL = 9;
    final int SET_RAM_FAIL = 10;
    final int SELECT_SOFTWARE_FAIL = 11;
    final int OPEN_CART_FAIL = 12;
    final int CHECK_CART_FAIL = 13;
    final int REMOVE_CART_FAIL = 14;
    String WEBDRIVER = "webdriver.chrome.driver";
    String WEBDRIVER_PATH = "c:\\chromedriver_win32\\chromedriver.exe";

    public int mainPage() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/build-your-own-expensive-computer-2";
        webDriver.get(url);
        String expectedTitle = "Demo Web Shop. Build your own expensive computer";
        String actualTitle = webDriver.getTitle();
        if (!expectedTitle.equals(actualTitle)) exitCode =  MAIN_PAGE_FAIL;  // return error code - unable to open main page
        webDriver.quit();
        return exitCode;
    }

    public int setCPU() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/build-your-own-expensive-computer-2";
        webDriver.get(url);

        try {
            String fastCPU_Selector = "#product_attribute_74_5_26_82";
            webDriver.findElement(By.cssSelector(fastCPU_Selector)).click();

            String RAM_Selector = "#product_attribute_74_6_27_85";
            webDriver.findElement(By.cssSelector(RAM_Selector)).click();
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            exitCode = SET_CPU_FAIL;
        }

        webDriver.quit();
        return exitCode;
    }

    public int setRAM() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/build-your-own-expensive-computer-2";
        webDriver.get(url);
        try {
            String fastCPU_Selector = "#product_attribute_74_5_26_82";
            webDriver.findElement(By.cssSelector(fastCPU_Selector)).click();

        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            webDriver.quit();
            exitCode = TEST_PREPARATION_FAIL;
        }

        try {
            String RAM_Selector = "#product_attribute_74_6_27_85";
            webDriver.findElement(By.cssSelector(RAM_Selector)).click();
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            exitCode = SET_RAM_FAIL;
        }

        webDriver.quit();
        return exitCode;
    }

    public int setSoftware() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/build-your-own-expensive-computer-2";
        webDriver.get(url);
        try {
            //set CPU
            String fastCPU_Selector = "#product_attribute_74_5_26_82";
            webDriver.findElement(By.cssSelector(fastCPU_Selector)).click();
            //set RAM
            String RAM_Selector = "#product_attribute_74_6_27_85";
            webDriver.findElement(By.cssSelector(RAM_Selector)).click();
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            webDriver.quit();
            exitCode = TEST_PREPARATION_FAIL;
            return exitCode;
        }

        try {
            // select all software
            String optionListSelector = "#product-details-form > div > div.product-essential > div.overview > div.attributes > dl > dd:nth-child(8) > ul";
            List<WebElement> softwareOptions = webDriver.findElements(By.cssSelector(optionListSelector + ">li>input"));
            for (WebElement  element : softwareOptions) {
                if (!element.isSelected()) element.click();
            }
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            exitCode = SELECT_SOFTWARE_FAIL;
        }

        webDriver.quit();
        return exitCode;
    }

    public int addToCart() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/build-your-own-expensive-computer-2";
        webDriver.get(url);
        try {
            //set CPU
            String fastCPU_Selector = "#product_attribute_74_5_26_82";
            webDriver.findElement(By.cssSelector(fastCPU_Selector)).click();
            //set RAM
            String RAM_Selector = "#product_attribute_74_6_27_85";
            webDriver.findElement(By.cssSelector(RAM_Selector)).click();
            // select all software
            String optionListSelector = "#product-details-form > div > div.product-essential > div.overview > div.attributes > dl > dd:nth-child(8) > ul";
            List<WebElement> softwareOptions = webDriver.findElements(By.cssSelector(optionListSelector + ">li>input"));
            for (WebElement  element : softwareOptions) {
                if (!element.isSelected()) element.click();
            }
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            webDriver.quit();
            exitCode = TEST_PREPARATION_FAIL;
            return exitCode;
        }

        try {
            // check shopping cart counter before
            String counterSelector = "#topcartlink > a > span.cart-qty";
            String beforeCounterString = webDriver.findElement(By.cssSelector(counterSelector)).getText();
            int beforeCounter = Integer.parseInt(beforeCounterString.substring(1, beforeCounterString.length()-1));
            // add to cart button click
            String addToCartSelector = "#add-to-cart-button-74";
            webDriver.findElement(By.cssSelector(addToCartSelector)).click();
            Thread.sleep(2000);
            // check shopping cart counter after
            String afterCounterString = webDriver.findElement(By.cssSelector(counterSelector)).getText();
            int afterCounter = Integer.parseInt(afterCounterString.substring(1, beforeCounterString.length()-1));
            if (afterCounter - beforeCounter != 1) exitCode = ADD_TO_CART_FAIL;
        } catch (NoSuchElementException | InterruptedException exception) {
            exception.printStackTrace();
            exitCode = ADD_TO_CART_FAIL;
        }

        webDriver.quit();
        return exitCode;
    }

    public int openCart() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/build-your-own-expensive-computer-2";
        webDriver.get(url);
        try {
            //set CPU
            String fastCPU_Selector = "#product_attribute_74_5_26_82";
            webDriver.findElement(By.cssSelector(fastCPU_Selector)).click();
            //set RAM
            String RAM_Selector = "#product_attribute_74_6_27_85";
            webDriver.findElement(By.cssSelector(RAM_Selector)).click();
            // select all software
            String optionListSelector = "#product-details-form > div > div.product-essential > div.overview > div.attributes > dl > dd:nth-child(8) > ul";
            List<WebElement> softwareOptions = webDriver.findElements(By.cssSelector(optionListSelector + ">li>input"));
            for (WebElement  element : softwareOptions) {
                if (!element.isSelected()) element.click();
            }
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            webDriver.quit();
            exitCode = TEST_PREPARATION_FAIL;
            return exitCode;
        }

        try {
            // get price for all selection
            // get base price
            String basePriceSelector = "#product-details-form > div > div.product-essential > div.overview > div.prices > div > span";
            double priceBase = Double.parseDouble(webDriver.findElement(By.cssSelector(basePriceSelector)).getText());
            // get CPU price
            String radioCPU_Selector = "#product-details-form > div > div.product-essential > div.overview > div.attributes > dl > dd:nth-child(2) > ul";
            List<WebElement> listRadioCPU = webDriver.findElements(By.cssSelector(radioCPU_Selector + ">li>input"));
            List<WebElement> listRadioCPU_Label = webDriver.findElements(By.cssSelector(radioCPU_Selector + ">li>label"));
            String strPriceCPU = "";
            for (int i = 0; i < listRadioCPU.size(); i++) {
                if (listRadioCPU.get(i).isSelected()) strPriceCPU = listRadioCPU_Label.get(i).getText();
            }
            double priceCPU = strPriceToDouble(strPriceCPU);
            // get RAM price
            String radioRAM_Selector = "#product-details-form > div > div.product-essential > div.overview > div.attributes > dl > dd:nth-child(4) > ul";
            List<WebElement> listRadioRAM = webDriver.findElements(By.cssSelector(radioRAM_Selector + ">li>input"));
            List<WebElement> listRadioRAM_Label = webDriver.findElements(By.cssSelector(radioRAM_Selector + ">li>label"));
            String strPriceRAM = "";
            for (int i = 0; i < listRadioRAM.size(); i++) {
                if (listRadioRAM.get(i).isSelected()) strPriceRAM = listRadioRAM_Label.get(i).getText();
            }
            double priceRAM = strPriceToDouble(strPriceRAM);
            // get HDD price
            String radioHDD_Selector = "#product-details-form > div > div.product-essential > div.overview > div.attributes > dl > dd:nth-child(6) > ul";
            List<WebElement> listRadioHDD = webDriver.findElements(By.cssSelector(radioHDD_Selector + ">li>input"));
            List<WebElement> listRadioHDD_Label = webDriver.findElements(By.cssSelector(radioHDD_Selector + ">li>label"));
            String strPriceHDD = "";
            for (int i = 0; i < listRadioHDD.size(); i++) {
                if (listRadioHDD.get(i).isSelected()) strPriceHDD = listRadioHDD_Label.get(i).getText();
            }
            double priceHDD = strPriceToDouble(strPriceHDD);
            // get software price
            String checkSoft_Selector = "#product-details-form > div > div.product-essential > div.overview > div.attributes > dl > dd:nth-child(8) > ul";
            List<WebElement> listCheckSoft = webDriver.findElements(By.cssSelector(checkSoft_Selector + ">li>input"));
            List<WebElement> listCheckSoft_Label = webDriver.findElements(By.cssSelector(checkSoft_Selector + ">li>label"));
            double priceSoft = 0;
            String strPriceSoft = "";
            for (int i = 0; i < listCheckSoft.size(); i++) {
                if (listCheckSoft.get(i).isSelected()) {
                    String itemPrice = listCheckSoft_Label.get(i).getText();
                    strPriceSoft = strPriceSoft + "\n" + itemPrice;
                    priceSoft += strPriceToDouble(itemPrice);
                }
            }
            double manualPrice = priceBase + priceCPU + priceRAM + priceHDD + priceSoft;
            // add to cart button click
            String addToCartSelector = "#add-to-cart-button-74";
            webDriver.findElement(By.cssSelector(addToCartSelector)).click();
            Thread.sleep(2000);
            // open cart page
            String openCartSelector = "#topcartlink";
            webDriver.findElement(By.cssSelector(openCartSelector)).click();
            Thread.sleep(2000);
            // get options price, exception will raise if no items in cart
            String optionsSelector = "body > div.master-wrapper-page > div.master-wrapper-content > div.master-wrapper-main > div > div > div.page-body > div > form > table > tbody > tr > td.product > div.attributes";
            String optionsMultiline = webDriver.findElement(By.cssSelector(optionsSelector)).getText();
            String[] lines = optionsMultiline.split("\\r?\\n");
            double optionsPrice = 0;
            for (String line : lines) {
                optionsPrice += strPriceToDouble(line);
            }
            String unitPriceSelector = "body > div.master-wrapper-page > div.master-wrapper-content > div.master-wrapper-main > div > div > div.page-body > div > form > table > tbody > tr > td.unit-price.nobr > span.product-unit-price";
            double unitPrice = Double.parseDouble(webDriver.findElement(By.cssSelector(unitPriceSelector)).getText());
            if (priceBase + optionsPrice != manualPrice || manualPrice != unitPrice) exitCode = OPEN_CART_FAIL;
        } catch (NoSuchElementException | InterruptedException exception) {
            exception.printStackTrace();
            exitCode = OPEN_CART_FAIL;
        }

        webDriver.quit();
        return exitCode;
    }

    public int removeFromCart() {
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        int exitCode = TEST_SUCCESS;

        // open main page
        String url = "http://demowebshop.tricentis.com/build-your-own-expensive-computer-2";
        webDriver.get(url);
        try {
            //set CPU
            String fastCPU_Selector = "#product_attribute_74_5_26_82";
            webDriver.findElement(By.cssSelector(fastCPU_Selector)).click();
            //set RAM
            String RAM_Selector = "#product_attribute_74_6_27_85";
            webDriver.findElement(By.cssSelector(RAM_Selector)).click();
            // select all software
            String optionListSelector = "#product-details-form > div > div.product-essential > div.overview > div.attributes > dl > dd:nth-child(8) > ul";
            List<WebElement> softwareOptions = webDriver.findElements(By.cssSelector(optionListSelector + ">li>input"));
            for (WebElement  element : softwareOptions) {
                if (!element.isSelected()) element.click();
            }
            // add to cart button click
            String addToCartSelector = "#add-to-cart-button-74";
            webDriver.findElement(By.cssSelector(addToCartSelector)).click();
            Thread.sleep(2000);
            // open cart page
            String openCartSelector = "#topcartlink";
            webDriver.findElement(By.cssSelector(openCartSelector)).click();
            Thread.sleep(2000);
        } catch (NoSuchElementException | InterruptedException exception) {
            exception.printStackTrace();
            webDriver.quit();
            exitCode = TEST_PREPARATION_FAIL;
            return exitCode;
        }

        try {
            // check remove checkbox
            String removeCheckSelector = "body > div.master-wrapper-page > div.master-wrapper-content > div.master-wrapper-main > div > div > div.page-body > div > form > table > tbody > tr > td.remove-from-cart > input[type=checkbox]";
            webDriver.findElement(By.cssSelector(removeCheckSelector)).click();
            // click Update shopping cart button
            String submitButtonSelector = "body > div.master-wrapper-page > div.master-wrapper-content > div.master-wrapper-main > div > div > div.page-body > div > form > div.buttons > div > input.button-2.update-cart-button";
            webDriver.findElement(By.cssSelector(submitButtonSelector)).click();
            String confirmationStringSelector = "body > div.master-wrapper-page > div.master-wrapper-content > div.master-wrapper-main > div > div.page.shopping-cart-page > div.page-body > div";
            // get delete confirmation
            String expectedString = "Your Shopping Cart is empty!";
            String actualString = webDriver.findElement(By.cssSelector(confirmationStringSelector)).getText();
            if (!actualString.contains(expectedString)) exitCode = REMOVE_CART_FAIL;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            exitCode = REMOVE_CART_FAIL;
        }

        webDriver.quit();
        return exitCode;
    }

    private double strPriceToDouble(String inputString) {
        int startIdx = inputString.indexOf('[');
        int finishIdx = inputString.indexOf(']');
        if (startIdx < 0 || finishIdx < 0) return 0;
        String strippedString = inputString.substring(startIdx + 1, finishIdx);
        return Double.parseDouble(strippedString);
    }

}
