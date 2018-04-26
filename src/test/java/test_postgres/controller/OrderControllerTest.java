package test_postgres.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import springfox.documentation.spring.web.json.Json;
import test_postgres.jpa.entity.Orders;
import test_postgres.mock.MockServiceOrder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MockServiceOrder.class, OrderController.class})
public class OrderControllerTest {
    @Autowired
    private OrderController controller;

    private MockMvc mockMvc;
    private final String URL = "http://localhost:8080/api/orders";
    private ObjectMapper objectMapper = new ObjectMapper();
    private Orders elem = new Orders(LocalDate.parse("2000-01-01"), 1);
    private List<Orders> testList = Arrays.asList(elem, elem, elem);

    @Before
    public void init() throws Exception{
        //MockitoAnnotations.initMocks(this); / ???
        mockMvc = standaloneSetup(controller).build();

        objectMapper.registerModule(new JavaTimeModule());
        //objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
    }

    @Test
    public void addTest() throws Exception{
        mockMvc.perform(post(URL + "/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(elem)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(objectMapper.writeValueAsString(elem)));
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
                .andExpect(content().string(objectMapper.writeValueAsString(elem)));
    }

    @Test
    public void updateTest() throws Exception{
        mockMvc.perform(put(URL + "/update/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(elem)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(objectMapper.writeValueAsString(elem)));
    }
}