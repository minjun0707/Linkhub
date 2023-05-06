package com.example.goodWriting;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
	@DisplayName("입력된 URL에서 이미지,제목,설명을 크롤링한다")
	void crawlDate() {

		String str ="https://tecoble.techcourse.co.kr/post/2022-11-01-mysql-dead-lock/";

		CrawledDataDTO data = crawler.getData(str);
		String title = data.getTitle();
		String description = data.getDescription();
		String img = data.getImg();

		Assertions.assertEquals("데드락 해결 모험기",title );
		Assertions.assertEquals("용어 설명     Cycle이란, 사용자가 특정 챌린지에 도전할 때 생성되는 객체이다. 비즈니스 규칙 상 하루에 한번씩 총…",description);
		Assertions.assertEquals("https://tecoble.techcourse.co.kr/static/264a2517af906c5a1c3c22b6f4c181cd/2f1b1/giron.jpg",img);

	}

	@Test
	@DisplayName("올바른 URL 접근")
	void isValidURLTest() throws IOException {

		String validUrl = "https://jddng.tistory.com";

		Assertions.assertEquals(true, crawler.isValidUrl(validUrl));
	}


	@Test
	@DisplayName("잘못된 URI 접근")
	void isNotValidURLTest() throws IOException {

		String validUrl = "https://jddng.tistory.com/safsdfs";

		Assertions.assertEquals(false, crawler.isValidUrl(validUrl));
	}

	@Test
	@DisplayName("URL regex를 만족한다")
	void matchRegexURLTest() {

		String validUrl = "https://jddng.tistory.com/dfsd2=";

		Assertions.assertEquals(true, crawler.isMatchRegex(validUrl));
	}

	@Test
	@DisplayName("URL regex를 만족하지못한다")
	void notMatchRegexTest() {

		String validUrl = "htts://jddng.tistory.com/dfsd2=";

		Assertions.assertEquals(false, crawler.isMatchRegex(validUrl));
	}


	@Test
	@DisplayName("HTTP status가 404가 아닌 URL 이다")
	void validHttpResponse()  {

		String validUrl = "http://jddng.tistory.com/";

		try {
			crawler.isHttpResponseOk(validUrl);
		} catch (IOException e) {
			System.out.println("에러");
		}
	}


	@Test
	@DisplayName("HTTP status가 404이다")
	void httpReponse404()  {

		String validUrl = "htㄴㅁㅇㅁ";
		try {
			crawler.isHttpResponseOk(validUrl);
		} catch (IOException e) {
			System.out.println("에러");
		}

	}

	@Test
	@DisplayName("https 프로토콜이 url에 존재한다")
	void haveHttpsProtocolInURL()  {

		String str = "https://google.com";
		String addedStr = crawler.addHttpsUrl(str);
		Assertions.assertEquals("https://google.com",addedStr);
	}

	@Test
	@DisplayName("https 프로토콜이 url에 존재하지않는다.")
	void notHttpsProtocolInURL()  {

		String str = "google.com";
		String addedStr = crawler.addHttpsUrl(str);
		Assertions.assertEquals("https://google.com",addedStr);
	}
}
