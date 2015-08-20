package com.sample.dao;

import com.sample.jpa.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by popikyardo on 06.08.15.
 */
@Repository
public interface TopicDAO extends JpaRepository<Topic, Long> {
}
