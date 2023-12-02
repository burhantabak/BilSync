package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.*;

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

    public boolean register(Object post) {
        switch (getPostType(post)) {
            case 0:
                return announcementPostService.register((AnnouncementPost) post);
            case 1:
                return borrowAndLendPostService.register((BorrowAndLendPost) post);
            case 2:
                return donationPostService.register((DonationPost) post);
            case 3:
                return lostAndFoundPostService.register((LostAndFoundPost) post);
            case 4:
                return normalPostService.register((NormalPost) post);
            case 5:
                return sectionExchangePostService.register((SectionExchangePost) post);
            case 6:
                return secondHandTradingPostService.register((SecondHandTradingPost) post);
            default:
                return false;
        }
    }
    private int getPostType(Object post) {
        if (post instanceof AnnouncementPost) {
            return 0;
        } else if (post instanceof BorrowAndLendPost) {
            return 1;
        } else if (post instanceof DonationPost) {
            return 2;
        } else if (post instanceof LostAndFoundPost) {
            return 3;
        } else if (post instanceof NormalPost) {
            return 4;
        } else if (post instanceof SectionExchangePost) {
            return 5;
        } else if (post instanceof SecondHandTradingPost) {
            return 6;
        } else {
            return -1;
        }
    }
}
