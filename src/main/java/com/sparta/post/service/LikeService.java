package com.sparta.post.service;

import com.sparta.post.entity.Message;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.PostLike;
import com.sparta.post.entity.User;
import com.sparta.post.repository.PostLikeRepository;
import com.sparta.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<?> togglePostLikes(Long id, User user) {
        // 좋아요 조회
        // 있으면 삭제
        // 없으면 생성
        postLikeRepository.findByPost_IdAndUser_Id(id, user.getId())
                .ifPresentOrElse(
                        postLikeRepository::delete,
                        () -> createPostLikes(id, user)
                );

        Message msg = new Message(200, "좋아요 요청 성공");
        return new ResponseEntity<>(msg,null, HttpStatus.OK);
    }

    private void createPostLikes(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
        postLikeRepository.save(new PostLike(post, user));
    }

    public Long getLikesOfPost(Long postId) {
        return postLikeRepository.countByPost_Id(postId);
    }
}
