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
function RadioButton() {
  const [isAnonymity, setIsAnonymity] = useState(true);

  const handleRadioChange = (event) => {
    setIsAnonymity(event.target.value === 'true');
  };

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