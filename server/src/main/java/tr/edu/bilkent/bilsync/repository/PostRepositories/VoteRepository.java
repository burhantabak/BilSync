package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.Vote;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
    public Vote getVoteByVoterIDAndPostID(long voterID, long postID);
}
