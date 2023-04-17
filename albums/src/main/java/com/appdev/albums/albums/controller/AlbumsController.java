package com.appdev.albums.albums.controller;

import com.appdev.albums.albums.response.AlbumRest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("albums")
@Slf4j
public class AlbumsController {

    @GetMapping
    public List<AlbumRest> loadAlbums() {
        log.info("Load Albums");
        return List.of(AlbumRest.builder()
                        .albumDescription("albumDescription")
                        .albumId("123")
                        .albumTitle("Title")
                        .albumUrl("url3")
                        .userId("1")
                        .build(),
                AlbumRest.builder()
                        .albumDescription("albumDescription")
                        .albumId("124")
                        .albumTitle("Title")
                        .albumUrl("url4")
                        .userId("1")
                        .build());
    }

}
