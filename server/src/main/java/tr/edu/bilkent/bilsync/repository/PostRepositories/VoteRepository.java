package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.Vote;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Vote entities.
 * It extends CrudRepository for basic CRUD operations and includes custom query methods.
 */
@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

    /**
     * Retrieves a Vote by the voter ID and post ID.
     *
     * @param voterID The ID of the voter.
     * @param postID  The ID of the post.
     * @return The Vote with the specified voter ID and post ID, if it exists.
     */
    public Vote getVoteByVoterIDAndPostID(long voterID, long postID);
}
