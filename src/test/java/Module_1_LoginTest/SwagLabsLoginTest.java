package Module_1_LoginTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import LibraryFiles.Base_Class;
import LibraryFiles.Utility_Class;
import Module_1_Login.SwagLabsHomePage;
import Module_1_Login.SwagLabsLoginPage;
import Module_1_Login.SwagLabsMenuPage;

public class SwagLabsLoginTest extends Base_Class {
	int a;
	int TCID;
	SwagLabsHomePage home;
	SwagLabsLoginPage login;
	SwagLabsMenuPage menu;

	@BeforeClass
	public void openBrowser() throws InterruptedException, EncryptedDocumentException, IOException {
		initializeBrowser();

		login = new SwagLabsLoginPage(d);
		home = new SwagLabsHomePage(d);
		menu = new SwagLabsMenuPage(d);
		a = 20;

	}

	@BeforeMethod
	public void loginToApp() throws InterruptedException, EncryptedDocumentException, IOException {
		System.out.println(a);
		login.inpSwagLabsLoginPageUN(Utility_Class.getDataFromPF("UN"));
		Thread.sleep(1000);
		login.inpSwagLabsLoginPagePwd(Utility_Class.getDataFromPF("PWD"));
		Thread.sleep(1000);
		login.clickSwagLabsLoginPageLoginBtn();
		Thread.sleep(3000);

	}

	@Test
	public void verifyTitle() throws EncryptedDocumentException, IOException {
		TCID = 101;
		String actTitle = home.getSwagLabsHomePageTitle();
		String expTitle = Utility_Class.getTestData(0, 1);
		Assert.assertEquals(actTitle, expTitle, "Failed: Both results are different");
	}

	@AfterMethod
	public void logoutFromApp(ITestResult result) throws InterruptedException, IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			Utility_Class.captureSS(d, TCID);

		}
		home.clickSwagLabsHomePageMenuBtn();
		Thread.sleep(2000);
		menu.clickSwagLabsMenuPageLogoutBtn();
		Thread.sleep(2000);
	}

	@AfterClass
	public void closeBrowser() {
		d.quit();
	}

}
