package com.sample.dao;

import com.sample.jpa.Article;
import com.sample.jpa.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by popikyardo on 06.08.15.
 */
@Repository
public interface ArticleDAO extends JpaRepository<Article, Long> {

    public List<Article> findByTopic(Topic topic);
}
