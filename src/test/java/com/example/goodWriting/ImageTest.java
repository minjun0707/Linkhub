package com.example.goodWriting;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.xml.bind.DatatypeConverter;

public class ImageTest {

	private final String REST_API_KEY = "8f907f5cf02399bb22197c35c5a80839";
	private final String T2I_URL = "https://api.kakaobrain.com/v1/inference/karlo/t2i";

	@Test
	@DisplayName("이미지 테스트")
	void getImagessss() throws IOException {

		String text = "docker, process";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + REST_API_KEY);
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody = objectMapper.writeValueAsString(Collections.singletonMap("prompt",
			Collections.singletonMap("text", text)));
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(T2I_URL, HttpMethod.POST, requestEntity,
			String.class);
		String responseJson = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		ImageResponse response = mapper.readValue(responseJson, ImageResponse.class);


		String base64ImageString = response.getImages().get(0).getImage();
		byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64ImageString);


		FileOutputStream fos = new FileOutputStream("output.png");
		fos.write(decodedBytes);
		fos.close();

		System.out.println("PNG 파일이 성공적으로 출력되었습니다.");


		// JFrame frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// // 이미지를 표시할 JLabel 생성
		// JLabel label = new JLabel(new ImageIcon(bufImg));
		// // JLabel을 JFrame에 추가
		// frame.getContentPane().add(label);
		// // JFrame 크기를 이미지에 맞게 조정
		// frame.pack();
		// // JFrame을 화면에 표시
		// frame.setVisible(true);

		// System.out.println(base64ImageString);
		// byte[] decodedBytes = Base64.getDecoder().decode(base64ImageString);
		// ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
		// BufferedImage image = ImageIO.read(bis);

		// ;
		//
		// // 이미지를 표시할 JFrame 생성
		// JFrame frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		// // 이미지를 표시할 JLabel 생성
		// JLabel label = new JLabel(new ImageIcon(image));
		//
		// // JLabel을 JFrame에 추가
		// frame.getContentPane().add(label);
		//
		// // JFrame 크기를 이미지에 맞게 조정
		// frame.pack();
		//
		// // JFrame을 화면에 표시
		// frame.setVisible(true);

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class ImageResponse {
		private List<ImageData> images;

		public List<ImageData> getImages() {
			return images;
		}

		public void setImages(List<ImageData> images) {
			this.images = images;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class ImageData {
		private String image;

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
	}
}
