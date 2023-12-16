const BASE_URL = 'http://localhost:8080'; // Adjust this based on your backend URL

const commentReportCall = (description,user, reportedEntityId) => {
  var raw = JSON.stringify({
    "description": description,
    "reportedEntityId": reportedEntityId,
    });

    return fetch(`${BASE_URL}/admin/reports/createCommentReport`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${user.token}`
      },  
      body: raw
    }).then((response) => {
      console.log(response);
    
      if (!response.ok) {
        throw new Error('Comment report failed. Please try again.');
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

  export default commentReportCall;