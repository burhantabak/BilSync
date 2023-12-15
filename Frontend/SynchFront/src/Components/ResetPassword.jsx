import React, { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';
import { resetPassword } from '../calling/authCalling';
export default function ResetPassword() {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);

  // Access individual query parameters
  const token = queryParams.get('ticket');
    const [errorMesssage, setErrorMessage] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const navigate = useNavigate();
    const handleSubmit = (event) => {
        event.preventDefault();
        if(newPassword === confirmPassword){
          const passwordRequestData = {
            newPassword : newPassword,
            token : token,
          };
          console.log(passwordRequestData)
          resetPassword(passwordRequestData).then(data=>console.log(data));
        }
        else{
          setErrorMessage("Your confirmed password doesn't match");
        }
        // Your validation and fetch logic remain unchanged
    };

    return (
        <div className="bg-blue-500 min-h-screen pt-5 flex flex-col items-center justify-center">
            
            <div className="bg-white rounded p-8 shadow-md w-full max-w-md">
                <h1 className="text-blue-500 text-2xl font-bold mb-6">Reset Password</h1>

                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-gray-700 mb-2" htmlFor="newPassword">
                            New Password
                        </label>
                        <input
                            type="password"
                            name="newPassword"
                            id="newPassword"
                            className="bg-white border border-gray-300 rounded px-4 py-2 w-full"
                            value={newPassword}
                            onChange={(e) => setNewPassword(e.target.value)}
                        />
                    </div>

                    <div className="mb-4">
                        <label className="block text-gray-700 mb-2" htmlFor="confirmPassword">
                            Confirm Password
                        </label>
                        <input
                            type="password"
                            name="confirmPassword"
                            id="confirmPassword"
                            className="bg-white border border-gray-300 rounded px-4 py-2 w-full"
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                        />
                    </div>

                    <button
                        type="submit"
                        className="bg-blue-500 text-white font-bold px-4 py-2 rounded w-full md:w-auto"
                    >
                        Update Account
                    </button>
                </form>
            </div>
        </div>
    );
}

