package com.sample.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.BaseWebTest;
import com.sample.jpa.Article;
import com.sample.jpa.Topic;
import com.sample.services.TopicService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by popikyardo on 06.08.15.
 */
public class TopicControllerTest extends BaseWebTest {

    private MockMvc mockMvc;

    @Mock
    TopicService topicServiceMock;

    @InjectMocks
    TopicController topicController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
    }

    @Test
    public void doListAllTopicsTest() throws Exception {
        Topic testTopic = new Topic().builder().id(1L).title("Test Topic").build();

        when(topicServiceMock.findAll()).thenReturn(Arrays.asList(testTopic));

        mockMvc.perform(get("/topics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test Topic")));

        verify(topicServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(topicServiceMock);
    }

    @Test
    public void doCreateTopicTest() throws Exception {
        Topic testTopic = new Topic().builder().title("Test Topic").build();

        Topic addedTopic = new Topic().builder()
                .id(1L)
                .title("Test Topic")
                .build();

        when(topicServiceMock.save(any(Topic.class))).thenReturn(addedTopic);


        mockMvc.perform(post("/topics")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(testTopic))
        ).andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test Topic")));
    }

    @Test
    public void doCreateTopicFailedTest() throws Exception {
        mockMvc.perform(post("/topics")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes("dummy string"))
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void tmp() throws Exception {
        Topic topic = new Topic().builder().id(1L).title("Title").build();
        Article article = new Article().builder().author("Author").title("Title").topic(topic).build();

        ObjectMapper mapper = new ObjectMapper();

        System.out.println(mapper.writeValueAsString(article));
    }
}
