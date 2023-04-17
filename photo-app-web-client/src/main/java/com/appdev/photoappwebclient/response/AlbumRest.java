package com.appdev.photoappwebclient.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AlbumRest {

    private String albumId;
    private String userId;
    private String albumTitle;
    private String albumDescription;
    private String albumUrl;

}
