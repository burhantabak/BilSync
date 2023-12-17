import React, { useEffect, useState } from 'react';
import { useData } from '../Context/DataContext';
import approveTransaction from '../calling/TransactionCaller.jsx/approveTransaction';
import takerApproval from '../calling/TransactionCaller.jsx/takerApproved';

const MyTransactionPage = () => {
  const { user, transactions, getThePosts } = useData();
  const [isFetching, setIsFetching] = useState(false);

  const handleApprove = async (transactionId) => {
    try {
      setIsFetching(true);

      if (transactionId) {
        if (user.userId === transactions.find((t) => t.id === transactionId)?.takerId) {
          // Approve as Taker
          await takerApproval(user, transactionId);
        } else if (user.userId === transactions.find((t) => t.id === transactionId)?.giverId) {
          // Approve as Giver
          await approveTransaction(user, transactionId);
        }

        // Refresh posts after approval
        getThePosts();
      }
    } catch (error) {
      console.error('Error handling approval:', error);
    } finally {
      setIsFetching(false);
    }
  };

  return (
    <div className="flex flex-col h-screen bg-gray-100">
      <div className="flex flex-row justify-between px-4 py-2 bg-gray-200">
        <h1 className="text-xl font-bold">My Transactions</h1>
      </div>
      <div className="flex flex-col overflow-auto px-4 py-2">
        {transactions && transactions.map((transaction) => (
          <div
            key={transaction.id}
            className="flex flex-row items-center justify-between py-2 px-4 mb-2 bg-gray-300 rounded-md"
          >
            <div className="flex flex-col">
              <span className="text-lg font-medium">{transaction.title}</span>
              <span className="text-sm text-gray-500">Status: {transaction.status}</span>
            </div>
            <div className="flex flex-row space-x-2">
              {transaction.takerId === user.userId && (
                <button
                  onClick={() => handleApprove(transaction.id)}
                  className="rounded-md bg-green-500 text-white px-4 py-1 hover:bg-green-600"
                  disabled={transaction.status === 'Approved' || isFetching}
                >
                  Approve As Taker
                </button>
              )}
              {transaction.giverId === user.userId && (
                <button
                  onClick={() => handleApprove(transaction.id)}
                  className="rounded-md bg-blue-500 text-white px-4 py-1 hover:bg-blue-600"
                  disabled={transaction.status === 'Approved' || isFetching}
                >
                  Approve As Giver
                </button>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MyTransactionPage;
