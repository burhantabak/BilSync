import React, { useEffect, useState } from 'react'
import FeedPage from './FeedPage';
import { useData } from '../Context/DataContext';
import ChatScreen from './ChatScreen.jsx';
import { getChats } from '../calling/chatsCalling.jsx';

export default function MainPage() {
    const {postList,chatList,getThePosts,isPostsLoading,getTheChats,getTheUsers} = useData();
    console.log(isPostsLoading)
    useEffect(()=> {getThePosts();getTheChats();getTheUsers();}
        ,[]
    );
    const [filterTrading, setFilterTrading] = useState(true);
    const [filterForum, setFilterForum] = useState(true);
    const [filterLostnFound, setFilterLostnFound] = useState(true);
    const [selectedChat, setSelectedChat] = useState(null);
    const [searchInput, setSearchInput] = useState('');
    const [selectedUsers,setSelectedUsers] = useState([]);
    const [isCreateChatSelected,setIsCreateChatSelected] = useState(false);
    
    console.log("selected chat" + selectedChat)
  return (  
    <div className='flex w-full divide-x-2 mt-4'>
        <div className='sticky  pt-5 px-2 divide-y'>
            <h2 className='font-bold text-center text-xl py-2'>Categories</h2>
            <ul className='px-4'>
                <li className='text-center w-full'>
                    <button onClick={()=>{setFilterTrading(!filterTrading)}} className={`w-full rounded-lg py-3 px-2 text-center font-semibold mt-5 ${filterTrading?"bg-gray-300":""} hover:bg-gray-300`}>Lost&Found</button>
                </li>
                <li className='text-center w-full'>
                    <button onClick={()=>{setFilterForum(!filterForum)}} className={`w-full rounded-lg py-3 px-2 text-center font-semibold mt-5 ${filterForum?"bg-gray-300":""} hover:bg-gray-300`}>Trading</button>
                </li>
                <li className='text-center w-full'>
                    <button onClick={()=>{setFilterLostnFound(!filterLostnFound)}} className={`w-full rounded-lg py-3 px-2 text-center font-semibold mt-5 ${filterLostnFound?"bg-gray-300":""} hover:bg-gray-300`}>Forum</button>
                </li>
                <li className='text-center w-full'>
                    <button onClick={()=>{setFilterLostnFound(!filterLostnFound)}} className={`w-full rounded-lg py-3 px-2 text-center font-semibold mt-5 ${filterLostnFound?"bg-gray-300":""} hover:bg-gray-300`}>Boş</button>
                </li>
            </ul>
        </div>
        <div className='mx-auto w-3/4 overflow-y-auto px-2 divide-y'>
            {/* <TradingPostItem vote={-1} title="Basys3 Sale" nameUser="Işıl Özgü" price={175}/>
            <TradingPostItem vote={11} title="Basys3 Sale" isBuy={true} nameUser="Kenan Zeynalov" price={2700}/>
            <TradingPostItem vote={3} title="Basys3 Sale" nameUser="Anıl Hoca" price={150}/>
            <TradingPostItem vote={2} title="Basys3 Sale" isBuy={true} nameUser="12344" price={3000}/> */}
            {(!selectedChat) && !isPostsLoading ? <FeedPage postList={postList} filterForum={filterForum} filterLostnFound={filterLostnFound} filterTrading={filterTrading}/>
            :<ChatScreen chat={selectedChat} setSelectedChat={setSelectedChat}/>}
        </div>
        <div className='  px-2 pt-5 flex-1 divide-y'>
            <h2 className='mt-4 font-bold text-center text-xl'>Chat</h2>
            <div className='flex items-center '>
        <input
          type='search'
          id='search-dropdown'
          className='block p-2.5 w-full z-20 text-sm text-gray-900 bg-gray-50 rounded-lg border-l-2 border border-gray-300 focus:ring-blue-500 focus:border-blue-500 focus:outline-none'
          placeholder='Search'
          style={{ zIndex: 0 }} // z index helps to show the dropdown menu on top of other elements
          value={searchInput}
          onChange={(e) => setSearchInput(e.target.value)}
          required
        /> 
        <button onClick={()=>setIsCreateChatSelected(true)}
            type = 'button'
            className='ml-4 bg-blue-400 text-white text-sm font-medium px-4 py-2 rounded-lg'>
            <svg 
            xmlns='http://www.w3.org/2000/svg'
            fill='none'
            viewBox='0 0 24 24'
            strokeWidth='1.5'
            stroke='currentColor'
            className='w-6 h-6'>
                <path 
                    strokeLinecap='round'
                    strokeLinejoin='round'
                    d='M12 6v12m6-6H6'
                    />
            </svg>
          </button>
        </div>
            {Array.isArray(chatList) && chatList.filter((chat) => chat.chatName.toLowerCase().includes(searchInput.toLowerCase())
            ).map((chat,index)=><ChatItem key={index} chat={chat} handleChat ={()=> setSelectedChat(chat)}/>)}
        </div>
        <CreateChatModal setIsCreateChatSelected={setIsCreateChatSelected} 
        isChatSelected={isCreateChatSelected} selectedUsers={selectedUsers} setSelectedUsers={setSelectedUsers}/>
    </div>
  )
}

function ChatItem({chat,handleChat}){
    console.log(chat)
    const {groupChat,chatName} = chat;
    console.log(`isGroupChat=${groupChat}`)
    if(chat==null){
    }
    return(
        <div onClick={()=>{handleChat();console.log("Clicked chat")}} 
        className='flex rounded-lg py-3 my-2 items-center hover:bg-gray-200'>
            <div>{groupChat ? <img className='w-4 h-4 mr-1' alt='group-icon' src='./group.svg'/>:
            <img className='w-4 h-4 mr-1' alt='person-icon' src='./person.svg'/>}</div>
            <div className='rounded-full bg-gray-300 w-5 h-5 mr-3'></div>
            <div className='text-sm'>
                <p>{chatName}</p>
            </div>
        </div>
    );
}
function CreateChatModal({isChatSelected, selectedUsers,setSelectedUsers, setIsCreateChatSelected}){
    const {allUsers} = useData();
    console.log(allUsers)
    const removeUser = (indexToRemove) => {
        setSelectedUsers([...selectedUsers.filter((_, index) => index !== indexToRemove)]);
      };
    
      const addUser = (name) => {
        console.log("adding")
        console.log(name)
        if (name !== "") {
          if(!(selectedUsers.find((value)=>name === value)))
          {
            setSelectedUsers([...selectedUsers, name]);
            setNameInput("");
          }
        }
      };
      const [nameInput, setNameInput] = useState("");
      const [isFocused, setIsFocused] = useState(false);
      const searchResult = allUsers.filter((userItem)=>userItem.name.match(nameInput))
    return(
        <div>
            {isChatSelected &&
            <div className="fixed  bottom-0 left-0 w-full h-full bg-gray-800 bg-opacity-50 flex justify-center items-center zIndex = 200 " style={{ zIndex: 110 }}>
             <div className="bg-white p-4 rounded-md">
             <div className='flex justify-end'>
                  <button onClick={()=>setIsCreateChatSelected(false)}>
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                  </button>
              </div>
              <div className='flex mb-2 justify-center font-bold'><h1>Create Chat</h1></div>
             <div className='flex mb-5 items-center'>
            <label htmlFor="" className='w-28 font-semibold'> AddUser: </label>
            <div>
            <input
            name='hashtag'
            type="text"
            value={nameInput}
            onFocus={()=>setIsFocused(true)}
            onBlur={()=>setIsFocused(false)}
            onChange={(event)=>setNameInput(event.target.value)}
            placeholder="Press enter to add tags"
            className="bg-gray-50 border border-gray-300
            text-gray-900 sm:text-sm rounded-lg focus:outline focus:outline-2
            focus:border-sky-500 block w-full p-2.5 px-3"
            />
            <div className='border absolute z-20 bg-slate-50 rounded-lg'>
            {isFocused && searchResult.map((result=><div key={result.id}className='hover:bg-slate-400 px-1 py-1 text-center'>
                <button onClick={()=>{console.log("result");addUser(result.name);setIsFocused(false)}}>
                    {result.name}
                </button>
                </div>))}
            </div>
            </div>
            </div>
            <ul id="tags" className="flex flex-wrap pl-28 pr-4">
            {selectedUsers.map((tag, index) => (
                <li
                key={index}
                className="tag bg-blue-500 text-white h-8 flex items-center justify-center rounded-md m-2 p-2"
                >
                <span className="tag-title">{tag}</span>
                <span
                    className="tag-close-icon ml-2 cursor-pointer"
                    onClick={() => removeUser(index)}
                >
                    x
                </span>
                </li>
            ))}
            </ul>
            {selectedUsers.length>1 && <div>
            <label htmlFor="" className='w-28 font-semibold'> AddGroupName: </label>
            <input
            name='hashtag'
            type="text"
            onKeyUp={(event) => (event.key === "Enter" ? addUser(event) : null)}
            placeholder="Press enter to add tags"
            className="bg-gray-50 border border-gray-300
            text-gray-900 sm:text-sm rounded-lg focus:outline focus:outline-2
            focus:border-sky-500 block w-full p-2.5 px-3"
            />
            </div>}
            <button type="submit" onClick={(event)=>handlePostCreation(event)}
            className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 
            focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 
            text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">
                Create Chat
            </button>
            </div>
            
            </div>
          }
        </div>
    )
}