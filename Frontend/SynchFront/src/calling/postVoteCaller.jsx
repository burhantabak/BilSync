const BASE_URL = 'http://localhost:8080'; // Adjust this based on your backend URL

const postVoteCaller = (postId,type, user) => {


    return fetch(`${BASE_URL}/post/vote/${postId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'plain/text',
        'Authorization': `Bearer ${user.token}`
      },  
      body: type,
    }).then((response) => {
      console.log(response);
    
      if (!response.ok) {
        throw new Error('Comment vote failed. Please try again.');
      }
    
      // Check if the response has a body before trying to parse it
      const contentType = response.headers.get('content-type');
      if (contentType && contentType.includes('plain/text')) {
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

  export default postVoteCaller;