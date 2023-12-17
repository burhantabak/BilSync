import React, { useState } from 'react'
import ImageInput from './ForumComponents/ImageInput'
import { HashtagInput } from './ForumComponents/HashtagInput';
import InputField from './ForumComponents/InputField';
import DateInput from './ForumComponents/DateInput';
import { useData } from '../Context/DataContext';
import { uploadFileCall } from '../calling/imageCalling';
import { createBorrowingPost } from '../calling/postCreationCalling';
import { useNavigate } from "react-router-dom";

export default function BorrowForm() {
    const [hashtags,setHashtags] = useState([]);
    const [title, setTitle] = useState("");
    const [isSubmitted, setIsCompleted] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const {user} = useData();
    const [description, setDescription] = useState("");
    const [imageFile, setImageFile] = useState(null);
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());

    const navigate = useNavigate();
    const handlePostCreation = (event) => {
    event.preventDefault();
    let imageName = "";
    if(imageFile)
    {
        uploadFileCall(imageFile,user).then((name)=>{
            const borrowPost = {
                title: title,
                description: description,
                imageName: imageName ? imageName : "",
                startDate:startDate.toISOString(),
                tags: hashtags,
                endDate:endDate.toISOString(),
            };
        
            // Debugging: Log the post object
            console.log("Post object:", borrowPost);
    
            createDonationPost(borrowPost,user).then((result) => {
                console.log(result);result === 200 ? setIsCompleted(true) : setErrorMessage(result);
            });
        });
    }
    if(imageName !== "not uploaded")
        {const borrowPost = {
            title: title,
            description: description,
            imageName: imageName ? imageName : "",
            startDate:startDate.toISOString(),
            tags: hashtags,
            endDate:endDate.toISOString(),
        };
    
        // Debugging: Log the post object
        console.log("Post object:", borrowPost);

        createBorrowingPost(borrowPost,user).then((result) => {
            console.log(result);
            if(result === 200) { 
                setIsCompleted(true) 
                navigate('/mainPage');
            } 
            else {
               setErrorMessage(result);
            }
        });
    }
    else{
        setErrorMessage(imageName)
    }
    };
  return (
    <div>
        <div className='flex justify-center mb-5'>
            <h1 className='font-bold text-2xl'>Borrow Creation</h1>
        </div>
        <HashtagInput tags={hashtags} setTags={setHashtags}/>
        <form>
            <ImageInput imageFile={imageFile} setImageFile={setImageFile}/>
            <InputField type={"text"} name={"Add Title"} handleEvent={setTitle}/>
            <InputField type={"text"} name={"Add Description"} handleEvent={setDescription}/>
            <div className='flex justify-between'>
            <DateInput setEndDate={setEndDate} setStartDate={setStartDate}
             startDate={startDate} endDate={endDate}/>
            </div>
            <div className='flex justify-center mb-1'>
            <p className='text-red-600'>{errorMessage}</p>
            </div>
            <button type="submit" onClick={(event)=>handlePostCreation(event)}
            className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 
            focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 
            text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">
                Create Post
            </button>
        </form>
    </div>
  )
}
