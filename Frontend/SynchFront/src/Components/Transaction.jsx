import React, { useState } from 'react';

export default function TransactionPage() {
  const [cardNumber, setCardNumber] = useState('');
  const [cardPassword, setCardPassword] = useState('');
  const [nameSurname, setNameSurname] = useState('');
  const [ccv, setCcv] = useState('');
  const [isTransactionComplete, setTransactionComplete] = useState(false);
  const [error, setError] = useState('');

  const handleBuyNow = () => {

    // Validate fields before processing
    if (!cardNumber || !cardPassword || !nameSurname || !ccv) {
      setError('Please fill in all fields.');
      return;
    }

    // Validate card number length
    
    const cardNum = cardNumber.replace(/\D/g, '');

    if (cardNum.length !== 16) {
      setError('Invalid card number. It should be 16 digits.');
      return;
    }

    // Handle the buy now action, e.g., setTransactionComplete(true)
    setTransactionComplete(true);
    setError(''); // Reset error if transaction is successful
  };

  const handleCardNumberChange = (e) => {
    // Remove non-numeric characters
    const input = e.target.value.replace(/\D/g, '');
  
    // Format the input with spaces after every 4 digits
    const formattedInput = input.replace(/(\d{4})/g, '$1 ').trim();
  
    // Update the state
    setCardNumber(formattedInput);
  };

  return (
    <div className="flex flex-col items-center justify-center h-screen bg-gray-100">
      <h1 className="text-2xl font-bold text-blue-900 mb-4">Buying Process</h1>
      <div className="grid grid-cols-1 gap-4 mb-4">
        <div>
          <label htmlFor="cardNumber" className="block text-sm font-medium text-gray-700">
            Card Number
          </label>
          <input
            type="text"
            id="cardNumber"
            name="cardNumber"
            value={cardNumber}
            onChange={handleCardNumberChange}
            className="mt-1 p-2 border rounded-md"
            placeholder="**** XXXX XXXX 1234"
          />
        </div>
        <div>
          <label htmlFor="cardPassword" className="block text-sm font-medium text-gray-700">
            Card Password
          </label>
          <input
            type="password"
            id="cardPassword"
            name="cardPassword"
            value={cardPassword}
            onChange={(e) => setCardPassword(e.target.value)}
            className="mt-1 p-2 border rounded-md"
            placeholder="Enter your card password"
          />
        </div>
        <div>
          <label htmlFor="nameSurname" className="block text-sm font-medium text-gray-700">
            Name & Surname
          </label>
          <input
            type="text"
            id="nameSurname"
            name="nameSurname"
            value={nameSurname}
            onChange={(e) => setNameSurname(e.target.value)}
            className="mt-1 p-2 border rounded-md"
            placeholder="Enter your name and surname"
          />
        </div>
        <div>
          <label htmlFor="ccv" className="block text-sm font-medium text-gray-700">
            CCV
          </label>
          <input
            type="text"
            id="ccv"
            name="ccv"
            value={ccv}
            onChange={(e) => setCcv(e.target.value)}
            className="mt-1 p-2 border rounded-md"
            placeholder="Enter CCV"
          />
        </div>
      </div>
      {error && <p className="text-red-500 mb-4">{error}</p>}
      {isTransactionComplete ? (
        <div className="text-center">
          <p className="text-green-500 text-lg font-bold mb-4">Transaction Completed!</p>
          <p className="text-gray-700">Thank you for your purchase. Your order will be shipped soon.</p>
        </div>
      ) : (
        <button
          onClick={handleBuyNow}
          className="bg-green-500 text-white px-6 py-3 rounded-full hover:bg-green-600 focus:outline-none focus:ring focus:border-blue-300"
        >
          Buy Now
        </button>
      )}
    </div>
  );
}



   