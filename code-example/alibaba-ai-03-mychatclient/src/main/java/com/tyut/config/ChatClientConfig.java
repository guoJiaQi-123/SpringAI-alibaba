package com.tyut.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version v1.0
 * @author OldGj 2025/1/6
 * @apiNote ChatClient配置类
 */
@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient getChatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultSystem("You are a friendly chat bot that answers question in the voice of a Pirate")
                .build();
    }

}
