package com.sample.controller;

import com.sample.exception.CustomLogicNotValidException;
import com.sample.jpa.Article;
import com.sample.jpa.Topic;
import com.sample.services.ArticleService;
import com.sample.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by popikyardo on 06.08.15.
 */
@RestController
public class ArticleController {

        @Autowired
        private ArticleService articleService;

        @Autowired
        private TopicService topicService;


        @RequestMapping(value = "/topics/{topicId}/articles", method = RequestMethod.POST)
        public Article createArticle(@PathVariable Long topicId, @RequestBody @Valid Article article) throws CustomLogicNotValidException {
                Topic topic = topicService.findOne(topicId);
                if(topic==null) {
                        throw new CustomLogicNotValidException("Non-existing topic");
                }
                article.setTopic(topic);
                return articleService.save(article);
        }

        @RequestMapping(value = "/topics/{topicId}/articles/{articleId}", method = RequestMethod.PUT)
        public Article editArticle(@PathVariable Long topicId, @PathVariable Long articleId, @RequestBody @Valid Article article) throws CustomLogicNotValidException {
                Topic topic = topicService.findOne(topicId);
                if(topic==null) {
                        throw new CustomLogicNotValidException("Non-existing topic");
                }

                Article existingArticle = articleService.findOne(articleId);
                if(existingArticle==null) {
                        throw new CustomLogicNotValidException("You cannot edit non-existing article");
                } else {
                        if(existingArticle.getTopic().getId()!= topicId) {
                                throw new CustomLogicNotValidException("Topic does not contains this article");
                        }
                }
                existingArticle.setTopic(topic);
                return articleService.save(article);
        }

        @RequestMapping(value = "/topics/{topicId}/articles/{id}", method = RequestMethod.DELETE)
        public ResponseEntity<String> deleteArticle(@PathVariable Long topicId, @PathVariable Long id) throws CustomLogicNotValidException {
                Topic topic = topicService.findOne(topicId);
                if(topic==null) {
                        throw new CustomLogicNotValidException("Non-existing topic");
                }


                articleService.removeById(id);
                return ResponseEntity.ok().body("Article successfully deleted");
        }

        @RequestMapping(value = "/topics/{topicId}/articles/{id}")
        public Article showArticle(@PathVariable Long topicId, @PathVariable Long id) {
                return articleService.findOne(id);
        }

        @RequestMapping(value = "/topics/{topicId}/articles")
        public List<Article> showArticlesForTopic(@PathVariable Long topicId) {
                return articleService.findByTopicId(topicId);
        }

}