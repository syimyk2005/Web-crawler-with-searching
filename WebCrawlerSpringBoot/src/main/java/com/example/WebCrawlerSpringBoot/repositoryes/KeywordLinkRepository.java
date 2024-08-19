package com.example.WebCrawlerSpringBoot.repositoryes;


import com.example.WebCrawlerSpringBoot.models.KeywordLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordLinkRepository extends JpaRepository<KeywordLink, Long> {
}

