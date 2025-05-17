package org.etjen.quizz_app.service;

import org.etjen.quizz_app.model.Question;
import org.etjen.quizz_app.model.QuestionWrapper;
import org.etjen.quizz_app.model.Quiz;
import org.etjen.quizz_app.model.SubmitQuiz;
import org.etjen.quizz_app.repository.QuestionRepository;
import org.etjen.quizz_app.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Question q : questions){
            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );
            questionWrappers.add(qw);
        }
        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<SubmitQuiz> responses) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        int right = 0;
        int i = 0;
        for (SubmitQuiz r : responses){
            if (r.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
