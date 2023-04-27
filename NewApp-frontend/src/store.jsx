import { legacy_createStore } from "redux";

import rootReducer from "./reducers";

const store=legacy_createStore(rootReducer);
console.log('store')

export default store

