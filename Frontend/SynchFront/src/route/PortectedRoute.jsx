import { Navigate } from "react-router-dom";
import { useData } from "../Context/DataContext";

export default function ProtectedRoute({children}){
    const {user} = useData();
    console.log(user);
    if(!user){
        return <Navigate to="login"/>;
    }
    return children;
}