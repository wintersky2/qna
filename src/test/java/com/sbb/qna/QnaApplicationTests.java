package com.sbb.qna;

import com.sbb.qna.answer.Answer;
import com.sbb.qna.answer.AnswerRepository;
import com.sbb.qna.question.Question;
import com.sbb.qna.question.QuestionRepository;
import com.sbb.qna.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QnaApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    QuestionService questionService;

    @Test
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);  // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장
    }

    @Test
    void test02() {
        List<Question> questionList = this.questionRepository.findAll();
        assertEquals(3, questionList.size());

        Question q = questionList.get(0);
        assertEquals("textSub", q.getSubject());
    }

    @Test
    void test03() {
        Optional<Question> optionalQuestion = this.questionRepository.findById(1);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            assertEquals(1, question.getId());
        }
    }

    @Test
    void test04() {
        Question question = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, question.getId());
    }

    @Test
    void test05() {
        Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());
    }

    @Test
    void test06() {
        List<Question> questionList = this.questionRepository.findBySubjectLike("sbb%");
        assertEquals(1, questionList.size());
    }

    @Test
    void test07() {
        Optional<Question> oq = this.questionRepository.findById(1);
        if (oq.isPresent()) {
            Question question = oq.get();
            question.setSubject("수정된 제목");
            question.setContent("수정된 내용");
            assertEquals("수정된 제목", question.getSubject());
            assertEquals("수정된 내용", question.getContent());
            this.questionRepository.save(question);
        }
    }

    @Test
    void test08() {
        Optional<Question> oq = this.questionRepository.findById(3);
        if (oq.isPresent()) {
            Question question = oq.get();
            this.questionRepository.delete(question);
        }
    }


    @Test
    void test09() {
        Answer a = new Answer();
        Optional<Question> oq = this.questionRepository.findById(1);
        Question question = oq.get();
        a.setQuestion(question);
        a.setCreateDate(LocalDateTime.now());
        a.setContent("답변~id=1");
        this.answerRepository.save(a);
    }

    @Test
    void test10() {
        List<Answer> answerList = this.answerRepository.findAll();
        assertEquals(1, answerList.size());
    }

    @Test
    void test11() {
        Optional<Answer> oa = this.answerRepository.findById(2);
        if (oa.isPresent()) {
            Answer answer = oa.get();
            answer.setContent("변경한 답변");
            this.answerRepository.save(answer);
            assertEquals("변경한 답변", oa.get().getContent());
        }
    }

    @Test
    void test12() {
        Optional<Answer> oa = this.answerRepository.findById(1);
        if (oa.isPresent()) {
            this.answerRepository.delete(oa.get());
            assertEquals(0, this.answerRepository.count());
        }
    }
    @Test
    void test13() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            this.questionService.create(subject, content);
        }
    }
}
