package com.example.WebCrawlerSpringBoot.Services;

import com.example.WebCrawlerSpringBoot.models.Keyword;
import com.example.WebCrawlerSpringBoot.repositoryes.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KeywordService {
    @Autowired
    private KeywordRepository keywordRepository;

    public void save(Keyword keyword) {
        keywordRepository.save(keyword);
    }

    public List<Keyword> findAll() {
        return keywordRepository.findAll();
    }

    public Keyword findById(Long id) {
        return keywordRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        keywordRepository.deleteById(id);
    }
}
