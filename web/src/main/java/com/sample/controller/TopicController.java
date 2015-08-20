package com.sample.controller;

import com.sample.jpa.Topic;
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
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/topics", method = RequestMethod.POST)
    public Topic createTopic(@RequestBody @Valid Topic topic) {
        return topicService.save(topic);
    }

    @RequestMapping("/topics")
    public List<Topic> listAll() {
        return topicService.findAll();
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTopic(@PathVariable Long id) {
        topicService.removeById(id);
        return ResponseEntity.ok().body("Topic successfully deleted");
    }
}
