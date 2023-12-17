
const BASE_URL = 'http://localhost:8080'; // Adjust this based on your backend URL



const approveTransaction = (user, id) => {

  return fetch(`${BASE_URL}/transactions/update/giverApproved/${id}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user.token}`
    },  
  }).then((response) => {
    console.log(response);
    if (response.ok) {
      console.log(response);

      return response.json();
    } else {
      throw new Error('Transactions could not be loaded . Please try again.');
    }
  }).catch((error) => {
    console.error('Error making API call:', error);
    console.log(response);
    console.log(response);

    throw new Error('An error occurred. Please try again later.');
  });
};

export default approveTransaction;