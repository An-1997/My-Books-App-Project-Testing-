import { ContainerPage } from './container.po';

import { element, by, browser, protractor } from 'protractor';
import { async, tick } from '@angular/core/testing';

describe('My-Books App', () => {
  let page: ContainerPage;
  

  beforeEach(() => {
    page = new ContainerPage();
    page.navigateTo();
  
  browser.driver.manage().window().maximize();
  });

  it('should delete the first book', async()=>{
      browser.sleep(2000);
      browser.driver.executeScript('window.scrollTo(94,188);').then(function() {
      page.clickOnDelete01();
      })
      browser.sleep(2000);
      
      let val = element(by.xpath('/html/body/div[1]')).getText();
      browser.sleep(2000);
      expect(val).toEqual('Laws, etc deleted from your favourites');
  })

  it('should go to favourites to verify',async()=>{
    browser.sleep(3000);
    browser.driver.executeScript('window.scrollTo(0,0);').then(function() {
     page.clickOnDashboard();
    })
     browser.sleep(3000);
     page.clickOnFavourites();
  })

  it('should delete the second book', async()=>{
    browser.sleep(4000);
    browser.driver.executeScript('window.scrollTo(94,188);').then(function() {
    page.clickOnDelete02();
    })
    browser.sleep(2000);
    
    let val = element(by.xpath('/html/body/div[1]')).getText();
    browser.sleep(2000);
    expect(val).toEqual('Gama deleted from your favourites');
})


  it('deletion failure', async()=>{
    browser.sleep(3000);
    browser.driver.executeScript('window.scrollTo(0,0);').then(function() {
     
     })
     browser.sleep(2000);
    
      browser.sleep(2000);
     let book = element(by.xpath("/html/body/app-root/book-favourite/div/book-container/div/book-thumbnail[1]/mat-card/mat-card-header/div/mat-card-title")).isPresent();
     expect(book).toEqual(false);
      
  })
  it("should logout", async()=>{
    browser.sleep(2000);
    browser.driver.executeScript('window.scrollTo(0,0);').then(function() {
       page.clickOnLogout();
    })
    browser.sleep(1000);
    expect(browser.getCurrentUrl()).toEqual("http://localhost:4200/login");
  })
})