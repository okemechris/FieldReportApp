package com.areatechservices.fieldreportapp.Domain;

import android.net.Uri;

/**
 * Created by djbabs on 12/15/18.
 */

public class ImageDomain {
    private String name;

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    private Uri imageUri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
}
