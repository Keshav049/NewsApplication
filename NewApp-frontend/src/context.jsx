import React, { createContext, useState } from "react";
import FlagApp from "./components/FlagApp";

const AppContext = createContext();

const AppProvider = ({ children }) => {
  const [countryGroup, setCountryGroup] = useState([]);
  const [code, setCode] = useState("in");
  const [catagory, setCatagory] = useState("");
  const [name,setName] = useState("");

  return (
    <AppContext.Provider
      value={{
        coutryArray: [countryGroup, setCountryGroup],
        catagoryData: [catagory, setCatagory],
        countryCode: [code, setCode],
        loginName:[name,setName]
      }}
    >
      <FlagApp />
      {children}
    </AppContext.Provider>
  );
};
export { AppContext, AppProvider };
