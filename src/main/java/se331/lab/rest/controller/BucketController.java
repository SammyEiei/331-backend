package se331.lab.rest.controller;

import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import se331.lab.rest.util.CloudStorageHelper;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class BucketController {
    final CloudStorageHelper cloudStorageHelper;
    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadFileComponent(@RequestPart(value = "image") MultipartFile file) throws IOException, ServletException {
        return ResponseEntity.ok(this.cloudStorageHelper.getStorageFileDto(file, "imageupload-f5898.appspot.com"));
    }
}