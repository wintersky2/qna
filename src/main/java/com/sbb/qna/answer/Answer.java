package com.sbb.qna.answer;

import com.sbb.qna.question.Question;
import com.sbb.qna.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "TEXT")
    private String content;
    @CreatedDate
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    @ManyToOne
    private Question question;
    @ManyToOne
    private SiteUser author;
    @ManyToMany
    Set<SiteUser> voter;
}
