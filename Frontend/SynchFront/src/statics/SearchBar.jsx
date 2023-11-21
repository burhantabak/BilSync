import Dropdown from "./DropDown";
import { ChevronDownIcon } from '@heroicons/react/20/solid'
import { Fragment, useEffect } from 'react'
import { Menu, Transition } from '@headlessui/react'
import { useData } from "../Context/DataContext";
import { useState } from "react";

import { useSearchResults } from './useSearchResults'; // Import the custom hook



export const  SearchBar = () => {
  
    const [input, setInput] = useState("");
    const { postList, chatList } = useData();
     const { updateResults } = useSearchResults(); // Call the custom hook

    useEffect(() => {
        // Lets filter data 
       
        const filtered = postList.filter((post) => {
          return post.title.toLowerCase().includes(input.toLowerCase());
        });

        const filteredChat = chatList.filter((chat) => {
          return chat.userName.toLowerCase().includes(input.toLowerCase());
        });

        const combinedResults = [...filtered, ...filteredChat];
        updateResults(combinedResults);

    }, []);
    const handleChange = (value) =>{
        setInput(value);
    };
    
  
  
  
  
  
    return (
    
<form className='mb-2'>
    <div className="flex items-center gap-1">
        <label htmlFor="search-dropdown" className="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Your Email</label>
        <Dropdown/>
        <div className="relative w-3/4">
            <input type="search" id="search-dropdown" 
                className="block p-2.5 w-full z-20 text-sm text-gray-900 bg-gray-50 rounded-lg border-l-2 border border-gray-300 focus:ring-blue-500 focus:border-blue-500 focus: outline-none" 
                placeholder="Search Mockups, Logos, Design Templates..."
                value = {input}
                onChange= {(e) => handleChange(e.target.value)} required/>
            <button type="submit" 
                className="absolute top-0 right-0 p-2.5 text-sm font-medium h-full text-white bg-blue-700 rounded-r-lg border border-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300">
                    <svg className="w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                     <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
                </svg>
                <span className="sr-only">Search</span>
            </button>
        </div>
        <button className=" text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
            <div className="flex">
            <h2>Create</h2>
            <ChevronDownIcon className="-mr-1 h-5 w-5 text-white" aria-hidden="true" />
            </div>
            </button>
    </div>
</form>

  )
}

function CreateButton(){
    return(
        <Menu as={"div"}>
            <div>
                <Menu.Button className="inline-flex w-full justify-center gap-x-1.5 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                Create
                <ChevronDownIcon className="-mr-1 h-5 w-5 text-gray-400" aria-hidden="true" />
                </Menu.Button>
            </div>
        </Menu>
    );
}
