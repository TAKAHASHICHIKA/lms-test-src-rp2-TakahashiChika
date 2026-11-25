package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

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

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {

		// 「【研修関係】」リンクをクリック
		webDriver.findElement(By.linkText("【研修関係】")).click();

		// 検索結果が表示されるまで待機
		visibilityTimeout(By.cssSelector("table tbody tr"), 20);

		// 検索結果欄に表示されている質問を取得
		List<WebElement> questionList = webDriver
				.findElements(By.cssSelector("table tbody tr td dl dt span:nth-child(2)"));

		// 検索結果が2件だけ表示されていることを検証
		assertEquals(2, questionList.size());

		// 期待値と実際の検索結果を比較
		assertTrue(questionList.stream()
				.anyMatch(question -> question.getText().equals("キャンセル料・途中退校について")));

		assertTrue(questionList.stream()
				.anyMatch(question -> question.getText().equals("研修の申し込みはどのようにすれば良いですか？")));

		// 検索結果の位置を取得
		int position = webDriver.findElement(By.cssSelector("table")).getLocation().getY();

		// 検索結果の位置までスクロール
		scrollTo(String.valueOf(position));

		// スクリーンショットを撮影する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		// 「Q.キャンセル料・途中退校について」をクリック
	    webDriver.findElement(By.xpath(
	            "//dt[span[2][text()='キャンセル料・途中退校について']]")).click();

	    // 回答が表示されるまで待機
	    visibilityTimeout(By.cssSelector("dd.fs18"), 20);

	    // 回答内容を取得
	    WebElement answer = webDriver.findElement(
	            By.cssSelector("dd.fs18"));

	    // 回答が表示されていることを検証
	    assertTrue(answer.isDisplayed());

	    // スクリーンショットを撮影する
	    getEvidence(new Object() {
	    });
	}

}
