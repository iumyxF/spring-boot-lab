package com.example.translation.dao;

import com.example.translation.entities.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iumyx
 * @description:
 * @date 2024/4/16 14:03
 */
public class WordRepository {

    private static final ArrayList<Word> wordList = new ArrayList<>();

    private static volatile WordRepository repository;

    private WordRepository() {
    }

    public static WordRepository getInstance() {
        if (repository == null) {
            synchronized (WordRepository.class) {
                if (repository == null) {
                    repository = new WordRepository();
                }
            }
        }
        return repository;
    }

    public void save(List<Word> words) {
        wordList.addAll(words);
    }

    public List<Word> getAll() {
        return wordList;
    }
}
