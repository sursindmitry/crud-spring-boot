package com.sursindmitry.crud.service;

import com.sursindmitry.crud.dto.RestDto;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RestServiceImpl implements RestService {

    private final RestClient restClient;

    public RestServiceImpl() {
        this.restClient = RestClient.builder()
            .baseUrl("http://94.198.50.185:7081/api/users")
            .build();
    }


    @Override
    public String getAllUsers() {

        List<String> cookies = restClient.get()
            .uri("")
            .retrieve()
            .toBodilessEntity()
            .getHeaders().get(HttpHeaders.SET_COOKIE);

        String[] cookie = cookies.get(0).split(";");


        return cookie[0];
    }

    @Override
    public String addUser(String cookie) {
        RestDto dto = new RestDto();
        dto.setId(3L);
        dto.setName("James");
        dto.setLastName("Brown");
        dto.setAge((byte) 25);

        return restClient.post()
            .uri("")
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .header("Cookie", cookie)
            .retrieve()
            .body(new ParameterizedTypeReference<>() {
            });
    }

    @Override
    public String updateUser(String cookie) {
        RestDto dto = new RestDto();
        dto.setId(3L);
        dto.setName("Thomas");
        dto.setLastName("Shelby");
        dto.setAge((byte) 25);


        return restClient.put()
            .uri("")
            .header("Cookie", cookie)
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .retrieve()
            .body(new ParameterizedTypeReference<>() {
            });
    }

    @Override
    public String deleteUser(String cookie) {

        return restClient.delete()
            .uri("/" + 3L)
            .header("Cookie", cookie)
            .retrieve()
            .body(new ParameterizedTypeReference<>() {
            });
    }
}
