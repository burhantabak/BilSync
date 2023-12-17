import { Navigate } from "react-router-dom";
import { useData } from "../Context/DataContext";

export default function ProtectedRoute({children, adminOnly, userOnly}){
    const {user} = useData();
    console.log(user);


    if(!user){
        return <Navigate to="login"/>;
    }

    if(adminOnly && !user.isAdmin){
        return <Navigate to="/mainPage"/>;
    }
    else if(user.isAdmin && userOnly){
        return <Navigate to="/admin/adminPanel"/>
    }

    return children;
}