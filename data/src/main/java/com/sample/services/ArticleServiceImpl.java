package com.sample.services;

import com.sample.dao.ArticleDAO;
import com.sample.jpa.Article;
import com.sample.jpa.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by popikyardo on 06.08.15.
 */
@Service
public class ArticleServiceImpl extends GenericServiceImpl<Article, Long> implements ArticleService {

    private ArticleDAO articleDAO;

    @Autowired
    public ArticleServiceImpl(
            @Qualifier("articleDAO") JpaRepository<Article, Long> genericDao) {
        super(genericDao);
        this.articleDAO = (ArticleDAO) genericDao;
    }

    @Override
    public List<Article> findByTopicId(Long topicId) {
        Topic topic = new Topic().builder().id(topicId).build();
        return articleDAO.findByTopic(topic);
    }
}
