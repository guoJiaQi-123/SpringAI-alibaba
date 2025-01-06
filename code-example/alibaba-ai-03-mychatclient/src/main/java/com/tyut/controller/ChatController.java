package com.tyut.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @version v1.0
 * @author OldGj 2025/1/6
 * @apiNote 流式输出
 */
@RestController
@RequestMapping("/ai")
public class ChatController {

    @Resource
    private ChatClient chatClient;


    @GetMapping("/chat")
    public Flux<String> chat(String input) {
        return this.chatClient.prompt()
                .user(input)
                .stream()
                .content();
    }
}