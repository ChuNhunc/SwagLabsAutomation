package com.automation.SwagLabsAutomation.pages;

import com.automation.commons.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    public void HomePage(WebDriver driver) {
        this.driver = driver;
    }
    private Integer itemsInCart = 0;
    private List<WebElement> inventoryItemList = driver.findElements(By.className("inventory_item"));
    private By cartIcon = By.className("shopping_cart_link");
    private By inventoryItemName = By.className("inventory_item_name");
    private By inventoryItemNumber = By.className("shopping_cart_badge");
    private By inventoryButton = By.className("btn_inventory");
    private By inventoryPrice = By.className("inventory_item_price");
    private By sortDropdown = By.className("product_sort_container");
    private By menuButton = By.className("bm-burger-button");
    private By menu = By.className("bm-wrap");

    public void clickAddToCartBtn(int index) {
        inventoryItemList.get(index).findElement(inventoryButton).click();
    }

    public String getInventoryBtnText(int index) {
        WebElement inventoryBtn = inventoryItemList.get(index).findElement(inventoryButton);
        return inventoryBtn.getText();
    }

    public double getInventoryItemPrice(int index) {
        WebElement inventoryItemPrice = inventoryItemList.get(index).findElement(inventoryPrice);
        String priceText = inventoryItemPrice.getText().replace("$", "");
        return Double.parseDouble(priceText);
    }

    public Integer getInventoryItemQuantity() {
        return Integer.parseInt(WebUI.getText(inventoryItemNumber));
    }

    public void clickMenuButton() {
        WebUI.clickElement(menuButton);
    }

    public void clickSortButton() {
        WebUI.clickElement(sortDropdown);
    }

    public void sortAtoZ() {
        clickSortButton();
        WebUI.selectDropdownByValue(sortDropdown, "az");
    }

    public void sortZtoA() {
        clickSortButton();
        WebUI.selectDropdownByValue(sortDropdown, "za");
    }

    public void sortLowToHigh() {
        clickSortButton();
        WebUI.selectDropdownByValue(sortDropdown, "lohi");
    }

    public void sortHighToLow() {
        clickSortButton();
        WebUI.selectDropdownByValue(sortDropdown, "hilo");
    }

    public void verifyMenuIsDisplayed() {
        WebUI.verifyElementIsDisplayed(menu);
    }

    public void verifyInventoryButtonText(String expectedText, int index) {
        String btnText = getInventoryBtnText(index);
        WebUI.assertEquals(expectedText, btnText, "Inventory button text does not match the expected text.");
    }

    public void verifyAddToCartSuccessfully(int index) {
        this.itemsInCart = Integer.parseInt(WebUI.getText(inventoryItemNumber));
        clickAddToCartBtn(index);
        WebUI.assertEquals(Integer.parseInt(WebUI.getText(inventoryItemNumber)), this.itemsInCart + 1, "Item was not added to cart successfully.");
    }

    public void verifyRemoveFromCartSuccessfully(int index) {
        this.itemsInCart = Integer.parseInt(WebUI.getText(inventoryItemNumber));
        clickAddToCartBtn(index);
        WebUI.assertEquals(Integer.parseInt(WebUI.getText(inventoryItemNumber)), this.itemsInCart - 1, "Item was not removed from cart successfully.");
    }

    public void verifySortAtoZSuccessfully() {
        sortAtoZ();
        List<String> actualList = new ArrayList<>();
        for(WebElement item : inventoryItemList) {
            String itemName = item.findElement(inventoryItemName).getText();
            actualList.add(itemName.trim());
        }
        List<String> expectedList = new ArrayList<>(actualList);
        Collections.sort(expectedList);
        WebUI.assertEquals(actualList, expectedList, "List is not sort A to Z");
    }

    public void verifySortZtoASuccessfully() {
        sortZtoA();
        List<String> actualList = new ArrayList<>();
        for(WebElement item : inventoryItemList) {
            String itemName = item.findElement(inventoryItemName).getText();
            actualList.add(itemName.trim());
        }
        List<String> expectedList = new ArrayList<>(actualList);
        Collections.sort(expectedList, Collections.reverseOrder());
        WebUI.assertEquals(actualList, expectedList, "List is not sort Z to A");
    }

    public void verifySortPriceLowToHighSuccessfully() {
        sortLowToHigh();
        List<Double> actualList = new ArrayList<>();
        for(WebElement item : inventoryItemList) {
            String itemPrice = item.findElement(inventoryPrice).getText().replace("$", "");
            actualList.add(Double.parseDouble(itemPrice.trim()));
        }
        List<Double> expectedList = new ArrayList<>(actualList);
        Collections.sort(expectedList);
        WebUI.assertEquals(actualList, expectedList, "List is not sort price low to high");
    }

    public void verifySortPriceHighToLowSuccessfully() {
        sortHighToLow();
        List<Double> actualList = new ArrayList<>();
        for(WebElement item : inventoryItemList) {
            String itemPrice = item.findElement(inventoryPrice).getText().replace("$", "");
            actualList.add(Double.parseDouble(itemPrice.trim()));
        }
        List<Double> expectedList = new ArrayList<>(actualList);
        Collections.sort(expectedList, Collections.reverseOrder());
        WebUI.assertEquals(actualList, expectedList, "List is not sort price high to low");
    }

}
