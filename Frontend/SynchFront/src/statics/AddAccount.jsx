import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function AddAccount() {
  const [accountType, setAccountType] = useState('');
  const [name, setName] = useState('');
  const [surname, setSurname] = useState('');
  const [profileImage, setProfileImage] = useState(null);
  const [email, setEmail] = useState('');

  const navigate = useNavigate();

  const handleAccountTypeChange = (event) => {
    setAccountType(event.target.value);
  };

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleSurnameChange = (event) => {
    setSurname(event.target.value);
  };

  const handleProfileImageChange = (event) => {
    setProfileImage(event.target.files[0]);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Add your submission logic here
  };

  const handleGoBack = () => {
    navigate(-1);
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
        <h1 className="text-blue-500 text-2xl font-bold mb-6">Add Account</h1>

        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block text-gray-700 mb-2" htmlFor="accountType">
              Account Type
            </label>
            <select
              name="accountType"
              value={accountType}
              onChange={handleAccountTypeChange}
              className="rounded-md border border-gray-300 px-4 py-2 w-full focus:outline-none focus:border-blue-500 transition duration-300 ease-in-out"
            >
              <option value="">Choose an account type</option>
              <option value="student">Student</option>
              <option value="alumni">Alumni</option>
              <option value="instructor">Instructor</option>
            </select>
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 mb-2" htmlFor="name">
              Name
            </label>
            <input
              type="text"
              name="name"
              value={name}
              onChange={handleNameChange}
              className="rounded-md border border-gray-300 px-4 py-2 w-full focus:outline-none focus:border-blue-500 transition duration-300 ease-in-out"
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 mb-2" htmlFor="surname">
              Surname
            </label>
            <input
              type="text"
              name="surname"
              value={surname}
              onChange={handleSurnameChange}
              className="rounded-md border border-gray-300 px-4 py-2 w-full focus:outline-none focus:border-blue-500 transition duration-300 ease-in-out"
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 mb-2" htmlFor="profileImage">
              Upload Profile Image
            </label>
            <input
              type="file"
              name="profileImage"
              onChange={handleProfileImageChange}
              className="rounded-md border border-gray-300 px-4 py-2 w-full focus:outline-none focus:border-blue-500 transition duration-300 ease-in-out"
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 mb-2" htmlFor="email">
              Add Email
            </label>
            <input
              type="email"
              name="email"
              value={email}
              onChange={handleEmailChange}
              className="rounded-md border border-gray-300 px-4 py-2 w-full focus:outline-none focus:border-blue-500 transition duration-300 ease-in-out"
            />
          </div>

          <button
            type="submit"
            className="bg-blue-500 text-white font-bold px-4 py-2 rounded w-full md:w-auto"
          >
            Add Account
          </button>
        </form>
      </div>
    </div>
  );
}