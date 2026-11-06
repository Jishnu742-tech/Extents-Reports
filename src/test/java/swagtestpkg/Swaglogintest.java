package swagtestpkg;

import org.testng.Assert;
import org.testng.annotations.Test;

import swagbasepkg.Swagbase;

public class Swaglogintest extends Swagbase{
	@Test(priority=1)
	public void login() throws Exception {
		test=extent.createTest("Loginvalidation");
		swagobj.loginmethod("standard_user", "secret_sauce");
		Thread.sleep(2000);
	}
	@Test(priority=2)
	public void validation() {
		test=extent.createTest("Loginvalidation");
		String expectedurl="https://www.saucedemo.com/inventory.html";
		String actualurl=swagobj.urlmethod();
		Assert.assertEquals(expectedurl, actualurl);
	}
	@Test(priority=3)
	public void swagtitleverification() {
		test=extent.createTest("swagtitleverification");
		String Exp="Swag Labs";
		String actual=swagobj.titlemethod();
		System.out.println("actual");
		Assert.assertEquals(Exp, actual);
	}
}
