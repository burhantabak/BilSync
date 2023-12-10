import React, { useState } from 'react'
import { Disclosure } from '@headlessui/react';
import { ChevronRightIcon } from '@heroicons/react/20/solid';
export default function CategoryDisclosure({title, categoryList}){
    console.log("categoryList: "+categoryList)
    return (
      <Disclosure>
        {({ open }) => (
          <>
        <Disclosure.Button className={"py-2 w-full hover:bg-gray-200 flex rounded-lg mt-1 "}>
          <ChevronRightIcon className={`${open ? 'rotate-90 transform' : ''} h-6 w-6`}/>
          <h1 className='text-lg font-bold'>{title}</h1>
        </Disclosure.Button>
        <Disclosure.Panel className={`text-gray-900 flex flex-col`}>
            {categoryList.map((value, index) =>{
                console.log("Called");
                return <button key={index} className='py-1 px-2 hover:bg-gray-200 rounded lg'>
                    <h1>{value}</h1>
                </button>
            })}
        </Disclosure.Panel>
        </>)}
      </Disclosure>
    )
  }