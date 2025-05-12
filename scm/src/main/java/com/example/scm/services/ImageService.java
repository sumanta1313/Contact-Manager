package com.example.scm.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadimage(MultipartFile contactimage, String filename);

    String getUrlFromPublicId(String publicId);

    String uploadImage(MultipartFile contactImage, String filename);
}
