package com.yetx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
public class WebSocket {
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    private final Logger logger = LoggerFactory.getLogger(WebSocket.class);
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSockets.add(this);
        logger.info("新连接");
    }
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        logger.info("断开连接");
    }
    /*@OnError
    public void onError(){
        logger.error("连接出现问题");
    }*/
    @OnMessage
    public void onMessage(String message){
        logger.info("收到新消息：{}",message);
    }

    public void sendMessage(String message){
        for(WebSocket webSocket:webSockets){
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
