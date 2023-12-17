import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useData } from '../Context/DataContext';
import getAllUsers from '../calling/userCalling';
import { getImage } from '../calling/imageCalling';
import { changeEmail } from '../calling/changeEmailCalling';
// Import additional functions as needed

const mockTransactions = [
  { id: 1, title: 'Transaction 1', status: 'Pending' },
  { id: 2, title: 'Transaction 2', status: 'Approved' },
  { id: 3, title: 'Transaction 3', status: 'Pending' },
];

export default function MyTransactionPage() {
  const { user } = useData();
  const [transactions, setTransactions] = useState(mockTransactions);
  const navigate = useNavigate();

  useEffect(() => {
    // Example: getAllTransactionsForUser(user).then(transactions => setTransactions(transactions));
  }, [user]);

  const handleApproveDeny = (transactionId, action) => {
    // Perform the action (approve/deny) for the given transaction
    // Example: approveDenyTransaction(transactionId, action).then(updatedTransactions => setTransactions(updatedTransactions));
  };

  return (
    <div className="flex flex-col h-screen bg-gray-100">
      <div className="flex flex-row justify-between px-4 py-2 bg-gray-200">
        <h1 className="text-xl font-bold">My Transactions</h1>
      </div>
      <div className="flex flex-col overflow-auto px-4 py-2">
        {transactions.map((transaction) => (
          <div
            key={transaction.id}
            className="flex flex-row items-center justify-between py-2 px-4 mb-2 bg-gray-300 rounded-md"
          >
            <div className="flex flex-col">
              <span className="text-lg font-medium">{transaction.title}</span>
              <span className="text-sm text-gray-500">Status: {transaction.status}</span>
            </div>
            <div className="flex flex-row space-x-2">
              <button
                onClick={() => handleApproveDeny(transaction.id, 'approve')}
                className="rounded-md bg-green-500 text-white px-4 py-1 hover:bg-green-600"
                disabled={transaction.status === 'Approved'}
              >
                Approve
              </button>
              <button
                onClick={() => handleApproveDeny(transaction.id, 'deny')}
                className="rounded-md bg-red-500 text-white px-4 py-1 hover:bg-red-600"
                disabled={transaction.status === 'Denied'}
              >
                Deny
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}