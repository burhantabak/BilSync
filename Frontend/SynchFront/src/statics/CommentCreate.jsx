  import React from 'react'
  import { useState } from 'react';
  import CreateComment from '../calling/commentCalling';
  import { useData } from '../Context/DataContext';
  import reportPostCalling from '../calling/reportsCalling';
  import postVoteCaller from '../calling/postVoteCaller';
  import ReportBox from './ReportBox';

  export default function CommentCreate({isUpvote, setUpvote,isDownvote , setDownvote,  postId, handlePostCreation,commentList}) {
    const [commentInput, setCommentInput] = useState("");
    const {user} = useData();
  const {voteCounts} = useData();
    const handleInput = (event)=>{
      setCommentInput(event.target.value);
    }
    const handleEnter = (event) => {
      if (event.key === 'Enter' && commentInput.trim() !== "") {
        try {
          const result = CreateComment(commentInput, postId);
          setCommentInput("");
          handlePostCreation(result); // Update the commentList in your component state
        } catch (error) {
          console.log('Error creating comment: ', error);
        }
      }
    };
    
    const handleButtonSubmit = () => {
      if (commentInput.trim() !== "") {
        try {
          const result = CreateComment(commentInput, postId,user, handlePostCreation);
          console.log(result);
          setCommentInput("");
          handlePostCreation(result); // Update the commentList in your component state
        } catch (error) {
          console.log('Error creating comment: ', error);
        }
      }
    };


    const handleUpvote = () => {
      postVoteCaller(postId, 'upvote', user)
        .then((response) => {
          // Handle successful upvote
          console.log('Upvoted successfully');
        })
        .catch((error) => {
          // Handle errors
          console.error('Error upvoting:', error.message);
        });
    };
    
    const handleDownvote = () => {
      postVoteCaller(postId, 'downvote', user)
        .then((response) => {
          // Handle successful downvote
          console.log('Downvoted successfully');

        })
        .catch((error) => {
          // Handle errors
          console.error('Error downvoting:', error.message);
        });
    };


    return (
          <div className="flex justify-around w-full items-center pr-1 pl-1">
            <div className='font-bold grow-1 text-2xl flex justify-center items-center gap-2'>
                  <button onClick={handleUpvote}>
                      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={isUpvote? 3:1.5} stroke="currentColor" className="w-6 h-6">
                      <path strokeLinecap="round" strokeLinejoin="round" d="M4.5 10.5L12 3m0 0l7.5 7.5M12 3v18" />
                      </svg>
                  </button>
                  <h2>{voteCounts[postId]}</h2>
                  <button onClick={handleDownvote}>
                      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={isDownvote? 3:1.5} stroke="currentColor" className="w-6 h-6">
                      <path strokeLinecap="round" strokeLinejoin="round" d="M19.5 13.5L12 21m0 0l-7.5-7.5M12 21V3" />
                      </svg>
                  </button>
              </div>
            <div className='w-3/4'>
                  <div className="relative my-2 px-4 pr-8">
                      <input onChange={handleInput} value={commentInput}
                      type="search" id="search-dropdown" className="block p-2.5 w-full z-20 text-sm text-gray-900 bg-gray-50 rounded-lg border-l-2 border border-gray-300 focus:ring-blue-500 focus:border-blue-500 focus:outline-none "  placeholder="Write a comment" required/>
                      <button onClick = {handleButtonSubmit}  type="submit" className="absolute top-0 right-0 p-2.5 text-sm font-medium h-full text-white bg-blue-700 rounded-r-lg border border-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300">
                      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                      <path strokeLinecap="round" strokeLinejoin="round" d="M6 12L3.269 3.126A59.768 59.768 0 0121.485 12 59.77 59.77 0 013.27 20.876L5.999 12zm0 0h7.5" />
                      </svg>
                          <span className="sr-only">Search</span>
                      </button>
                  </div>
              </div>
              <ReportBox postId={postId}/>
          </div>
    )
  }

  