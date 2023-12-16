const BASE_URL = 'http://localhost:8080'; // Adjust this based on your backend URL

const reportPostCalling = (description,user, reportedEntityId) => {
  var raw = JSON.stringify({
    "description": description,
    "reportedEntityId": reportedEntityId,
    });

    return fetch(`${BASE_URL}/admin/reports/createPostReport`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${user.token}`
      },  
      body:raw
    }).then((response) => {
      console.log(response);
      if (response.ok) {
        console.log(response);
  
        return response.json();
      } else {
        throw new Error('Post report failed. Please try again.');
      }
    }).catch((error) => {
      console.error('Error making API call:', error);
      console.log(response);
      console.log(response);
  
      throw new Error('An error occurred. Please try again later.');
    });
  };
  
  export default reportPostCalling;