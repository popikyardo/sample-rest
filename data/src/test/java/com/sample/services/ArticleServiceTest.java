package com.sample.services;

import com.sample.BaseTest;
import com.sample.dao.TopicDAO;
import com.sample.jpa.Article;
import com.sample.jpa.Topic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by popikyardo on 06.08.15.
 */
public class ArticleServiceTest extends BaseTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TopicDAO topicDAO;

    @Before
    public void init() {
        Topic topic = new Topic().builder().id(2L).title("Test").build();
        topicDAO.save(topic);
    }

    @Test
    public void doCRUDTest() throws InterruptedException {
        Topic topic = new Topic().builder().id(2L).title("Test").build();

        Article article = new Article().builder().id(1L).author("Dan Abnett").title("Horus Rising").text("A lot of text").topic(topic).build();
        articleService.save(article);

        Article createdArticle = articleService.findOne(1L);

        assertNotNull(createdArticle);
        assertEquals("Dan Abnett", createdArticle.getAuthor());
        assertEquals("Horus Rising", createdArticle.getTitle());
        assertEquals("A lot of text", createdArticle.getText());

        createdArticle.setText("Updated text");
        articleService.save(createdArticle);   //Spring JPA Repository uses save(...) method both to create and to update an entity

        Article updatedArticle = articleService.findOne(1L);

        assertNotNull(updatedArticle);
        assertEquals("Dan Abnett", updatedArticle.getAuthor());
        assertEquals("Horus Rising", updatedArticle.getTitle());
        assertEquals("Updated text", updatedArticle.getText());

        articleService.removeById(1L);
        assertNull(articleService.findOne(1L));
    }

    @After
    public void tearDown() {
        topicDAO.delete(2L);
    }
}
