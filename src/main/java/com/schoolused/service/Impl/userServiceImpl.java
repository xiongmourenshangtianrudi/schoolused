package com.schoolused.service.Impl;

import com.schoolused.controler.code;
import com.schoolused.controler.token.token;
import com.schoolused.entry.User;
import com.schoolused.mapper.UserDao;
import com.schoolused.mapper.tokenDao;
import com.schoolused.service.userService;
import com.schoolused.until.jwtuntil;
import com.sun.glass.ui.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class userServiceImpl implements userService {

    @Autowired
    UserDao userDao;
    @Autowired
    jwtuntil jwtuntil;
    @Autowired
    token token;
    @Autowired
    tokenDao tokenDao;
    @Override
    public List<User> findAllUser() {
        return userDao.findAlluser();
    }

    @Override
    public String loginService(User userInfo) {

        User user = userDao.login(userInfo.getUserName());
        if (userInfo.getUserName() != "") {
            if (userInfo.getUserPwd().equals(user.getUserPwd())) {
                String Token = jwtuntil.jwt(user.getUserName(), user.getUserPwd(), user.getUserPermissions());//生成token
                token.saveToken(user.getUserName(),Token);
                System.out.println(token.getToken(user.getUserName()));
                return Token;
            } else {
                return null;
            }
        }
        return null;
        }

    @Override
    public User getUser(User userInfo) {
        User user = userDao.login(userInfo.getUserName());
        return user;
    }

    @Override
    public int loginOut(String value) {


        return 0;
    }

    @Override
    public int insertUserServe(User user) {

        int result = userDao.insertUerInfo(user);
        if(result >0){
            return 301;
        }else {
            return 300;
        }
    }

    @Override
    public int updateUserServe(User user) {
        int x =userDao.updateUserInfo(user);
        return x;
    }

    @Override
    public int deleteServe(List<Integer> id) {
             int res = userDao.delete(id);
             System.out.println(res);
        return res;
    }

    @Override
    public int deleteServe(int userid) {
        if( userid >=0){
            return userDao.deleteUser(userid);
        }else{
            return code.getRequestErro();
        }
    }

}
