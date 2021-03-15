import { AppPage } from './app.po';
import { element, by, browser, protractor } from 'protractor';
import { async, tick } from '@angular/core/testing';

describe('My-Books App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display My Favourite Books as application title', () => {
    page.navigateTo();
    let spanElement = element(by.css('span')).getText();    
    expect(spanElement).toEqual('My Favourite Books');
  });

 
});
