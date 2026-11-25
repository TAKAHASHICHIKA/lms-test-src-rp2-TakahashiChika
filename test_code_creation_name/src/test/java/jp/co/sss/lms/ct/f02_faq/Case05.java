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
 * ケース05
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

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
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {
		 // キーワード検索欄に「キャンセル」を入力
	    webDriver.findElement(By.name("keyword")).sendKeys("キャンセル");
	    
	    getEvidence(new Object(){},"form");

	    // 検索ボタンをクリック
	    webDriver.findElement(By.cssSelector("input[type='submit']")).click();

	    // 検索結果が表示されるまで待機
	    visibilityTimeout(By.cssSelector("table tbody tr"), 20);
	    
	    // 検索結果が1件だけ表示されていることを検証
	    assertEquals(1, webDriver.findElements(By.cssSelector("table tbody tr")).size());

	    // 検索結果の検証
	    assertEquals("キャンセル料・途中退校について",
	            webDriver.findElement(By.cssSelector("table tbody tr dt span:nth-child(2)")).getText());

	    // 検索結果の位置を取得
	    int position = webDriver.findElement(By.cssSelector("table")).getLocation().getY();

	    // 検索結果の位置までスクロール
	    scrollTo(String.valueOf(position));

	    // スクリーンショットを撮影
	    getEvidence(new Object(){},"answer");
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {
		// TODO ここに追加
	}

}
