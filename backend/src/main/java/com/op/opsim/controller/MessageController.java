package com.op.opsim.controller;

import com.op.opsim.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/message")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;


    // 1. Random Emoji from emojis.json according to ${topic}
    @GetMapping(path = "/emoji", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getRandomEmoji(@RequestParam(value = "topic", required = false) String topic) {
        try {
            String emoji = messageService.getRandomEmojiByTopic(topic);
            if (emoji == null || emoji.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(emoji);
        } catch (Exception e) {
            logger.error("Failed to get random emoji for topic={}", topic, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 2. Random Magic words from poemMap in magic.json
    @GetMapping(path = "/magic", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getRandomMagicWord(@RequestParam(value = "topic", required = false) String topic) {
        try {
            String magicWord = messageService.getRandomMagicWordByTopic(topic);
            if (magicWord == null || magicWord.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(magicWord);
        } catch (Exception e) {
            logger.error("Failed to get random magic word for topic={}", topic, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 3. Answer with meme if hit memeMap in magic.json
    @GetMapping(path = "/meme", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getMemeAnswer(@RequestParam(value = "question", required = false) String question) {
        try {
            String memeAnswer = messageService.getMemeAnswerByQuestion(question);
            if (memeAnswer == null || memeAnswer.isEmpty()) {
                memeAnswer = "Klee don't know~ :kleemote:";
            }
            return ResponseEntity.ok(memeAnswer);
        } catch (Exception e) {
            logger.error("Failed to get meme answer for question={}", question, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 4. What to eat today?
    @GetMapping(path = "/eat", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getRandomEat(@RequestParam(value = "topic", required = false) String topic) {
        try {
            String foodSuggestion = messageService.getRandomFoodSuggestionByTopic(topic);
            if (foodSuggestion == null || foodSuggestion.isEmpty()) {
                foodSuggestion = "cup noodles";
            }
            return ResponseEntity.ok(foodSuggestion);
        } catch (Exception e) {
            logger.error("Failed to get food suggestion for topic={}", topic, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
