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
import AboutUs from "./Components/AboutUs.jsx"
import EditAccount from "./Components/EditAccount.jsx"
import CreatePost from "./Components/CreatePostPage.jsx"
import ResetPassword from "./Components/ResetPassword.jsx"
import ForgetPassword from "./Components/ForgetPassword.jsx"
import ChangePassword from "./Components/ChangePassword.jsx"
import EditProfile from "./Components/EditProfile.jsx"
import AdminPanel from "./Components/AdminPanel.jsx"
import AddAccount from "./statics/AddAccount.jsx"

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
              <Route path="/forgetPassword" element = {<ForgetPassword/>}/>
              <Route path="/mainPage" element={<ProtectedRoute>
                <MainPage/>
                </ProtectedRoute>}/>

              <Route path="/commentScreen" element={<CommentScreen/>}/>
              <Route path="/admin/adminPanel" element={<AdminPanel/>}/>
              <Route path="/editAccount" element={<ProtectedRoute><EditAccount/></ProtectedRoute>}/>{/**Kardeşim elinden öper */}
              <Route path="/editProfile" element={<ProtectedRoute><EditProfile/></ProtectedRoute>}/>{/**Kardeşim elinden öper */}
              <Route path="/aboutUs" element = {<AboutUs/>}/>
              <Route path="/createPost" element = {<ProtectedRoute><CreatePost/></ProtectedRoute>}/>
              <Route path="/resetPassword" element = {<ResetPassword/>}/>
              <Route path="/changePassword" element = {<ForgetPassword/>}/>
              <Route path="/admin/addAccount" element = {<AddAccount/>}/>
              <Route path="/*" element={<Navigate to="/login" replace={true} />}/>
            </Routes>
          </BrowserRouter>
        </DataProvider>
      </div>
    </>
  )
}

export default App
