import { useState } from 'react';

export const useSearchResults = () => {
  const [results, setResults] = useState([]);

  const updateResults = (newResults) => {
    setResults(newResults);
  };

  return { results, updateResults };
};
