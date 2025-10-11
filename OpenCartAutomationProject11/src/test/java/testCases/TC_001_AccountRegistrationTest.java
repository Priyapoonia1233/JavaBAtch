package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	
	@Test
	public void verify_account_registration() {
		
		logger.info("***** Starting TC001_AccountRegistrationTest  ****");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicking on My Account");
		hp.clickRegister();
		logger.info("Clicking on Register");
		
		RegistrationPage regpage=new RegistrationPage(driver);
		logger.info("Entering the user's details");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomeNumber());
		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message");
		
		String confmsg=regpage.getConfirmationMsg();
		//Assert.assertEquals(confmsg, "Your Account Has Been Createdt5!");
		if(confmsg.equals("Your Account Has Been Created!")) {
	        Assert.assertTrue(true);
	        logger.info("Test Passed");
	    }
		else {
			logger.error("Expected message not found!Test Failed Actual: " + confmsg);
		    Assert.fail("Test failed");
		}
		
		}
		catch(Exception e) {

			logger.error("⚠️ Exception occurred: " + e.getMessage());
	        Assert.fail("Test Failed due to exception: " + e.getMessage());
		}
		finally {
			logger.info("***** Finished TC001_AccountRegistrationTest *****");
		}
	}
		
}

