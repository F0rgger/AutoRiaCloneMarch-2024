package com.autoria.autoriaplatform.service;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProfanityFilterService {

    private static final Set<String> PROFANITIES = Set.of("badword1", "badword2", "badword3");

    public boolean containsProfanity(String text) {
        for (String word : PROFANITIES) {
            if (text.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }
}