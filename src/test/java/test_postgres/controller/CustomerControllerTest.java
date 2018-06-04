package test_postgres.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import test_postgres.jpa.entity.Customer;
import test_postgres.mock.MockServiceCustomer;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MockServiceCustomer.class, CustomerController.class})
public class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    private MockMvc mockMvc;
    private final String URL = "http://localhost:8080/api/customer";
    private ObjectMapper objectMapper = new ObjectMapper();
    private Customer testCustomer = new Customer("Superman", true);
    private List<Customer> testList = Arrays.asList(testCustomer, testCustomer, testCustomer);

    @Before
    public void init() throws Exception{
        //MockitoAnnotations.initMocks(this); / ???
        mockMvc = standaloneSetup(customerController).build();
    }

    @Test
    public void customerCheckTest() throws Exception{
        mockMvc.perform(get(URL + "/check/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("true"));
    }

    @Test
    public void vipStatusCheckTest() throws Exception{
        mockMvc.perform(get(URL + "/check/vip/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("true"));
    }

    @Test
    public void addTest() throws Exception{
        mockMvc.perform(post(URL + "/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(testCustomer)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(objectMapper.writeValueAsString(testCustomer)));
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
                .andExpect(content().string(objectMapper.writeValueAsString(testCustomer)));
    }

    @Test
    public void updateTest() throws Exception{
        mockMvc.perform(put(URL + "/update/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(testCustomer)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(objectMapper.writeValueAsString(testCustomer)));
    }
}