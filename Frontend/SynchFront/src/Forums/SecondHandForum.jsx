import React, { useState } from 'react'
import ImageInput from './ForumComponents/ImageInput'
import { HashtagInput } from './ForumComponents/HashtagInput';
import InputField from './ForumComponents/InputField';
export default function SecondHandForum() {
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
            <h1 className='font-bold text-2xl'>Second Hand Trading Post Creation</h1>
        </div>
        <form>
            <HashtagInput tags={hashtags} setTags={setHashtags}/>
            <ImageInput/>
            <InputField type={"number"} name={"Add Title"} handleEvent={null}/>
            <InputField type={"text"} name={"Add Description"} handleEvent={null}/>
            <div className='flex justify-between'>
            <InputField type={"number"} name={"Price"} handleEvent={null}/>
            <InputField type={"text"} name={"IBAN"} handleEvent={null}/>
            </div>
        </form>
    </div>
  )
}
