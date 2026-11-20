package jp.co.sss.lms.ct.f01_login1;

import java.io.File;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.web.server.LocalServerPort;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト ログイン機能①
 * ケース01
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース01 ログイン画面への遷移")
public class Case01 {

	@LocalServerPort
	private int port;

	/** GoogleChromeDriver. */
	private WebDriver driver;

	private WebDriverWait wait;

	/** 前処理 */
	@BeforeEach
	void setUp() {
		// Chromeの起動オプション設定
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
		WebDriverUtils.webDriver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	/** 後処理 */
	@AfterEach
	void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	@Test
	public void testLoginDisplay() throws Exception {

		// 指定のURLの画面を開く
		driver.get("http://localhost:8080/lms");

		//待ち処理を追加
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("input[type='submit'][value='ログイン']")));

		// エビデンス保存先のフォルダを作成
		new File("evidence").mkdirs();
		
		//スクリーンショットを撮影する
		WebDriverUtils.getEvidence(new Object() {});
	}

}
