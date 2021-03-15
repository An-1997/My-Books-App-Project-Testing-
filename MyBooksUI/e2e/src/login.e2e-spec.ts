import { LoginPage } from './login.po';
import { element, by, browser, protractor } from 'protractor';
import { async, tick } from '@angular/core/testing';

describe('My-Books App', () => {
  let page: LoginPage;

  beforeEach(() => {
    page = new LoginPage();
    page.navigateTo();
    browser.driver.manage().window().maximize();
  });

  it('should display My Favourite Books as application title', () => {
    
    
    let spanElement = element(by.css('span')).getText();    
    expect(spanElement).toEqual('My Favourite Books');
  });

  it('Invalid Login test 01', async()=>{
    page.setusername('nash');
    browser.sleep(2000);
    page.clickOnLoginButton();
    browser.sleep(2000);
    let val = element(by.xpath('/html/body/div[1]')).getText();
    browser.sleep(2000);
    expect(val).toEqual('User Id and Password are mandatory');
  })

  it('Invalid Login test 02', async()=>{
    page.setPassword("nash123");
    browser.sleep(2000);
    page.clickOnLoginButton();
    browser.sleep(2000);
    let val = element(by.xpath('/html/body/div[1]')).getText();
    browser.sleep(2000);
    expect(val).toEqual('User Id and Password are mandatory');
  })

  it('Invalid Login Test 03', async()=>
  {
   page.setusername("govindan");
   page.setPassword("gd123");
   page.clickOnLoginButton();
   browser.sleep(3000);
   expect<any>(browser.getCurrentUrl()).toEqual('http://localhost:4200/login');
  });
  

  it('Valid Login Test01', async()=>
    {
     page.setusername("vishnu");
     page.setPassword("vishnu1234");
     page.clickOnLoginButton();
     browser.sleep(3000);
     expect<any>(browser.getCurrentUrl()).toEqual('http://localhost:4200/books/home');
    });
 
});
