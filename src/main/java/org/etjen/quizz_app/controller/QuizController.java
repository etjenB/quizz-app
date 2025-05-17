package org.etjen.quizz_app.controller;

import org.etjen.quizz_app.model.Question;
import org.etjen.quizz_app.model.QuestionWrapper;
import org.etjen.quizz_app.model.Quiz;
import org.etjen.quizz_app.model.SubmitQuiz;
import org.etjen.quizz_app.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numOfQ, @RequestParam String title){
        return quizService.createQuiz(category, numOfQ, title);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<SubmitQuiz> responses){
        return quizService.submitQuiz(id, responses);
    }
}
