package tr.edu.bilkent.bilsync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.dto.ChatDto;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.service.ChatService;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
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
        List<ChatDto> userChats = chatService.getChatsByUser(currentUser).stream().map(ChatDto::new).toList();
        return ResponseEntity.ok(userChats);
    }

    @PostMapping("/{chatId}/inviteUsers")
    public ResponseEntity<String> inviteUsersToGroup(
            @PathVariable Long chatId,
            @RequestBody List<Long> inviteeIds,
            @AuthenticationPrincipal UserEntity currentUser){
        chatService.inviteUsers(chatId, inviteeIds, currentUser);
        return ResponseEntity.ok("Invite sent successfully.");
    }
}
