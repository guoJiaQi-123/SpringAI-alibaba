package com.tyut.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.dashscope.chat.MessageFormat;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * 图生文 -》 图像理解
 *
 */
@RestController
@RequestMapping("/ai")
public class ImageToTextController {
    @Autowired
    private ChatModel chatModel;

    /**
     * 图生文
     * @param file 文件
     * @param message 提示词
     * @return
     */
    @PostMapping("/image")
    public String imageToText(MultipartFile file, String message) throws URISyntaxException, MalformedURLException {
        // 1、创建媒介
        Media media = new Media(MimeTypeUtils.IMAGE_PNG, file.getResource());
        // 第二种写法 图片url地址 https://dashscope.oss-cn-beijing.aliyuncs.com/images/dog_and_girl.jpeg
        // Media media = new Media(MimeTypeUtils.IMAGE_PNG, new URI("https://dashscope.oss-cn-beijing.aliyuncs.com/images/dog_and_girl.jpeg").toURL());
        // 2、创建用户消息
        UserMessage userMessage = new UserMessage(message, media);
        userMessage.getMetadata().put(DashScopeChatModel.MESSAGE_FORMAT, MessageFormat.IMAGE);
        // 3、发送给LLM
        ChatResponse chatResponse = chatModel.call(new Prompt(userMessage, DashScopeChatOptions.builder()
                .withModel("qwen-vl-max-latest")
                .withMultiModel(true)
                .build()));
        return chatResponse.getResult().getOutput().getContent();
    }
}