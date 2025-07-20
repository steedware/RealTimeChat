package org.example.service;

import org.example.dto.MessageResponse;
import org.example.model.Message;
import org.example.model.User;
import org.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    public Message sendMessage(String senderUsername, String receiverUsername, String content) {
        User sender = userService.findByUsername(senderUsername);
        User receiver = userService.findByUsername(receiverUsername);

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);

        return messageRepository.save(message);
    }

    public List<MessageResponse> getConversation(String username1, String username2) {
        User user1 = userService.findByUsername(username1);
        User user2 = userService.findByUsername(username2);

        List<Message> messages = messageRepository.findConversationBetweenUsers(user1, user2);

        return messages.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<MessageResponse> getUnreadMessages(String username) {
        User user = userService.findByUsername(username);
        List<Message> messages = messageRepository.findByReceiverAndIsReadFalse(user);

        return messages.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public void markAsRead(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        message.setRead(true);
        messageRepository.save(message);
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
