import LoginForm from "./Components/Login"
import MainPage from "./Components/MainPage"
import Navbar from "./Components/Navbar"
import { BrowserRouter, Routes, Route } from "react-router-dom"
function App() {

  return (
    <>
      <div>
        <BrowserRouter>
          <Navbar/>
          <Routes>
            <Route path="/login" element={<LoginForm/>}/>
            <Route path="/mainPage" element={<MainPage/>}/>
          </Routes>
        </BrowserRouter>
      </div>
    </>
  )
}

export default App
