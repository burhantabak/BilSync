import React, { useState, useEffect } from "react";
import { useData } from '../Context/DataContext';
import TradingPostItem from "../Components/TradingPostItem";
import { useNavigate } from "react-router-dom";  
import { useParams } from "react-router-dom";

const UserProfile = () => {
  const [profilePicture, setProfilePicture] = useState("https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_1280.png");
  const { postList, user, allUsers, getTheUsers, getThePosts } = useData();
  const [userPosts, setUserPosts] = useState([]);
  const [isLoadingUserPosts, setIsLoadingUserPosts] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    setIsLoadingUserPosts(true);
    // Fetch data and wait for both to complete before proceeding
    Promise.all([getTheUsers(), getThePosts()]).then(() => {
      const authorIdFinal = findId(authorName).id; // Ensure correct ID assignment
      setUserPosts(postList.filter((post) => post.authorID == authorIdFinal));
      setIsLoadingUserPosts(false);
    });
  }, []);

  useEffect(() => {
    if (allUsers && allUsers.length > 0) {
      const authorIdFinal = findId(authorName).id; // Recalculate userPosts on allUsers update
      console.log("Checkpoint in useEffect", authorIdFinal);
      setUserPosts(postList.filter((post) => post.authorID == authorIdFinal));
    }
  }, [postList]); // Only recalculate userPosts when postList changes

  const { authorID: authorName } = useParams(); // Get the authorID from the URL
  console.log("authorID", authorName);

  const findId = (authorName) => {
    const foundUser = allUsers.find((user) => user.name == authorName);
    console.log("foundUser", foundUser);
    return foundUser || {};
  };

  var authorIdFinal = findId(authorName)
  authorIdFinal = authorIdFinal.id;
  console.log("authorID final", authorIdFinal);

  const findUserById = (authorIdFinal) => {
    const foundUser = allUsers.find((user) => user.id == authorIdFinal);
    return foundUser || {};    // Return the found user or an empty object if not found
  };

  // Use the findUserById function to get the clicked user
  const clickedUser = findUserById(authorIdFinal);
  console.log(clickedUser.bio )
  console.log(allUsers)

  console.log("Kenanin postlari: " , userPosts);

  return (
    <div className="flex flex-row h-screen bg-gray-100">
      <div className="w-1/3 flex flex-col p-4">
        <div className="flex flex-col items-center">
          <p className="text-dark font-bold text-2xl mb-2">{clickedUser.name}</p>
          {clickedUser.imageData ? (
            <img src={clickedUser.imageData} className="rounded-full h-40 w-40 border-2 border-gray-300" alt="Profile" />
          ) : (
            <img
              className="rounded-full h-40 w-40 border-2 border-gray-300"
              src={profilePicture}
              alt="Profile Picture"
            />
          )}
        </div>
        <div className="mt-4 bg-gradient-to-r from-blue-200 to-blue-300 p-4 rounded-md">
          <p className="text-dark text-lg font-bold mb-2">Bio:</p>
          <p className="text-gray-700 leading-relaxed">{clickedUser.bio}</p>
        </div>
      </div>
      <div className="w-2/3 flex flex-col p-4">
        <div className="mb-4">
          <h2 className="text-dark font-bold text-2xl">Post History</h2>
        </div>
        <div className="space-y-4">
          {userPosts.map((post) => (
            <TradingPostItem
              key={post.id}
              post={post}
              isProfile={true}
              className="flex flex-col bg-white border-gray-300 rounded-md p-4 shadow-md transition duration-300 ease-in-out transform hover:scale-105"
            >
              <h3 className="text-dark font-bold text-xl mb-2">{post.title}</h3>
              <p className="text-gray-600">{post.content}</p>
              <button
                onClick={() => handleStarPost(post.id)} // You need to define handleStarPost
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

export default UserProfile;
