package com.sparta.post.controller;

import com.sparta.post.security.UserDetailsImpl;
import com.sparta.post.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/post/{id}/likes")
    public ResponseEntity<?> createPost(@PathVariable Long id,
                                      @AuthenticationPrincipal UserDetailsImpl principal)  {
        return likeService.togglePostLikes(id, principal.getUser());
    }
}
