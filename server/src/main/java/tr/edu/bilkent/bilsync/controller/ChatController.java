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

/**
 * Controller class for handling chat-related operations, including WebSocket messaging.
 */
@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    private final UserRepository userRepository;

    /**
     * Constructor for the ChatController class.
     *
     * @param chatService     The service responsible for handling chat-related operations.
     * @param userRepository  The repository for user-related operations.
     */
    @Autowired
    public ChatController(ChatService chatService, UserRepository userRepository) {
        this.chatService = chatService;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new chat.
     *
     * @param chatDto       The ChatDto containing information about the chat.
     * @param currentUser   The authenticated user creating the chat.
     * @return ResponseEntity indicating the result of the chat creation operation.
     */
    @PostMapping("/create")
    public ResponseEntity<String> createChat(
            @RequestBody ChatDto chatDto,
            @AuthenticationPrincipal UserEntity currentUser) {
        chatService.createChat(chatDto, currentUser);
        return ResponseEntity.ok("Chat created successfully");
    } // todo check if the same private chat exists

    /**
     * Retrieves the list of chats for the authenticated user.
     *
     * @param currentUser   The authenticated user.
     * @return ResponseEntity containing the list of chat information.
     */
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

    /**
     * Invites users to a group chat.
     *
     * @param chatId        The ID of the chat.
     * @param inviteeIds    The list of user IDs to be invited.
     * @param currentUser   The authenticated user inviting others.
     * @return ResponseEntity indicating the result of the invitation operation.
     */
    @PostMapping("/{chatId}/inviteUsers")
    public ResponseEntity<String> inviteUsersToGroup(
            @PathVariable Long chatId,
            @RequestBody List<Long> inviteeIds,
            @AuthenticationPrincipal UserEntity currentUser) {
        chatService.inviteUsers(chatId, inviteeIds, currentUser);
        return ResponseEntity.ok("Invite sent successfully.");
    }

    /**
     * Sends a message to a chat.
     *
     * @param chatId            The ID of the chat.
     * @param chatMessageDto    The ChatMessageDto containing the message information.
     * @param currentUser       The authenticated user sending the message.
     * @return ResponseEntity indicating the result of the message sending operation.
     */
    @PostMapping("/{chatId}/sendMessage")
    public ResponseEntity<String> sendMessageToChat(@PathVariable Long chatId, @RequestBody ChatMessageDto chatMessageDto, @AuthenticationPrincipal UserEntity currentUser) {
        chatService.sendMessageToChat(chatId, chatMessageDto, currentUser);
        return ResponseEntity.ok("Message sent successfully.");
    }

    /**
     * Retrieves messages from a chat.
     *
     * @param chatId        The ID of the chat.
     * @param currentUser   The authenticated user requesting messages.
     * @return ResponseEntity containing the list of messages in the chat.
     */
    @GetMapping("/{chatId}")
    public ResponseEntity<List<ChatMessageDto>> getMessages(@PathVariable Long chatId, @AuthenticationPrincipal UserEntity currentUser) {
        List<ChatMessageDto> messages = chatService.getMessagesByChatId(chatId);
        return ResponseEntity.ok(messages);
    }

    /**
     * WebSocket endpoint for sending messages to a chat.
     *
     * @param chatId            The ID of the chat.
     * @param chatMessageDto    The ChatMessageDto containing the message information.
     * @param currentUser       The authenticated user sending the message.
     * @return ChatMessageDto representing the sent message.
     */
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

    /**
     * WebSocket endpoint for subscribing to a chat.
     *
     * @param chatId        The ID of the chat.
     * @param currentUser   The authenticated user subscribing to the chat.
     */
    @MessageMapping("/subscribe/chat/{chatId}")
    public void subscribeToChat(@DestinationVariable Long chatId, @AuthenticationPrincipal UserEntity currentUser) {

        boolean userHasAccess = chatService.userHasAccessToChat(currentUser, chatId);

        if (!userHasAccess) {
            throw new AccessDeniedException("You do not have access to this chat.");
        }
    }
}
