import { Navigate } from "react-router-dom";
import { useData } from "../Context/DataContext";

export default function ProtectedRoute({children, adminOnly = false}){
    const {user} = useData();
    console.log(user);


    if(!user){
        return <Navigate to="login"/>;
    }

    if(adminOnly && user.name !== "admin"){
        return <Navigate to="/mainPage"/>;
    }

    return children;
}