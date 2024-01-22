import React, { useState } from 'react'
import CommentComponent from './CommentComponent';
import CommentCreate from '../statics/CommentCreate';
import formatDate, { formatTime } from './HelperFunctions/DateFormat';
import { useNavigate } from 'react-router-dom';
import { useData } from '../Context/DataContext';

export default function ForumPost({post,isLostnFound, isAnynomous}) {
    if(!post){
        return <h1>loading</h1>
    }

    const [isStarred,setStarred] = useState(false);
    const [vote, setVotes] = useState(post.vote);
    const [isUpvote, setUpvote] = useState(false);
    const [isDownvote,setDownvote] = useState(false);
    const [commentList, setCommentList] = useState(post.commentList);
    const {user, allUsers} = useData();
    const navigate = useNavigate();

    const foundUser = allUsers.find((user) => user.id == post.authorID);

    const handleProfileClick = () => {
        const clickedUser = allUsers.find((user) => user.id === post.authorID);
        console.log("clickedUserId in handleProfileClick:", clickedUser); 
        const userName = clickedUser.name;
        console.log("CLIKED ON THE USER NAMEEEE", userName);
      
        // Use React Router params to pass authorID
        navigate(`/profile/${userName}`);
      };


    if(post.isAnonymous){
        return null;
    }

  return (
       <div className='mx-5 my-4 flex flex-col items-center bg-gray-100 rounded-lg divide-y'>
           <div className='flex justify-between w-full items-center px-2'>
           <div className='flex items-center'>
                  {foundUser.imageData ? (
                    <img
                        src={foundUser.imageData}
                        alt='user-profile'
                        className='rounded-full bg-gray-300 w-10 h-10 mr-3'
                        onClick={handleProfileClick}
                    />
                  ):  (
                    <div className='rounded-full bg-gray-300 w-5 h-5 mr-3'></div>
                )}                    <div>
                    <p className='font-semibold'>{post.name}</p>
                    <small>
                    {post.postType === 0 ? "Announcement" : post.postType === 4 ? "Normal" : "Forum"}
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
              <div className='w-1/2 px-3 py-2 flex divide-x gap-5'>
              {!post.imageData ? 
            <img alt='post-image' 
            src='https://developers.elementor.com/docs/assets/img/elementor-placeholder-image.png'
             className='grow-2 my-2 mx-4 w-2/3 h-2/3 overflow-hidden'></img>:
              <img src={post.imageData} 
              alt={`${post.imageName}`}/>}
                <div className='grow-2 px-3 py-1 font-semibold flex flex-col justify-around'>
                    <div className='flex gap-5'>
                    <button onClick={()=>{setStarred(!isStarred)}}>
                        <svg xmlns="http://www.w3.org/2000/svg" fill={isStarred?"rgb(246,190,0)":"none"} viewBox="0 0 24 24" strokeWidth={1.5} stroke="rgb(246,190,0)" className="w-6 h-6">
                        <path strokeLinecap="round" strokeLinejoin="round" d="M11.48 3.499a.562.562 0 011.04 0l2.125 5.111a.563.563 0 00.475.345l5.518.442c.499.04.701.663.321.988l-4.204 3.602a.563.563 0 00-.182.557l1.285 5.385a.562.562 0 01-.84.61l-4.725-2.885a.563.563 0 00-.586 0L6.982 20.54a.562.562 0 01-.84-.61l1.285-5.386a.562.562 0 00-.182-.557l-4.204-3.602a.563.563 0 01.321-.988l5.518-.442a.563.563 0 00.475-.345L11.48 3.5z" />
                        </svg>
                    </button>
                    </div>
                </div>
            </div>
            <div className='w-full'>
                <CommentComponent commentList={post.commentList}/>
            </div>
            <div className='w-full'>
            <CommentCreate isUpvote={isUpvote} isDownvote={isDownvote} vote={vote} 
        setDownvote={()=>{if(isDownvote){setDownvote(false);setVotes(vote+1);}else{setDownvote(true);isUpvote?setVotes(vote-2):setVotes(vote-1);setUpvote(false);}}} 
        setUpvote={()=>{if(isUpvote){setUpvote(false);setVotes(vote-1);}else{setUpvote(true);isDownvote?setVotes(vote+2):setVotes(vote+1);setDownvote(false);}}}
        postId={post.id}
        handlePostCreation={setCommentList}
        commentList ={commentList}
        />
            </div>
        </div> 
  )
}
