// EditProfilePage.jsx
import React, { useState, useEffect } from 'react';
import { useData } from '../Context/DataContext';
import ImageInput from '../Forums/ForumComponents/ImageInput';
import { uploadFileCall } from '../calling/imageCalling';
import { editProfile } from '../calling/editProFileCalling';
import { ArrowLeftIcon } from '@heroicons/react/20/solid';
import { useNavigate } from 'react-router-dom';

const EditProfilePage = () => {
  const { user, getTheUsers } = useData();
  const [newBio, setNewBio] = useState(user.bio || "");
  const [imageFile, setImageFile] = useState(null);
  const navigate = useNavigate();

  // Fetch user data to ensure the profile picture is available
  useEffect(() => {
    getTheUsers();
  }, []);

  const handleSaveProfile = async () => {
    try {
      let imageName = null;

      // If there's an image file, upload it and get the image name
      if (imageFile) {
        imageName = await uploadFileCall(imageFile, user);
      }

      // Save the updated bio
      await editProfile(imageName, newBio, user);
      console.log("Updated the bio successfully!");
      console.log("New Bio Test Case: ", newBio);

      // Navigate to the profile page only if both image upload and bio update are successful
      navigate('/profilePage');
    } catch (error) {
      console.error("Error updating profile:", error);
      // Handle error, show a message, etc.
    }
  };

  return (
    <div className="flex flex-col items-center">
      <h2 className="text-2xl font-bold mb-4">Edit Profile</h2>
      <div className="mb-4 flex items-center">
        {/* Display the current profile picture or a default placeholder */}
        {user.imageData ? (
          <img
            className="rounded-full h-40 w-40 border-2 border-gray-300 mr-4"
            src={user.imageData}
            alt="Profile Picture"
          />
        ) : (
          <div className='rounded-full h-40 w-40 border-2 border-gray-300 mr-4 bg-slate-500'></div>
        )}
        <label htmlFor='profilePicture'>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth={1.5}
            stroke="currentColor"
            className="w-6 h-6 cursor-pointer"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10"
            />
          </svg>
          <input
            onChange={(event) => {
              console.log(event.target.files[0]);
              setImageFile(event.target.files[0]);
            }}
            accept='image/jpeg,image/png,image/svg+xml'
            type="file"
            name="editPp"
            id="profilePicture"
            className='hidden'
          />
        </label>
      </div>
      <div className="mb-4">
        <p className="text-dark text-lg font-bold mb-2">Edit Bio:</p>
        <textarea
          value={newBio}
          onChange={(e) => setNewBio(e.target.value)}
          rows="4"
          cols="50"
        />
      </div>
      <button
        onClick={handleSaveProfile}
        className="bg-blue-500 text-white font-bold px-4 py-2 rounded-full hover:bg-blue-600 transition duration-300"
      >
        Save Profile
      </button>
    </div>
  );
};

export default EditProfilePage;
