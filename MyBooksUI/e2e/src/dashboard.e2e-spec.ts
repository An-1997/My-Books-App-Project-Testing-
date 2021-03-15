import { DashboardPage } from './dashboard.po';

import { element, by, browser, protractor } from 'protractor';
import { async, tick } from '@angular/core/testing';

describe('My-Books App', () => {
  let page: DashboardPage;
  

  beforeEach(() => {
    page = new DashboardPage();
    page.navigateTo();
  
  browser.driver.manage().window().maximize();
  });
 
  it('should search for book and add the same 01',async()=>{
   
    page.searchForBook("hunter");
    browser.actions().sendKeys(protractor.Key.ENTER).perform();
    browser.sleep(3000);
    browser.driver.executeScript('window.scrollTo(94,188);').then(function() {
     page.selectBookFromList();    
 })
   browser.sleep(2000);
   let val = element(by.xpath("/html/body/div[1]")).getText();
   browser.sleep(1000);
   expect(val).toEqual(`Laws, etc added to your favourites`)
   
 })
 
it('should show error when same book is added',async()=>{
  
   page.clickOnAll();
   page.clickOnTitle();
   browser.sleep(2000);
   browser.driver.executeScript('window.scrollTo(0,0);').then(function() {
    page.searchForBook("hunter");
   })
    browser.actions().sendKeys(protractor.Key.ENTER).perform();
    browser.sleep(3000);
    browser.driver.executeScript('window.scrollTo(94,188);').then(function() {
     page.selectBookFromList();    
 })
   browser.sleep(2000);
   let val = element(by.xpath("/html/body/div[1]")).getText();
   browser.sleep(1000);
   expect(val).toEqual(`Book already available in your Favourites`)
 
})

it('should go to favourites to verify',async()=>{
  browser.sleep(3000);
  browser.driver.executeScript('window.scrollTo(0,0);').then(function() {
   page.clickOnFavourite();
  })
   browser.sleep(3000);
   page.clickOnDashboard();
})

it('should search for book and add the same 02',async()=>{
   
   page.searchForBook("gama");
   browser.actions().sendKeys(protractor.Key.ENTER).perform();
   browser.sleep(3000);
   browser.driver.executeScript('window.scrollTo(94,188);').then(function() {
    page.selectBookFromList();    
})
  browser.sleep(2000);
  let val = element(by.xpath("/html/body/div[1]")).getText();
  browser.sleep(1000);
  expect(val).toEqual(`Gama added to your favourites`)
  
})

it('should search for book and add the same 03',async()=>{
  
  browser.driver.executeScript('window.scrollTo(0,0);').then(function() {
  page.searchForBook("gama");
  browser.actions().sendKeys(protractor.Key.ENTER).perform();
  })
  
  browser.sleep(3000);
  browser.driver.executeScript('window.scrollTo(94,188);').then(function() {
   page.selectBookFromList02();    
})
 browser.sleep(2000);
 let val = element(by.xpath("/html/body/div[1]")).getText();
 browser.sleep(1000);
 expect(val).toEqual(`Gama added to your favourites`)
 
})




})