package com.example.WebCrawlerSpringBoot;

import com.example.WebCrawlerSpringBoot.models.Link;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SearchEngine {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Link> findLinksByKeyword(String keyword) {
        return entityManager.createQuery(
                        "SELECT l FROM Link l JOIN KeywordLink kl ON l.id = kl.link.id JOIN Keyword k ON k.id = kl.keyword.id WHERE k.keyword = :keyword", Link.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }
}
