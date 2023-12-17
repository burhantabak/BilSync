import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import the useNavigate hook
import { forgotPasswordRequest } from '../calling/authCalling';

const ForgetPassword = () => {
  const [email, setEmail] = useState('');
  const [errorMessage,setErrorMessage] = useState("");
  const [successMessage,setSuccessMessage] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    forgotPasswordRequest(email).then(response=>{
      console.log( "response:");
      console.log(response);
      if(response === 200){
        setSuccessMessage("Password reset link is sent.\n It will be valid for 1 hour");
        setErrorMessage("");
      }
      else{
        setSuccessMessage("");
        setErrorMessage("Mail cannot be found");
      }
    })
  };
  const navigate = useNavigate(); // Call the hook

  const handleGoBack = () => { // Create a handler function
      navigate(-1); // Navigate back to the previous page
  };


  return (
    <div className="bg-500 w-full h-full">
       <div className="flex items-center mb-4">
            <button
            className="text-blue-500 hover:underline"
            onClick={handleGoBack}
            >
            &larr; Back
        </button>
      </div>
      <div className="container mx-auto px-4 py-4">
        <h1 className="text-black text-2xl font-bold">Forgot Password</h1>

        <form className="mt-8" onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="text-black block mb-2" htmlFor="email">
              Email Address
            </label>
            <input
              type="email"
              name="email"
              id="email"
              className="bg-white border border-gray-300 rounded px-4 py-2 w-full"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          {successMessage&&<div className='flex justify-center'>
            <p className='text-green-400 font-semibold'>{successMessage}</p>
            </div>}
          {errorMessage&&<div className='flex justify-center'>
            <p className='text-red-700 font-semibold'>{errorMessage}</p>
            </div>}
          <div className="flex justify-center">
            <button onClick={()=> forgotPasswordRequest(email)}
            type="submit" className="text-white bg-blue-500 font-bold px-4 py-2 rounded">
              Send Reset Link
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ForgetPassword;