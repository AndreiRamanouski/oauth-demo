package com.appdev.photoappwebclient.controller;

import com.appdev.photoappwebclient.response.AlbumRest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AlbumsController {

    //    private final RestTemplate restTemplate;

    private final WebClient webClient;

    @GetMapping
    public String getAlbums(Model model,
            @AuthenticationPrincipal OidcUser principal) {

        log.info("Get albums");
        log.info("Principal: {}", principal);
        OidcIdToken idToken = principal.getIdToken();
        log.info("Token {}", idToken.getTokenValue());

        String url = "http://localhost:8082/albums";

        //        HttpHeaders headers = new HttpHeaders();
        //        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " +tokenValue);
        //        ResponseEntity<List<AlbumRest>> responseEntity = restTemplate.exchange(url,
        //        HttpMethod.GET, new HttpEntity<>(headers),
        //                new ParameterizedTypeReference<List<AlbumRest>>() {
        //                });
        //        log.info("Response from albums-service: {}", responseEntity);
        //        List<AlbumRest> albumRest = responseEntity.getBody();

        List<AlbumRest> albumRest = webClient.get().uri(url).retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>() {
                }).block();

        log.info("Albums {}", albumRest);
        model.addAttribute("albums", albumRest);
        return "albums";
    }

}
