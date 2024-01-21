export default async function getAllUsers(user) {
    try {

      if (!user || !user.token) {
        throw new Error('User or user token is missing.');
      }
      var myHeaders = new Headers();
      myHeaders.append("Authorization", `Bearer ${user.token}`);
    
      var requestOptions = {
        method: 'GET',
        headers: myHeaders,
      };
    
      const response = await fetch("http://localhost:8080/user/usersSecure", requestOptions);
    
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
    
      const result = await response.json();
      console.log(result); // You can remove this line or modify it as needed
      return result; // Return the result to the calling code
    } catch (error) {
      console.error('Error:', error);
      throw error; // Rethrow the error for the calling code to handle
    }
  }

  