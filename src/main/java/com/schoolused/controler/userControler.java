package com.schoolused.controler;

import com.schoolused.entry.IdList;
import com.schoolused.entry.User;
import com.schoolused.mapper.UserDao;
import com.schoolused.service.Impl.userServiceImpl;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/User")
public class userControler {
    @Autowired
    userServiceImpl userService;
    @GetMapping
    public Result findAllUser(){
        List<User> sult = userService.findAllUser();
        return new Result(new code().REQUEST_OK,sult,"ok");
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        System.out.println(user);

        if(userService.loginService(user)!=null){
            return new Result(301,userService.getUser(user),userService.loginService(user),"登录成功");
        }else {
            return new Result(300,"登录失败");
        }

    }

   @GetMapping("/{token}")
   public Result login(@PathVariable String token){

        userService.loginOut(token);
        return new Result(301,"登出成功");
   }

   @PostMapping("/newUser")
    public Result newUser(@RequestBody User user){
        System.out.println(user);
        int Result = userService.insertUserServe(user);
        return new Result(Result,"插入结果");
   }

    @PostMapping("/updateuser")//更新用户信息
    public Result updateUser(@RequestBody User user){
        System.out.println(user);
        int Result = userService.updateUserServe(user);

        return new Result(code.REQUEST_OK,Result,"更新成功");
    }

   @PostMapping("/DeleteUser")
   public Result DeleteUser(@RequestBody List<Integer> ids){
       System.out.println(ids);
       int Result = userService.deleteServe( ids);
       return new Result(301,Result,"删除成功");
   }
   @GetMapping("/DeleteUser/{id}")
    public Result DeleteUser(@PathVariable int id) {
       if (id >= 0) {
           if (userService.deleteServe(id) > 0) {
               return new Result(code.REQUEST_OK, userService.deleteServe(id), "删除成功" + userService.deleteServe(id) + "条数据");
           } else {
               return new Result(code.REQUEST_ERRO, "删除失败");
           }
       }else {
           return new Result(code.REQUEST_ERRO, "删除失败");
       }
   }
}
