package com.sbb.qna.answer;

import com.sbb.qna.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
     private final AnswerRepository answerRepository;

     public String create(Question question, String content){
         Answer answer = new Answer();
         answer.setContent(content);
         answer.setQuestion(question);
         answer.setCreateDate(LocalDateTime.now());
         this.answerRepository.save(answer);

         return question.getSubject();
     }
}
