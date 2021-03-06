package com.assignment.demo;


import com.assignment.demo.domain.EventConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.Connection;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class MyApplicationTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Connection connection;

    @Mock
    private EventConverter eventConverter;

    private MyApplication myApplication;

    @Before
    public void setup() {
        initMocks(this);
        myApplication = new MyApplication(objectMapper, connection, eventConverter);
    }

    @Test(expected = InvalidParameterException.class)
    public void testRun_EmptyArgs() throws IOException {
        String[] args = {};
        myApplication.run(args);
    }

    @Test(expected = InvalidParameterException.class)
    public void testRun_EmptyFilePath() throws IOException {
        String[] args = {""};
        myApplication.run(args);
    }

    @Test(expected = FileNotFoundException.class)
    public void testRun_InvalidFilePath() throws IOException {
        String[] args = {"invalid"};
        myApplication.run(args);
    }

    @Test(expected = NullPointerException.class)
    public void testRun_ValidJsonMissingRequiredField() throws IOException {
        String[] args = {"src/test/resources/missing_required_fields.txt"};
        myApplication.run(args);
    }

    @Test(expected = NullPointerException.class)
    public void testRun_InvalidJson() throws IOException {
        String[] args = {"src/test/resources/invalid_json.txt"};
        myApplication.run(args);
    }
}