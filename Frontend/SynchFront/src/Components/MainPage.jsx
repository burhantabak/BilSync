import React, { useState } from 'react'
import {SearchBar} from '../statics/SearchBar'
import Post from '../model/Post.js'
import CommentData from '../model/CommentData.js'
import TradingPostItem from './TradingPostItem';
import ForumPost from './ForumPost';
import FeedPage from './FeedPage';
import { useData } from '../Context/DataContext';

export default function MainPage() {
    const {postList,chatList} = useData();
    console.log(postList);
    const [filterTrading, setFilterTrading] = useState(true);
    const [filterForum, setFilterForum] = useState(true);
    const [filterLostnFound, setFilterLostnFound] = useState(true);
    const [selectedChat, setSelectedChat] = useState(null);
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
            </ul>
        </div>
        <div className='mx-auto w-3/4 overflow-y-auto px-2 divide-y'>
            {/* <TradingPostItem vote={-1} title="Basys3 Sale" nameUser="Işıl Özgü" price={175}/>
            <TradingPostItem vote={11} title="Basys3 Sale" isBuy={true} nameUser="Kenan Zeynalov" price={2700}/>
            <TradingPostItem vote={3} title="Basys3 Sale" nameUser="Anıl Hoca" price={150}/>
            <TradingPostItem vote={2} title="Basys3 Sale" isBuy={true} nameUser="12344" price={3000}/> */}
            <FeedPage postList={postList} filterForum={filterForum} filterLostnFound={filterLostnFound} filterTrading={filterTrading}/>
        </div>
        <div className='  px-2 pt-5 flex-1 divide-y'>
            <h2 className='mt-4 font-bold text-center text-xl'>Chat</h2>
            {chatList.map((chat,index)=><ChatItem key={index} chat={chat}/>)}
        </div>
    </div>
  )
}

function ChatItem({chat}){
    console.log(chat)
    const {isGroupChat,userName} = chat;
    console.log(`isGroupChat=${isGroupChat}`)
    if(chat=null){
        return <div>Loading..</div>
    }
    return(
        <div className='flex rounded-lg py-3 my-2 items-center hover:bg-gray-200'>
            <div>{isGroupChat ? <img className='w-4 h-4 mr-1' alt='group-icon' src='./group.svg'/>:
            <img className='w-4 h-4 mr-1' alt='person-icon' src='./person.svg'/>}</div>
            <div className='rounded-full bg-gray-300 w-5 h-5 mr-3'></div>
            <div className='text-sm'>
                <p>{userName}</p>
            </div>
        </div>
    );
}