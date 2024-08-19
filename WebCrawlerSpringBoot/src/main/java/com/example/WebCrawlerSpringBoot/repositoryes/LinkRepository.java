package com.example.WebCrawlerSpringBoot.repositoryes;

import com.example.WebCrawlerSpringBoot.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
}