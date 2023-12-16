export function getChats(user){
    const sentUrl = "http://localhost:8080/chat/chats";

  return fetch(sentUrl, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user.token}`
    },
  })
  .then(response => {
    console.log("finished fetch");
    console.log(response.status);

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