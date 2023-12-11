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

    public PostService(
            AnnouncementPostService announcementPostService,
            BorrowAndLendPostService borrowAndLendPostService,
            DonationPostService donationPostService,
            LostAndFoundPostService lostAndFoundPostService,
            NormalPostService normalPostService,
            SectionExchangePostService sectionExchangePostService,
            SecondHandTradingPostService secondHandTradingPostService) {
        this.announcementPostService = announcementPostService;
        this.borrowAndLendPostService = borrowAndLendPostService;
        this.donationPostService = donationPostService;
        this.lostAndFoundPostService = lostAndFoundPostService;
        this.normalPostService = normalPostService;
        this.sectionExchangePostService = sectionExchangePostService;
        this.secondHandTradingPostService = secondHandTradingPostService;
    }

    public boolean createPost(Object post) {
        Post postObj = (Post) post;
        switch (postObj.getPostType()) {
            case 0:
                return announcementPostService.createPost((AnnouncementPost) post);
            case 1:
                return borrowAndLendPostService.createPost((BorrowAndLendPost) post);
            case 2:
                return donationPostService.createPost((DonationPost) post);
            case 3:
                return lostAndFoundPostService.createPost((LostAndFoundPost) post);
            case 4:
                return normalPostService.createPost((NormalPost) post);
            case 5:
                return sectionExchangePostService.createPost((SectionExchangePost) post);
            case 6:
                return secondHandTradingPostService.createPost((SecondHandTradingPost) post);
            default:
                return false;
        }
    }

    public HashSet<Post> getPostsSortedByDate() {
        HashSet<Post> allPosts = new HashSet<Post>();

        // Add posts of each type to the set, sorted by date
        addSortedPosts(allPosts, announcementPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, borrowAndLendPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, donationPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, lostAndFoundPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, normalPostService.getPostsSortedByDate());
        addSortedPosts(allPosts, sectionExchangePostService.getPostsSortedByDate());
        addSortedPosts(allPosts, secondHandTradingPostService.getPostsSortedByDate());

        return allPosts;
    }

    private <T extends Post> void addSortedPosts(HashSet<Post> allPosts, List<T> posts) {
        posts.sort(Comparator.comparing(Post::getPostDate).reversed());
        allPosts.addAll(posts);
    }
}
