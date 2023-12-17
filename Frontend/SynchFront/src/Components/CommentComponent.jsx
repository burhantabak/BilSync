import React, { useState } from 'react'
import { Disclosure } from '@headlessui/react';
import { ChevronRightIcon } from '@heroicons/react/20/solid';
import { useData } from '../Context/DataContext';
import commentReportCall from '../calling/commentReportCall';
import formatDate, { formatTime } from './HelperFunctions/DateFormat';

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
            commentId = {comment.id}
            likeNo={comment.likeNo}
            commentDate = {comment.publishDate}
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
function CommentItem({isReplyComment, commenterName, commenterText,likeNo,handleReportClick,showReportBox,setShowReportBox,setReportReason,reportReason, commentId, commentDate}){


    const [isLiked, setisLiked] = useState(false);
    const [like, setLikeNo] = useState(likeNo);
    return(
        <div className={`${isReplyComment?"pl-8":"pl-2"} py-2 pr-2 w-full flex items-center justify-between`}>
            <div className='flex gap-3'>
                <div className='h-8 w-8 rounded-full bg-gray-300'></div>
                <div>
                    <h2 className='test-sm font-semibold'>{commenterName}</h2>
                    <p className='text-sm'>{commenterText}</p>
                    <div className="font-semibold text-gray-700 px-2 flex items-center justify-center space-x-1">

                        <small className="self-center">.</small>
                        <button onClick={handleReportClick} className="hover:underline">
                        <small className="text-red-500 cursor-pointer">Report</small>
                        </button>
                        <small className="self-center">.</small>
                </div>
                </div>
                </div>
            <PostInteraction 
              showReportBox={showReportBox}
              setShowReportBox={setShowReportBox}
              reportReason={reportReason}
              setReportReason={setReportReason}
              commentId = {commentId}
                  />
            <div className='text-center'>
                    <h2 className='text-sm font-semibold'>{formatDate(commentDate)}</h2>
                    <p className='text-sm'>{formatTime(commentDate)}</p>
            </div>
        </div>
    );
}

function PostInteraction({ isStarred, setStarred, postId,showReportBox,setShowReportBox ,reportReason,setReportReason, commentId}) {

  const { user } = useData();



  const handleReportSubmit = () => {
    console.log('Report submitted with reason:', reportReason);
    console.log(reportReason);
    console.log("Comment ID", commentId);
    console.log("Comment reason report", reportReason);

    // Call  reporting function with post ID
    commentReportCall(reportReason, user, commentId)
      .then(() => {
        console.log('Report submitted successfully.');
      })
      .catch((error) => {
        console.error('Error submitting report:', error);
      });

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