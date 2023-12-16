import { createContext, useContext, useMemo } from "react";
import { authUser } from "../calling/authCalling";
import { useLocalStorage } from "./useLocalStorage";
import { useState } from "react";
import { getAllPosts } from "../calling/postCalling";
import getAllUsers from "../calling/userCalling";
import matchUserID from "../calling/matchUserId";
const DataContext = createContext();

export const DataProvider = ({ children }) => {
  const [user, setUser] = useLocalStorage("user", {});
  const [error, setError] = useState(""); // New state to store authentication error
  const [postList, setPostList] = useState([]);
  const [allUsers, setAllUsers] = useState([]);
  const [isPostsLoading, setIsPostsLoading] = useState(true);
  const login = async (userName, password) =>{

    try{
      let claimedUser = await authUser(userName, password);
      setUser(claimedUser);
      setError(""); // Reset error state on successful login
    }
    catch(error){
      setError("Invalid username or password"); // Set error state on unsuccessful login
      console.log("are we in data context")
      error = "Invalid username or password"; 
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
  
  const logout = ()=>{
    setUser(null);
  }
  
  const chatList = [
    {
      userName: "Tuna Saygın",
      isGroupChat: false,
      messages: [
        {
          message: "Merhabalar hocam yarın CS319 dersi olacak mı?",
          date: "15th October 20:53",
          isReceived: true,
          userName: "Eray Tüzün",
        },
        {
          message: "Evet, yarın dersimiz olacak. Saat 14:30'da buluşalım.",
          date: "15th October 21:05",
          isReceived: false,
        },
        {
          message: "Tamamdır, görüşmek üzere.",
          date: "15th October 21:06",
          isReceived: true,
        },
      ],
    },
    {
      userName: "Kenan Zeynalov",
      isGroupChat: false,
      messages: [
        {
          message: "Günaydın, ders materyalleri paylaşılacak mı?",
          date: "16th October 08:30",
          isReceived: true,
          userName: "Eray Tüzün",
        },
        {
          message: "Evet, hemen paylaşacağım.",
          date: "16th October 08:35",
          isReceived: false,
        },
      ],
    },
    {
      userName: "CS Dream Group",
      isGroupChat: true,
      messages: [
        {
          message: "Bugün laboratuvar çalışması ile ilgili toplantımız var.",
          date: "16th October 14:00",
          isReceived: true,
        },
        {
          message: "Evet, hatırlatma için teşekkür ederim. Gerekli hazırlıkları yapacağım.",
          date: "16th October 14:05",
          isReceived: false,
        },
      ],
    },
    // Add more entries as needed
  ];
  const value = useMemo(() => ({postList, chatList, user, login, logout, error, getThePosts,isPostsLoading,getTheUsers}), [allUsers,isPostsLoading,postList,chatList,user,login,logout, error]);

  return <DataContext.Provider value={value}>{children}</DataContext.Provider>;
};

export const useData = () => {
  return useContext(DataContext);
};
