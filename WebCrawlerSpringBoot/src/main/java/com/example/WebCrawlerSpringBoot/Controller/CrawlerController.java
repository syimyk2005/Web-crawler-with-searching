package com.example.WebCrawlerSpringBoot.Controller;
import com.example.WebCrawlerSpringBoot.SearchEngine;
import com.example.WebCrawlerSpringBoot.Crawler;
import com.example.WebCrawlerSpringBoot.models.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crawler")
public class CrawlerController {

    @Autowired
    private Crawler crawler;

    @Autowired
    private SearchEngine searchEngine;

    @GetMapping("/start")
    public String startCrawler() {
        crawler.startCrawling();
        return "Crawling started!";
    }


    @GetMapping("/links")
    public List<Link> getLinksByKeyword(@RequestParam String keyword) {
        return searchEngine.findLinksByKeyword(keyword);
    }

}