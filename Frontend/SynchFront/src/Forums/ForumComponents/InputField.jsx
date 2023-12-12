import React from 'react'

export default function InputField({type,name, handleEvent}) {
  return (
    <div className='mx-2 flex mb-3 items-center'>
        <label htmlFor={name} className='font-semibold w-28'>{name}:</label>
        <input type={type} name={name} id="email" onChange={(event)=>handleEvent(event.target.value)}
        className=" bg-gray-50 border border-gray-300
        text-gray-900 sm:text-sm rounded-lg focus:outline focus:outline-2
        focus:border-sky-500 block w-full p-2.5 px-3" placeholder={name} required=""/>
    </div>
  )
}