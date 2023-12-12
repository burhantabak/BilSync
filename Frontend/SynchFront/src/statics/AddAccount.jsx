import React, { useState } from 'react';

export default function AddAccount() {
  const [accountType, setAccountType] = useState('');
  const [name, setName] = useState('');
  const [surname, setSurname] = useState('');
  const [profileImage, setProfileImage] = useState(null);
  const [email, setEmail] = useState('');

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
  };

  return (
    <div className="flex flex-col h-full bg-gray-100">
      <div className="flex flex-row justify-between px-4 py-2 bg-gray-200">
        <h1 className="text-xl font-bold">Add Account</h1>
      </div>
      <form className="flex flex-col overflow-auto px-4 py-2" onSubmit={handleSubmit}>
        <div className="mb-4">
          <label className="block text-sm font-medium text-gray-700">Account Type</label>
          <select
            name="accountType"
            value={accountType}
            onChange={handleAccountTypeChange}
            className="rounded-md border border-gray-300 px-2 py-1"
          >
            <option value="">Choose an account type</option>
            <option value="student">Student</option>
            <option value="alumni">Alumni</option>
            <option value="instructor">Instructor</option>
        </select>
        </div>
        <div className="mb-4">
          <label className="block text-sm font-medium text-gray-700">Name</label>
          <input
            type="text"
            name="name"
            value={name}
            onChange={handleNameChange}
            className="rounded-md border border-gray-300 px-2 py-1"
          />
        </div>
        <div className="mb-4">
          <label className="block text-sm font-medium text-gray-700">Surname</label>
          <input
            type="text"
            name="surname"
            value={surname}
            onChange={handleSurnameChange}
            className="rounded-md border border-gray-300 px-2 py-1"
          />
        </div>
        <div className="mb-4">
          <label className="block text-sm font-medium text-gray-700">Upload Profile Image</label>
          <input
            type="file"
            name="profileImage"
            onChange={handleProfileImageChange}
            className="rounded-md border border-gray-300 px-2 py-1"
          />
        </div>
        <div className="mb-4">
          <label className="block text-sm font-medium text-gray-700">Add Email</label>
          <input
            type="email"
            name="email"
            value={email}
            onChange={handleEmailChange}
            className="rounded-md border border-gray-300 px-2 py-1"
          />
        </div>
        <button type="submit" className="rounded-md bg-blue-500 text-white px-4 py-1 hover:bg-blue-600">
          Add Account
        </button>
      </form>
    </div>
)};