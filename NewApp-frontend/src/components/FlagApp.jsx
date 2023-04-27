import React, { useContext } from "react";

import { useState, useEffect } from "react";

import axios from "axios";
import { AppContext } from '../context';

const FlagApp = () => {

  const { coutryArray } = useContext(AppContext);
  const [countryGroup, setCountryGroup] = coutryArray;


  const [countryState, setCountryState] = useState({
    loading: false,
    countries: [],
    errorMassage: "",
  });
  const { loading, errorMassage, countries } = countryState;


  useEffect(() => {
    const fetchData = async () => {
      try {
        // fetch spiner
        setCountryState({
          ...countryState,
          loading: true,
        });

        // fetch data

        const dataUrl = `https://restcountries.com/v3.1/all`;
        const response = await axios.get(dataUrl);
        console.log("countries : ", response);
        setCountryState({
          ...countryState,
          countries: response.data,
          loading: false,
        });
      } catch (error) {
        setCountryState({
          ...countryState,
          loading: false,
          errorMassage: "something is wrong",
        });
      }
    };

    fetchData();


  }, []);

  setCountryGroup(countries);

}
export default FlagApp
