package com.tyut;


import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Test;

import javax.swing.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void load() throws InterruptedException {
        // 1.读取图像
        Mat imageMat = opencv_imgcodecs.imread("F:\\GitHub\\SpringAI-alibaba\\haizeiwang.jpg");
        // 2.检查图像是否读取成功
        if (imageMat.empty()) {
            System.out.println("图像加载失败");
            return;
        }
        //3、滤镜美白
        Mat applyMat = apply(imageMat);
        //4、显示图片
        CanvasFrame canvasFrame = new CanvasFrame("hello");
        canvasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OpenCVFrameConverter.ToMat toMat = new OpenCVFrameConverter.ToMat();
        canvasFrame.showImage(toMat.convert(applyMat));
        //5、等待用户关闭窗口
        canvasFrame.waitKey();
    }

    /**
     * 美白滤镜
     * 调整图片的亮度和对比度
     * @param sourceImg
     * @return
     */
    private static Mat apply(Mat sourceImg) {
        Mat result = new Mat();
        // 调整亮度和对比度                 1.2 对比度  10  亮度
        sourceImg.convertTo(result, -1, 1.2, 10);
        return result;
    }
}
