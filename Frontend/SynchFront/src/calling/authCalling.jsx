const backendBaseUrl = "http://localhost:8080";
const login = "/auth/login";
export async function authUser(userName, password){
    try 
    {
        const loginUrl = backendBaseUrl + login;
        console.log("entered fetch method")
        const response = await fetch(loginUrl,{
        method:'POST',
        headers:{
            'Content-Type':'application/json',
        },
        body: JSON.stringify({
            email:userName,
            password:password,
        })
        });
        console.log("finished fetch")
        console.log(response.status);
        if(response.status === 200){
            return await response.json();
        }
        else{
            throw new Error(`Failed to authenticate. Status: ${response.status}`);
        }
    }catch(err){
        console.log("Error:",err);
            throw err;

    }
    return null;
} 