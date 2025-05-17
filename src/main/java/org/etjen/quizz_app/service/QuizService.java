package org.etjen.quizz_app.service;

import org.etjen.quizz_app.model.Question;
import org.etjen.quizz_app.model.Quiz;
import org.etjen.quizz_app.repository.QuestionRepository;
import org.etjen.quizz_app.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;


    public ResponseEntity<String> createQuiz(String category, int numOfQ, String title) {
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numOfQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
