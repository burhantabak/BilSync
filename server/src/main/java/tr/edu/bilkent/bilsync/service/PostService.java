package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

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

    public HashSet<Post> getPostsSortedByDate() {
        HashSet<Post> allPosts = new HashSet<>();

        addSortedPosts(allPosts, announcementPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, borrowAndLendPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, donationPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, lostAndFoundPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, normalPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, sectionExchangePostService.getPostsSortedByDate());
        addSortedPosts(allPosts, secondHandTradingPostService.getPostsSortedByDate());

        return allPosts;
    }

    //TODO: Will make it more efficient instead of repetitive code
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

    private <T extends Post> void addSortedPosts(HashSet<Post> allPosts, List<T> posts) {
        posts.sort(Comparator.comparing(Post::getPostDate).reversed());
        allPosts.addAll(posts);
    }

    public boolean createOrSaveComment(Comment comment) {
        return commentService.createOrSaveComment(comment);
    }
    public Comment getCommentByID(long id) {
        return commentService.getCommentByID(id);
    }
}
