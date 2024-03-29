import Dropdown from "./DropDown";
import { ChevronDownIcon } from '@heroicons/react/20/solid'
import { Fragment, useEffect } from 'react'
import { Menu, Transition } from '@headlessui/react'
import { useData } from "../Context/DataContext";
import { useState } from "react";

import { useSearchResults } from './useSearchResults'; // Import the custom hook
import { useNavigate } from "react-router-dom";

{/**kardeşim elinden öper */}

export const  SearchBar = ({setResult, onCategoryChange}) => {
  
    const [input, setInput] = useState("");
    const { postList, chatList } = useData();
    const { updateResults } = useSearchResults(); // Call the custom hook
    const nav = useNavigate();
   

    const filterResults = () => {
        // Lets filter data 
       
        const filtered = postList.filter((post) => {
          return post.title.toLowerCase().includes(input.toLowerCase());
        });

        // const filteredChat = chatList.filter((chat) => {
        //   return chat.userName.toLowerCase().includes(input.toLowerCase());
        // });

        // const combinedResults = [...filtered, ...filteredChat];
        const combinedResults = [filtered];
        
        updateResults(combinedResults);
        

    };
    useEffect(() => { 
        filterResults();
    }, []);


    const handleChange = (value) =>{
        const lowerCaseValue = value.toLowerCase(); // make insensitive 
        setResult(lowerCaseValue);
    };

    const handleCategoryChange = (category) =>{
        onCategoryChange(category);
        };

    const handleSubmit = (e) =>{
        e.preventDefault();
        filterResults();
    };
    
  
  
  
  
  
    return (
    <div className="flex w-full gap-2  space justify-around">
        <form className='mb-2 flex-grow' onSubmit={handleSubmit}>
            <div className="flex items-center gap-1">
                <label htmlFor="search-dropdown" className="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Your Email</label>
                <Dropdown onCategoryChange={handleCategoryChange}/>
                <div className="relative w-full">
                    <input type="search" id="search-dropdown" 
                        className="block p-2.5 w-full z-20 text-sm text-gray-900 bg-gray-50 rounded-lg border-l-2 border border-gray-300 focus:ring-blue-500 focus:border-blue-500 focus: outline-none" 
                        placeholder="Search Mockups, Logos, Design Templates..."
                        onChange= {(e) => handleChange(e.target.value)} required/>
                    <button type="submit" 
                        className="absolute top-0 right-0 p-2.5 text-sm font-medium h-full text-white bg-blue-700 rounded-r-lg border border-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300">
                            <svg className="w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                            <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
                        </svg>
                        <span className="sr-only">Search</span>
                    </button>
                </div>
            </div>
        </form>
        <button onClick={()=>{nav("/createPost")}}
        className=" text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 text-center mb-2">
            Create Post
        </button>
    </div>

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
