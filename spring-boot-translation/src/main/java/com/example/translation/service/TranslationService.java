package com.example.translation.service;

import com.example.translation.entities.Word;

import java.util.List;

/**
 * @author iumyx
 * @description:
 * @date 2024/4/16 13:42
 */
public interface TranslationService {

    List<Word> getBatchTranslate(List<Word> words, String sourceLanguage, String targetLanguage);
}
