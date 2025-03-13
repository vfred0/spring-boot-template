package com.gnoboa.api.resources;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public class HttpHeader {
    public static HttpHeaders getHttpHeaders(UUID userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId)
                .toUri();
        httpHeaders.setLocation(location);
        return httpHeaders;
    }
}
