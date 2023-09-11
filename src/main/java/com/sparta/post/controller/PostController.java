package com.sparta.post.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.dto.PostResponseListDto;
import com.sparta.post.entity.Message;
import com.sparta.post.entity.Post;
import com.sparta.post.jwt.JwtUtil;
import com.sparta.post.service.PostService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Slf4j
@RestController
// Json 형태로 객체 데이터를 반환
// @ResponseBody + @Controller
// Model 객체를 만들어 데이터를 담고 view 찾기
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // @RequestBody 는 Json 형식으로 넘겨주어야한다.
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto requestDto,
                                      @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue)  {
        return postService.createPost(requestDto,tokenValue);
    }

    @GetMapping("/posts")
    public Page<PostResponseDto> getPosts(@PageableDefault(size=5) Pageable pageable){
        return postService.getPosts(pageable);
    }

    // @RequestBody -> Json 기반의 메시지를 사용하는 요청의 경우
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    //@PathVariable uri -> id
    @PutMapping("/post/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id,@RequestBody PostRequestDto requestDto,
                                            @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue){
        return postService.updatePost(id, requestDto, tokenValue);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<Message> deletePost(@PathVariable Long id, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue){
        return postService.deletePost(id,  tokenValue);
    }

    @GetMapping("/post/cate/{category}")
    public Page<PostResponseDto> getCategoryPost(@PageableDefault(size=5) Pageable pageable, @PathVariable("category") String category){
        return postService.getCategoryPost(pageable, category);
    }
}
