import {ArrowLeftIcon, ArrowRightIcon, XMarkIcon } from '@heroicons/react/20/solid';
import React, { useState } from 'react'
export default function StaticModal(props) {
    const [showModal, setShowModal] = React.useState(false);
    const [currentPage, setCurrentPage] = useState(1);
  return (
    <>
        <button onClick={()=>{setShowModal(true);}}
        type="submit" className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
        {props.name}
        </button>
      {showModal ? (
        <>
            <div className='px-3 py-2 bg-white rounded-lg w-1/2 h-3/4 mx-auto my-auto z-50 inset-0 fixed outline-none'>
                <div className='flex justify-end'>
                    <button onClick={()=>{setShowModal(false);}} className='text-gray-500y'>
                        <XMarkIcon className='h-12 text-gray-500'/>
                    </button>
                </div>
                <h1 className='text-2xl font-bold text-center'>{props.title}</h1>
                <Stepper current={currentPage}/>
                {props.children}
            </div>
            <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
        </>
      ) : null}
    </>
  );
}
function Stepper({current}){
    return(
        <div className="p-3">
            <div className="mx-4 p-4 mb-8">
                <div className="flex items-center">
                    <Trial number={1} description={"Add Label"} current={current} isFirst={true}/>
                    <Trial number={2} description={"Image/Description"} current={current} isFirst={false}/>
                    <Trial number={3} description={"Add Tag"} current={current} isFirst={false}/>
                    <Trial number={4} description={"Pricing"} current={current} isFirst={false}/>
                </div>
            </div>
            <div className='flex justify-between text-gray-500 px-5'>
                <button className='h-10 w-10'><ArrowLeftIcon/></button>
                <button className='h-10 w-10'><ArrowRightIcon/></button>
            </div>
        </div>
    );
}
function Trial({description, number,current,isFirst}){
    const isActive = current == number;
    return(
        <>
            {isFirst || <div class={`flex-auto border-t-2 transition duration-500 ease-in-out ${isActive?"border-primary-600":"border-gray-300"}`}></div>}
            <div class={`flex items-center ${isActive? "text-white":"text-gray-500"} relative`}>
                <div class={`rounded-full transition duration-500 ease-in-out h-12 w-12 py-3 border-2 ${isActive&&"bg-primary-600"} border-primary-600 text-center`}>
                    {/* <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-user-plus ">
                        <path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                        <circle cx="8.5" cy="7" r="4"></circle>
                        <line x1="20" y1="8" x2="20" y2="14"></line>
                        <line x1="23" y1="11" x2="17" y2="11"></line>
                    </svg> */} {number}
                </div>
                <div class="absolute top-0 -ml-10 text-center mt-16 w-32 text-xs font-medium uppercase text-gray-600">{description}</div>
            </div>
        </>
    );
}
