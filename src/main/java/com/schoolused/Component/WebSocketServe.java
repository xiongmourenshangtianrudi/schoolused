package com.schoolused.Component;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.schoolused.controler.Result;
import com.schoolused.entry.admin.adminControlinfo;
import com.schoolused.entry.admin.systemNotice;
import com.schoolused.entry.chatContants;
import com.schoolused.entry.chatGroups;
import com.schoolused.service.Impl.systemNoticeImpl;
import com.schoolused.service.chatserve.impl.chatServiceImp;
import com.schoolused.until.Theads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author websocket服务
 */
@ServerEndpoint(value = "/chatserver/{userid}")
@Component
 public class WebSocketServe {

    private chatServiceImp chatServiceImp;
    private systemNoticeImpl systemNotice;

    private static final Logger log = LoggerFactory.getLogger(WebSocketServe.class);

    /**
     * 记录当前在线连接数
     */
    public static final Map<Integer, Session> sessionMap = new ConcurrentHashMap<>();

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServe.applicationContext = applicationContext;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userid") int userid) {

        sessionMap.put(userid, session);//用于当用户链接时触发
        log.info("有新用户加入，username={}, 当前在线人数为：{}", userid, sessionMap.size());
        chatServiceImp =applicationContext.getBean(chatServiceImp.class);//通过上下文获取bean
        systemNotice = applicationContext.getBean(systemNoticeImpl.class);//通过上下文获取bean
        List<systemNotice> notices = systemNotice.getNotice(userid);//查询出所有系统通知并发送到客户端
        System.out.println(chatServiceImp.getchatGroups(userid));
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        result.set("users", array);
        for (Object key : sessionMap.keySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("username", key);
            // {"username", "zhang", "username": "admin"}
            array.add(jsonObject);
        }
        //System.out.println(JSONUtil.toJsonStr(sessionMap));
        sendMessage(JSONUtil.toJsonStr(new Result(201,notices,"systemnotice")),session);
        sendMessage(JSONUtil.toJsonStr(new Result(301,chatServiceImp.
                getchatGroups(userid),"userinfo")),session);  // 后台发送消息给所有的客户端
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("userid") int userid) {
        sessionMap.remove(userid);
        log.info("有一连接关闭，移除username={}的用户session, 当前在线人数为：{}", userid, sessionMap.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * 后台收到客户端发送过来的消息
     * onMessage 是一个消息的中转站
     * 接受 浏览器端 socket.send 发送过来的 json数据
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userid") int userid) {
        log.info("服务端收到用户username={}的消息:{}", userid, message);
        chatServiceImp =applicationContext.getBean(chatServiceImp.class);
        JSONObject obj = JSONUtil.parseObj(message);
        int toUsername = obj.getInt("uid2"); // to表示发送给哪个用户，比如 admin
        int uid1 = obj.getInt("uid1");
        int cid =obj.getInt("cid");
        String text = obj.getStr("content"); // 发送的消息文本  hello
        long DateTime = obj.getLong("createtime");
        Date date = new Date(DateTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        // {"to": "admin", "text": "聊天文本"}
        chatContants chatContants = new chatContants();
        chatContants.setUid1(uid1);
        chatContants.setUid2(toUsername);
        chatContants.setCid(cid);
        chatContants.setContent(text);
        chatContants.setCreatetime(dateString);
        chatServiceImp.addChatcontants(chatContants); //往数据库中进行存储
        Session toSession = sessionMap.get(toUsername); // 根据 to用户名来获取 session，再通过session发送消息文本
        if (toSession != null) {
            // 服务器端 再把消息组装一下，组装后的消息包含发送人和发送的文本内容
            // {"from": "zhang", "text": "hello"}
            //chatServiceImp.getchatcontant(cid)
            this.sendMessage(JSONUtil.toJsonStr(chatContants), toSession);
            log.info("发送给用户username={}，消息：{}", toUsername, chatContants.toString());
            //往数据库中存储
        } else {
            log.info("发送失败，未找到用户username={}的session", toUsername);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

//    服务器发送通知信息


    public void sendWarnInfo(systemNotice notice, int toUsername){

        Session toSession = sessionMap.get(toUsername);
        if(toSession!=null){
            this.sendMessage(JSONUtil.toJsonStr(notice),toSession);//往客户端发送数据
        }else{
            log.info("当前用户不在线");
        }


    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

    /**
     * 服务端发送消息给所有客户端
     */
    private void sendAllMessage(String message) {
        try {

            for (Session session : sessionMap.values()) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }
}
