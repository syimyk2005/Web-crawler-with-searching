// KeywordRepository.java
package com.example.WebCrawlerSpringBoot.repositoryes;

import com.example.WebCrawlerSpringBoot.models.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findByKeyword(String keyword);

}
