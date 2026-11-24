package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// 指定のURLの画面を開く
		goTo("http://localhost:8080/lms");
		
		//検証
		assertEquals("http://localhost:8080/lms/", webDriver.getCurrentUrl());

		//スクリーンショットを撮影する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// ログイン情報を入力
		webDriver.findElement(By.name("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.name("password")).sendKeys("ItTest2025");

		// ログインボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		// ログアウトボタンが表示されるまで待機
		visibilityTimeout(By.cssSelector("button[onclick*='/lms/logout']"), 20);
		
		//検証
		assertEquals("http://localhost:8080/lms/course/detail", webDriver.getCurrentUrl());

		//スクリーンショットを撮影する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// 機能メニューをクリック
		webDriver.findElement(By.linkText("機能")).click();

		// ヘルプをクリック
		webDriver.findElement(By.linkText("ヘルプ")).click();
		
		//検証
		assertEquals("http://localhost:8080/lms/help", webDriver.getCurrentUrl());

		// スクリーンショットを撮影する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// よくある質問をクリック
		webDriver.findElement(By.linkText("よくある質問")).click();

		// 別タブに切り替える
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		
		//検証
		assertEquals("http://localhost:8080/lms/faq", webDriver.getCurrentUrl());

		// スクリーンショットを撮影する
		getEvidence(new Object() {
		});
	}

}
