package com.assessments.cicassesment.controller;

import com.assessments.cicassesment.entitiy.RecipientEntity;
import com.assessments.cicassesment.service.CicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.ModelExtensionsKt;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CicController.class, secure = false)
@ComponentScan(basePackages = { "classpath:/CicController"})
public class CicControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static final Logger logger = Logger.getLogger(CicController.class.getName());

    @MockBean
    private CicService service;
    RecipientEntity mockRecipient = new RecipientEntity("Fido M", "fido@mail.com");
    String exampleRecipientJson = "{\"name\":\"Fido\", \"emailAddress\": \"m@g.co\"]}";

    @Test
    public void addRecipient() {
    }

    @Test
    public void getRecipients() throws Exception{

        List<RecipientEntity> mockRecipients = new ArrayList<>();
        RecipientEntity recipient1 = new RecipientEntity(Mockito.anyString(), Mockito.anyString());
        RecipientEntity recipient2 = new RecipientEntity(Mockito.anyString(), Mockito.anyString());
        mockRecipients.add(recipient1);
        mockRecipients.add(recipient2);
        Mockito.when(service.getAllRecipients()).thenReturn(mockRecipients);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/cic/getallrecipients/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        logger.log(Level.INFO, ""+result.getResponse());

        String expected = "{id:1,name:Fido,emailAddress:m@g.co}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void getRecipient() {
    }

    @Test
    public void sendMail() {
    }

    @Test
    public void editRecipient() {
    }

    @Test
    public void deleteRecipient() {
    }
}