package com.op.opsim.controller;

import com.op.opsim.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // 1. Random Emoji from emojis.json according to ${topic}
    @GetMapping(path = "/emoji")
    public ResponseEntity<Object> getRandomEmoji(String topic) {
        try {
            String emoji = messageService.getRandomEmojiByTopic(topic);
            if (emoji == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(emoji);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 2. Random Magic words from poemMap in magic.json
    @GetMapping(path = "/magic")
    public ResponseEntity<Object> getRandomMagicWord(String topic) {
        try {
            String magicWord = messageService.getRandomMagicWordByTopic(topic);
            if (magicWord == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(magicWord);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. Answer with meme if hit memeMap in magic.json
    @GetMapping(path = "/meme")
    public ResponseEntity<Object> getMemeAnswer(String question) {
        try {
            String memeAnswer = messageService.getMemeAnswerByQuestion(question);
            if (memeAnswer == null) {
                memeAnswer = "Klee don't know~ :kleemote:";
            }
            return ResponseEntity.ok(memeAnswer);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 4. What to eat today?
    @GetMapping(path = "/eat")
    public ResponseEntity<Object> getRandomEat(String topic) {
        try {
            String foodSuggestion = messageService.getRandomFoodSuggestionByTopic(topic);
            if (foodSuggestion == null) {
                foodSuggestion = "cup noodles";
            }
            return ResponseEntity.ok(foodSuggestion);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
