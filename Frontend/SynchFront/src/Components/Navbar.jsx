import { Menu, Transition } from '@headlessui/react';
import { ChevronDownIcon } from '@heroicons/react/20/solid';
import React, { Fragment } from 'react'
import { useData } from '../Context/DataContext';
import { useNavigate } from 'react-router-dom';


export default function Navbar() {
  const itemList = ["Edit Profile","Edit Account","Sign Out"]
  const {user} = useData();
  const { logout } = useData();
  

  const isLoggedIn = user != null;
  const navigate = useNavigate();

  const handleItemClick = (item, event) => {
    console.log(item);
    event.preventDefault();
    if(item  == "Sign Out"){
      logout();
      navigate("/login");
    }
    else if(item == "Edit Profile"){
      navigate("/editProfile");
    }
    else if(item == "Edit Account"){
      navigate("/editAccount");
    }
  };

  return (
    <div className='flex bg-sky-600 justify-between px-4'>
        <h1 className='text-3xl text-white font-bold font-mono py-3'>BilSync</h1>
        <div>
            <ul className='flex gap-5'>
                <li className='text-white text-xl mr-4 hover:bg-gray-400 py-4 px-1'><a href="/aboutUs">About Us</a></li>
                {isLoggedIn||<li className='text-white py-4 text-xl mr-4 hover:bg-gray-400'><a href="/login">Login</a></li>}
                {isLoggedIn&&<li className='h-full text-white text-xl mr-4 py-4 hover:bg-gray-400'><DropdownProfile itemList={itemList} handleItemClick={handleItemClick}/></li>}
            </ul>
        </div>
    </div>
  )
}
function Profile(){
  const {user} = useData();
    return(
      <div className='flex items-center'>
          <div className='mr-2 rounded-full h-8 w-8 bg-gray-500'></div>
          <h2 className='inline-block'> {user.name}</h2>
          <ChevronDownIcon className='w-5 h-5'/>
      </div>
    );
}
function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}
function DropdownProfile({itemList, handleItemClick}){
    const {logout} = useData();
    const navigate = useNavigate();

    return(
      <Menu as={"div"} zIndex={100}>
        <Menu.Button>
          <Profile/>
        </Menu.Button>
        <Transition
        as={Fragment}
        enter="transition ease-out duration-100"
        enterFrom="transform opacity-0 scale-95"
        enterTo="transform opacity-100 scale-100"
        leave="transition ease-in duration-75"
        leaveFrom="transform opacity-100 scale-100"
        leaveTo="transform opacity-0 scale-95"
        >
           <Menu.Items className="absolute right-8 z-10 mt-2 w-[12%] origin-top-right bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none ">
          {itemList.map((item, index) => {
            return (
              <div key={index} className="py-1">
                <Menu.Item>
                  {({ active }) => (
                    <a
                      href="#"
                      onClick={(event) => handleItemClick(item, event)}
                      className={classNames(
                        active ? 'w-full bg-gray-100 text-gray-900' : 'text-gray-700',
                        'block px-4 py-2 text-sm text-center'
                      )}
                    >
                      {item}
                    </a>
                  )}
                </Menu.Item>
              </div>
            );
          })}
        </Menu.Items>
      </Transition>
    </Menu>
  );
}