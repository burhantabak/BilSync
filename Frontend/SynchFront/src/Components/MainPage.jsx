import React from 'react'
import SearchBar from '../statics/SearchBar'
import PostItem from './PostItem';

export default function MainPage() {
  return (
    <div className='flex w-full divide-x-2 mt-4'>
        <div className='flex-1 sticky  pt-5 px-2 divide-y'>
            <h2 className='font-bold text-center text-xl py-2'>Categories</h2>
            <ul className='px-4'>
                <li className='rounded-lg py-3 px-2 text-center font-semibold mt-5 hover:bg-gray-300'>Profile</li>
                <li className='rounded-lg py-3 px-2 text-center font-semibold mt-5 hover:bg-gray-300'>Lost&Found</li>
                <li className='rounded-lg py-3 px-2 text-center font-semibold mt-5 hover:bg-gray-300'>Trading</li>
                <li className='rounded-lg py-3 px-2 text-center font-semibold mt-5 hover:bg-gray-300'>Forum</li>
            </ul>
        </div>
        <div className='mx-auto grow-2 overflow-y-auto px-2 divide-y'>
            <SearchBar/>
            <PostItem vote={-1} nameUser="12344" price={175}/>
            <PostItem vote={11} isBuy={true} nameUser="12344" price={2700}/>
            <PostItem vote={3} nameUser="12344" price={150}/>
            <PostItem vote={2} isBuy={true} nameUser="12344" price={3000}/>
        </div>
        <div className='px-2 pt-5 flex-1 divide-y'>
            <h2 className='mt-4 font-bold text-center text-xl'>Chat</h2>
            <ChatItem isGroupChat={false} nameUser="Ahmet Tarık"/>
            <ChatItem isGroupChat={false} nameUser="Işıl"/>
            <ChatItem isGroupChat={false} nameUser="Burhan"/>
            <ChatItem isGroupChat={false} nameUser="Kenan"/>
            <ChatItem isGroupChat={false} nameUser="Tuna"/>
        </div>
    </div>
  )
}

function ChatItem(props){
    return(
        <div className='flex rounded-lg py-3 my-2 items-center hover:bg-gray-200'>
            <div>{props.isGroupChat ? <img className='w-5 h-5 mr-2' alt='group-icon' src='./group.svg'/>:
            <img className='w-4 h-4 mr-2' alt='person-icon' src='./person.svg'/>}</div>
            <div className='rounded-full bg-gray-300 w-5 h-5 mr-3'></div>
            <div>
                <p>{props.nameUser}</p>
            </div>
        </div>
    );
}