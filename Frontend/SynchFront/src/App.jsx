import { useState } from "react"
import CommentScreen from "./Components/CommentScreen"
import LoginForm from "./Components/Login"
import MainPage from "./Components/MainPage"
import Navbar from "./Components/Navbar"
import { BrowserRouter, Routes, Route } from "react-router-dom"
import { DataProvider } from "./Context/DataContext"
function App() {
  const [isLoggedIn,setLoggedIn] = useState(false);
  return (
    <>
      <div>
        <DataProvider>
          <BrowserRouter>
            <Navbar isLoggedIn={isLoggedIn}/>
            <Routes>
              <Route path="/login" element={<LoginForm handleLogin={()=>{setLoggedIn(true)}}/>}/>
              <Route path="/mainPage" element={<MainPage/>}/>
              <Route path="/commentScreen" element={<CommentScreen/>}/>
              <Route path="/*" element={<LoginForm handleLogin={()=>{setLoggedIn(true)}}/>}/>
            </Routes>
          </BrowserRouter>
        </DataProvider>
      </div>
    </>
  )
}

export default App
