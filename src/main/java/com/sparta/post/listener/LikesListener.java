package com.sparta.post.listener;

import com.sparta.post.config.ListenerConfig;
import com.sparta.post.entity.Comment;
import com.sparta.post.entity.Post;
import com.sparta.post.service.LikeService;
import jakarta.persistence.PostLoad;

public class LikesListener {
    private static LikeService likeService;

    private static LikeService getLikeService() {
        if(likeService == null)
            likeService = ListenerConfig.getContext().getBean(LikeService.class);
        return likeService;
    }

    @PostLoad
    public void callbackFunctionOnPostLoad(Object entity) {
        if(entity instanceof Post post) {
            setMetaInfoToPost(post);
        } else if(entity instanceof Comment comment) {
            setMetaInfoToComment(comment);
        }
    }

    private void setMetaInfoToPost(Post post) {
        // 좋아요 갯수 부여.
        Long likes =  getLikeService().getLikesOfPost(post.getId());
        post.setLikes(likes);
    }

    private void setMetaInfoToComment(Comment comment) {
        // 좋아요 갯수 부여.
        Long likes =  getLikeService().getLikesOfComment(comment.getId());
        comment.setLikes(likes);
    }
}
