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
export function getChatById(chatId, user){
    const sentUrl = `http://localhost:8080/chat/${chatId}`;
    console.log("getChatById called")
  return fetch(sentUrl, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user.token}`
    },
  })
  .then(response => {
    console.log("finished fetch");
    console.log(response);

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
export function sendMessage(chatId,messageBody, user,refreshChat){
    var raw = JSON.stringify({
    "body": messageBody
    });
    console.log(raw)
    var requestOptions = {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${user.token}`
      },
    body: raw,
    };

    return fetch(`http://localhost:8080/chat/${chatId}/sendMessage`, requestOptions)
    .then(response => {
        return response;
    })
    .then((result) => {
        refreshChat(); // Update the commentList in your component state

    })
    .catch(error => console.log('error state: ', error));
}