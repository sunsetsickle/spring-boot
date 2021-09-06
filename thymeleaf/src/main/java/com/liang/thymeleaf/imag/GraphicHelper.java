package com.liang.thymeleaf.imag;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public final class GraphicHelper {


    private static String captcha;

    public static String getCaptcha() {
        return captcha;
    }

    private static final Random random = new Random();
    private static final char[] CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    public static StringBuilder getCaptchaString(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 6; i++)
        {
            builder.append(CHARS[random.nextInt(CHARS.length)]);
        }

        captcha = String.valueOf(builder);

        return builder;

    }

    public static void create(HttpServletResponse response) throws IOException {
        final OutputStream output = response.getOutputStream(); // 获得可以向客户端返回图片的输出流
        final String imgType="jpeg";
        final int width=180;
        final int height=50;
        //StringBuilder sb = new StringBuilder();//对字符串的操作频繁，使用StringBuffer

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics graphic = image.getGraphics();

        //graphic.setColor(Color.getColor("F8F8F8"));

        graphic.fillRect(0, 0, width, height);

        Color[] colors = new Color[] { Color.BLUE, Color.GRAY, Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE,
                Color.CYAN };//验证码每个字符随机颜色（七种）


        // 在 "画板"上生成干扰线条 ( 50 是线条个数)
        for (int i = 0; i < 50; i++) {
            graphic.setColor(colors[random.nextInt(colors.length)]);
            final int x = random.nextInt(width);
            final int y = random.nextInt(height);
            final int w = random.nextInt(20);
            final int h = random.nextInt(20);
            final int signA = random.nextBoolean() ? 1 : -1;
            final int signB = random.nextBoolean() ? 1 : -1;
            graphic.drawLine(x, y, x + w * signA, y + h * signB);
        }



        // 在 "画板"上绘制字母
        graphic.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        StringBuilder captchaString=getCaptchaString();
        String s="";
        for (int i = 0; i < 6; i++) {
            s = String.valueOf((char)captchaString.codePointAt(i));
            graphic.setColor(colors[random.nextInt(colors.length)]);
            graphic.drawString(s, i * (width / 6), height - (height / 3));
        }
        graphic.dispose();
        try {
            ImageIO.write(image, imgType, output);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}