import React, { useState } from 'react';
import reportPostCalling from '../calling/reportsCalling';
import { useData } from '../Context/DataContext';

function ReportBox({isStarred,setStarred, postId}){

    const [showReportBox, setShowReportBox] = useState(false);
    const [reportReason, setReportReason] = useState('');
    const {user} = useData();

    const handleReportClick = () => {
      setShowReportBox(true);
      console.log("Report clicked")
    };
    const handleReportSubmit = () => {
        if (reportReason.length < 5) {
            // Display an alert or perform any other action to inform the user
            alert('Report reason must be at least 5 words.');
            setReportReason('');
            return;
          }
        console.log('Report submitted with reason:', reportReason);
        console.log(reportReason);
        console.log("POST ID", postId);
        // Call  reporting function with post ID
        reportPostCalling(reportReason, user, postId)
            .then(() => {
            console.log('Report submitted successfully.');
            })
            .catch((error) => {
            console.error('Error submitting report:', error);
            });
    
        // Reset state after submission
        setShowReportBox(false);
        setReportReason('');
    };

      return(
          <div className='pl-1 flex items-center'>
          <button className='flex items-center flex-col hover:underline' onClick={handleReportClick}>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
              <path strokeLinecap="round" strokeLinejoin="round" d="M3 3v1.5M3 21v-6m0 0l2.77-.693a9 9 0 016.208.682l.108.054a9 9 0 006.086.71l3.114-.732a48.524 48.524 0 01-.005-10.499l-3.11.732a9 9 0 01-6.085-.711l-.108-.054a9 9 0 00-6.208-.682L3 4.5M3 15V4.5" />
            </svg>
            <p className='text-xs'>Report</p>
          </button>
    
          {/* Report Box */}
          {showReportBox && (
            <div className="fixed  bottom-0 left-0 w-full h-full bg-gray-800 bg-opacity-50 flex justify-center items-center zIndex = 200 " style={{ zIndex: 110 }}>
              <div className="bg-white p-4 rounded-md">
              <div className='flex justify-end'>
                  <button onClick={()=>setShowReportBox(false)}>
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                  </button>
              </div>
                <p>Report Reason:</p>
                <textarea
                  value={reportReason}
                  onChange={(e) => setReportReason(e.target.value)}
                  className="w-full p-2 border border-gray-300 rounded-md"
                  placeholder="Enter your report reason..."
                />
                <button
                  onClick={handleReportSubmit}
                  className="mt-2 px-4 py-2 bg-blue-700 text-white rounded-md"
                >
                  Submit Report
                </button>
              </div>
            </div>
          )}
      </div>
      );
  }

export default ReportBox;