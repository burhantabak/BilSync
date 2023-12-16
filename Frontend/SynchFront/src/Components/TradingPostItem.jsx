import React, { useState } from 'react'
import Drowpdown from '../statics/DropDown';
import { ChevronRightIcon } from '@heroicons/react/20/solid';
import Dropdown from '../statics/DropDown';
import CommentComponent from './CommentComponent';
import CommentCreate from '../statics/CommentCreate';
import formatDate, { formatTime } from './HelperFunctions/DateFormat';
import { useNavigate } from 'react-router-dom';
import createTransaction from '../calling/transactioncall';
import { useData } from '../Context/DataContext';
export default function TradingPostItem({post, isProfile}) {
    const [isStarred,setStarred] = useState(false);
    const [vote, setVotes] = useState(post.votes);
    const [isUpvote, setUpvote] = useState(false);
    const [isDownvote,setDownvote] = useState(false)
    const [commentList, setCommentList] = useState(post.commentList);
    const [transactionComplete, setTransactionComplete] = useState(false);
    const [isSold, setIsSold] = useState(post.isHeld || false);
    const {user} = useData();
    
    const navigate = useNavigate();

    const handleBuyNow = (postId) => {
        // Handle the buy now action, e.g., setTransactionComplete(true)
        // Update the state or perform other actions based on postId
        setTransactionComplete(true);
        setIsSold(true); // Set isSold to true when the item is sold

      };
    

    const handleBuyClick = () => {
        // Navigate to the TransactionPage with the post id
        createTransaction(
            post.id, user
            // Add other necessary data
          ).then((response) => {
            setTransactionComplete(true);
            handleBuyNow(post.id);
      
            setIsSold(true); // Set isSold to true when the item is sold
          }).catch((error) => {
            // Handle the case where the transaction failed
          });
        navigate(`/transaction/${post.id}`, );;
      };
  return (
    <div className='mx-5 my-4 flex flex-col items-center bg-gray-100 rounded-lg divide-y'>
        <div className='flex justify-between w-full items-center px-2'>
                <div className='flex items-center'>
                    <div className='rounded-full bg-gray-300 w-5 h-5 mr-3'></div>
                    <div>
                    <p className='font-semibold'>{post.name}</p>
                    <small>
                    {post.postType === 1 ? "Borrow&Lend":
                     post.postType === 2 ? "Donation":
                      post.postType === 3? "Lost&Found":
                       post.postType === 5? "Section Exchange":
                        post.postType === 6? "2ndHandTrading"  : "Forum Post"}
                        </small>
                    </div>
                </div>
                <h2 className='text-xl text-gray-900 font-bold'>{post.title}</h2>
                <div className='flex justify-end pr-3 py-2'>
                    <div className='text-center'>
                    <h2 className='text-sm font-semibold'>{formatDate(post.postDate)}</h2>
                    <p className='text-sm'>{formatTime(post.postDate)}</p>
                    </div>
                </div>
            </div>
        <div className='w-full text-center'>
            {post.description}
        </div>
        {isSold && <p className="text-green-500 font-bold">This item has been sold!</p>}

        {!isProfile && <div className='w-1/2 px-3 py-2 flex divide-x gap-5'>
            <img alt='post-image' src='basys3.png' className='grow-2 my-2 mx-4 w-2/3 h-2/3 overflow-hidden'></img>
            <div className='grow-2 px-3 py-1 font-semibold flex flex-col justify-around'>
                <h2>{post.price}TL</h2>
                <div className='flex gap-5'>
                <button type="submit" onClick={handleBuyClick}
                className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                    {post.postType ? "Buy":"Borrow"}
                </button>
                <button onClick={()=>{setStarred(!isStarred)}}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill={isStarred?"rgb(246,190,0)":"none"} viewBox="0 0 24 24" strokeWidth={1.5} stroke="rgb(246,190,0)" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M11.48 3.499a.562.562 0 011.04 0l2.125 5.111a.563.563 0 00.475.345l5.518.442c.499.04.701.663.321.988l-4.204 3.602a.563.563 0 00-.182.557l1.285 5.385a.562.562 0 01-.84.61l-4.725-2.885a.563.563 0 00-.586 0L6.982 20.54a.562.562 0 01-.84-.61l1.285-5.386a.562.562 0 00-.182-.557l-4.204-3.602a.563.563 0 01.321-.988l5.518-.442a.563.563 0 00.475-.345L11.48 3.5z" />
                    </svg>
                </button>
                </div>
            </div>
        </div>}
        {isProfile  && <div className='w-1/2 px-3 py-2 flex divide-x gap-5'>
            <img alt='post-image' src='basys3.png' className='grow-2 my-2 mx-4 w-2/3 h-2/3 overflow-hidden'></img>
            <div className='grow-2 px-3 py-1 font-semibold flex flex-col justify-around'>
                <h2>{post.price}TL</h2>
                <div className='flex gap-5'>
                {!isProfile && <button type="submit" className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                </button> }
                <button onClick={()=>{setStarred(!isStarred)}}>
                    
                </button>
                </div>
            </div>
        </div>}
        <div className='w-full'>
            <CommentComponent commentList={commentList}/>
        </div>
        {!isProfile && <div className='w-full'>
        <CommentCreate isUpvote={isUpvote} isDownvote={isDownvote} vote={vote} 
        setDownvote={()=>{if(isDownvote){setDownvote(false);setVotes(vote+1);}else{setDownvote(true);isUpvote?setVotes(vote-2):setVotes(vote-1);setUpvote(false);}}} 
        setUpvote={()=>{if(isUpvote){setUpvote(false);setVotes(vote-1);}else{setUpvote(true);isDownvote?setVotes(vote+2):setVotes(vote+1);setDownvote(false);}}}
        postId={post.id}
        handlePostCreation={setCommentList}
        commentList ={commentList}
        />
    </div> }
    </div> 
  )
}
