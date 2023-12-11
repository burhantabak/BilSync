import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import the useNavigate hook

const EditAccount = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const navigate = useNavigate(); // Call the hook


    const handleGoBack = () => { // Create a handler function
        navigate(-1); // Navigate back to the previous page
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        // Validate data
        if (!name || !email || !password || !newPassword || !confirmPassword) {
            alert('Please fill all required fields');
            return;
        }

        if (newPassword !== confirmPassword) {
            alert('New passwords do not match');
            return;
        }

        // Send data to backend API
        fetch('/api/account/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name,
                email,
                password,
                newPassword,
            }),
        }).then(() => {
            alert('Account updated successfully');
            // Clear form
            setName('');
            setEmail('');
            setPassword('');
            setNewPassword('');
            setConfirmPassword('');
        }).catch((error) => {
            alert('Error updating account');
            console.error(error);
        });
    };

    return (
        
        <div className=" w-full h-full">
            <div className="flex items-center mb-4">
                     <button
                        className="text-blue-500 hover:underline"
                        onClick={handleGoBack}
                     >
                        &larr; Back
                    </button>
                </div>
            <div className="container mx-auto px-4 py-4">
                <h1 className="text-black text-2xl font-bold">Edit Account</h1>

                <form className="mt-8" onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="text-black block mb-2" htmlFor="email">
                            Email
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

                    <div className="mb-4">
                        <label className="text-black block mb-2" htmlFor="password">
                            Old Password
                        </label>
                        <input
                            type="password"
                            name="password"
                            id="password"
                            className="bg-white border border-gray-300 rounded px-4 py-2 w-full"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>

                    <div className="mb-4">
                        <label className="text-black block mb-2" htmlFor="newPassword">
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
                        <label className="text-black block mb-2" htmlFor="confirmPassword">
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
                        className="text-white bg-blue-500 font-bold py-2 px-4 rounded"
                    >
                        Update Account
                    </button>
                </form>
            </div>
        </div>
    );
};

export default EditAccount;

