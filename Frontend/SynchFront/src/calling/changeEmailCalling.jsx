export function changeEmail(userId,newEmail,user){
    const apiUrl = `http://localhost:8080/admin/users/${userId}/change-email`
    const requestOptions = {
        method: 'PUT',
        headers: {
          'Content-Type': 'text/plain',
          'Authorization': `Bearer ${user.token}`
        },
        body: newEmail,
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