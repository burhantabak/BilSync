// transactionCallin.jsx

const BASE_URL = 'http://localhost:8080'; // Adjust this based on your backend URL



const createTransaction = (postId,user) => {

  return fetch(`${BASE_URL}/transactions/create/${postId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user.token}`
    },
  }).then((response) => {
    console.log(response);
    if (response.ok) {
      return response.json();
    } else {
      throw new Error('Transaction failed. Please try again.');
    }
  }).catch((error) => {
    console.error('Error making API call:', error);
    throw new Error('An error occurred. Please try again later.');
  });
};

export default createTransaction;