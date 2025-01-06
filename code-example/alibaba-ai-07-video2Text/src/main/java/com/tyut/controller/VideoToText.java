package com.tyut.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.dashscope.chat.MessageFormat;
import com.tyut.util.FrameHelper;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 图生文 -》 图像理解
 *
 */
@RestController
@RequestMapping("/ai")
public class VideoToText {
    @Resource
    private ChatModel chatModel;

    @PostMapping("/video")
    public String video(MultipartFile file, String message) {
        // 1、视频图片
        List<Media> mediaList = FrameHelper.load(file);
        UserMessage userMessage = new UserMessage(message, mediaList);
        userMessage.getMetadata().put(DashScopeChatModel.MESSAGE_FORMAT, MessageFormat.VIDEO);
        // 2、发送请求
        ChatResponse chatResponse = chatModel.call(new Prompt(userMessage, DashScopeChatOptions.builder()
                .withModel("qwen-vl-max-latest")
                .withMultiModel(true).build()));
        return chatResponse.getResult().getOutput().getContent();
    }
}