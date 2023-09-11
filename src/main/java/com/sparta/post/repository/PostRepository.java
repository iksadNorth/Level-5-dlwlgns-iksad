package com.sparta.post.repository;

import com.sparta.post.entity.Comment;
import com.sparta.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends CrudRepository<Post, Long> { //extends JpaRepository<Post, Long> {
   // List<Post> findAllByOrderByCreatedAt();
    Page<Post> findAllByOrderByCreatedAt(Pageable pageable);
}
