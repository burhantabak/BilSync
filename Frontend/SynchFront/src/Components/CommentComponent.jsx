import React, { useState } from 'react'
import { Disclosure } from '@headlessui/react';
import { ChevronRightIcon } from '@heroicons/react/20/solid';
export default function CommentComponent({commentList}){
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
            {commentList.map((comment,index)=><CommentItem key={index} isReplyComment={comment.isReply}
             commenterName={comment.userName} commenterText={comment.text} likeNo={comment.likeNo}/>)}
        </Disclosure.Panel>
        </>)}
      </Disclosure>
    )
  }
function CommentItem({isReplyComment, commenterName, commenterText,likeNo}){
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
                        <button href="#" className="hover:underline">
                            <small>Report</small>
                        </button>
                        <small className="self-center">.</small>
                </div>
                </div>
                </div>
            <div className='text-center'>
                    <h2 className='text-sm font-semibold'>17th October 2023</h2>
                    <p className='text-sm'>17:54</p>
            </div>
        </div>
    );
}
