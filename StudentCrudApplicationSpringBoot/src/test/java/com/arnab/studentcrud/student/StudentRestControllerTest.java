package com.arnab.studentcrud.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> doRestCall(String uri, MultiValueMap<String, String> queryParams, MultiValueMap<String, String> headers, String body, Map<String, String> pathParams, HttpMethod method) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);
        if (queryParams != null) {
            builder.queryParams(queryParams);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(builder.buildAndExpand(pathParams).toUri(), method, requestEntity, String.class);
        return responseEntity;
    }

    @Test
    void getAllStudents() {
        String url = "http://localhost:" + this.port + "/students";
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        System.out.println(response.getBody());
        
    }

    @Test
    void addStudent() {
        String url = "http://localhost:" + this.port + "/students";
        String body = "{\"name\":\"John\",\"grp\":\"SQA\",\"age\":25}";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        
        
    }

    @Test
    void getStudentById() {
        String url = "http://localhost:" + this.port + "/students/{id}";
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", "14");
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class, pathVariables);
        System.out.println(response.getBody());
        
    }

    @Test
    void updateStudent() {
        String url = "http://localhost:" + this.port + "/students/{id}";
        String body = "{\"name\":\"John\",\"grp\":\"ML\",\"age\":25}";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", "14");
        
        
    }

    @Test
    void deleteStudentById() {
        String url = "http://localhost:" + this.port + "/students/{id}";
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", "18");
        
    }
}
