import { createContext, useContext, useMemo } from "react";
import { authUser } from "../calling/authCalling";
import { useLocalStorage } from "./useLocalStorage";
import { useState } from "react";
import { getAllPosts } from "../calling/postCalling";
import getAllUsers from "../calling/userCalling";
import matchUserID from "../calling/matchUserId";
import { getChats } from "../calling/chatsCalling";
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
    await getAllUsers(user).then(users=>{setAllUsers(users);console.log(allUsers);});
  }
  const getThePosts = ()=>{
    setIsPostsLoading(true);
    console.log("get the posts called");
    getAllPosts(user).then(data=>{
      if(data >= 400){
        setUser(null);
      }
      getAllUsers(user).then((users)=>{
        console.log("usersssssssssssssss:")
        console.log(users)
        setPostList(matchUserID(users,data))
      })
      console.log("mathcing user ID::::")
      setIsPostsLoading(false);
    });
  }
  const getTheChats = ()=>{
    getChats(user).then(result=>setChatList(result));
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
