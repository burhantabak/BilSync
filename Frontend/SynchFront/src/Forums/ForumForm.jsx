import React, { useState } from 'react'
import { HashtagInput } from './ForumComponents/HashtagInput';
import ImageInput from './ForumComponents/ImageInput';
import InputField from './ForumComponents/InputField';

export default function ForumForm() {
    const [hashtags,setHashtags] = useState([]);
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
            <ImageInput/>
            <InputField type={"number"} name={"Add Title"} handleEvent={null}/>
            <InputField type={"text"} name={"Add Description"} handleEvent={null}/>
            <RadioButton/>
            <button type="submit" 
            className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 
            focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 
            text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">
                Create Post
            </button>
        </form>
    </div>
  )
}
function  RadioButton(){
    const [isAnonymity, setIsAnonymity] = useState(true); // Set the default value based on your requirements

  // Function to handle radio button change
  const handleRadioChange = (event) => {
    setIsAnonymity(event.target.id === 'flexRadioDefault01');
  };

  return (
    <div className='flex'>
      <p className='font-bold mr-3 '>Anonymity:</p>
      {/* Radio button for Anonymous */}
      <div className="mb-[0.125rem] block min-h-[1.5rem] pl-[1.5rem]">
        <input
          type="radio"
          name="flexRadioDefault"
          id="radioDefault01"
          value={isAnonymity}
          onChange={handleRadioChange}
        />
        <label
          className="mt-px inline-block pl-[0.15rem] hover:cursor-pointer"
          htmlFor="radioDefault01"
        >
          Anonymous
        </label>
      </div>

      {/* Radio button for Public */}
      <div className="mb-[0.125rem] block min-h-[1.5rem] pl-[1.5rem]">
      <input type="radio" id="publicRadio" name="name1" value={!isAnonymity} onChange={handleRadioChange}/>
        <label
          className="mt-px inline-block pl-[0.15rem] hover:cursor-pointer"
          htmlFor="radioDefault02"
        >
          Public
        </label>
      </div>

      {/* Display the value of isAnonymity */}
      <p>Is Anonymous: {isAnonymity ? 'Yes' : 'No'}</p>
    </div>
  );
}
