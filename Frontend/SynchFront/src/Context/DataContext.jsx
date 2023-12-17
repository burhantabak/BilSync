import { createContext, useContext, useMemo } from "react";
import { authUser } from "../calling/authCalling";
import { useLocalStorage } from "./useLocalStorage";
import { useState } from "react";
import { getAllPosts } from "../calling/postCalling";
import getAllUsers from "../calling/userCalling";
import matchUserID from "../calling/matchUserId";
import { getChats } from "../calling/chatsCalling";
import { getImage } from "../calling/imageCalling";
const DataContext = createContext();

export const DataProvider = ({ children }) => {
  const [user, setUser] = useLocalStorage("user", {});
  const [error, setError] = useState(""); // New state to store authentication error
  const [postList, setPostList] = useState([]);
  const [chatList, setChatList] = useState([]);
  const [allUsers, setAllUsers] = useState([]);
  const [isPostsLoading, setIsPostsLoading] = useState(true);
  const login = async (userName, password) =>{

    try{
      let claimedUser = await authUser(userName, password);
      setUser(claimedUser);
      setError(""); // Reset error state on successful login
    }
    catch(error){
      setError("Invalid email or password"); // Set error state on unsuccessful login
      console.log("are we in data context")
      error = "Invalid email or password"; 
    }
  }
  const getTheUsers =async ()=>{
    console.log("get the users");
    await getAllUsers(user).then(users=>{
      const imagedUserList = users.map(async (userItem)=>{
        const imageData = await getImage(userItem.profileImageName,user);
        return {...userItem, imageData};
      })

      Promise.all(imagedUserList).then(
        (imagedUsers)=>{
          setAllUsers(imagedUsers)
          console.log("images with users")
          console.log(imagedUsers);
          const registeredUser = imagedUsers.find((claimedUser)=>claimedUser.id==user.userId)
          console.log(registeredUser)
          console.log(registeredUser)
          console.log(user.userId)
          if(registeredUser){setUser({...user,profileImageName: registeredUser.profileImageName,
             imageData: registeredUser.imageData, bio:registeredUser.bio})
             console.log(user)}
          return imagedUsers;
        }
      )
    });
  }
  const getThePosts = ()=>{
    setIsPostsLoading(true);
    console.log("get the posts called");
    getAllPosts(user).then(data=>{
      if(data >= 400){
        setUser(null);
      }

      getAllUsers(user).then((users)=>{
        console.log("usersssssssssssssss:");
  console.log(users);
        console.log(data)
  const updatedPostList = matchUserID(users, data).map(async (post) => {
    // Fetch image for each post
    const imageData = await getImage(post.imageName, user);
    
    // Update the post object with the image data
    return { ...post, imageData };
  });

  // Wait for all image fetch operations to complete
  Promise.all(updatedPostList)
    .then((postsWithImages) => {
      // Set the updated post list with image data
      console.log(postsWithImages);
      setPostList(postsWithImages);
    })
    .catch((error) => {
      console.error('Error fetching images:', error);
    });

      })
      console.log("mathcing user ID::::")
      setIsPostsLoading(false);
    });
  }
  const getTheChats = ()=>{
    getChats(user).then(result=>{if(result){console.log("chatt");console.log(result);setChatList(result)}});
  } 
  const logout = ()=>{
    setUser(null);
  }
  
  const value = useMemo(() => ({chatList,getTheChats,postList, chatList, user, login, logout, error, getThePosts,isPostsLoading,getTheUsers}), [allUsers,isPostsLoading,postList,chatList,user,login,logout, error]);

  return <DataContext.Provider value={value}>{children}</DataContext.Provider>;
};

export const useData = () => {
  return useContext(DataContext);
};
