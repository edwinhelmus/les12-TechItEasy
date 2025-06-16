package nl.novi.TechItEasy.controllers;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

abstract class Common {

    public static URI constructURI(Long id) {
        URI uri = URI.create(
                ServletUriComponentsBuilder.
                        fromCurrentRequest().
                        path("/" + id).toUriString());
        return uri;
    }
}
