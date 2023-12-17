const BASE_URL = 'http://localhost:8080'; // Adjust this based on your backend URL

const reportPostCalling = (description,user, reportedEntityId) => {
  var raw = JSON.stringify({
    "description": description,
    "reportedEntityId": reportedEntityId,
    });
    console.log(raw);

    return fetch(`${BASE_URL}/admin/reports/createPostReport`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${user.token}`
      },  
      body: raw
    }).then((response) => {
      console.log(response);
    
      if (!response.ok) {
        throw new Error('Post report failed. Please try again.');
      }
    
      // Check if the response has a body before trying to parse it
      const contentType = response.headers.get('content-type');
      if (contentType && contentType.includes('application/json')) {
        return response.json();
      } else {
        // If the response doesn't have a JSON body, return nothing bro
        return null;
      }
    }).catch((error) => {
      console.error('Error making API call:', error);
      throw new Error('An error occurred. Please try again later.');
    });
  }

  export default reportPostCalling;
export function getAllReports(user){
  const apiUrl = "http://localhost:8080/admin/reports/all"
  return fetch(apiUrl, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user.token}`
    },
  })
  .then(response => {
    console.log(response)
    if (response.status === 200) {
      return response.json();
    }
     else {
      return response.status;
    }
  })
  .catch(err => {
    console.log("Error:", err);
    throw err;
  });
}