import React, { useState } from 'react'
import Drowpdown from '../statics/DropDown';
import { ChevronRightIcon } from '@heroicons/react/20/solid';
import Dropdown from '../statics/DropDown';
import CommentComponent from './CommentComponent';

export default function TradingPostItem({post}) {
    const [isStarred,setStarred] = useState(false);
    const [vote, setVotes] = useState(post.vote);
    const [isUpvote, setUpvote] = useState(false);
    const [isDownvote,setDownvote] = useState(false)
  return (
    <div className='mx-5 my-4 flex flex-col items-center bg-gray-100 rounded-lg divide-y'>
        <div className='flex justify-between w-full items-center px-2'>
                <div className='flex items-center'>
                    <div className='rounded-full bg-gray-300 w-5 h-5 mr-3'></div>
                    <div>
                    <p className='font-semibold'>{post.nameUser}</p>
                    <small>Trading Post</small>
                    </div>
                </div>
                <h2 className='text-xl text-gray-900 font-bold'>{post.title}</h2>
                <div className='flex justify-end pr-3 py-2'>
                    <div className='text-center'>
                    <h2 className='text-sm font-semibold'>17th October 2023</h2>
                    <p className='text-sm'>17:54</p>
                    </div>
                </div>
            </div>
        <div className='w-full text-center'>
            {post.description}
        </div>
        <div className='w-1/2 px-3 py-2 flex divide-x gap-5'>
            <div className='font-bold grow-1 text-3xl flex flex-col justify-center items-center gap-2'>
                <button onClick={()=>{if(isUpvote){setUpvote(false);setVotes(vote-1);}else{setUpvote(true);isDownvote?setVotes(vote+2):setVotes(vote+1);setDownvote(false);}}}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={isUpvote? 3:1.5} stroke="currentColor" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M4.5 10.5L12 3m0 0l7.5 7.5M12 3v18" />
                    </svg>
                </button>
                <h2>{vote}</h2>
                <button onClick={()=>{if(isDownvote){setDownvote(false);setVotes(vote+1);}else{setDownvote(true);isUpvote?setVotes(vote-2):setVotes(vote-1);setUpvote(false);}}}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={isDownvote? 3:1.5} stroke="currentColor" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M19.5 13.5L12 21m0 0l-7.5-7.5M12 21V3" />
                    </svg>
                </button>
            </div>
            <img alt='post-image' src='basys3.png' className='grow-2 my-2 mx-4 w-1/2 h-1/2 overflow-hidden'></img>
            <div className='grow-2 px-3 py-1 font-semibold flex flex-col justify-around'>
                <h2>{post.price}TL</h2>
                <div className='flex gap-5'>
                <button type="submit" className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                    {post.isBuy ? "Buy":"Borrow"}
                </button>
                <button onClick={()=>{setStarred(!isStarred)}}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill={isStarred?"rgb(246,190,0)":"none"} viewBox="0 0 24 24" strokeWidth={1.5} stroke="rgb(246,190,0)" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M11.48 3.499a.562.562 0 011.04 0l2.125 5.111a.563.563 0 00.475.345l5.518.442c.499.04.701.663.321.988l-4.204 3.602a.563.563 0 00-.182.557l1.285 5.385a.562.562 0 01-.84.61l-4.725-2.885a.563.563 0 00-.586 0L6.982 20.54a.562.562 0 01-.84-.61l1.285-5.386a.562.562 0 00-.182-.557l-4.204-3.602a.563.563 0 01.321-.988l5.518-.442a.563.563 0 00.475-.345L11.48 3.5z" />
                    </svg>
                </button>
                </div>
            </div>
        </div>
        <div className='w-full'>
            <CommentComponent commentList={post.comments}/>
        </div>
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
    </div>
  )
}
// function CommentListDisclosure(){
//   return (
//     <Disclosure>
//       {({ open }) => (
//         <>
//       <Disclosure.Button className={"py-2 w-full hover:bg-gray-200 flex justify-center"}>
//         <ChevronRightIcon className={`${open ? 'rotate-90 transform' : ''} h-6 w-6`}/>
//         Wiew Comments
//       </Disclosure.Button>
//       <Disclosure.Panel className={`text-gray-900`}>
//         <CommentItem likeNo={2} isReplyComment={false} commenterName={"Tuna"} commenterText={"Sağlam iş çıkartıyor."}/>
//         <CommentItem likeNo={1} isReplyComment={true} commenterName={"Ahmet Tarık"} commenterText={"Aynen abi."}/>
//       </Disclosure.Panel>
//       </>)}
//     </Disclosure>
//   )
// }

// function CommentItem({isReplyComment, commenterName, commenterText,likeNo}){
//     const [isLiked, setisLiked] = useState(false);
//     const [like, setLikeNo] = useState(likeNo);
//     return(
//         <div className={`${isReplyComment?"pl-8":"pl-2"} py-2 pr-2 flex items-center justify-between`}>
//             <div className='flex gap-3'>
//                 <div className='h-8 w-8 rounded-full bg-gray-300'></div>
//                 <div>
//                     <h2 className='test-sm font-semibold'>{commenterName}</h2>
//                     <p className='text-sm'>{commenterText}</p>
//                     <div class="font-semibold text-gray-700 px-2 flex items-center justify-center space-x-1">
//                         <button onClick={()=>{if(isLiked){setisLiked(false);setLikeNo(like-1);}else{setisLiked(true);setLikeNo(like+1)}}} href="#" class="flex items-center">
//                             <svg xmlns="http://www.w3.org/2000/svg" fill={isLiked?"red" : "none"} viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
//                             <path strokeLinecap="round" strokeLinejoin="round" d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z" />
//                             </svg>
//                             <small>{like}</small>
//                         </button>
//                         <button href="#" class="hover:underline">
//                             <small>Reply</small>
//                         </button>
//                         <small class="self-center">.</small>
//                         <button href="#" class="hover:underline">
//                             <small>Report</small>
//                         </button>
//                         <small class="self-center">.</small>
//                 </div>
//                 </div>
//                 </div>
//             <div className='text-center'>
//                     <h2 className='text-sm font-semibold'>17th October 2023</h2>
//                     <p className='text-sm'>17:54</p>
//             </div>
//         </div>
//     );
// }
