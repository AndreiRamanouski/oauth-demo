package com.appdev.photos.photos.controller;

import com.appdev.photos.photos.response.PhotoRest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photos")
@Slf4j
public class PhotosController {

    @GetMapping
    public List<PhotoRest> loadPhotos() {
        log.info("Load all photos");
        return List.of(PhotoRest.builder()
                        .photoDescription("photoDescription")
                        .photoUrl("url1")
                        .albumId("123")
                        .photoId("321")
                        .photoTitle("title")
                        .userId("1")
                        .build(),
                PhotoRest.builder()
                        .photoDescription("photoDescription")
                        .photoUrl("url2")
                        .albumId("123")
                        .photoId("432")
                        .photoTitle("title")
                        .userId("1")
                        .build());
    }
}
