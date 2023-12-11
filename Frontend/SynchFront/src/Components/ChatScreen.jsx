import { ArrowLeftIcon } from '@heroicons/react/20/solid'
import React from 'react'
export default function ChatScreen({chat,setSelectedChat}) {
  if(!chat){
    return <h1>loading chat...</h1>
  }
    const {messages,userName} = chat;
  return (
    <div className='flex flex-col w-full divide-y h-screen justify-around pb-3'>
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
          <CreateChat/>
        </div>
    </div>
  )
}
function MessageScreen({messages}){
  return (<div className='h-full flex flex-col-reverse'>
    {messages.map((message,index)=> <MessageItem messageData={message} key={index} />).reverse()}
  </div>)
}
function MessageItem({messageData}){
  const {message,isReceived} = messageData;

    return(
      <div className={`flex ${isReceived || "flex-row-reverse"} py-2 gap-2`}>
        <div className='h-8 w-8 rounded-full bg-gray-400'></div>
        <div className={`max-w-md ${isReceived ? "bg-gray-400":"bg-primary-400"} text-white p-3 rounded-2xl`}>
            <p>{message}</p>
        </div>
      </div>
    )
}
function CreateChat(){
  return (
  <div className='w-full'>
    <div className="relative my-2 px-4 pr-8">
        <input type="search" id="search-dropdown" className="block p-2.5 w-full z-20 text-sm text-gray-900 bg-gray-50 rounded-lg border-l-2 border border-gray-300 focus:ring-blue-500 focus:border-blue-500 " placeholder="Write a comment" required/>
        <button type="submit" className="absolute top-0 right-0 p-2.5 text-sm font-medium h-full text-white bg-blue-700 rounded-r-lg border border-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
        <path strokeLinecap="round" strokeLinejoin="round" d="M6 12L3.269 3.126A59.768 59.768 0 0121.485 12 59.77 59.77 0 013.27 20.876L5.999 12zm0 0h7.5" />
        </svg>
            <span className="sr-only">Search</span>
        </button>
    </div>
  </div>
  )
}
