import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


const EditAccount = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const navigate = useNavigate();

    const handleGoBack = () => {
        navigate(-1);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        // Your validation and fetch logic remain unchanged
    };

    return (
        <div className="bg-blue-500 min-h-screen flex flex-col items-center justify-center">
            <div className="mb-4">
                <button
                    className="text-white"
                    onClick={handleGoBack}
                >
                    &larr; Back
                </button>
            </div>
            <div className="bg-white rounded p-8 shadow-md w-full max-w-md">
                <h1 className="text-blue-500 text-2xl font-bold mb-6">Edit Account</h1>

                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-gray-700 mb-2" htmlFor="email">
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
                        <label className="block text-gray-700 mb-2" htmlFor="password">
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
};

export default EditAccount;
