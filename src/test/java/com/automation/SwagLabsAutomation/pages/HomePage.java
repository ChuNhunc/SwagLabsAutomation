package com.automation.SwagLabsAutomation.pages;

import com.automation.commons.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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



}
