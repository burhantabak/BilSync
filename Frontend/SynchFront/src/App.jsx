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
import ReportsPage from "./Components/Reports.jsx"
import EditProfilePage from "./statics/EditProfilePage.jsx"
import TransactionPage from "./Components/Transaction.jsx"
import MyTransactionPage from "./statics/MyTransactions.jsx"

function App() {
  const handleBuyNow = () => {
    // Implement your buy now logic here
    console.log("Buy now logic");
  };
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
              <Route path="/mainPage" element={<ProtectedRoute userOnly={true} adminOnly={false}>
                <MainPage/>
                </ProtectedRoute>}/>

              <Route path="/commentScreen" element={<CommentScreen/>}/>
              <Route path="/editAccount" element={<ProtectedRoute userOnly={true} adminOnly={false}><EditAccount/></ProtectedRoute>}/>
              <Route path="/editProfile" element={<ProtectedRoute userOnly={true} adminOnly={false}><EditProfilePage/></ProtectedRoute>}/>
              <Route path="/profilePage" element={<ProtectedRoute userOnly={true} adminOnly={false}><EditProfile/></ProtectedRoute>}/>
              <Route path="/createPost" element = {<ProtectedRoute userOnly={true} adminOnly={false}><CreatePost/></ProtectedRoute>}/>
              <Route path="/transaction" element = {<ProtectedRoute userOnly={true} adminOnly={false}><TransactionPage/></ProtectedRoute>}/>
              <Route path="/transaction/:postId" element={<ProtectedRoute userOnly={true} adminOnly={false}><TransactionPage handleBuyNow={handleBuyNow} /></ProtectedRoute>} />
              <Route path="/resetPassword" element = {<ResetPassword/>}/>
              <Route path="/changePassword" element = {<ForgetPassword/>}/>
              <Route path="/admin/addAccount" element={<ProtectedRoute adminOnly={true} userOnly={false}><AddAccount/></ProtectedRoute>}/>
              <Route path="/admin/Reports" element = {<ProtectedRoute adminOnly={true} userOnly={false}><ReportsPage/></ProtectedRoute>}/>
              <Route path="/admin/adminPanel" element={<ProtectedRoute adminOnly={true} userOnly={false}><AdminPanel/></ProtectedRoute>}/>
              <Route path="/myTransactions" element={<ProtectedRoute userOnly={true} adminOnly={false}><MyTransactionPage/></ProtectedRoute>}/>

              <Route path="/aboutUs" element = {<AboutUs/>}/>
              <Route path="/*" element={<Navigate to="/login" replace={true} />}/>
            </Routes>
          </BrowserRouter>
        </DataProvider>
      </div>
    </>
  )
}

export default App
