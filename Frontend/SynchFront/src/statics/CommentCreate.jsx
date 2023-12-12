import React from 'react'
import { useState } from 'react';
export default function CommentCreate({isUpvote, setUpvote,isDownvote , setDownvote, vote}) {
  return (
        <div className="flex justify-around w-full items-center pr-1 pl-1">
	        <div className='font-bold grow-1 text-2xl flex justify-center items-center gap-2'>
                <button onClick={setUpvote}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={isUpvote? 3:1.5} stroke="currentColor" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M4.5 10.5L12 3m0 0l7.5 7.5M12 3v18" />
                    </svg>
                </button>
                <h2>{vote}</h2>
                <button onClick={setDownvote}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={isDownvote? 3:1.5} stroke="currentColor" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M19.5 13.5L12 21m0 0l-7.5-7.5M12 21V3" />
                    </svg>
                </button>
            </div>
	        <div className='w-3/4'>
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
            <PostInteraction/>
        </div>
  )
}
function PostInteraction({isStarred,setStarred}){

  const [showReportBox, setShowReportBox] = useState(false);
  const [reportReason, setReportReason] = useState('');

  const handleReportClick = () => {
    setShowReportBox(true);
    console.log("Report clicked")
  };

  const handleReportSubmit = () => {
    console.log('Report submitted with reason:', reportReason);

    // Reset state after submission
    setShowReportBox(false);
    setReportReason('');
  };

    return(
        <div className='pl-1 flex items-center'>
        <button className='flex items-center flex-col hover:underline' onClick={handleReportClick}>
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
            <path strokeLinecap="round" strokeLinejoin="round" d="M3 3v1.5M3 21v-6m0 0l2.77-.693a9 9 0 016.208.682l.108.054a9 9 0 006.086.71l3.114-.732a48.524 48.524 0 01-.005-10.499l-3.11.732a9 9 0 01-6.085-.711l-.108-.054a9 9 0 00-6.208-.682L3 4.5M3 15V4.5" />
          </svg>
          <p className='text-xs'>Report</p>
        </button>
  
        {/* Report Box */}
        {showReportBox && (
          <div className="fixed  bottom-0 left-0 w-full h-full bg-gray-800 bg-opacity-50 flex justify-center items-center zIndex = 200">
            <div className="bg-white p-4 rounded-md">
              <p>Report Reason:</p>
              <textarea
                value={reportReason}
                onChange={(e) => setReportReason(e.target.value)}
                className="w-full p-2 border border-gray-300 rounded-md"
                placeholder="Enter your report reason..."
              />
              <button
                onClick={handleReportSubmit}
                className="mt-2 px-4 py-2 bg-blue-700 text-white rounded-md"
              >
                Submit Report
              </button>
            </div>
          </div>
        )}
        <button className='flex items-center flex-col hover:underline' onClick={()=>{setStarred(!isStarred)}}>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
            <path strokeLinecap="round" strokeLinejoin="round" d="M2.25 12.76c0 1.6 1.123 2.994 2.707 3.227 1.087.16 2.185.283 3.293.369V21l4.076-4.076a1.526 1.526 0 011.037-.443 48.282 48.282 0 005.68-.494c1.584-.233 2.707-1.626 2.707-3.228V6.741c0-1.602-1.123-2.995-2.707-3.228A48.394 48.394 0 0012 3c-2.392 0-4.744.175-7.043.513C3.373 3.746 2.25 5.14 2.25 6.741v6.018z" />
            </svg>
            <p className='text-xs'>Chat</p>
        </button>
    </div>
    );
}
