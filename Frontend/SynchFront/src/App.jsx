import { useState } from "react"
import CommentScreen from "./Components/CommentScreen"
import LoginForm from "./Components/Login"
import MainPage from "./Components/MainPage"
import Navbar from "./Components/Navbar"
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom"
import { DataProvider } from "./Context/DataContext"
import ProtectedRoute from "./route/PortectedRoute"
import { SearchBar } from "./statics/SearchBar.jsx"
import { SearchResultList } from "./statics/SearchResultList.jsx"




function App() {
  const [results, setResults] = useState([]);
  const [isLoggedIn,setLoggedIn] = useState(false);
  return (
    <>
      <div>
        <DataProvider>
          <BrowserRouter>
            <Navbar isLoggedIn={isLoggedIn}/>
            <Routes>
              <Route path="/login" element={<LoginForm handleLogin={()=>{setLoggedIn(true)}}/>}/>
              <Route path="/mainPage" element={<ProtectedRoute>
                <MainPage/>
                </ProtectedRoute>}/>
              <Route path="/commentScreen" element={<CommentScreen/>}/>
              <Route path="/*" element={<Navigate to="/login" replace={true} />}/>
            </Routes>
          </BrowserRouter>
        </DataProvider>
      </div>
    </>
  )
}

export default App
