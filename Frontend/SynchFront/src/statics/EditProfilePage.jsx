// EditProfilePage.jsx
import React, { useState } from 'react';

const EditProfilePage = ({ user, onSave }) => {
  //const [newProfilePicture, setNewProfilePicture] = useState(user.profilePicture);
  const [newBio, setNewBio] = useState(user.bio);

  const handleSaveProfilePicture = () => {
    // Save the updated profile picture
    onSave({ profilePicture: newProfilePicture });
  };

  const handleSaveBio = () => {
    // Save the updated bio
    onSave({ bio: newBio });
  };

  return (
    <div className="flex flex-col items-center">
      <h2 className="text-2xl font-bold mb-4">Edit Profile</h2>
      <div className="mb-4">
        <img
          className="rounded-full h-40 w-40 border-2 border-gray-300"
          src={newProfilePicture}
          alt="Profile Picture"
        />
        <input
          type="text"
          value={newProfilePicture}
          onChange={(e) => setNewProfilePicture(e.target.value)}
        />
        <button
          onClick={handleSaveProfilePicture}
          className="bg-blue-500 text-white font-bold px-4 py-2 rounded-full hover:bg-blue-600 transition duration-300"
        >
          Save Profile Picture
        </button>
      </div>
      <div className="mb-4">
        <p className="text-dark text-lg font-bold mb-2">Edit Bio:</p>
        <textarea
          value={newBio}
          onChange={(e) => setNewBio(e.target.value)}
          rows="4"
          cols="50"
        />
        <button
          onClick={handleSaveBio}
          className="bg-blue-500 text-white font-bold px-4 py-2 rounded-full hover:bg-blue-600 transition duration-300"
        >
          Save Bio
        </button>
      </div>
    </div>
  );
};

export default EditProfilePage;
