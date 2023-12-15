  import React, { useState } from "react";
  import { useData } from '../Context/DataContext';
  import TradingPostItem from "./TradingPostItem";
  import ForumPost from "./ForumPost";
  import { useEffect } from "react";
  import { useNavigate } from "react-router-dom";  

  const ProfilePage = () => {
    const [profilePicture, setProfilePicture] = useState("https://media.licdn.com/dms/image/C5603AQEYoQTc9d-tyg/profile-displayphoto-shrink_200_200/0/1516280847373?e=2147483647&v=beta&t=3oQIHia8rF_NV4W8hCAqEt64KQxzEtYTcH1eyOl_Xos");
    const [userType, setUserType] = useState("Instructor");
    const [bio, setBio] = useState(
      "I am a Computer Engineering Instructor at Bilkent University"
    );

    const navigate = useNavigate();

    const authorId = 1;
    const { postList, isPostsLoading, getThePosts, user } = useData();
    const [userPosts, setUserPosts] = useState([]); // Add this line to define userPosts state

    const [isLoadingUserPosts, setIsLoadingUserPosts] = useState(false); // New state for user posts loading

    /* Tuna im considering case where data was not fetched in that stage therefore i use effect to see wheres the problem and why data is not fetched but there is something wrong check console   */
    useEffect(() => { /* */ 
      if (postList.length == 0) {
        getThePosts(user); // Trigger data fetching
        console.log("Size is 0 something is wrong with the datacontext");
      }
    }, [postList.length, isPostsLoading, user]); // Update useEffect dependencies

    useEffect(() => {
      if (postList.length) { // Only run if posts are fetched
        console.log("Checkpoint useeffectseocnd" , postList);
        setIsLoadingUserPosts(true); // Set loading state
        const userPosts = postList.filter((post) => post.authorID === authorId);
        console.log("Checkpoint userposts" , userPosts);
        setUserPosts(userPosts); // Update user posts state
        setIsLoadingUserPosts(false); // Reset loading state
        console.log("Gijdillag react test 1" , postList[2]);

      }
    }, [postList]); // Update useEffect dependency



    
    console.log("Checkpoint ppostlisst" , postList);
    console.log("Example Post:", postList[0]);
    console.log("Example Post:", postList[1]);
    console.log("Example Post:", postList[2]);


    const handleEditProfile = () => {
      navigate("/editProfile");
    };

    const handleStarPost = (postId) => {
    };
    console.log(postList);

    console.log(postList);
    console.log("userPosts: ", userPosts);
    console.log("are we okay" , postList[0]);
    console.log("are we okay" , postList[1]);
    console.log("Gijdillag react test 1" , postList[2]);
    console.log("Gijdillag react test 2" , postList[2]);



    const starredPosts = userPosts.filter((post) => post.isStarred);

    return (
      <div className="flex flex-row h-screen bg-gray-100">
      <div className="w-1/3 flex flex-col p-4">
        <div className="flex flex-col items-center">
          <p className="text-dark font-bold text-2xl mb-2">{user.name}</p>
          <img
            className="rounded-full h-40 w-40 border-2 border-gray-300"
            src={profilePicture}
            alt="Profile Picture"
          />
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
          <p className="text-gray-700 leading-relaxed">{bio}</p>
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
                console.log("are we here?"),
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
          {/* Starred Posts Section */}
          <div className="mt-8">
            <h2 className="text-dark font-bold text-2xl">Starred Posts</h2>
            {/* Display Starred Posts or a message */}
            {starredPosts.length > 0 ? (
              <div className="space-y-4">
                {starredPosts.map((post) => (
                  <div
                    key={post.id}
                    className="flex flex-col bg-white border-gray-300 rounded-md p-4 shadow-md"
                  >
                    <h3 className="text-dark font-bold text-xl mb-2">{post.title}</h3>
                    <p className="text-gray-600">{post.content}</p>
                  </div>
                ))}
              </div>
            ) : (
              <p className="text-gray-600 text-center">No starred posts yet!</p>
            )}
          </div>
        </div>
      </div>
    );
  };

  export default ProfilePage;