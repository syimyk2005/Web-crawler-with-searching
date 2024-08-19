package com.example.WebCrawlerSpringBoot.models;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keyword;
    @ManyToMany(mappedBy = "keywords")
    private Collection<Link> links;
    public Keyword() {}

    public Keyword(String keyword) {
        this.keyword = keyword;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Collection<Link> getLinks() {
        return links;
    }

    public void setLinks(Collection<Link> links) {
        this.links = links;
    }
}
