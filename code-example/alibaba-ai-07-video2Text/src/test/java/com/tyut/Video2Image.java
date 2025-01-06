package com.tyut;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @author OldGj 2025/1/6
 * @apiNote 视频转图片
 */
public class Video2Image {

    @Test
    public void video2Image() {
        // 定义视频文件路径
        File file = new File("F:\\GitHub\\SpringAI-alibaba\\jdk.mp4");
        // 定义帧图像保存目录
        String framePath = "F:\\GitHub\\SpringAI-alibaba\\output\\";
        // 存储帧图像文件路径的列表
        List<String> strList = new ArrayList<>();
        try {
            // 使用 FFmpegFrameGrabber 初始化视频抓取工具
            FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file.getPath());
            Java2DFrameConverter converter = new Java2DFrameConverter(); // 帧到图像转
            // 开始抓取视频内容
            ff.start();
            ff.setFormat("mp4"); // 设置视频格式为 mp4
            int length = ff.getLengthInFrames(); // 获取视频帧数
            Frame frame;
            // 遍历视频帧，每一帧转换为图像并保存
            for (int i = 1; i < length; i++) {
                frame = ff.grabFrame(); // 抓取当前帧
                if (frame.image == null) { // 跳过空帧
                    continue;
                }
                // 将视频帧转换为 BufferedImage 格式
                BufferedImage image = converter.getBufferedImage(frame);// 定义当前帧图像的保存路径
                String path = framePath + i + ".png";
                File picFile = new File(path);
                // 将图像写入文件，并记录图像路径
                ImageIO.write(image, "png", picFile);
                strList.add(path); // 保存图像路径到列表中
            }
            // 将所有帧图像路径保存到 IMAGE_CACHE 缓存中
            //IMAGE_CACHE.put("img", strList);// 结束视频抓取
            ff.stop();
        } catch (
                Exception e) {
            // 捕获异常并输出错误信息
            System.err.println("视频帧抓取失败：" + e.getMessage());

        }

    }
}