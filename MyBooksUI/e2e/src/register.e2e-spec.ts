import { RegisterPage } from './register.po';
import { element, by, browser, protractor } from 'protractor';
describe('My-Books App', () => {
    let page: RegisterPage;
    beforeEach(() => {
      page = new RegisterPage();
      page.navigateTo();
      browser.driver.manage().window().maximize();
    });
    it('Error while registering existing user', () => {
        page.clickOnRegisterButton();
        page.setFirstname("Kishan");
        page.setLastname("Patel");
        page.setuserid("kish123");
     page.setPassword("jkush");
     page.clickOnRegisterUserButton();
     browser.sleep(5000);
     expect(browser.getCurrentUrl()).toEqual("http://localhost:4200/register");
      });

      it('Register User without first name', () => {
        page.clickOnRegisterButton();
        page.setLastname("Patel");
        page.setuserid("kish123");
        page.setPassword("jkush");
        page.clickOnRegisterUserButton();
        browser.sleep(2000);
        let val = element(by.xpath("/html/body/div[1]")).getText();
        browser.sleep(3000);
        expect(val).toEqual("First name is mandatory");
      });

      it('Register User without last name', () => {
        page.clickOnRegisterButton();
        page.setFirstname("Kishan");
        page.setuserid("kish123");
        page.setPassword("jkush");
        page.clickOnRegisterUserButton();
        browser.sleep(2000);
        let val = element(by.xpath("/html/body/div[1]")).getText();
        browser.sleep(3000);
        expect(val).toEqual("Last name is mandatory");
      });

      it('Register User without UserId', () => {
        page.clickOnRegisterButton();
        page.setFirstname("Kishan");
        page.setLastname("Patel");
        page.setPassword("jkush");
        page.clickOnRegisterUserButton();
        browser.sleep(2000);
        let val = element(by.xpath("/html/body/div[1]")).getText();
        browser.sleep(3000);
        expect(val).toEqual("User Id and Password are mandatory");
      });

      it('Register User without password', () => {
        page.clickOnRegisterButton();
        page.setFirstname("Kishan");
        page.setLastname("Patel");
        page.setuserid("kish123");
        page.clickOnRegisterUserButton();
        browser.sleep(2000);
        let val = element(by.xpath("/html/body/div[1]")).getText();
        browser.sleep(3000);
        expect(val).toEqual("User Id and Password are mandatory");
      });

      it('Register User without UserID and password', () => {
        page.clickOnRegisterButton();
        page.setFirstname("Kishan");
        page.setLastname("Patel");
        page.clickOnRegisterUserButton();
        browser.sleep(2000);
        let val = element(by.xpath("/html/body/div[1]")).getText(); 
        browser.sleep(3000);
        expect(val).toEqual("User Id and Password are mandatory");
      });

      it('Test for clear button', () => {
        page.clickOnRegisterButton();
        page.setFirstname("Kishan");
        page.setLastname("Patel");
        page.setuserid("kish123");
        page.setPassword("jkush");
        browser.sleep(3000);
        page.clickOnClearButton();
        browser.sleep(3000);
        expect(browser.getCurrentUrl()).toEqual("http://localhost:4200/register");
        
      });
    

      it('should register new user', () => {
        page.clickOnRegisterButton();
        page.setFirstname("Elsa");
        page.setLastname("Raju");
        page.setuserid("elsa");
     page.setPassword("elsa123");
     page.clickOnRegisterUserButton();
     browser.sleep(3000);
     let val = element(by.xpath("/html/body/div[1]")).getText();
     browser.sleep(2000);
     expect(val).toEqual("Registration successful");
        
      });
    })