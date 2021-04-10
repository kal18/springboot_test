package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test1() throws Exception{
        //this calls the post method based on the api and post testing
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=stringToPost")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("stringToPost")));

        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=stringToPost").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("has been deleted")));
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(not(containsString("stringToPost"))));
    }

    @Test
    void test2() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=tAco")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=taco")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=salsa0111!")).andReturn();

        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("tAco")));
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("taco")));

        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=tAco").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("has been deleted")));
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("taco")));

        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=taco").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("has been deleted")));
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(not(containsString("taco"))));

        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=salsa0111!").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("has been deleted")));
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(not(containsString("salsa0111!"))));
    }
}