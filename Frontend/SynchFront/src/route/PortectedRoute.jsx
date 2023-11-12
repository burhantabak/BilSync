import { Navigate } from "react-router-dom";
import { useAuth } from "../../hooks/useAuth";

export default function ProtectedRoute({children}){
    const context = useAuth();
    console.log(context);
    if(!context.user){
        return <Navigate to="login"/>;
    }
    return children;
}