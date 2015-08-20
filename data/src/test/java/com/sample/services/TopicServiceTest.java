package com.sample.services;

import com.sample.BaseTest;
import com.sample.dao.TopicDAO;
import com.sample.jpa.Topic;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by popikyardo on 06.08.15.
 */
public class TopicServiceTest extends BaseTest {

    @Autowired
    private TopicService topicService;

    @Test
    public void doCRUDTest() {
        Topic topic = new Topic().builder().id(1L).title("Sci-Fi").build();
        topicService.save(topic);

        Topic createdTopic  = topicService.findOne(1L);

        assertNotNull(createdTopic);
        assertEquals("Sci-Fi", createdTopic.getTitle());

        createdTopic.setTitle("Warhammer 40K");
        topicService.save(createdTopic);   //Spring JPA Repository uses save(...) method both to create and to update an entity

        Topic updatedTopic = topicService.findOne(1L);

        assertNotNull(updatedTopic);
        assertEquals("Warhammer 40K", updatedTopic.getTitle());

        topicService.removeById(1L);
        assertNull(topicService.findOne(1L));
    }
}
