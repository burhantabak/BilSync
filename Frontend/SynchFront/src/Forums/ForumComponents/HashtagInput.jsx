import React, { useState } from 'react'

export const HashtagInput = (props) => {
  
    const removeTags = (indexToRemove) => {
      props.setTags([...props.tags.filter((_, index) => index !== indexToRemove)]);
    };
  
    const addTags = (event) => {
      if (event.target.value !== "") {
        if(!(props.tags.find((value)=>event.target.value === value)) || !(event.target.value.includes("#")))
        {
          props.setTags([...props.tags, event.target.value]);
          event.target.value = "";
        }
      }
    };
  
    return (
      <div className="mx-2 mb-2">
        <div className='flex'>
            <label htmlFor="" className='w-28 font-semibold'> Hashtags: </label>
            <input
            name='hashtag'
            type="text"
            onKeyUp={(event) => (event.key === "Enter" ? addTags(event) : null)}
            placeholder="Press enter to add tags"
            className="bg-gray-50 border border-gray-300
            text-gray-900 sm:text-sm rounded-lg focus:outline focus:outline-2
            focus:border-sky-500 block w-full p-2.5 px-3"
            />
        </div>
        <ul id="tags" className="flex flex-wrap pl-28 pr-4">
          {props.tags.map((tag, index) => (
            <li
              key={index}
              className="tag bg-blue-500 text-white h-8 flex items-center justify-center rounded-md m-2 p-2"
            >
              <span className="tag-title">#{tag}</span>
              <span
                className="tag-close-icon ml-2 cursor-pointer"
                onClick={() => removeTags(index)}
              >
                x
              </span>
            </li>
          ))}
        </ul>
      </div>
    );
  };