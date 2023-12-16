package tr.edu.bilkent.bilsync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.dto.ChatDto;
import tr.edu.bilkent.bilsync.dto.ChatMessageDto;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.repository.UserRepository;
import tr.edu.bilkent.bilsync.service.ChatService;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    private final UserRepository userRepository;

    @Autowired
    public ChatController(ChatService chatService, UserRepository userRepository) {
        this.chatService = chatService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createChat(
            @RequestBody ChatDto chatDto,
            @AuthenticationPrincipal UserEntity currentUser) {
        chatService.createChat(chatDto, currentUser);
        return ResponseEntity.ok("Chat created successfully");
    }

    @GetMapping("/chats")
    public ResponseEntity<List<ChatDto>> getUserChats(@AuthenticationPrincipal UserEntity currentUser) {
        List<ChatDto> userChats = chatService.getChatsByUser(currentUser).stream().map(chat -> {
            ChatDto chatDto = new ChatDto(chat);
            Long otherUserId = chatDto.getUserIds().stream().filter(id -> id != currentUser.getId()).findFirst().orElseThrow(() -> new RuntimeException("Invalid chat"));
            UserEntity otherUser = userRepository.findById(otherUserId).orElseThrow(() -> new RuntimeException("Invalid Chat"));
            chatDto.setChatName(otherUser.getName());
            return chatDto;
        }).toList();
        return ResponseEntity.ok(userChats);
    }

    @PostMapping("/{chatId}/inviteUsers")
    public ResponseEntity<String> inviteUsersToGroup(
            @PathVariable Long chatId,
            @RequestBody List<Long> inviteeIds,
            @AuthenticationPrincipal UserEntity currentUser) {
        chatService.inviteUsers(chatId, inviteeIds, currentUser);
        return ResponseEntity.ok("Invite sent successfully.");
    }

    @PostMapping("/{chatId}/sendMessage")
    public ResponseEntity<String> sendMessageToChat(@PathVariable Long chatId, @RequestBody ChatMessageDto chatMessageDto, @AuthenticationPrincipal UserEntity currentUser) {
        chatService.sendMessageToChat(chatId, chatMessageDto, currentUser);
        return ResponseEntity.ok("Message sent successfully.");
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<List<ChatMessageDto>> getMessages(@PathVariable Long chatId, @AuthenticationPrincipal UserEntity currentUser) {
        List<ChatMessageDto> messages = chatService.getMessagesByChatId(chatId);
        return ResponseEntity.ok(messages);
    }

    @MessageMapping("/chat/{chatId}/sendMessage")
    @SendTo("/topic/chat/{chatId}")
    public ChatMessageDto sendMessageToChatWebSocket(@DestinationVariable Long chatId, @RequestBody ChatMessageDto chatMessageDto, @AuthenticationPrincipal UserEntity currentUser) {

        boolean userHasAccess = chatService.userHasAccessToChat(currentUser, chatId);

        if (userHasAccess) {
            return chatService.sendMessageToChat(chatId, chatMessageDto, currentUser);
        } else {
            throw new AccessDeniedException("You do not have access to this chat.");
        }
    }

    @MessageMapping("/subscribe/chat/{chatId}")
    public void subscribeToChat(@DestinationVariable Long chatId, @AuthenticationPrincipal UserEntity currentUser) {

        boolean userHasAccess = chatService.userHasAccessToChat(currentUser, chatId);

        if (!userHasAccess) {
            throw new AccessDeniedException("You do not have access to this chat.");
        }
    }
}
