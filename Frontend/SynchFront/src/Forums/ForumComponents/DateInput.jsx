import React, { useState } from 'react'
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css"
export default function DateInput({startDate,endDate,setStartDate, setEndDate}) {
  return (
    <div className='pl-2 flex items-center mb-3'>
      <p className='font-semibold w-28'>Select Time Interval:</p>
      <DatePicker
      showIcon
      selected={startDate}
      onChange={(date) => {setStartDate(date)}}
    />
    <span className='font-semibold mx-5'>to</span>
    <DatePicker
      showIcon
      selected={endDate}
      onChange={(date) => {setEndDate(date)}}
    />
    </div>
  );
}
