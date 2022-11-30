package com.example.operationbasics;

public interface DataController {

    void additionScore(int correctAnswer, int studentAnswer);
    void subtractionScore(int correctAnswer, int studentAnswer);
    void quit();
    void next();
}
