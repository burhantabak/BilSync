import React from 'react'
import { SearchResult } from './SearchResult'

export const SearchResultList = ({results}) => {
  return (
    <div className=' width: 100%;
    background-color: white;
    display: flex;
    flex-direction: column;
    box-shadow:  0px 0px 8px #ddd;
    border-radius: 10px;
    margin-top: 1rem;
    max-height: 300px;
    overflow-y: scroll;'>{

        results.map((result, index) => {
            return <SearchResult key={index} result={result.title}/>
        })

    }



    </div>
  )
}
