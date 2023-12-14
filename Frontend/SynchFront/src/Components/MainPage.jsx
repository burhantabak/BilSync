import React, { useEffect, useState } from 'react'
import FeedPage from './FeedPage';
import { useData } from '../Context/DataContext';
import ChatScreen from './ChatScreen.jsx';

export default function MainPage() {
    const {postList,chatList,getThePosts,isPostsLoading} = useData();
    console.log(isPostsLoading)
    useEffect(()=> getThePosts()
        ,[]
    );
    const [filterTrading, setFilterTrading] = useState(true);
    const [filterForum, setFilterForum] = useState(true);
    const [filterLostnFound, setFilterLostnFound] = useState(true);
    const [selectedChat, setSelectedChat] = useState(null);
    const [searchInput, setSearchInput] = useState('');

    
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
        <button 
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
            {chatList.filter((chat) => chat.userName.toLowerCase().includes(searchInput.toLowerCase())
            ).map((chat,index)=><ChatItem key={index} chat={chat} handleChat ={()=> setSelectedChat(chat)}/>)}
        </div>
    </div>
  )
}

function ChatItem({chat,handleChat}){
    console.log(chat)
    const {isGroupChat,userName} = chat;
    console.log(`isGroupChat=${isGroupChat}`)
    if(chat=null){
        return <div>Loading..</div>
    }
    return(
        <div onClick={()=>{handleChat();console.log("Clicked chat")}} 
        className='flex rounded-lg py-3 my-2 items-center hover:bg-gray-200'>
            <div>{isGroupChat ? <img className='w-4 h-4 mr-1' alt='group-icon' src='./group.svg'/>:
            <img className='w-4 h-4 mr-1' alt='person-icon' src='./person.svg'/>}</div>
            <div className='rounded-full bg-gray-300 w-5 h-5 mr-3'></div>
            <div className='text-sm'>
                <p>{userName}</p>
            </div>
        </div>
    );
}