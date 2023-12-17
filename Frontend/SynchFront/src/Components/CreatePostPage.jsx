import React, { useState } from 'react'
import CategoryDisclosure from '../statics/CategoryDisclosure'
import SecondHandForum from '../Forums/SecondHandForum'
import ForumForm from '../Forums/ForumForm';
import SectionExchangeForm from '../Forums/SectionExchangeForm';
import DonationForm from '../Forums/DonationForm';
import LostnFoundForm from '../Forums/LostnFoundForm';
import BorrowForm from '../Forums/BorrowForm';

export default function CreatePost() {
  const [selectedForm, setSelectedForm] = useState("Second Hand");
  const categories = {"Trading":["Second Hand","Borrowing","Donation","Section Exchange"],"Lost&Found":[],"Forum":["Basic Forum","Announcement"]}
  return (
    <div className='w-full flex divide-x mt-5'>
        <div className='w-1/4 divide-y flex flex-col items-center'>
          <div>
              <h1 className='font-extrabold text-2xl mb-2'>Categories</h1>
          </div>
          <div>
            {Object.keys(categories).map((key,index)=>{
              console.log("key:"+key+"/value: "+categories[key])
              if(categories[key].length){return <CategoryDisclosure handleClick={setSelectedForm} key={index} title={key} categoryList={categories[key]}/>}
              else{return <button key={index} onClick={() => setSelectedForm(key)}
              className='text-lg font-bold pl-4 pr-2 py-2 w-full hover:bg-gray-200 flex rounded-lg mt-1'>
                {key}
              </button>}
            })}
          </div>
        </div>
        <div className='w-2/3'>
            {selectedForm ==="Second Hand" && <SecondHandForum/>}
            {selectedForm ==="Basic Forum" && <ForumForm/>}
            {selectedForm === "Section Exchange" && <SectionExchangeForm/>}
            {selectedForm === "Donation" && <DonationForm/>}
            {selectedForm === "Lost&Found" && <LostnFoundForm/>}
            {selectedForm === "Borrowing" && <BorrowForm/>}
        </div>
    </div>
  )
}
