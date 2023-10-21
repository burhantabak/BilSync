import { ArrowLeftIcon } from '@heroicons/react/20/solid'
import React from 'react'
export default function ChatScreen({chat,setSelectedChat}) {
    const {messages,userName} = chat;
  return (
    <div className='w-full divide-x'>
        <div className='flex justify-between'>
        <div className=''>
            <button onClick={setSelectedChat}><ArrowLeftIcon className='h-8 w-8'/></button>
        </div>
        <div>{userName}</div>
        <div></div>
        </div>
    </div>
  )
}
function MessageItem({messageData}){
  const {message} = messageData;
    return(
      <div className='flex'>
        <div className='h-8 w-8 rounded-full bg-gray-400'></div>
        <div className={`max-w-md bg-primary-400 text-white`}>
            <p>{message}</p>
        </div>
      </div>
    )
}
