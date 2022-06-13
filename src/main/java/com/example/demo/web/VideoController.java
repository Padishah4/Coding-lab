package com.example.demo.web;

import com.example.demo.entity.ImageModel;
import com.example.demo.entity.Video;
import com.example.demo.payload.reponce.MessageResponse;
import com.example.demo.services.VideoUploadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.Principal;


@RestController
@RequestMapping("api/video")
@AllArgsConstructor
@CrossOrigin
public class VideoController {
    @Autowired
    private VideoUploadService videoUploadService;
    @PostMapping("/{postId}/upload")
    public ResponseEntity<MessageResponse> uploadVideoToPost(@PathVariable("postId") String postId,
                                                             @RequestParam("file") MultipartFile file,
                                                             Principal principal) throws IOException {
        videoUploadService.uploadVideoToPost(file,principal,Long.parseLong(postId));
        return ResponseEntity.ok(new MessageResponse("Video Uploaded Successfully"));
    }

    @GetMapping("/{postId}/video")
    public ResponseEntity<Video> getVideoToPost(@PathVariable("postId") String postId) {
        Video postVideo = videoUploadService.getVideoToPost(Long.parseLong(postId));
        return new ResponseEntity<>(postVideo, HttpStatus.OK);
    }



}
