package org.example.controller;

import org.example.dto.MessageRequest;
import org.example.dto.MessageResponse;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest messageRequest, Authentication authentication) {
        String senderUsername = authentication.getName();

        var message = messageService.sendMessage(
            senderUsername,
            messageRequest.getReceiverUsername(),
            messageRequest.getContent()
        );

        MessageResponse response = new MessageResponse();
        response.setId(message.getId());
        response.setContent(message.getContent());
        response.setSenderUsername(message.getSender().getUsername());
        response.setReceiverUsername(message.getReceiver().getUsername());
        response.setTimestamp(message.getTimestamp());
        response.setRead(message.isRead());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/conversation/{username}")
    public ResponseEntity<List<MessageResponse>> getConversation(@PathVariable String username, Authentication authentication) {
        String currentUsername = authentication.getName();
        List<MessageResponse> messages = messageService.getConversation(currentUsername, username);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/unread")
    public ResponseEntity<List<MessageResponse>> getUnreadMessages(Authentication authentication) {
        String username = authentication.getName();
        List<MessageResponse> messages = messageService.getUnreadMessages(username);
        return ResponseEntity.ok(messages);
    }

    @PutMapping("/read/{messageId}")
    public ResponseEntity<Void> markAsRead(@PathVariable Long messageId) {
        messageService.markAsRead(messageId);
        return ResponseEntity.ok().build();
    }
}
