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
import org.openqa.selenium.WebElement;

/**
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

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
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {

		// 「提出済み」の行が表示されるまで待機
		visibilityTimeout(
				By.xpath("//tr[.//span[contains(text(),'提出済み')]]"), 20);

		// 「提出済み」と表示されている研修日の行を取得
		WebElement row = webDriver.findElement(
				By.xpath("//tr[.//span[contains(text(),'提出済み')]]"));

		// 該当する行の「詳細」ボタンをクリック
		row.findElement(By.cssSelector("input[type='submit']")).click();

		// セクション詳細画面に遷移するまで待機
		visibilityTimeout(By.cssSelector("form"), 20);

		// 検証
		assertEquals("http://localhost:8080/lms/section/detail",
				webDriver.getCurrentUrl());

		// スクリーンショットを撮影する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// 「提出済み日報【デモ】を確認する」ボタンが表示されるまで待機
	    visibilityTimeout(
	            By.xpath("//input[@value='提出済み日報【デモ】を確認する']"), 20);

	    // 「提出済み日報【デモ】を確認する」ボタンをクリック
	    webDriver.findElement(
	            By.xpath("//input[@value='提出済み日報【デモ】を確認する']")).click();

	    // レポート登録画面に遷移するまで待機
	    visibilityTimeout(By.cssSelector("form"), 20);

	    // 検証
	    assertEquals("http://localhost:8080/lms/report/regist",
	            webDriver.getCurrentUrl());

	    // スクリーンショットを撮影する
	    getEvidence(new Object() {
	    });
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		// TODO ここに追加
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		// TODO ここに追加
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		// TODO ここに追加
	}

}
