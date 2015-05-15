package com.apress.prospringmvc.bookstore.web.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoWebSocketHandler extends TextWebSocketHandler {
	@Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.out.println("#####################################");
        System.out.println("handleMessage....................");
        System.out.println("#####################################");
		String payloadMessage = (String) message.getPayload();
        session.sendMessage(new TextMessage("ECHO : " + payloadMessage));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Connection�� ������ ��, ȣ��Ǵ� method
    	System.out.println("#####################################");
        System.out.println("afterConnectionEstablished....................");
        System.out.println("#####################################");
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Connection�� ����� ��, ȣ��Ǵ� method
    	System.out.println("#####################################");
        System.out.println("afterConnectionClosed....................");
        System.out.println("#####################################");
        super.afterConnectionClosed(session, status);
    }
}
