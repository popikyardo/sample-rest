package com.sample.services;

import com.sample.jpa.Article;

import java.util.List;

/**
 * Created by popikyardo on 06.08.15.
 */
public interface ArticleService extends GenericService<Article, Long> {

    public List<Article> findByTopicId(Long topicId);
}
