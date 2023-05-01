package com.example.goodWriting;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.goodWriting.domain.crawl.CrawledDataDTO;
import com.example.goodWriting.domain.crawl.Crawler;

public class CrawlerTest {

	//https:// 가 없는경우는 추가
	//잘못된 url (정규식)
	//url은 올바르나 api 호출 문제(404)
	//아예 없는 경우 예외 터트려서 (크롤링에서 예외 터지는 거 잡아서 , 카카오,다음,네이버 잘못된 api호출를 하면 404에러를 응답해주는데)

	//이미지 파싱시 오픈그래픽태그를 우선
	//타이틀 디스크립션도 여기서 하는데 얘네가 없으면 그냥 기본 글
	//만약 이지미가 없으면
	//없을시 첫번째 이미지
	//없을시 기본 이미지




	Crawler crawler = new Crawler();

	@Test
	void 크롤링() {

		String str ="https://tecoble.techcourse.co.kr/post/2022-11-01-mysql-dead-lock/";

		CrawledDataDTO data = crawler.getData(str);
		String title = data.getTitle();
		String description = data.getDescription();
		String img = data.getImg();

		Assertions.assertEquals("데드락 해결 모험기",title );
		Assertions.assertEquals("용어 설명     Cycle이란, 사용자가 특정 챌린지에 도전할 때 생성되는 객체이다. 비즈니스 규칙 상 하루에 한번씩 총…",description);
		Assertions.assertEquals("https://static/9a8e208eb938af089c498336dd233cf0/00405/deadlock_teaser.png",img);

	}





	@Test
	void isValid_올바른URL() {

		String validUrl = "https://jddng.tistory.com/safsdfs";

		Assertions.assertEquals(true, crawler.isValidUrl(validUrl));
	}


	@Test
	void isValid_잘못된URL() {

		String validUrl = "https://jddng.tistory.com/safsdfs";

		Assertions.assertEquals(false, crawler.isValidUrl(validUrl));
	}

	@Test
	void isMatchRegex_올바른URL() {

		String validUrl = "https://jddng.tistory.com/dfsd2=";

		Assertions.assertEquals(true, crawler.isMatchRegex(validUrl));
	}

	@Test
	void isMatchRegex_잘못된URL() {

		String validUrl = "htts://jddng.tistory.com/dfsd2=";

		Assertions.assertEquals(false, crawler.isMatchRegex(validUrl));
	}


	@Test
	void isHttpResponseOk_올바른URL()  {

		String validUrl = "http://jddng.tistory.com/";

		try {
			crawler.isHttpResponseOk(validUrl);
		} catch (IOException e) {
			System.out.println("에러");
		}
	}


	@Test
	void isHttpResponseOk_잘못된URL()  {

		String validUrl = "htㄴㅁㅇㅁ";
		try {
			crawler.isHttpResponseOk(validUrl);
		} catch (IOException e) {
			System.out.println("에러");
		}

	}

	@Test
	void addHttpsUrl_https가_주소에_있다()  {

		String str = "https://google.com";
		String addedStr = crawler.addHttpsUrl(str);
		Assertions.assertEquals("https://google.com",addedStr);
	}

	@Test
	void addHttpsUrl_https가_주소에_없다()  {

		String str = "google.com";
		String addedStr = crawler.addHttpsUrl(str);
		Assertions.assertEquals("https://google.com",addedStr);
	}
}
