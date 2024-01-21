  import React, { useState } from "react";
  import { useData } from '../Context/DataContext';
  import TradingPostItem from "./TradingPostItem";
  import ForumPost from "./ForumPost";
  import { useEffect } from "react";
  import { useNavigate } from "react-router-dom";  

  const ProfilePage = () => {
    const [profilePicture, setProfilePicture] = useState("https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_1280.png");
    const { postList, isPostsLoading, getThePosts, user, getTheUsers } = useData();
    const [userType, setUserType] = useState(user?user.accountType:"");
    console.log("Checkpoint user " , user);
    const navigate = useNavigate();
    const [userPosts, setUserPosts] = useState([]); // Add this line to define userPosts state
    const [isLoadingUserPosts, setIsLoadingUserPosts] = useState(false); // New state for user posts loading
    
    useEffect(() => {
      // Call getThePosts and getUsers  only when the component mounts
      getTheUsers(); // solved problem for real time data fethicng
      getThePosts();
    }, []); // Empty dependency array

    useEffect(() => {
      if (postList.length) { // Only run if posts are fetched
        setIsLoadingUserPosts(true); // Set loading state
        const userPosts = postList.filter((post) => post.authorID === user.userId);
        console.log("Checkpoint userposts" , userPosts);
        setUserPosts(userPosts); // Update user posts state
        setIsLoadingUserPosts(false); // Reset loading state
      }
    }, [postList, user]); // Update useEffect dependency
    
    console.log("Checkpoint ppostlisst" , postList);

    const handleEditProfile = () => {
      navigate("/editProfile");
    };

    const handleStarPost = (postId) => {
    };

    console.log("Why user bio is undefined? : " , user.bio);

    // when there is no picture, it fucks up


    const starredPosts = userPosts.filter((post) => post.isStarred);

    return (
      <div className="flex flex-row h-screen bg-gray-100">
      <div className="w-1/3 flex flex-col p-4">
        <div className="flex flex-col items-center">
          <p className="text-dark font-bold text-2xl mb-2">{user.name}</p>
          {user.imageData? <img src={user.imageData} className="rounded-full h-40 w-40 border-2 border-gray-300" alt="denemeee" />:<img
            className="rounded-full h-40 w-40 border-2 border-gray-300"
            src={profilePicture}
            alt="Profile Picture"
          />}
          <div className="mt-4">
            <p className="text-dark font-bold text-xl">{userType}</p>
            <button
              onClick={handleEditProfile}
              className="bg-blue-500 text-white font-bold px-4 py-2 rounded-full mt-2 md:w-auto hover:bg-blue-600 transition duration-300"
            >
              Edit Profile
            </button>
          </div>
        </div>
        <div className="mt-4 bg-gradient-to-r from-blue-200 to-blue-300 p-4 rounded-md">
          <p className="text-dark text-lg font-bold mb-2">Bio:</p>
          <p className="text-gray-700 leading-relaxed">{user.bio}</p>
        </div>
      </div>
        <div className="w-2/3 flex flex-col p-4">
          {/* Post History Section */}
          <div className="mb-4">
            <h2 className="text-dark font-bold text-2xl">Post History</h2>
          </div>
          <div className="space-y-4">
            {/* Post Cards */}
            {userPosts.map((post) => (
                console.log(post),   
                console.log(post.id),
                console.log("are we here?"),
                <TradingPostItem
                key={post.id}
                post={post}
                isProfile={true}
                className="flex flex-col bg-white border-gray-300 rounded-md p-4 shadow-md transition duration-300 ease-in-out transform hover:scale-105"
              >
              <h3 className="text-dark font-bold text-xl mb-2">{post.title}</h3>
              <p className="text-gray-600">{post.content}</p>
              <button
                onClick={() => handleStarPost(post.id)}
                className="text-primary hover:text-opacity-75 flex items-center"
              >
                {post.isStarred ? "Unstar" : "Star"} {post.isStarred && "⭐"}
              </button>
              </TradingPostItem>
            ))}
          </div>
          
        </div>
      </div>
    );
  };

  export default ProfilePage;