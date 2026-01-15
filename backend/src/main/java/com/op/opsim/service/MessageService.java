package com.op.opsim.service;

import com.op.opsim.provider.EmojiProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

    @Autowired
    private final EmojiProvider emojiProvider;

    @Autowired
    private final MagicProvider magicProvider;

    public String getRandomEmojiByTopic(String topic) {
        String emoji = "";
        if (emojiProvider.hasTopic(topic)) {
            var emojis = emojiProvider.getEmojisByTopic(topic);
            //TODO: use Lottery to get random emoji
            int randomIndex = (int) (Math.random() * emojis.size());
            emoji = emojis.get(randomIndex);
        } else {
            emoji = magicProvider.getMagicEmoji();
        }
        return emoji;
    }


}
