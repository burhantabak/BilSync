import React from 'react'

export default function Navbar() {
  return (
    <div className='flex bg-sky-600 justify-between py-4 px-4'>
        <h1 className='text-3xl text-white font-bold font-mono'>BilSync</h1>
        <div>
            <ul className='flex gap-5'>
                <li className='text-white text-xl mr-4 hover:text-neutral-400'><a href="#">About Us</a></li>
                <li className='text-white text-xl mr-4 hover:text-neutral-400'><a href="#">Login</a></li>
            </ul>
        </div>
    </div>
  )
}
