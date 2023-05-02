package com.example.goodWriting.domain.crawl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	//한글이 추가 되지 않는 부분을 추가
	private static final String URL_REGEX = "^(http|https)://[a-z0-9ㄱ-ㅎ가-힣]+([\\-\\.]{1}[a-z0-9ㄱ-ㅎ가-힣]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/[a-zA-Z0-9ㄱ-ㅎ가-힣\\-\\._\\?,'/\\\\+&amp;%$#=~]*)?$";

	public CrawledDataDTO getData(String inputUrl) {
		String url = inputUrl;

		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(5000).get();
		} catch (IOException e) {
			throw new RuntimeException("Connection timed out after 5 seconds", e);
		}

		// 제목
		String title = doc.select("meta[property=og:title]").attr("content");
		// 내용
		String description = doc.select("meta[property=og:description]").attr("content");
		// 이미지
		String image = doc.select("meta[property=og:image]").attr("content");

		//제목이 없는 경우
		if (title.isEmpty()) {
			title = "제목을 입력해주세요";
		}

		//내용이 없는 경우
		if (description.isEmpty()) {
			description = "간단한 설명을 입력해주세요";
		}

		//이미지가 없는 경우 첫번째 이미지
		if (image.isEmpty()) {
			Elements images = doc.select("img");
			for (Element firstImage : images) {
				String imageUrl = firstImage.attr("abs:src");
				if (imageUrl != null && !imageUrl.isEmpty()) {
					image = imageUrl;
				}
			}
		}

		//기본이미지
		if (image.isEmpty()) {
			image = "https://www.edology.com/build/images/logo-og.jpg";
		}

		return new CrawledDataDTO(title, description, image);

	}

	public String addHttpsUrl(String url) {
		url = url.trim();

		if (url.startsWith("https://")) {
			return url;
		}

		url = "https://" + url;

		return url;
	}

	public boolean isValidUrl(String url) throws IOException {

		url = addHttpsUrl(url);

		//이메일 유효성 검사
		if (isMatchRegex(url) == false) {
			return false;
		}

		if (isHttpResponseOk(url) == false) {
			return false;
		}

		return true;

	}

	public boolean isMatchRegex(String url) {
		return Pattern.matches(URL_REGEX, url);
	}

	public boolean isHttpResponseOk(String url) throws IOException {
		URL connectedUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)connectedUrl.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		int statusCode = connection.getResponseCode();

		if (statusCode == 404) {
			System.out.println(statusCode);
			return false;
		}

		connection.disconnect();

		return true;
	}

}
