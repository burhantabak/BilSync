import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useData } from '../Context/DataContext';
import getAllUsers from '../calling/userCalling';
import { getImage } from '../calling/imageCalling';
import { changeEmail } from '../calling/changeEmailCalling';
import { banUser, unBanUser } from '../calling/adminCalling';
const mockUsers = [
  { id: 1, name: 'Tuna Saygin', email: 'tuna.saygin@ug.bilkent.edu.tr' , isBanned: false,   imageUrl: 'https://media.licdn.com/dms/image/C4D03AQFnedyongjCEw/profile-displayphoto-shrink_800_800/0/1668101973946?e=1706140800&v=beta&t=Rrw9xDrS-k7l0eLVvwz5VmWuNslnoFhzLlqQi6QfdAI' },
  { id: 2, name: 'Kanan Zeynalov', email: 'kanan.zeynalov@ug.bilkent.edu.tr', isBanned: true, imageUrl: 'https://media.licdn.com/dms/image/D4D03AQE9ip_PWaWaCA/profile-displayphoto-shrink_800_800/0/1702173044193?e=1707955200&v=beta&t=ozf-9tLZhMY5ZlOj0wJY8PC-fPxaoZf89ueyTUyHnME' },
  { id: 3, name: 'Ahmet Tarık Uçur', email: 'ahmet.tarik@ug.bilkent.edu.tr', isBanned: false,imageUrl: 'https://media.licdn.com/dms/image/D4D03AQHZwUnvSaPpQg/profile-displayphoto-shrink_200_200/0/1673429310545?e=1706140800&v=beta&t=gjunIpeWSZ5OItjjTYDy2vb4HL9pVLPuKnGMb564XVY'},
];

export default function AdminPanel() {
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredUsers, setFilteredUsers] = useState(mockUsers);
  const {allUsers,getTheUsers,user} = useData();
  const [showChangeEmailBox,setShowChangeEmailBox] = useState(null)
  const navigate = useNavigate();
  const [newEmail, setNewEmail] = useState("");
  useEffect(()=>{
    //since function is not working here is the hardcoded version
    getAllUsers(user).then(users=>{
      const imagedUserList = users.map(async (user)=>{
        const imageData = await getImage(users.profileImage,user);
        return {...user, imageData};
      })

      Promise.all(imagedUserList).then(
        (imagedUsers)=>{
          setFilteredUsers(imagedUsers)
          console.log("images with users")
          console.log(imagedUsers);
          const registeredUser = imagedUsers.find((claimedUser)=>claimedUser.id==user.userId)
          console.log(registeredUser)
          console.log(user.userId)
          if(registeredUser){setUser({...user,profileImageName: registeredUser.profileImageName,
             imageData: registeredUser.imageData, bio:registeredUser.bio})
             console.log(user)}
          return imagedUsers;
        }
      )
    });
  }
    ,[]
  )
  const handleChangeEmail = (userItem) => {
    console.log(`Change email for user with ID: ${userItem}`);
    setShowChangeEmailBox(userItem);
  };
  const handleChangeEmailForm = ()=>{
    if(newEmail !== ""){
      changeEmail(showChangeEmailBox.id,newEmail,user)
      setShowChangeEmailBox(null);
    }
  }

  const handleBanUser = (userItem) => {
    if(!userItem.isBanned){
      banUser(userItem.id,user).then(result => console.log(result));
    }
    else{
      unBanUser(userItem.id,user);
    }
  };

  const handleReports = () => {
    console.log('Navigate to reports endpoint'); // i will change when i will impllement 
    navigate("/admin/Reports")
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
        {filteredUsers.map((userItem) => (
          <div
            key={userItem.id}
            className="flex flex-row items-center justify-between py-2 px-4 mb-2 bg-gray-300 rounded-md"
          >
            <div className="flex flex-col">
              {userItem.imageData && !(userItem.profileImageName === "OUR_DEFAULT_IMAGE_PATH")?<img
                src={userItem.imageUrl} 
                alt="Profile picture"
                className="w-10 h-10 rounded-full mr-4"
              />: <div className='w-10 h-10 bg-slate-500 rounded-full mr-4'></div>}
              <span className="text-lg font-medium">{userItem.name}</span>
              <span className="text-sm text-gray-500">{userItem.email}</span>
            </div>
            <div className="flex flex-row space-x-2">
              <button
                onClick={() => handleChangeEmail(userItem)}
                className="rounded-md bg-red-500 text-white px-4 py-1 hover:bg-red-600"
              >
                Change Email
              </button>
              {}
              <button
                onClick={() => handleBanUser(userItem)}
                className="rounded-md bg-blue-500 text-white px-4 py-1 hover:bg-blue-600"
              >
                {userItem.isBanned ? 'Unban' : 'Ban'}
              </button>
            </div>
          </div>
        ))}
      </div>
      {showChangeEmailBox && (
        <div className="fixed  bottom-0 left-0 w-full h-full bg-gray-800 bg-opacity-50 flex justify-center items-center zIndex = 200 " style={{ zIndex: 110 }}>
         <div className="bg-white p-4 rounded-md">
            <div className='flex justify-end'>
                  <button onClick={()=>setShowChangeEmailBox(null)}>
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                  </button>
              </div>
            <p>New Email:</p>
            <input
              value={newEmail}
              onChange={(e) => setNewEmail(e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-md"
              placeholder="new email"
            />
            <button
              onClick={handleChangeEmailForm}
              className="mt-2 px-4 py-2 bg-blue-700 text-white rounded-md"
            >
              Change Email
            </button>
         </div>
        </div>
      )}
    </div>
  );
}

