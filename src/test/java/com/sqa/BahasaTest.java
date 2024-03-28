package com.sqa;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sqa.drivers.DriverSingleton;
import com.sqa.pages.BahasaPage;
import com.sqa.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BahasaTest {

    private static WebDriver driver;

    public static ExtentTest extentTest;

    private static LoginPage loginPage = new LoginPage();

    private static BahasaPage bahasaPage = new BahasaPage();

    public BahasaTest(){
        driver = Hooks.driver;
        extentTest = Hooks.extentTest;
    }

    @Given("User berada di halaman Bahasa")
    public void User_berada_di_halaman_Bahasa() throws InterruptedException {
        driver.get("http://akademik-ba.kemhan.go.id/");
        loginPage.formLogin();
        Thread.sleep(3000);
        WebElement Bahasa = driver.findElement(By.xpath("//a[normalize-space()='Bahasa']"));
        Bahasa.click();
        extentTest.log(LogStatus.PASS,"User berada di halaman Bahasa");
    }

    @When("User klik button tambah baru")
    public void User_klik_button_tambah_baru() throws InterruptedException {
        Thread.sleep(3000);
        bahasaPage.clickBtnTambah();
        extentTest.log(LogStatus.PASS,"User klik button tambah baru");
    }

    @And("User input jenis bahasa yang ingin dibuat")
    public void User_input_jenis_bahasa_yang_ingin_dibuat() throws InterruptedException {
        Thread.sleep(3000);
        bahasaPage.inputBahasa("Bahasa Testing");
        extentTest.log(LogStatus.PASS,"User input jenis bahasa yang ingin dibuat");
    }

    @And("User memilih deskripsi")
    public void User_memilih_deskripsi() throws InterruptedException {
        DriverSingleton.delay(2);
        WebElement dropdownElement = driver.findElement(By.xpath("//*[@id=\"addModal\"]/div/div/div/div/div[2]/div/form/div[1]/div[2]/select"));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue("KIBI");
        extentTest.log(LogStatus.PASS,"User memilih deskripsi");
    }

    @And("User klik button simpan")
    public void User_klik_button_simpan() throws InterruptedException {
        Thread.sleep(2000);
        bahasaPage.clickBtnSimpan();
        extentTest.log(LogStatus.PASS,"User klik button simpan");
    }

    @Then("User berhasil menemukan bahasa yang baru ditambahkan")
    public void User_berhasil_menemukan_bahasa_yang_baru_ditambahkan() throws InterruptedException {
        Thread.sleep(3000);
        bahasaPage.inputSearch("Bahasa Testing");
        Assert.assertEquals(bahasaPage.GetTxtValidasiSearch(),"Bahasa Testing");
        extentTest.log(LogStatus.PASS,"User berhasil menemukan bahasa yang baru ditambahkan");
    }






}
