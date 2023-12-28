package com.sbb.qna.answer;

import com.sbb.qna.question.Question;
import com.sbb.qna.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;
    @PostMapping("/create/{id}")
    public String create(@PathVariable("id") Integer id, @RequestParam("content") String content) {
        Answer answer = new Answer();
        Question question = this.questionService.getQuestion(id);

        this.answerService.create(question,content);
        return String.format("redirect:/question/detail/%s", id);
    }
}
