package com.example.demo.repository;

import com.example.demo.entity.VideoModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<VideoModal, Long> {
    Optional<VideoModal> findByUserId(Long userId);
    Optional<VideoModal> findByPostId(Long postId);
}
