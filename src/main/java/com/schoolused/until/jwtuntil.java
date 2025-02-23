package com.schoolused.until;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
@Component
public class jwtuntil {//生成token
    public long time =1000* 60*60*24;
    //private String signature ="admin";
    public String jwt(String username,String signature,String role){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header 头
                .setHeaderParam("type","JWT")
                .setHeaderParam("alg","HS256")
                //payload 载荷
                .claim("username",username)//用户名
                .claim("role",role)//权限
                .setSubject("admin-test")//主题
                .setExpiration(new Date(System.currentTimeMillis()+time))//有效时间
                .setId(UUID.randomUUID().toString())//id
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)//加密算法
                .compact();//保存成字符串
        return jwtToken;
        //System.out.println(jwtToken);
    }

    public Claims  parse(String signature,String token){//对token进行解密
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJwts =jwtParser.setSigningKey(signature).parseClaimsJws(token);//对token进行解密
        Claims claims =claimsJwts.getBody();
        //System.out.println(claims.get("username"));
        return claims;
    }

    public boolean checkRole(Claims claims,String role){//鉴定权限
        System.out.println(claims.get("role"));
        if(claims.get("role").equals(role)){//判断是否为root权限
            return true;
        }else{
            return false;
        }

    }

}
