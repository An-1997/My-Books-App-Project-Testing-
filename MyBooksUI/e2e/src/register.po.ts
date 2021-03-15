import {browser, by , element} from 'protractor';
export class RegisterPage{
    public baseUrl ='http://localhost:4200/';
    navigateTo(): any
  {
    browser.ignoreSynchronization= true;
   // browser.waitForAngularEnabled;
    return browser.get(this.baseUrl);
  }
   clickOnRegisterButton(): any{
    return element (by.xpath("/html/body/app-root/auth-login/div/form/div[3]/button[2]/span")).click();
    }
    setFirstname(word: string): any{
        browser.sleep(5000);
        element(by.id("firstName")).sendKeys(word);
      }
      setLastname(word: string): any{
        browser.sleep(5000);
        element(by.id("lastName")).sendKeys(word);
      }
    setuserid(word: string): any{
        browser.sleep(5000);
        element(by.id("userId")).sendKeys(word);
      }
      setPassword(word: string): any{
        browser.sleep(5000);
        element(by.id("password")).sendKeys(word);
      }
      clickOnRegisterUserButton(): any{
        return element (by.xpath("/html/body/app-root/auth-register/div/form/div[5]/button[1]/span")).click();
        }
      clickOnClearButton(): any{
        element(by.xpath("/html/body/app-root/auth-register/div/form/div[5]/button[2]/span")).click();
      }
}