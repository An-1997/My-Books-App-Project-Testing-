import { browser, by, element } from 'protractor';

export class DashboardPage {
    public baseUrl ='http://localhost:4200/books/home';

  navigateTo(): any
  {
    browser.ignoreSynchronization= true;
   // browser.waitForAngularEnabled;
    return browser.get(this.baseUrl);

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

  clickOnLogoutButton(): any{
    return element(by.xpath("/html/body/app-root/mat-toolbar/button[3]")).click();
  }

  clickOnDashboard(): any{
      return element(by.xpath("/html/body/app-root/mat-toolbar/button[1]/span")).click();
  }

  clickOnFavourite(): any{
      return element(by.xpath("/html/body/app-root/mat-toolbar/button[2]/span")).click();
  }

  searchForBook(word: string): any{
    browser.sleep(5000);
    element(by.xpath('/html/body/app-root/book-dashboard/div/form/mat-form-field[2]/div/div[1]/div/input')).sendKeys(word);
  }

  clickSearchForBook(): any{
    browser.sleep(5000);
    element(by.xpath('/html/body/app-root/book-dashboard/div/form/mat-form-field[2]/div/div[1]/div/input')).click()
  }

  selectBookFromList(): any{
    browser.sleep(5000);
    element.all(by.xpath("/html/body/app-root/book-dashboard/div/book-container/div/book-thumbnail[1]/mat-card/mat-card-actions/button/span")).click();
  }
  

  catchThePopUp(): any{
     element.all(by.xpath("/html/body/div[1]"))
  }

  clickOnAll(): any{
    element.all(by.xpath('//*[@id="mat-select-3"]/div/div[1]')).click();
  }

  clickOnTitle(): any{
    element.all(by.xpath('//*[@id="mat-option-13"]/span')).click();
  }
  selectBookFromList02(): any{
    browser.sleep(6000);
    element(by.xpath('/html/body/app-root/book-dashboard/div/book-container/div/book-thumbnail[2]/mat-card/mat-card-actions/button/span')).click(); 
 
 }
}
