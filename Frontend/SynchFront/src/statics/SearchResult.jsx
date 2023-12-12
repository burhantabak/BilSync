import React from 'react'

export const SearchResult = (results) => {
  return (
    <div
        className = 'padding 10px 20px'
        onClick = {(e)  => alert('You clicked on me!')}
    >

         {/* Render specific properties of the 'result' object here */}
      {results.title}
    </div>
  )
}
