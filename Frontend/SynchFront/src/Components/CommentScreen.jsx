import React, { useState } from 'react'
import StaticModal from '../statics/StaticModal'
import DatePicker from 'react-datepicker'
import ImageInput from '../Forums/ForumComponents/ImageInput';
import { useData } from '../Context/DataContext';
export default function CommentScreen() {
  const [startDate, setStartDate] = useState(new Date());
  const [imageFile, setImageFile] = useState(null);
  const {user} = useData();
  const handleImage = ()=>{
    if (!imageFile) {
      console.error('No image file selected.');
      return;
    }

    const reader = new FileReader();
    let raw;
    reader.onload = (event) => {
      const arrayBuffer = event.target.result;
      const byteArray = new Uint8Array(arrayBuffer);

      const raw = JSON.stringify({
        body: 'Deneme deneme oluyor mu',
        image: Array.from(byteArray),
      });

      console.log(raw);

      const requestOptions = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${user.token}`, // Make sure 'user' is defined
        },
        body: raw,
      };

      fetch('http://localhost:8080/chat/1/sendMessage', requestOptions)
        .then((response) => {
          console.log(response);
          if (response.status === 200) {
            return response.status;
          } else if (response.status >= 400) {
            return response.text();
          }
          return 'Unidentified error occurred :(';
        })
        .then((result) => {
          console.log(result);
          return result;
        })
        .catch((error) => console.log('error state: ', error));
    };
    reader.onerror = (error) => {
      console.error('Error reading image file:', error);
    };

    reader.readAsArrayBuffer(imageFile);
  };
  return (
    <div className='w-1/2 mx-auto'>
        <StaticModal name="Click Here" title="Buying Process"/>
        <ImageInput imageFile={imageFile} setImageFile={setImageFile}/>
        <button onClick={handleImage}>CLick ME!</button>
    </div>
    )
}