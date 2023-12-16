import React, { useState } from 'react'
import { HashtagInput } from './ForumComponents/HashtagInput';
import ImageInput from './ForumComponents/ImageInput';
import InputField from './ForumComponents/InputField';

export default function ForumForm() {
    const [hashtags,setHashtags] = useState([]);
    const [title,setTitle] = useState("");
    const [description,setDescription] = useState("");
    const [imageFile, setImageFile] = useState(null);
    const handlePostCreation = (event) => {
      event.preventDefault();
      let imageName = "";
      if(imageFile)
      {
          uploadFileCall(imageFile,user).then((name)=>{imageName = name});
      }
      if(imageName !== "not uploaded")
          {const borrowPost = {
              title: title,
              description: description,
              imageName: imageName ? imageName : "",
              tags: hashtags,              
          };
      
          // Debugging: Log the post object
          console.log("Post object:", borrowPost);
  
          createBasicForumPost(borrowPost,user).then((result) => {
              console.log(result);result === 200 ? setIsCompleted(true) : setErrorMessage(result);
          });}
      else{
          setErrorMessage(imageName)
      }
      };

    const [isAnonymity, setIsAnonymity] = useState(true);

    const handleRadioChange = (event) => {
      setIsAnonymity(event.target.value === 'true');
    };
  
    const handlePrivacyChange = () => {
      setIsPrivate(!isPrivate);
    };
    
    const addTag= (event)=>{
        if (event.target.value !== "") {
            setHashtags([...hashtags, event.target.value]);
            event.target.value = "";
            console.log(hashtags)
        }
    };
    const removeTags = indexToRemove => {
        setHashtags([...hashtags.filter((_, index) => index !== indexToRemove)]);
      };
    return (
    <div>
        <div className='flex justify-center mb-5'>
            <h1 className='font-bold text-2xl'>Forum Post Creation</h1>
        </div>
        <HashtagInput tags={hashtags} setTags={setHashtags}/>
        <form>
            <ImageInput imageFile={imageFile} setImageFile={setImageFile}/>
            <InputField type={"text"} name={"Add Title"} handleEvent={setTitle}/>
            <InputField type={"text"} name={"Add Description"} handleEvent={setDescription}/>
            <RadioButton isAnonymity={isAnonymity} handleRadioChange={handleRadioChange}/>
            <div className='flex items-center mb-3'>
          <input
            checked={isAnonymity}
            onChange={handlePrivacyChange}
          />
        </div>

            <button type="submit "  onClick={(event)=>handlePostCreation(event)}
            className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 
            focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 
            text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">
                Create Post
            </button>
        </form>
    </div>
  )
}
function RadioButton({ isAnonymity, handleRadioChange }) {

  return (
    <div className='flex'>
      <p className='font-bold mr-3 '>Anonymity:</p>

      <div className="mb-[0.125rem] block min-h-[1.5rem] pl-[1.5rem]">
        <input
          type="radio"
          name="anonymity"
          id="radioDefault01"
          value={true}
          checked={isAnonymity}
          onChange={handleRadioChange}
        />
        <label
          className="mt-px inline-block pl-[0.15rem] hover:cursor-pointer"
          htmlFor="radioDefault01"
        >
          Private
        </label>
      </div>

      <div className="mb-[0.125rem] block min-h-[1.5rem] pl-[1.5rem]">
        <input
          type="radio"
          id="publicRadio"
          name="anonymity"
          value={false}
          checked={!isAnonymity}
          onChange={handleRadioChange}
        />
        <label
          className="mt-px inline-block pl-[0.15rem] hover:cursor-pointer"
          htmlFor="publicRadio"
        >
          Public
        </label>
      </div>

    </div>
  );
}