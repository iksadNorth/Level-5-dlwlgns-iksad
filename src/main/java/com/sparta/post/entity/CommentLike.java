package com.sparta.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "comment_like")
@Getter @Setter @NoArgsConstructor
public class CommentLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public CommentLike(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }
}
