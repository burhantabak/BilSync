import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const mockUsers = [
  { id: 1, name: 'Tuna Saygin', email: 'tuna.saygin@ug.bilkent.edu.tr' , isBanned: false,   imageUrl: 'https://media.licdn.com/dms/image/C4D03AQFnedyongjCEw/profile-displayphoto-shrink_800_800/0/1668101973946?e=1706140800&v=beta&t=Rrw9xDrS-k7l0eLVvwz5VmWuNslnoFhzLlqQi6QfdAI' },
  { id: 2, name: 'Kanan Zeynalov', email: 'kanan.zeynalov@ug.bilkent.edu.tr', isBanned: true, imageUrl: 'https://media.licdn.com/dms/image/D4D03AQE9ip_PWaWaCA/profile-displayphoto-shrink_800_800/0/1702173044193?e=1707955200&v=beta&t=ozf-9tLZhMY5ZlOj0wJY8PC-fPxaoZf89ueyTUyHnME' },
  { id: 3, name: 'Ahmet Tarık Uçur', email: 'ahmet.tarik@ug.bilkent.edu.tr', isBanned: false,imageUrl: 'https://media.licdn.com/dms/image/D4D03AQHZwUnvSaPpQg/profile-displayphoto-shrink_200_200/0/1673429310545?e=1706140800&v=beta&t=gjunIpeWSZ5OItjjTYDy2vb4HL9pVLPuKnGMb564XVY'},
];

export default function AdminPanel() {
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredUsers, setFilteredUsers] = useState(mockUsers);

  const navigate = useNavigate();
  

  const handleChangeEmail = (userId) => {
    console.log(`Change email for user with ID: ${userId}`);
  };

  const handleBanUser = (userId) => {
    console.log(`Ban/Unban user with ID: ${userId}`);
  };

  const handleReports = () => {
    console.log('Navigate to reports endpoint'); // i will change when i will impllement 
  };

  const handleAddAccount = () => {

    navigate("/admin/addAccount");
  };

 
  const handleSearch = (event) => {
    const searchValue = event.target.value.toLowerCase();
    setSearchTerm(searchValue);
    setFilteredUsers(
      mockUsers.filter((user) =>
        user.name.toLowerCase().includes(searchValue) ||
        user.email.toLowerCase().includes(searchValue)
      )
    );
  };

  return (
    <div className="flex flex-col h-screen bg-gray-100">
      <div className="flex flex-row justify-between px-4 py-2 bg-gray-200">
        <h1 className="text-xl font-bold">Admin Panel</h1>
        <div className="flex space-x-2 items-center">

        <input
          type="text"
          placeholder="Search users..."
          className="rounded-md border border-gray-300 px-2 py-1"
          onChange={handleSearch}
        />
        <button 
            onClick={handleAddAccount}
            className="rounded-md bg-blue-500 text-white px-4 py-1 hover:bg-blue-600"
            >
            Add Account
            </button>
        <button
            onClick={handleReports}
            className="rounded-md bg-blue-500 text-white px-4 py-1 hover:bg-blue-600"
        >  Reports </button>
      </div>
        </div>
      <div className="flex flex-col overflow-auto px-4 py-2">
        {filteredUsers.map((user) => (
          <div
            key={user.id}
            className="flex flex-row items-center justify-between py-2 px-4 mb-2 bg-gray-300 rounded-md"
          >
            <div className="flex flex-col">
              <img
                src={user.imageUrl} 
                alt="Profile picture"
                className="w-10 h-10 rounded-full mr-4"
              />
              <span className="text-lg font-medium">{user.name}</span>
              <span className="text-sm text-gray-500">{user.email}</span>
            </div>
            <div className="flex flex-row space-x-2">
              <button
                onClick={() => handleChangeEmail(user.id)}
                className="rounded-md bg-red-500 text-white px-4 py-1 hover:bg-red-600"
              >
                Change Email
              </button>
              {}
              <button
                onClick={() => handleBanUser(user.id)}
                className="rounded-md bg-blue-500 text-white px-4 py-1 hover:bg-blue-600"
              >
                {user.isBanned ? 'Unban' : 'Ban'}
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

