package com.schoolused.controler;


import com.schoolused.entry.uuidAndImg;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.Base64;

@RestController
@RequestMapping("/font/image")
public class fontCodeControl {//用于生成字符图片来进行验证操作
    //基础字符内容
    private String words="123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    private Random r = new Random();
    private String uuid;
    private Base64.Encoder encoder = Base64.getEncoder();
    Map<String,Object> checkCode= new HashMap<>();
    @GetMapping("/get")
    public uuidAndImg get(HttpServletResponse response )throws IOException {
        int width=200;
        int height=40;
        int count=4;
        //创建画板
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //创建笔触
        Graphics gs = bufferedImage.getGraphics();
        //设置笔触颜色
        gs.setColor(Color.lightGray);
        //涂满整个画板
        gs.fillRect(0,0,width,height);

        gs.setFont(new Font("宋体",Font.BOLD+Font.ITALIC,40));
        int length = words.length();//获取words的长度
        //记录生成的字符
        StringBuffer stringCode = new StringBuffer();
        for(int i=0;i<count;i++)
        {
            //在图片中填充文字
            gs.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));//设置随机rgb颜色

            //随机0~words的个数值,不能取到最大值
            int index=r.nextInt(length);
            char c = words.charAt(index);
            gs.drawString(c+"", 30+(i*40), 30);
            stringCode.append(c);
        }
        gs.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        gs.drawArc(r.nextInt(width),r.nextInt(height),30,60 ,0,360);
        System.out.println(stringCode.toString());
        uuid = UUID.randomUUID().toString();
        checkCode.put(uuid,stringCode);
        //输出图片到客户端
        //ServletOutputStream os = response.getOutputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"png",os);
        byte[] imageBytes = os.toByteArray();
        String imge =encoder.encodeToString(imageBytes);
        return new uuidAndImg(uuid,imge);
    }

    @GetMapping("/chieck/{code}/{uuid}")
    public Result checkCode(@PathVariable String code,@PathVariable String uuid){
        boolean isAccess=false;
        try {
            isAccess=code.equals(checkCode.get(uuid).toString());
        }catch (NullPointerException e){
            isAccess=false;
        }

        if(isAccess){
            checkCode.remove(uuid);
            System.out.println(checkCode.toString());
            return new Result(301,true,"验证成功");
        }else{
            return new Result(300,false,"验证失败");
        }

    }

}
