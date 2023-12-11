import React from 'react'
import CategoryDisclosure from '../statics/CategoryDisclosure'
import SecondHandForum from '../Forums/SecondHandForum'

export default function CreatePost() {
  const categories = {"Trading":["Second Hand","Borrowing","Donation"],"Lost&Found":[],"Forum Post":["Basic Forum Post"]}
  return (
    <div className='w-full flex divide-x mt-5'>
        <div className='w-1/4 divide-y flex flex-col items-center'>
          <div>
              <h1 className='font-extrabold text-2xl mb-2'>Categories</h1>
          </div>
          <div>
            {Object.keys(categories).map((key,index)=>{
              console.log("key:"+key+"/value: "+categories[key])
              if(categories[key].length){return <CategoryDisclosure key={index} title={key} categoryList={categories[key]}/>}
              else{return <button key={index} className='text-lg font-bold pl-4 pr-2 py-2 w-full hover:bg-gray-200 flex rounded-lg mt-1'>
                {key}
              </button>}
            })}
          </div>
        </div>
        <div className='w-2/3'>
            <SecondHandForum/>
        </div>
    </div>
  )
}
