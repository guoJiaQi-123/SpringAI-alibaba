package com.tyut.util;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.ai.model.Media;
import org.springframework.core.io.PathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FrameHelper {
    public static List<Media> load(MultipartFile multipartFile) {
        List<Media> mediaList = new ArrayList<>();
        // 2、设置图片输出路径
        String imagePath = "F:\\GitHub\\SpringAI-alibaba\\code-example\\alibaba-ai-07-video2Text\\src\\main\\resources\\output\\";
        try {
            // 4、初始化视频抓取工具
            FFmpegFrameGrabber ff = new
                    FFmpegFrameGrabber(multipartFile.getInputStream());
            // 5、帧到图像转换工具
            Java2DFrameConverter converter = new Java2DFrameConverter();
            // 6、抓取内容
            ff.start();
            ff.setFormat("mp4");
            // 7、获取视频帧数
            int lengthInFrames = ff.getLengthInFrames();
            Frame frame;
            // 8、遍历视频帧。每一帧都转为图片保存
            int index = 0;
            for (int i = 0; i < lengthInFrames; i++) {
                // 9、抓取当前帧
                frame = ff.grabFrame();
                // 10、判断空帧
                if (frame.image == null) {
                    continue;
                }
//                if (index > 10) {
//                    break;
//                }
                index++;
                System.out.println(index);
                // 11、将视频帧转为BufferedImage
                BufferedImage image = converter.getBufferedImage(frame);// 12、图片路径
                String imageFilePath = imagePath + i + ".png";
                File file = new File(imageFilePath);// 13、将图像写入文件。并且记录图片路径
                ImageIO.write(image, "png", file);
                Media media = new Media(
                        MimeTypeUtils.IMAGE_PNG,
                        new PathResource(imageFilePath));
                mediaList.add(media);
            }
            ff.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mediaList;
    }
}