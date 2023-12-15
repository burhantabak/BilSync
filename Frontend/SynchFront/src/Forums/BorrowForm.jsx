import React, { useState } from 'react'
import ImageInput from './ForumComponents/ImageInput'
import { HashtagInput } from './ForumComponents/HashtagInput';
import InputField from './ForumComponents/InputField';
import DateInput from './ForumComponents/DateInput';
export default function BorrowForm() {
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
            <h1 className='font-bold text-2xl'>Borrow Creation</h1>
        </div>
        <HashtagInput tags={hashtags} setTags={setHashtags}/>
        <form>
            <ImageInput/>
            <InputField type={"number"} name={"Add Title"} handleEvent={null}/>
            <InputField type={"text"} name={"Add Description"} handleEvent={null}/>
            <div className='flex justify-between'>
            <DateInput/>
            </div>
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
