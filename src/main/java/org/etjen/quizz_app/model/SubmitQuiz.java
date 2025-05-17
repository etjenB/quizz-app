package org.etjen.quizz_app.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SubmitQuiz {
    private Integer id;
    private String response;
}
