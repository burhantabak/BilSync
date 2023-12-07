package tr.edu.bilkent.bilsync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.dto.ChatDto;
import tr.edu.bilkent.bilsync.entity.Chat;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.filter.JWTAuthFilter;
import tr.edu.bilkent.bilsync.service.ChatService;
import tr.edu.bilkent.bilsync.service.TokenService;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    private final TokenService tokenService;

    @Autowired
    public ChatController(ChatService chatService, TokenService tokenService) {
        this.chatService = chatService;
        this.tokenService = tokenService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createChat(@RequestBody ChatDto chatDto) {
        chatService.createChat(chatDto);
        return ResponseEntity.ok("Chat created successfully");
    }

    @GetMapping("/chats")
    public ResponseEntity<List<Chat>> getUserChats(@AuthenticationPrincipal UserEntity user) {
        List<Chat> userChats = chatService.getChatsByUser(user);
        return ResponseEntity.ok(userChats);
    }
}
