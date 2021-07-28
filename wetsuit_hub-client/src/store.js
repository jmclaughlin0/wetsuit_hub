import {configureStore} from "@reduxjs/toolkit";
import wetsuitsReducer from './wetsuitsSlice'
import orderReducer from './orderSlice'

export default configureStore({
    reducer: {
        wetsuits: wetsuitsReducer,
        order: orderReducer
    },
})