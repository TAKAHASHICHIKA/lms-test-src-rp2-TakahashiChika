package jp.co.sss.lms.ct.f03_report;

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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト レポート機能
 * ケース07
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {

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
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
	    // 「未提出」の行にある「詳細」ボタンを取得
	    WebElement detailButton = webDriver.findElement(By.xpath(
	        "//tr[.//span[text()='未提出']]//input[@value='詳細']"
	    ));

	    // 「詳細」ボタンが画面中央に来るようにスクロール
	    ((JavascriptExecutor) webDriver).executeScript(
	        "arguments[0].scrollIntoView({block: 'center'});", detailButton);

	    // 「詳細」ボタンをクリック
	    detailButton.click();

	    // セクション詳細画面が表示されるまで待機
	    visibilityTimeout(By.tagName("h2"), 20);

	    // 画面遷移を検証
	    assertEquals("http://localhost:8080/lms/section/detail",
	            webDriver.getCurrentUrl());

	    // スクリーンショットを撮影する
	    getEvidence(new Object() {
	    });
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// 「提出する」ボタンをクリック
	    webDriver.findElement(By.xpath(
	        "//input[@type='submit' and contains(@value,'を提出する')]"
	    )).click();

	    // レポート登録画面が表示されるまで待機
	    visibilityTimeout(By.tagName("h2"), 20);

	    // 画面遷移を検証
	    assertEquals("http://localhost:8080/lms/report/regist", webDriver.getCurrentUrl());

	    // スクリーンショットを撮影する
	    getEvidence(new Object() {
	    });
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() {
		 // 報告レポート内容に「良かった。」を入力
	    webDriver.findElement(By.cssSelector("textarea")).sendKeys("良かった。");

	    // 入力後のスクリーンショットを撮影
	    getEvidence(new Object() {}, "form");
	    
	    // 「提出する」ボタンをクリック
	    webDriver.findElement(By.xpath("//button[text()='提出する']")).click();

	    // セクション詳細画面に戻るまで待機
	    visibilityTimeout(By.tagName("h2"), 20);

	    // 「提出済み〇〇を確認する」ボタンが表示されるまで待機
	    visibilityTimeout(By.xpath(
	        "//input[contains(@value,'提出済み') and contains(@value,'を確認する')]"
	    ), 20);

	    // 検証
	    assertTrue(webDriver.findElement(By.xpath(
	        "//input[contains(@value,'提出済み') and contains(@value,'を確認する')]"
	    )).isDisplayed());

	    // 遷移後のスクリーンショットを撮影
	    getEvidence(new Object() {}, "answer");
	}

}
