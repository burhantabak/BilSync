import React, { useState } from 'react'
import ImageInput from './ForumComponents/ImageInput'
import { HashtagInput } from './ForumComponents/HashtagInput';
import InputField from './ForumComponents/InputField';
import { createTradingPost } from '../calling/postCreationCalling';
import { useData } from '../Context/DataContext';
import { useNavigate } from "react-router-dom";  
import { uploadFileCall } from '../calling/imageCalling';


export default function SecondHandForum() {
    const [title, setTitle] = useState("");
    const [isSubmitted, setIsCompleted] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const {user} = useData();
    const [description, setDescription] = useState("");
    const [imageFile, setImageFile] = useState(null);
    const [price, setPrice] = useState(0);
    const [iban, setIban] = useState("");
    const navigate = useNavigate();
    const [hashtags,setHashtags] = useState([]);
    const handlePostCreation = (event) => {
    event.preventDefault();
    let imageName = "";
    if(imageFile)
    {
        uploadFileCall(imageFile,user).then((name)=>{
            const post = {
                title: title,
                description: description,
                imageName: name,
                iban: iban,
                tags: hashtags,
                price: price,
            };
        
            // Debugging: Log the post object
            console.log("Post object:", post);
    
            createTradingPost(post,user).then((result) => {
                console.log(result);result === 200 ? setIsCompleted(true) : setErrorMessage(result);
            })

            if(setIsCompleted){
                navigate('/mainPage');
            }

        });
    }
    else if(imageName !== "not uploaded")
        {const post = {
            title: title,
            description: description,
            imageName: imageName ? imageName : "",
            iban: iban,
            tags: hashtags,
            price: price,
        };
    
        // Debugging: Log the post object
        console.log("Post object:", post);

        createTradingPost(post,user).then((result) => {
            console.log(result);result === 200 ? setIsCompleted(true) : setErrorMessage(result);
        });}
    else{
        setErrorMessage(imageName)
    }
    };
    
  return (
    <div>
        <div className='flex justify-center mb-5'>
            <h1 className='font-bold text-2xl'>Second Hand Trading Post Creation</h1>
        </div>
        <HashtagInput tags={hashtags} setTags={setHashtags}/>
        <form>
            <ImageInput imageFile={imageFile} setImageFile={setImageFile}/>
            <InputField value={title} type={"text"} name={"Add Title"} handleEvent={setTitle}/>
            <InputField value={description} type={"text"} name={"Add Description"} handleEvent={setDescription}/>
            <div className='flex justify-between'>
            <InputField value= {price} type={"number"} name={"Price"} handleEvent={setPrice}/>
            <InputField value={iban} type={"text"} name={"IBAN"} handleEvent={setIban}/>
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
