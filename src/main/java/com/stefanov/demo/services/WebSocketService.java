package com.stefanov.demo.services;

import com.stefanov.demo.config.BnbUrlProps;
import com.stefanov.demo.config.WebSocketProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebSocketService extends TextWebSocketHandler {

    private WebSocketSession session;

    private final WebSocketProps webSocketProps;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("WS session to client started.");
        session.setTextMessageSizeLimit(webSocketProps.getTextMessageSizeLimit());
        this.session = session;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        this.session = null;
        log.warn("WS session to client closed... {}", status);
    }

    public void send(String str) {
        if (session == null || !session.isOpen()) {
            log.warn("Message not sent via Websocket: session is {}", session);
            return;
        }
        try {
            session.sendMessage(new TextMessage(str));
        } catch (IOException e) {
            log.error("There was an error sending ws message: ", e);
        }
    }
}