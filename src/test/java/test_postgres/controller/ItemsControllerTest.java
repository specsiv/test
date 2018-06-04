package test_postgres.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import test_postgres.jpa.entity.Items;
import test_postgres.mock.MockServiceItems;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MockServiceItems.class, ItemsController.class})
public class ItemsControllerTest {
    @Autowired
    private ItemsController itemsController;

    private MockMvc mockMvc;
    private final String URL = "http://localhost:8080/api/items";
    private ObjectMapper objectMapper = new ObjectMapper();
    private Items testItem = new Items("Superman", 1,1);
    private List<Items> testList = Arrays.asList(testItem, testItem, testItem);

    @Before
    public void init() throws Exception{
        //MockitoAnnotations.initMocks(this); / ???
        mockMvc = standaloneSetup(itemsController).build();
    }

    @Test
    public void itemCheckTest() throws Exception{
        mockMvc.perform(get(URL + "/check/asdasdasd"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("true"));
    }

    @Test
    public void getByNameTest() throws Exception{
        mockMvc.perform(get(URL + "/get/byName/asdasdasd"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(objectMapper.writeValueAsString(testItem)));
    }

    @Test
    public void addTest() throws Exception{
        mockMvc.perform(post(URL + "/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(testItem)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(objectMapper.writeValueAsString(testItem)));
    }

    @Test
    public void getAllTest() throws Exception{
        mockMvc.perform(get(URL + "/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(objectMapper.writeValueAsString(testList)));
    }

    @Test
    public void getTest() throws Exception{
        mockMvc.perform(get(URL + "/get/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(objectMapper.writeValueAsString(testItem)));
    }

    @Test
    public void updateTest() throws Exception{
        mockMvc.perform(put(URL + "/update/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(testItem)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(objectMapper.writeValueAsString(testItem)));
    }
}