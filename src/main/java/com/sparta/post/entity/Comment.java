package com.sparta.post.entity;

import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.listener.LikesListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
@EntityListeners(LikesListener.class)
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<CommentLike> commentLikeList = new ArrayList<>();

    @Transient
    private Long likes;

    public Comment(CommentRequestDto requestDto, String username) {
        this.content = requestDto.getContent();
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

    public String getUsername() {
        return this.user == null ? "[UNKNOWN]" : this.user.getUsername();
    }

    // post가 출력이 안되어야함. dto 하나 만들어서 post 빼고 입력받고 Responsedto에도 해당 dto list 가져옴
}
