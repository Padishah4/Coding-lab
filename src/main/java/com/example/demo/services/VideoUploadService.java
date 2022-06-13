package com.example.demo.services;


import com.example.demo.entity.ImageModel;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.entity.Video;
import com.example.demo.exceptions.ImageNotFoundException;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class VideoUploadService {
    public static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    private VideoRepository videoRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    @Autowired
    public VideoUploadService(VideoRepository videoRepository, UserRepository userRepository, PostRepository postRepository) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Video uploadVideoToPost(MultipartFile file, Principal principal, Long postId) throws IOException{
        User user = getUserByPrincipal(principal);
        Post post = user.getPosts()
                .stream()
                .filter(p-> p.getId().equals(postId))
                .collect(toSinglePostCollector());

        Video video = new Video();
        video.setPostId(post.getId());
        video.setData(file.getBytes());
        video.setUserId(user.getId());
        video.setName(file.getOriginalFilename());
        LOG.info("Uploading video to Post {} ",post.getId());

        return videoRepository.save(video);
    }

    public Video getVideoToPost (Long postId) {
        Video video = videoRepository.findByPostId(postId)
                .orElseThrow(()-> new ImageNotFoundException("Cannot find video to Post: " + postId));
        if (!ObjectUtils.isEmpty(video)) {
            video.setData(video.getData());
        }
        return video;
    }


    public User getUserByPrincipal(Principal principal){
        String username = principal.getName();
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }
    private <T> Collector<T,?,T> toSinglePostCollector() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1){
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }


}
