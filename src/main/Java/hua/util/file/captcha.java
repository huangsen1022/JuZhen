package hua.util.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class captcha{
	private static Random r = new Random();
    private static char[] chs = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private static final int NUMBER_OF_CHS = 4;
    private static final int IMG_WIDTH = 70;
    private static final int IMG_HEIGHT = 30;
    @RequestMapping("/getCaptcha")
	public void getCaptcha(HttpSession httpSession,HttpServletResponse resp) {
		BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Color c = new Color(255, 255, 255);                                                                                     
        g.setColor(c);
        g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);  
        StringBuffer sb = new StringBuffer(); 
        Font f = new Font("arial",Font.PLAIN,20);
        g.setFont(f);
        int index;
        for (int i = 0; i < NUMBER_OF_CHS; i++) {
            index = r.nextInt(chs.length);
            g.setColor(new Color(r.nextInt(88), r.nextInt(210), r.nextInt(150)));
            g.drawString(chs[index] + "",15 * i + 3, 18);  
            sb.append(chs[index]);                                                      
        }
        for(int i=0;i<10;i++) {
        	g.drawLine(r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100));
        }
//        sb.toString().toLowerCase()
        httpSession.setAttribute("captcha", sb.toString().toLowerCase());
//        System.out.println("ͼ����֤�룺"+sb.toString().toLowerCase());
        try {
			ImageIO.write(image, "jpg", resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
