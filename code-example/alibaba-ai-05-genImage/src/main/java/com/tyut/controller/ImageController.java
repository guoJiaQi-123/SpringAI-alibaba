package com.tyut.controller;

import org.springframework.ai.image.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @author OldGj 2025/1/6
 * @apiNote ImageModel
 */
@RestController
@RequestMapping("/ai")
public class ImageController {

    private final ImageModel imageModel;

    ImageController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping("/genImage")
    public String chat(String input) {
        ImageOptions options = ImageOptionsBuilder.builder()
                .withModel("wanx-v1")
                .build();
        ImagePrompt imagePrompt = new ImagePrompt(input, options);
        ImageResponse response = imageModel.call(imagePrompt);
        String imageUrl = response.getResult().getOutput().getUrl();
        return "redirect:" + imageUrl;
    }
}