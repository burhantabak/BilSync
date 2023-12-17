package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.*;
import tr.edu.bilkent.bilsync.entity.PostEntities.*;
import tr.edu.bilkent.bilsync.service.CommentService;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * Service class for managing various types of posts, comments, and related operations.
 * Acts as a facade to interact with different post-related services.
 */
@Service
public class PostService {

    private final AnnouncementPostService announcementPostService;
    private final BorrowAndLendPostService borrowAndLendPostService;
    private final DonationPostService donationPostService;
    private final LostAndFoundPostService lostAndFoundPostService;
    private final NormalPostService normalPostService;
    private final SectionExchangePostService sectionExchangePostService;
    private final SecondHandTradingPostService secondHandTradingPostService;
    private final CommentService commentService;

    /**
     * Constructor for PostService, injecting required services.
     *
     * @param announcementPostService      The service for handling announcement posts.
     * @param borrowAndLendPostService     The service for handling borrow and lend posts.
     * @param donationPostService          The service for handling donation posts.
     * @param lostAndFoundPostService      The service for handling lost and found posts.
     * @param normalPostService            The service for handling normal posts.
     * @param sectionExchangePostService   The service for handling section exchange posts.
     * @param secondHandTradingPostService The service for handling second-hand trading posts.
     * @param commentService               The service for handling comments.
     */
    public PostService(
            AnnouncementPostService announcementPostService,
            BorrowAndLendPostService borrowAndLendPostService,
            DonationPostService donationPostService,
            LostAndFoundPostService lostAndFoundPostService,
            NormalPostService normalPostService,
            SectionExchangePostService sectionExchangePostService,
            SecondHandTradingPostService secondHandTradingPostService,
            CommentService commentService) {
        this.announcementPostService = announcementPostService;
        this.borrowAndLendPostService = borrowAndLendPostService;
        this.donationPostService = donationPostService;
        this.lostAndFoundPostService = lostAndFoundPostService;
        this.normalPostService = normalPostService;
        this.sectionExchangePostService = sectionExchangePostService;
        this.secondHandTradingPostService = secondHandTradingPostService;
        this.commentService = commentService;
    }

    /**
     * Creates or saves a post in the appropriate service based on its type.
     *
     * @param post The post to be created or saved.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean createOrSavePost(Object post) {
        Post postObj = (Post) post;
        return switch (postObj.getPostType()) {
            case 0 -> announcementPostService.createOrSavePost((AnnouncementPost) post);
            case 1 -> borrowAndLendPostService.createOrSavePost((BorrowAndLendPost) post);
            case 2 -> donationPostService.createOrSavePost((DonationPost) post);
            case 3 -> lostAndFoundPostService.createOrSavePost((LostAndFoundPost) post);
            case 4 -> normalPostService.createOrSavePost((NormalPost) post);
            case 5 -> sectionExchangePostService.createOrSavePost((SectionExchangePost) post);
            case 6 -> secondHandTradingPostService.createOrSavePost((SecondHandTradingPost) post);
            default -> false;
        };
    }

    /**
     * Retrieves all posts sorted by date.
     *
     * @return A TreeSet containing all posts sorted by date.
     */
    public TreeSet<Post> getPostsSortedByDate() {
        TreeSet<Post> allPosts = new TreeSet<>(Comparator.comparing(Post::getPostDate).reversed());

        addSortedPosts(allPosts, announcementPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, borrowAndLendPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, donationPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, lostAndFoundPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, normalPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, sectionExchangePostService.getPostsSortedByDate());
        addSortedPosts(allPosts, secondHandTradingPostService.getPostsSortedByDate());

        return allPosts;
    }

    /**
     * Retrieves a post by its ID from the appropriate service based on its type.
     *
     * @param id The ID of the post to retrieve.
     * @return The post with the specified ID.
     */
    public Post getPostByID(long id) {
        Post foundPost = null;
        for (byte postType = 0; postType <= 6 && foundPost == null; postType++) {
            switch (postType) {
                case 0 -> foundPost = announcementPostService.getPostByID(id);
                case 1 -> foundPost = borrowAndLendPostService.getPostByID(id);
                case 2 -> foundPost = donationPostService.getPostByID(id);
                case 3 -> foundPost = lostAndFoundPostService.getPostByID(id);
                case 4 -> foundPost = normalPostService.getPostByID(id);
                case 5 -> foundPost = sectionExchangePostService.getPostByID(id);
                case 6 -> foundPost = secondHandTradingPostService.getPostByID(id);
            }
        }
        return foundPost;
    }

    /**
     * Deletes a post by its ID from the appropriate service based on its type.
     *
     * @param id The ID of the post to delete.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean deletePostById(long id) {
        try {
            for (byte postType = 0; postType <= 6; postType++) {
                switch (postType) {
                    case 0 -> announcementPostService.deleteById(id);
                    case 1 -> borrowAndLendPostService.deleteById(id);
                    case 2 -> donationPostService.deleteById(id);
                    case 3 -> lostAndFoundPostService.deleteById(id);
                    case 4 -> normalPostService.deleteById(id);
                    case 5 -> sectionExchangePostService.deleteById(id);
                    case 6 -> secondHandTradingPostService.deleteById(id);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Sets the resolved status of a trading post.
     *
     * @param postId   The ID of the post to update.
     * @param resolved The resolved status to set.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean setAsResolved(long postId, boolean resolved) {
        try {
            Post post = getPostByID(postId);
            if(!(post instanceof TradingPost))
                return false;
            ((TradingPost) post).setIsResolved(resolved);
            createOrSavePost(post);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    /**
     * Sets the held status of a trading post.
     *
     * @param postId The ID of the post to update.
     * @param held   The held status to set.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean setHeld(long postId, boolean held) {
        try {
            Post post = getPostByID(postId);
            if(!(post instanceof TradingPost))
                return false;
            ((TradingPost) post).setIsHeld(held);
            createOrSavePost(post);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    /**
     * Sets the taker ID of a trading post.
     *
     * @param post     The trading post to update.
     * @param takerId  The taker ID to set.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean setTakerId(TradingPost post, Long takerId) {
        try {
            post.setTakerID(takerId);
            createOrSavePost(post);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    /**
     * Adds posts to the main TreeSet by combining posts from various services.
     *
     * @param allPosts The main TreeSet containing all posts.
     * @param posts    The list of posts to add.
     * @param <T>      The type of the posts.
     */
    private <T extends Post> void addSortedPosts(TreeSet<Post> allPosts, List<T> posts) {
        allPosts.addAll(posts);
    }

    /**
     * Creates or saves a comment using the CommentService.
     *
     * @param comment The comment to be created or saved.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean createOrSaveComment(Comment comment) {
        return commentService.createOrSaveComment(comment);
    }

    /**
     * Retrieves a comment by its ID using the CommentService.
     *
     * @param id The ID of the comment to retrieve.
     * @return The comment with the specified ID.
     */
    public Comment getCommentByID(long id) {
        return commentService.getCommentByID(id);
    }
}
