import React, { useState } from 'react';
import { useParams } from 'react-router-dom'; // Import useParams from react-router-dom
import { useNavigate } from 'react-router-dom';


export default function TransactionPage(handleBuyNow) {
  const [cardNumber, setCardNumber] = useState('');
  const [expiryMonth, setExpiryMonth] = useState('');
  const [expiryYear, setExpiryYear] = useState('');
  const [nameSurname, setNameSurname] = useState('');
  const [ccv, setCcv] = useState('');
  const [isTransactionComplete, setTransactionComplete] = useState(false);
  const [error, setError] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const { postId } = useParams(); // Get postId from the URL params
  const navigate = useNavigate();
  


  const handleBuyNowLocal = () => {
    // Validate fields before processing
    if (!cardNumber || !expiryMonth || !expiryYear || !nameSurname || !ccv) {
      setError('Please fill in all fields.');
      return;
    }

    // Validate card number length
    const cardNum = cardNumber.replace(/\D/g, '');

    if (cardNum.length !== 16) {
      setError('Invalid card number. It should be 16 digits.');
      return;
    }

    // Validate that "Name & Surname" does not contain numbers
    if (/\d/.test(nameSurname)) {
      setError('Name & Surname should not contain numbers.');
      return;
    }

    // Handle the buy now action, e.g., setTransactionComplete(true)
    setTransactionComplete(true);
    handleBuyNow(postId);
    setError(''); // Reset error if the transaction is successful
    return null;
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
    <div className="flex flex-col items-center justify-center h-screen bg-gray-100 p-4">
      <h1 className="text-2xl font-bold text-blue-900 mb-4">Buying Process</h1>
      <div className="flex justify-between mt-1">
        <div className="flex items-center">
          <div
            className={`w-8 h-8 bg-blue-500 rounded-full flex items-center justify-center text-white font-bold ${
              currentPage === 1 ? 'bg-opacity-100' : 'bg-opacity-75'
            }`}
          >
            1
          </div>
          <span className="ml-2 text-gray-700">Payment</span>
        </div>
        <div className="flex items-center">
          <div className="w-4 h-1 bg-gray-300 mx-2"></div>
          <div
            className={`w-8 h-8 bg-gray-200 rounded-full flex items-center justify-center text-gray-500 font-bold ${
              currentPage === 2 ? 'bg-opacity-100' : 'bg-opacity-75'
            }`}
          >
            2
          </div>
          <span className="ml-2 text-gray-700">Contact</span>
        </div>
      </div>

      <div className="grid grid-cols-1 gap-4 mb-4 mt-4">
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
            maxLength={"19"}  // 16 digits + 3 spaces for spaces between them
            className="mt-1 p-2 border rounded-md"
            placeholder="**** XXXX XXXX 1234"
          />
        </div>
        <div>
          <label htmlFor="expiryDate" className="block text-sm font-medium text-gray-700">
            Card Expiry Date
          </label>
          <div className="flex">
            <select
              id="expiryMonth"
              name="expiryMonth"
              value={expiryMonth}
              onChange={(e) => setExpiryMonth(e.target.value)}
              className="mt-1 p-2 border rounded-md mr-2"
            >
              <option value="" disabled>
                Month
              </option>
              <option value="01">01</option>
              <option value="02">02</option>
              <option value="03">03</option>
              <option value="04">04</option>
              <option value="05">05</option>
              <option value="06">06</option>
              <option value="07">07</option>
              <option value="08">08</option>
              <option value="09">09</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
            </select>
            <select
              id="expiryYear"
              name="expiryYear"
              value={expiryYear}
              onChange={(e) => setExpiryYear(e.target.value)}
              className="mt-1 p-2 border rounded-md"
            >
              <option value="" disabled>
                Year
              </option>
              <option value="2023">2023</option>
              <option value="2024">2024</option>
              <option value="2025">2025</option>
              <option value="2026">2026</option>
              <option value="2027">2027</option>
              <option value="2028">2028</option>
              <option value="2029">2029</option>
              <option value="2030">2030</option>
            </select>
          </div>
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
            type="password"
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
          onClick={handleBuyNowLocal}
          className="bg-green-500 text-white px-6 py-3 rounded-full hover:bg-green-600 focus:outline-none focus:ring focus:border-blue-300"
        >
          Buy Now
        </button>
      )}
    </div>
  );
}
