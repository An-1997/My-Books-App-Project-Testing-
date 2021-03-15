import { browser, by, element } from 'protractor';

export class LoginPage {
    public baseUrl ='http://localhost:4200/';

  navigateTo(): any
  {
    browser.ignoreSynchronization= true;
   // browser.waitForAngularEnabled;
    return browser.get(this.baseUrl);

  }

  getbrowserTitle(): any 
  {
    return browser.getTitle();
  }

  setusername(word: string): any{
    browser.sleep(5000);
    element(by.id("userId")).sendKeys(word);
  }

  setPassword(word: string): any{
    browser.sleep(5000);
    element(by.id("password")).sendKeys(word);
  }

  clickOnLoginButton(): any{
      element(by.xpath("/html/body/app-root/auth-login/div/form/div[3]/button[1]/span")).click();
  }


}