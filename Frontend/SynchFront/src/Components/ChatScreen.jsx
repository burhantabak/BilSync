import { ArrowLeftIcon } from '@heroicons/react/20/solid'
import React from 'react'
export default function ChatScreen({chat,setSelectedChat}) {
  if(!chat){
    return <h1>loading chat...</h1>
  }
    const {messages,userName} = chat;
  return (
    <div className='flex flex-col w-full divide-y h-100 justify-around'>
        <div className='flex justify-between items-center mb-2'>
        <div className=''>
            <button onClick={()=>setSelectedChat(null)}><ArrowLeftIcon className='h-8 w-8'/></button>
        </div>
        <div className='flex justify-center gap-1 items-center'>
        <div className='rounded-full bg-gray-300 w-7 h-7 mr-3'></div>
          <div>
            {userName}
          </div>
        </div>
        <div></div>
        </div>
        <div className='flex-grow overflow-y-auto'>
          <MessageScreen messages={messages}/>
        </div>
        <div className='flex-grow-0'>
          Chat input is in here
        </div>
    </div>
  )
}
function MessageScreen({messages}){
  return (<div className='h-full'>
    {messages.map((message,index)=> <MessageItem messageData={message} key={index} />)}
  </div>)
}
function MessageItem({messageData}){
  const {message,isReceived} = messageData;

    return(
      <div className={`flex ${isReceived || "flex-row-reverse"} py-2 gap-2`}>
        <div className='h-8 w-8 rounded-full bg-gray-400'></div>
        <div className={`max-w-md bg-primary-400 text-white p-3 rounded-2xl`}>
            <p>{message}</p>
        </div>
      </div>
    )
}
