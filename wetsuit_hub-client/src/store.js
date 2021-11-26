import {configureStore} from "@reduxjs/toolkit";
import wetsuitsReducer from './wetsuitsSlice'

export default configureStore({
    reducer: {
        wetsuits: wetsuitsReducer
    },
})