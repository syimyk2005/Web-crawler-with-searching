package com.example.WebCrawlerSpringBoot;

import com.example.WebCrawlerSpringBoot.models.Keyword;
import com.example.WebCrawlerSpringBoot.models.KeywordLink;
import com.example.WebCrawlerSpringBoot.models.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.WebCrawlerSpringBoot.repositoryes.KeywordLinkRepository;
import com.example.WebCrawlerSpringBoot.repositoryes.KeywordRepository;
import com.example.WebCrawlerSpringBoot.repositoryes.LinkRepository;

@Component
public class Crawler {
    private static final Logger logger = LoggerFactory.getLogger(Crawler.class);
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private KeywordLinkRepository keywordLinkRepository;

    public void startCrawling() {
        String url = "https://en.wikipedia.org/";
        crawl(1, url, new ArrayList<>());
    }

    private void crawl(int level, String url, List<String> visited) {
        if (level <= 5) {
            Document document = request(url, visited);

            if (document != null) {
                logger.info("Saving link: " + url);
                Link link = linkRepository.save(new Link(url));

                extractAndSaveKeywords(url, link);

                for (Element linkElement : document.select("a[href]")) {
                    String nextLink = linkElement.absUrl("href");
                    if (!visited.contains(nextLink)) {
                        visited.add(nextLink);
                        crawl(level + 1, nextLink, visited);
                    }
                }
            }
        }
    }

    private void extractAndSaveKeywords(String url, Link link) {
        List<String> keywords = extractKeywordsFromUrl(url);

        for (String keywordStr : keywords) {
            Keyword keyword = keywordRepository.findByKeyword(keywordStr).orElseGet(() -> {
                Keyword newKeyword = new Keyword(keywordStr);
                keywordRepository.save(newKeyword);
                return newKeyword;
            });

            KeywordLink keywordLink = new KeywordLink(keyword, link);
            keywordLinkRepository.save(keywordLink);
            logger.info("Saving keyword-link: " + keywordStr + " -> " + link.getUrl());
        }
    }

    private List<String> extractKeywordsFromUrl(String url) {
        List<String> keywords = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            String text = document.text();
            
            String[] words = text.split("\\s+");
            for (String word : words) {

                if (word.length() >= 3 && keywords.size() <= 10) {
                    keywords.add(word);
                }
            }
        } catch (IOException e) {
            logger.error("Error extracting keywords from URL: " + url, e);
        }
        return keywords;
    }

    private Document request(String url, List<String> visited) {
        try {
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();
            if (connection.response().statusCode() == 200) {
                logger.info("Link: " + url);
                logger.info("Title: " + document.title());
                visited.add(url);
                return document;
            }
            return null;
        } catch (IOException e) {
            logger.error("Error requesting URL: " + url, e);
            return null;
        }
    }


}
