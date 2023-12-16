import React, { useState } from 'react'
import { Disclosure } from '@headlessui/react';
import { ChevronRightIcon } from '@heroicons/react/20/solid';
import { useData } from '../Context/DataContext';

export default function CommentComponent({commentList}){
  console.log('commentList:', commentList);
  const {user} = useData();
  
  const handleReportClick = () => {
    // Implement your logic for handling report click here
    console.log('Report clicked for comment:');

    // Set showReportBox to true to open the modal
    setShowReportBox(true);
  };

  const [showReportBox, setShowReportBox] = useState(false);
  const [reportReason,setReportReason]=useState("");

    return (
      <Disclosure>
        {({ open }) => (
          <>
        <Disclosure.Button className={"py-2 w-full hover:bg-gray-200 flex justify-center"}>
          <ChevronRightIcon className={`${open ? 'rotate-90 transform' : ''} h-6 w-6`}/>
          View Comments
        </Disclosure.Button>
        <Disclosure.Panel className={`text-gray-900`}>
          {/* <CommentItem likeNo={2} isReplyComment={false} commenterName={"Tuna"} commenterText={"Sağlam iş çıkartıyor."}/>
          <CommentItem likeNo={1} isReplyComment={true} commenterName={"Ahmet Tarık"} commenterText={"Aynen abi."}/> */}
            {commentList && Array.isArray(commentList) && commentList.map((comment,index)=><CommentItem 
            key={index} 
            isReplyComment={comment.isReply}
            commenterName={comment.userName} 
            commenterText={comment.text} 
            likeNo={comment.likeNo}
            setShowReportBox={setShowReportBox}
            showReportBox={showReportBox}
            handleReportClick={handleReportClick}
            setReportReason={setReportReason}
            reportReason={reportReason}
            />)}
        </Disclosure.Panel>
        </>)}
      </Disclosure>
    )
  }
function CommentItem({isReplyComment, commenterName, commenterText,likeNo,handleReportClick,showReportBox,setShowReportBox,setReportReason,reportReason}){
  
  // const [showReportBox, setShowReportBox] = useState(false);

   //const handleReportClick = () => {
  //   // Implement your logic for handling report click here
  //   console.log('Report clicked for comment:', commenterText);

  //   // Set showReportBox to true to open the modal
  //   setShowReportBox(true);
  // };


    const [isLiked, setisLiked] = useState(false);
    const [like, setLikeNo] = useState(likeNo);
    return(
        <div className={`${isReplyComment?"pl-8":"pl-2"} py-2 pr-2 flex items-center justify-between`}>
            <div className='flex gap-3'>
                <div className='h-8 w-8 rounded-full bg-gray-300'></div>
                <div>
                    <h2 className='test-sm font-semibold'>{commenterName}</h2>
                    <p className='text-sm'>{commenterText}</p>
                    <div className="font-semibold text-gray-700 px-2 flex items-center justify-center space-x-1">
                        <button onClick={()=>{if(isLiked){setisLiked(false);setLikeNo(like-1);}else{setisLiked(true);setLikeNo(like+1)}}} href="#" className="flex items-center">
                            <svg xmlns="http://www.w3.org/2000/svg" fill={isLiked?"red" : "none"} viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z" />
                            </svg>
                            <small>{like}</small>
                        </button>
                        <button href="#" className="hover:underline">
                            <small>Reply</small>
                        </button>
                        <small className="self-center">.</small>
                        <button onClick={handleReportClick} className="hover:underline">
                        <small className="text-red-500 cursor-pointer">Report</small>
                        </button>
                        <small className="self-center">.</small>
                </div>
                </div>
                </div>
            <div className='text-center'>
                    <h2 className='text-sm font-semibold'>17th October 2023</h2>
                    <p className='text-sm'>17:54</p>
            </div>
            <PostInteraction 
              showReportBox={showReportBox}
              setShowReportBox={setShowReportBox}
              reportReason={reportReason}
              setReportReason={setReportReason}
                  />
        </div>
    );
}

function PostInteraction({ isStarred, setStarred, postId,showReportBox,setShowReportBox ,reportReason,setReportReason}) {

  const { user } = useData();



  const handleReportSubmit = () => {
    console.log('Report submitted with reason:', reportReason);
    // Reset state after submission
    setShowReportBox(false);
    setReportReason('');
  };

  return (
    <div className='pl-1 flex items-center'>
      {/* Report Box */}
      {showReportBox && (
        <div className="fixed  bottom-0 left-0 w-full h-full bg-gray-800 bg-opacity-50 flex justify-center items-center zIndex = 200 " style={{ zIndex: 110 }}>
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
    </div>
  );
}