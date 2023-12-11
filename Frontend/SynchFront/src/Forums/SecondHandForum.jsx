import React, { useState } from 'react'
import ImageInput from './ForumComponents/ImageInput'
import { HashtagInput } from './ForumComponents/HashtagInput';
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
function InputField({type,name, handleEvent}){
    return(
    <div className='mx-2 flex mb-3 items-center'>
        <label htmlFor={name} className='font-semibold w-28'>{name}:</label>
        <input type={type} name={name} id="email" onChange={(event)=>handleEvent(event.target.value)}
        className=" bg-gray-50 border border-gray-300
        text-gray-900 sm:text-sm rounded-lg focus:outline focus:outline-2
        focus:border-sky-500 block w-full p-2.5 px-3" placeholder={name} required=""/>
    </div>
        )
}
