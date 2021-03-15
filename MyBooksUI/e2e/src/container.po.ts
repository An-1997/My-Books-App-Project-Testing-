import { browser, by, element } from 'protractor';

export class ContainerPage {
    public baseUrl ='http://localhost:4200/books/favourite';

  navigateTo(): any
  {
    browser.ignoreSynchronization= true;
   // browser.waitForAngularEnabled;
    return browser.get(this.baseUrl);

  }

  clickOnDelete01(): any{
      element(by.xpath('/html/body/app-root/book-favourite/div/book-container/div/book-thumbnail[1]/mat-card/mat-card-actions/button')).click();
  }

  clickOnDelete02(): any{
      element(by.xpath('/html/body/app-root/book-favourite/div/book-container/div/book-thumbnail/mat-card/mat-card-actions/button')).click();
  }
  clickOnDashboard(): any{
    element(by.xpath("/html/body/app-root/mat-toolbar/button[1]/span")).click();
  }
  clickOnFavourites(): any{
    element(by.xpath("/html/body/app-root/mat-toolbar/button[2]/span")).click();
  }
  verifyingThePresenceOfBook(): any {
    return element(by.xpath("/html/body/app-root/book-favourite/div/book-container/div/book-thumbnail[1]/mat-card/mat-card-actions/button/span")).isPresent();
​   }
    clickOnLogout(): any{
      element(by.xpath("/html/body/app-root/mat-toolbar/button[3]/span")).click();
    }

}