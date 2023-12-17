import { getImage } from "./imageCalling";

export default function matchUserID(userList,postList,user){
    if (userList.length > 0 && postList.length > 0) {
        // Map over postList and add userName and userImage to each post
        const updatedPostList = postList.map(post => {
          // Find the corresponding user in the userList
          const correspondingUser = userList.find(userItem => userItem.id === post.authorID);
            const newCommentList = post.commentList.map((comment)=>{
                const commentUser = userList.find(userData=>userData.id === comment.authorID)
                if(commentUser){
                    console.log("comment user")
                    console.log(commentUser)
                    return {
                        ...comment,
                        userName: commentUser.name,
                        profileImageName:commentUser.profileImageName,
                    }
                }
            })
          // If user is found, update the post with userName and userImage
          if (correspondingUser) {
            console.log("corr user");
            console.log(correspondingUser);
            return {
              ...post,
              commentList: newCommentList,
              name: correspondingUser.name,
              profileImageName: correspondingUser.profileImageName,
            };
          }
        
          // If user is not found, return the original post
          return post;
        });
        return updatedPostList;
    }
    return [];
}