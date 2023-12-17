// Import necessary dependencies and components
import React, { useEffect, useState } from 'react';
import { useData } from '../Context/DataContext';
import { getAllReports } from '../calling/reportsCalling';

// Define the ReportsPage component
const ReportsPage = () => {
  // Retrieve postList from the DataContext
  const { postList, getThePosts, user} = useData();
  const [reportList,setReportList] = useState([]);
  const [posts,setPosts] = useState([]);
  useEffect(()=>{getAllReports(user).then((reports)=>{console.log(reports);setReportList(reports)})},[]);
  useEffect(()=>setPosts(postList),[postList]);
  console.log(postList);
  // Define the initial reports state
  const [reports, setReports] = useState([
    {
      id: 1,
      type: 'Post Report',
      reporter: 'Tuna Saygın',
      reported: 'Kenan Zeynalov',
      description: 'İletişime geçince bana küfür etti',
    },
    {
      id: 2,
      type: 'Comment Report',
      reporter: 'Kenan Zeynalov',
      reported: 'Tuna Saygın',
      description: 'Irkçı comment attı',
      comment: {
        id: 1,
        content: 'k',
      },
    },
    // Add more reports as needed
  ]);

  // Define the selectedReport state
  const [selectedReport, setSelectedReport] = useState(null);

  // Define the handleBanUser function
  const handleBanUser = (reportId) => {
    // Implement ban user logic here
    console.log(`Banning user for report ${reportId}`);
  };

  // Define the handleDeleteReport function
  const handleDeleteReport = (reportId) => {
    // Implement delete report logic here
    console.log(`Deleting report ${reportId}`);
  };

  // Define the handleViewReportedItem function
  const handleViewReportedItem = (report) => {
    setSelectedReport(report);
    console.log("report");
    console.log(report)
    if (report.type === 'Post Report') {
      // Find the corresponding post based on the report's information
      const correspondingPost = postList.find((post) => post.authorId === report.id);
  
      // If the corresponding post is found, update the selectedReport with the post
      if (correspondingPost) {
        setSelectedReport(correspondingPost);
      }
    }
  };

  // Define the handleCloseModal function
  const handleCloseModal = () => {
    setSelectedReport(null);
  };

  // Return the JSX for the ReportsPage component
  return (
    <div className="container mx-auto p-8">
      <h2 className="text-3xl font-bold mb-8">Reports</h2>
      <table className="min-w-full bg-white border border-gray-300">
        <thead>
          <tr>
            <th className="py-2 px-4 border-b">Report Type</th>
            <th className="py-2 px-4 border-b">Reporter</th>
            <th className="py-2 px-4 border-b">Reported</th>
            <th className="py-2 px-4 border-b">Description</th>
            <th className="py-2 px-4 border-b">Actions</th>
          </tr>
        </thead>
        <tbody>
          {reports.map((report) => (
            <tr key={report.id}>
              <td className="py-2 px-4 border-b">{report.type}</td>
              <td className="py-2 px-4 border-b">{report.reporter}</td>
              <td className="py-2 px-4 border-b">{report.reported}</td>
              <td className="py-2 px-4 border-b">{report.description}</td>
              <td className="py-2 px-4 border-b">
                <button
                  className="bg-red-500 text-white px-4 py-2 rounded mr-2 hover:bg-red-600"
                  onClick={() => handleBanUser(report.id)}
                >
                  Ban User
                </button>
                <button
                  className="bg-blue-500 text-white px-4 py-2 rounded mr-2 hover:bg-blue-600"
                  onClick={() => handleDeleteReport(report.id)}
                >
                  Delete Report
                </button>
                {report.type === 'Post Report' && (
                  <button
                    className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
                    onClick={() => handleViewReportedItem(postList[0])}
                  >
                    View Post
                  </button>
                )}
                {report.type === 'Comment Report' && (
                  <button
                    className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600"
                    onClick={() => handleViewReportedItem(report)}
                  >
                    View Comment
                  </button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {selectedReport && (
        <div className="fixed inset-0 flex items-center justify-center">
          <div className="bg-black bg-opacity-50 absolute inset-0" onClick={handleCloseModal}></div>
          <div className="bg-white p-8 rounded-md z-50">
            {selectedReport.type === 'Post Report' && (
              posts &&  <TradingPostItem post={posts[0]} />   
            )}
            {selectedReport.type === 'Comment Report' && (
              <>
                <h3 className="text-2xl font-bold mb-4">Comment Report</h3>
                <p>{selectedReport.description}</p>
                <div className="mt-4">
                  <strong>Reported Comment:</strong>
                  <p>{selectedReport.comment.content}</p>
                </div>
              </>
            )}
            <button
              className="mt-4 bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600"
              onClick={handleCloseModal}
            >
              Close
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

// Export the ReportsPage component
export default ReportsPage;
