package com.example.demo.repository;


import com.example.demo.entity.ImageModel;
import com.example.demo.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByUserId(Long userId);
    Optional<Video> findByPostId(Long postId);

}
