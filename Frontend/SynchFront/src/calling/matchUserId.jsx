export default function matchUserID(userList,postList){
    console.log("match User ID ");
    console.log(postList);
    if (userList.length > 0 && postList.length > 0) {
        // Map over postList and add userName and userImage to each post
        const updatedPostList = postList.map(post => {
          // Find the corresponding user in the userList
          const correspondingUser = userList.find(user => user.id === post.authorID);
            const newCommentList = post.commentList.map((comment)=>{
                console.log("comment:")
                console.log(comment)
                const commentUser = userList.find(user=>user.id === comment.authorID)
                if(commentUser){
                    return {
                        ...comment,
                        userName: commentUser.name,
                    }
                }
            })
            console.log("new comment List");
            console.log(newCommentList)
          // If user is found, update the post with userName and userImage
          if (correspondingUser) {
            return {
              ...post,
              commentList: newCommentList,
              name: correspondingUser.name,
            };
          }
        
          // If user is not found, return the original post
          return post;
        });
        return updatedPostList;
    }
    return null;
}