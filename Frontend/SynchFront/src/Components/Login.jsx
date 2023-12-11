import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useData } from '../Context/DataContext';


export default function LoginForm({handleLogin}) {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const { login , error} = useData(); // Access error from the data context

  const nav = useNavigate();
  const handleAuth = async (event)=> {
    event.preventDefault();
      await login(userName, password);
      handleLogin();
      console.log("called");
      console.log(userName);
      console.log(password);
      nav("/mainPage");
}




  return (
    <div className=' p-5 py-3 mx-auto mt-10 w-1/3 md:w-2/3 lg:w-2/3 bg-white rounded-xl flex justify-evenly divide-x-4'>
        <div className='flex-1'>
            <h1 className='text-8xl'>Welcome to BilSync.</h1>
        </div>
        <div className='flex-1 p-10'>
        <h1 className='text-gray-900 text-3xl font-bold'> Login to your account</h1>
        {error && <p className="text-red-500">{error}</p>}
        <form className="space-y-4 md:space-y-6" action="#">
                  <div>
                      <label htmlFor="email" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your email</label>
                      <input type="email" name="email" id="email" onChange={(event)=>setUserName(event.target.value)}
                       className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:outline focus:outline-2 focus:border-sky-500 block w-full p-2.5" placeholder="username" required=""/>
                  </div>
                  <div>
                      <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                      <input type="password" name="password" onChange={(event)=>setPassword(event.target.value)}
                       id="password" placeholder="••••••••" className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-sky-300 focus:border-primary-600 block w-full p-2.5" required=""/>
                  </div>
                  <div className="flex items-center justify-between">
                      <div className="flex items-start">
                          <div className="flex items-center h-5">
                            <input id="remember" aria-describedby="remember" type="checkbox" className="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-primary-600 dark:ring-offset-gray-800" required=""/>
                          </div>
                          <div className="ml-3 text-sm">
                            <label htmlFor="remember" className="text-gray-500 dark:text-gray-500">Remember me</label>
                          </div>
                      </div>
                      <a href="#" className="text-sm font-medium text-primary-600 hover:underline dark:text-primary-500">Forgot password?</a>
                  </div>
                  <button onClick={handleAuth} type="submit" className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">Sign in</button>

              </form>
    </div>
    </div>
  )
}
