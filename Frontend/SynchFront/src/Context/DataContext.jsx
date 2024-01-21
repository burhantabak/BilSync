import { createContext, useContext, useMemo } from "react";
import { authUser } from "../calling/authCalling";
import { useLocalStorage } from "./useLocalStorage";
import { useState } from "react";
import { getAllPosts } from "../calling/postCalling";
import getAllUsers from "../calling/userCalling";
import matchUserID from "../calling/matchUserId";
import { getChats } from "../calling/chatsCalling";
import { getImage } from "../calling/imageCalling";
import retrieveTransactions from "../calling/TransactionCaller.jsx/getAllTransactions";

import { useEffect } from "react";
const DataContext = createContext();

export const DataProvider = ({ children }) => {
  const [user, setUser] = useLocalStorage("user", {});
  const [error, setError] = useState(""); // New state to store authentication error
  const [postList, setPostList] = useState([]);
  const [chatList, setChatList] = useState([]);
  const [allUsers, setAllUsers] = useState([]);
  const [isPostsLoading, setIsPostsLoading] = useState(true);
  const [transactions, setTransactions] = useState([]);  // Add transactions state
  const [voteCounts, setVoteCounts] = useState({}); // New state to store vote counts


  const login = async (userName, password) =>{

    try{
      let claimedUser = await authUser(userName, password);
      setUser(claimedUser);
      setError(""); // Reset error state on successful login
    }
    catch(error){
      setError("Invalid email or password"); // Set error state on unsuccessful login
      console.log("are we in data context")
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
             imageData: registeredUser.imageData, bio:registeredUser.bio, accountType: registeredUser.accountType})
             console.log(user)}

          return imagedUsers;
        }
      )
    });
  }

  useEffect(() => {
    if (user && user.token && !transactions.length) {
      const fetchTransactions = async () => {
        try {
          const fetchedTransactions = await retrieveTransactions(user);
  
          console.log('Transactions:', fetchedTransactions);
  
          // Check if transactions is undefined or null
          if (!fetchedTransactions || fetchedTransactions.length === 0) {
            console.log('No transactions yet.');
            return;
          }
  
          setTransactions(fetchedTransactions);
        } catch (error) {
          console.error('Error fetching transactions:', error);
        }
      };
  
      fetchTransactions();
    }
  }, [user, transactions]);

 

  // if(transactions.takerId == user.userId()&& transaction.status == PENDING TAKER APPROVAL){ display transactiosn } (button name = Approve As Taker. onCLick request; )


  const getThePosts = async () => {
    setIsPostsLoading(true);
    console.log("get the posts called");
  
    try {
      const data = await getAllPosts(user);
  
      if (data >= 400) {
        setUser(null);
        return; // Exit early if there's an issue with fetching posts
      }
  
      const users = await getAllUsers(user);
      console.log("Users?:");
      console.log(users);
      console.log(data);
  
      const updatedPostList = await Promise.all(
        matchUserID(users, data, user).map(async (post) => {
          // Fetch image for each post
          const imageData = await getImage(post.imageName, user);
  
          // Update the post object with the image data
          return { ...post, imageData };
        })
      );
  
      // Update vote counts
      const updatedVoteCounts = updatedPostList.reduce((acc, post) => {
        acc[post.id] = post.votes; // Replace 'voteCount' with the actual field in your post object
        return acc;
      }, {});
  
      setVoteCounts(updatedVoteCounts);
  
      // Update post list with image data
      setPostList(updatedPostList);
    } catch (error) {
      console.error("Error fetching posts:", error);
    } finally {
      console.log("matching user ID::::");
      setIsPostsLoading(false);
    }
  };
  
  const getTheChats = ()=>{
    getChats(user).then(result=>{if(result){console.log("chatt");console.log(result);setChatList(result)}});
  } 
  const logout = ()=>{
    setUser(null);
  }
  
  const value = useMemo(() => ({chatList,getTheChats,postList, user, login, logout, error, getThePosts,isPostsLoading,getTheUsers, transactions, allUsers,voteCounts}), [allUsers,isPostsLoading,postList,chatList,user,login,logout, error, transactions,voteCounts]);

  return <DataContext.Provider value={value}>{children}</DataContext.Provider>;
};

export const useData = () => {
  return useContext(DataContext);
};
