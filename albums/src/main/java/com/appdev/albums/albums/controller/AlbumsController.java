package com.appdev.albums.albums.controller;

import com.appdev.albums.albums.response.AlbumRest;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("albums")
public class AlbumsController {

    @GetMapping
    public List<AlbumRest> loadAlbums() {

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
