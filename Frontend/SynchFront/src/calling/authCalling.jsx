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
export function forgotPasswordRequest(userEmail){
    const apiUrl = "http://localhost:8080/auth/forgotPassword";

  const requestOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'text/plain',
    },
    body: userEmail,
  };

  return fetch(apiUrl, requestOptions)
    .then(response => { 
        console.log(response);
        return response.status})  // Return the status code
    .catch(error => {
      console.error('Error:', error);
      throw error;  // Re-throw the error for further handling if needed
    });
}
export function resetPassword(newPasswordClaim){
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    var raw = JSON.stringify({
    "token": newPasswordClaim.token,
    "newPassword": newPasswordClaim.newPassword,
    });
    console.log(raw)
    var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: raw,
    };

    return fetch("http://localhost:8080/auth/resetPassword", requestOptions)
    .then(response => {
        return response;
    })
    .then((result) => {
        console.log(result);
    })
    .catch(error => console.log('error state: ', error));
}