import {useState} from "react";

export const useLocalStorage = (keyName, value) => {
    const [storedValue, setStoredValue] = useState(()=>{
        try{
            const claimedValue = window.localStorage.getItem(keyName);
            if(claimedValue){
                return JSON.parse(claimedValue);
            }
            else{
                window.localStorage.setItem(keyName, value);
                return value
            }
        }catch(error){
            return value;
        }
    });
    const setValue = (newValue) => {
        try {
          window.localStorage.setItem(keyName, JSON.stringify(newValue));
        } catch (err) {}
        setStoredValue(newValue);
      };
      return [storedValue, setValue];
};