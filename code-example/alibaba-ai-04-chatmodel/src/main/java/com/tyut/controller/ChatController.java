package com.tyut.controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @author OldGj 2025/1/6
 * @apiNote chatModel
 */
@RestController
@RequestMapping("/ai")
public class ChatController {

    private ChatModel chatModel;

    public ChatController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/chat")
    public String chat(String input) {
        ChatResponse chatResponse = chatModel.call(new Prompt(input));
        return chatResponse.getResult().getOutput().getContent();
    }
}