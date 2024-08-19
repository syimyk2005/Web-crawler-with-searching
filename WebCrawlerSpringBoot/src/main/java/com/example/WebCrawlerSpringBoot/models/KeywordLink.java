package com.example.WebCrawlerSpringBoot.models;
import jakarta.persistence.*;

@Entity
public class KeywordLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @ManyToOne
    @JoinColumn(name = "link_id")
    private Link link;

    public KeywordLink() {}

    public KeywordLink(Keyword keyword, Link link) {
        this.keyword = keyword;
        this.link = link;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
