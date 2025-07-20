package org.example.controller;

import org.example.dto.MessageRequest;
import org.example.dto.MessageResponse;
import org.example.model.Message;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload MessageRequest messageRequest, Authentication authentication) {
        String senderUsername = authentication.getName();

        Message message = messageService.sendMessage(
            senderUsername,
            messageRequest.getReceiverUsername(),
            messageRequest.getContent()
        );

        MessageResponse response = convertToResponse(message);

        messagingTemplate.convertAndSendToUser(
            messageRequest.getReceiverUsername(),
            "/queue/messages",
            response
        );

        messagingTemplate.convertAndSendToUser(
            senderUsername,
            "/queue/messages",
            response
        );
    }

    private MessageResponse convertToResponse(Message message) {
        MessageResponse response = new MessageResponse();
        response.setId(message.getId());
        response.setContent(message.getContent());
        response.setSenderUsername(message.getSender().getUsername());
        response.setReceiverUsername(message.getReceiver().getUsername());
        response.setTimestamp(message.getTimestamp());
        response.setRead(message.isRead());
        return response;
    }
}
