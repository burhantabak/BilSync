import React from 'react'

export default function CommentScreen() {
  return (
    <div>
    <script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
    <div x-data="{ open1: false, open2: false }">
    <div class="h-screen bg-gray-100 flex justify-center items-center">
            <div class="flex items-center justify-center space-x-2">
            <div class="block">
                <div class="flex justify-center items-center space-x-2">
                    <div class="bg-gray-100 w-auto rounded-xl px-2 pb-2">
                    <div class="font-medium">
                        <a href="#" class="hover:underline text-sm">
                        <small>Ganendra</small>
                        </a>
                    </div>
                    <div class="text-xs">
                        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Expedita, maiores!
                    </div>
                    </div>
                    <div class="self-stretch flex justify-center items-center transform transition-opacity duration-200 opacity-0 hover:opacity-100">
                        <a href="#" class="">
                            <div class="text-xs cursor-pointer flex h-6 w-6 transform transition-colors duration-200 hover:bg-gray-100 rounded-full items-center justify-center">
                            <svg class="w-4 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"></path></svg>
                            </div>

                        </a>
                    </div>
                </div>
                <div class="flex justify-start items-center text-xs w-full">
                <div class="font-semibold text-gray-700 px-2 flex items-center justify-center space-x-1">
                    <a href="#" class="hover:underline">
                    <small>Like</small>
                    </a>
                <small class="self-center">.</small>
                    <a href="#" class="hover:underline">
                    <small>Reply</small>
                    </a>
                <small class="self-center">.</small>
                    <a href="#" class="hover:underline">
                    <small>15 hour</small>
                    </a>
                </div>
                </div>
                <div class="flex items-center space-x-2 space-y-2">
                <div class="group relative flex flex-shrink-0 self-start cursor-pointer pt-2">
            <img 
                x-on:mouseover="open2 = true" x-on:mouseleave="open2 = false"
            src="https://images.unsplash.com/photo-1610156830615-2eb9732de349?ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDExfHJuU0tESHd3WVVrfHxlbnwwfHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60" alt="" class="h-8 w-8 object-fill rounded-full"/>
            </div>

                <div class="flex items-center justify-center space-x-2">
                    <div class="block">
                    <div class="bg-gray-100 w-auto rounded-xl px-2 pb-2">
                        <div class="font-medium">
                        <a href="#" class="hover:underline text-sm">
                            <small>Hasan Muhammad</small>
                        </a>
                        </div>
                        <div class="text-xs">
                        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Expedita, maiores!
                        </div>
                    </div>
                    <div class="flex justify-start items-center text-xs w-full">
                        <div class="font-semibold text-gray-700 px-2 flex items-center justify-center space-x-1">
                        <a href="#" class="hover:underline">
                            <small>Like</small>
                        </a>
                        <small class="self-center">.</small>
                        <a href="#" class="hover:underline">
                            <small>Reply</small>
                        </a>
                        <small class="self-center">.</small>
                        <a href="#" class="hover:underline">
                            <small>15 hour</small>
                        </a>
                        </div>
                    </div>
                    </div>

                </div>
                
                <div class="self-stretch flex justify-center items-center transform transition-opacity duration-200 opacity-0 translate -translate-y-2 hover:opacity-100">
                    <a href="#" class="">
                    <div class="text-xs cursor-pointer flex h-6 w-6 transform transition-colors duration-200 hover:bg-gray-100 rounded-full items-center justify-center">
                        <svg class="w-4 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"></path></svg>
                    </div>

                    </a>
                </div>

                </div>

                
            </div>
            </div>

        </div>

        <div class="flex items-center space-x-2">
            <div class="flex flex-shrink-0 self-start cursor-pointer">
            <img src="https://images.unsplash.com/photo-1551122089-4e3e72477432?ixid=MXwxMjA3fDB8MHxzZWFyY2h8M3x8cnVieXxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60" alt="" class="h-8 w-8 object-fill rounded-full"/>
            </div>

            <div class="flex items-center justify-center space-x-2">
            <div class="block">
                <div class="bg-gray-100 w-auto rounded-xl px-2 pb-2">
                <div class="font-medium">
                    <a href="#" class="hover:underline text-sm">
                    <small>Nirmala</small>
                    </a>
                </div>
                <div class="text-xs">
                    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Expedita, maiores!
                </div>
                </div>
                <div class="flex justify-start items-center text-xs w-full">
                <div class="font-semibold text-gray-700 px-2 flex items-center justify-center space-x-1">
                    <a href="#" class="hover:underline">
                    <small>Like</small>
                    </a>
                <small class="self-center">.</small>
                    <a href="#" class="hover:underline">
                    <small>Reply</small>
                    </a>
                <small class="self-center">.</small>
                    <a href="#" class="hover:underline">
                    <small>15 hour</small>
                    </a>
                </div>
                </div>
                
            </div>
            </div>
            
            <div class="self-stretch flex justify-center items-center transform transition-opacity duration-200 opacity-0 translate -translate-y-2 hover:opacity-100">
            <a href="#" class="">
                <div class="text-xs cursor-pointer flex h-6 w-6 transform transition-colors duration-200 hover:bg-gray-100 rounded-full items-center justify-center">
                <svg class="w-4 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"></path></svg>
                </div>

            </a>
            </div>

        </div>

        <div class="flex items-center space-x-2">
            <div class="flex flex-shrink-0 self-start cursor-pointer">
            <img src="https://images.unsplash.com/photo-1609349744982-0de6526d978b?ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDU5fHRvd0paRnNrcEdnfHxlbnwwfHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60" alt="" class="h-8 w-8 object-cover rounded-full"/>
            </div>

            <div class="flex items-center justify-center space-x-2">
            <div class="block">
                <div class="bg-gray-100 w-auto rounded-xl px-2 pb-2">
                <div class="font-medium">
                    <a href="#" class="hover:underline text-sm">
                    <small>Arkadewi</small>
                    </a>
                </div>
                <div class="text-xs">
                    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Expedita, maiores!
                </div>
                </div>
                <div class="flex justify-start items-center text-xs w-full">
                <div class="font-semibold text-gray-700 px-2 flex items-center justify-center space-x-1">
                    <a href="#" class="hover:underline">
                    <small>Like</small>
                    </a>
                <small class="self-center">.</small>
                    <a href="#" class="hover:underline">
                    <small>Reply</small>
                    </a>
                <small class="self-center">.</small>
                    <a href="#" class="hover:underline">
                    <small>15 hour</small>
                    </a>
                </div>
                </div>
                
            </div>
            </div>
            
            <div class="self-stretch flex justify-center items-center transform transition-opacity duration-200 opacity-0 translate -translate-y-2 hover:opacity-100">
            <a href="#" class="">
                <div class="text-xs cursor-pointer flex h-6 w-6 transform transition-colors duration-200 hover:bg-gray-100 rounded-full items-center justify-center">
                <svg class="w-4 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"></path></svg>
                </div>

            </a>
            </div>
        </div>
        
        </div>
        
    </div>
  )
}
function CommentItem(){
    return(
        <div class="flex items-center justify-center space-x-2">
                    <div class="block">
                    <div class="bg-gray-100 w-auto rounded-xl px-2 pb-2">
                        <div class="font-medium">
                        <a href="#" class="hover:underline text-sm">
                            <small>Hasan Muhammad</small>
                        </a>
                        </div>
                        <div class="text-xs">
                        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Expedita, maiores!
                        </div>
                    </div>
                    <div class="flex justify-start items-center text-xs w-full">
                        <div class="font-semibold text-gray-700 px-2 flex items-center justify-center space-x-1">
                        <a href="#" class="hover:underline">
                            <small>Like</small>
                        </a>
                        <small class="self-center">.</small>
                        <a href="#" class="hover:underline">
                            <small>Reply</small>
                        </a>
                        <small class="self-center">.</small>
                        <a href="#" class="hover:underline">
                            <small>15 hour</small>
                        </a>
                        </div>
                    </div>
                    </div>

                </div>
    )
}