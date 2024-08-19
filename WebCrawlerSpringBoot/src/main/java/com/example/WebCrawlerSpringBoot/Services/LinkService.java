package com.example.WebCrawlerSpringBoot.Services;

import com.example.WebCrawlerSpringBoot.models.Link;
import com.example.WebCrawlerSpringBoot.repositoryes.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    public void save(Link link) {
        linkRepository.save(link);
    }

    public List<Link> findAll() {
        return linkRepository.findAll();
    }

    public Link findById(Long id) {
        return linkRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        linkRepository.deleteById(id);
    }
}
