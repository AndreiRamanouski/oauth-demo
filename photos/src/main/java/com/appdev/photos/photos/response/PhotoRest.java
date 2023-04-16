package com.appdev.photos.photos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PhotoRest {

    private String albumId;
    private String photoId;
    private String userId;
    private String photoTitle;
    private String photoDescription;
    private String photoUrl;

}
