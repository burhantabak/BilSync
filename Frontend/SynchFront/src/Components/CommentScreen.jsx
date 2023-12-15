import React, { useState } from 'react'
import StaticModal from '../statics/StaticModal'
import DatePicker from 'react-datepicker'
export default function CommentScreen() {
  const [startDate, setStartDate] = useState(new Date());
  return (
    <div className='w-1/2 mx-auto'>
        <StaticModal name="Click Here" title="Buying Process"/>
    </div>
    )
}