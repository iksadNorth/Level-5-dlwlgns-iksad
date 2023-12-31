package com.sparta.post.controller;

import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.dto.CommentResponseDto;
import com.sparta.post.entity.Message;
import com.sparta.post.jwt.JwtUtil;
import com.sparta.post.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDto requestDto,
                                           @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return commentService.createComment(requestDto, tokenValue);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto,
                                           @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return commentService.updateComment(id, requestDto, tokenValue);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return commentService.deleteComment(id, tokenValue);
    }
}
