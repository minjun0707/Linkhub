package com.example.goodWriting.domain.crawl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class CrawledDataDTO {
	public String title;
	public String description;
	public String img;


}
