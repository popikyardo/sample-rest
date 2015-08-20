package com.sample.services;

import com.sample.dao.TopicDAO;
import com.sample.jpa.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by popikyardo on 06.08.15.
 */
@Service
public class TopicServiceImpl extends GenericServiceImpl<Topic, Long> implements TopicService {

    private TopicDAO topicDAO;

    @Autowired
    public TopicServiceImpl(@Qualifier("topicDAO") JpaRepository<Topic, Long> genericDao) {
        super(genericDao);
        this.topicDAO = (TopicDAO) genericDao;
    }
}
