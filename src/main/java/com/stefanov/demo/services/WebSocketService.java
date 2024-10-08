package com.stefanov.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Service
@Slf4j
public class WebSocketService extends TextWebSocketHandler {

    private WebSocketSession session;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.debug("WS session to client started.");
        session.setTextMessageSizeLimit(9999999);
        this.session = session;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        this.session = null;
        log.debug("WS session to client closed... {}", status);
    }

    public boolean send(String str) {
        if (session == null) {
            return false;
        }
        try {
            session.sendMessage(new TextMessage(str));
        } catch (IOException e) {
            log.error("There was an error sending ws message: ", e);
        }
        return true;
    }
}