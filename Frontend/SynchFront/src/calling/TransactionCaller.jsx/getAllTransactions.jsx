
const BASE_URL = 'http://localhost:8080'; 

const retrieveTransactions = (user) => {

  return fetch(`${BASE_URL}/transactions/by-user`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user.token}`
    },  
  }).then((response) => {
    console.log(response);
    if (response.ok) {
      console.log(response);

      return response.json();
      
    }
    else if (response.status === 400) {
      console.log('No transactions yet.');
      return []; } // Return an empty array for a 400 status
    else {
      throw new Error('Transactions could not be loaded . Please try again.');
    }
  }).catch((error) => {
    console.error('Error making API call:', error);
    console.log(response);
    console.log(response);

    throw new Error('An error occurred. Please try again later.');
  });
};

export default retrieveTransactions;