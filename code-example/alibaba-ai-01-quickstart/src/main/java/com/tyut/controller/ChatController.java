package com.tyut.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @author OldGj 2025/1/6
 * @apiNote 快速入门
 *
 * 这个类是一个控制器，用于处理与聊天相关的HTTP请求。
 * 使用Spring框架的@RestController注解标记为一个RESTful控制器。
 * 映射的URL路径前缀为"/ai"。
 */
@RestController
@RequestMapping("/ai")
public class ChatController {

    /**
     * 私有成员变量chatClient，用于与聊天客户端进行交互。
     * 使用final修饰，表示一旦初始化后不可更改。
     */
    private final ChatClient chatClient;

    /**
     * 构造函数，通过传入的ChatClient.Builder对象来构建ChatClient实例。
     * @param builder ChatClient的构建器对象，用于创建ChatClient实例。
     */
    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /**
     * 处理GET请求，映射到"/ai/chat"路径。
     * 接收一个名为input的请求参数，表示用户输入的聊天内容。
     * @param input 用户输入的聊天内容。
     * @return 返回聊天客户端生成的响应内容。
     */
    @GetMapping("/chat")
    public String chat(String input) {
        // 调用chatClient的prompt方法开始一个新的聊天提示。
        // 使用user方法设置用户输入的内容。
        // 调用call方法发送请求并获取响应。
        // 最后，通过content方法获取响应的内容并返回。
        return this.chatClient.prompt()
                .user(input)
                .call()
                .content();
    }
}