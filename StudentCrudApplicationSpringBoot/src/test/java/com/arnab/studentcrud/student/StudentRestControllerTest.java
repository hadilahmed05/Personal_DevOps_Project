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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)

class StudentRestControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    StudentRestControllerTest() {
    }

    public ResponseEntity<String> doRestCall(String uri, MultiValueMap<String, String> querryParams, MultiValueMap<String, String> headers, String body, Map<String, String> pathParams, HttpMethod method) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);
        HttpEntity<String> requestEntity = new HttpEntity(body, headers);
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(builder.buildAndExpand(pathParams).toUri(), method, requestEntity, String.class);
        return responseEntity;
    }

    @Test
    void getAllStudents() {
        String url = "http://localhost:" + this.port;
        Map<String, String> pathVariables = new HashMap();
        HttpEntity<String> entity = new HttpEntity((Object)null, (MultiValueMap)null);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<String> response = this.restTemplate.exchange(builder.buildAndExpand(pathVariables).toUri(), HttpMethod.GET, entity, String.class);
        System.out.println((String)response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addStudent() {
        String url = "http://localhost:" + this.port + "/students";
        String body = "{\"name\":\"John\",\"grp\":\"SQA\",\"age\":25}";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        List<String> al = new ArrayList();
        al.add("application/json");
        headers.put("content-type", al);
        Map<String, String> pathParams = new HashMap();
        ResponseEntity<String> response = this.doRestCall(url, (MultiValueMap)null, headers, body, pathParams, HttpMethod.POST);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getStudentById() {
        String url = "http://localhost:" + this.port + "/students/{id}";
        Map<String, String> pathVariables = new HashMap();
        pathVariables.put("id", "14");
        HttpEntity<String> entity = new HttpEntity((Object)null, (MultiValueMap)null);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<String> response = this.restTemplate.exchange(builder.buildAndExpand(pathVariables).toUri(), HttpMethod.GET, entity, String.class);
        System.out.println((String)response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateStudent() {
        String url = "http://localhost:" + this.port + "/students/{id}";
        String body = "{\"name\":\"John\",\"grp\":\"ML\",\"age\":25}";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        List<String> al = new ArrayList();
        al.add("application/json");
        headers.put("content-type", al);
        Map<String, String> pathParams = new HashMap();
        pathParams.put("id", "14");
        ResponseEntity<String> response = this.doRestCall(url, (MultiValueMap)null, headers, body, pathParams, HttpMethod.PUT);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteStudentById() {
        String url = "http://localhost:" + this.port + "/students/{id}";
        String body = "{\"name\":\"John\",\"grp\":\"ML\",\"age\":25}";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        List<String> al = new ArrayList();
        al.add("application/json");
        headers.put("content-type", al);
        Map<String, String> pathParams = new HashMap();
        pathParams.put("id", "18");
        ResponseEntity<String> response = this.doRestCall(url, (MultiValueMap)null, headers, body, pathParams, HttpMethod.DELETE);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}