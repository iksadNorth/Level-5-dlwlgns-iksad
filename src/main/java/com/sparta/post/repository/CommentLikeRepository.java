package com.sparta.post.repository;

import com.sparta.post.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByComment_IdAndUser_Id(Long commentId, Long userId);

    Long countByComment_Id(Long commentId);
}
