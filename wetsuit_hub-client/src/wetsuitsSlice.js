import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import {allWetsuitsURL, scrapeWetsuitsURL} from "./URLS";

export const fetchWetsuits = createAsyncThunk( 'wetsuits/fetch', async() =>  {
        const response = await fetch(allWetsuitsURL,
            {method: 'GET'})

        const data = await response.json();

        return data;
    }
)

export const scrapeWetsuits = createAsyncThunk( 'wetsuits/scrape', async() =>  {
        const response = await fetch(scrapeWetsuitsURL,
            {method: 'POST'})

        const data = await response.json();

        return data;
    }
)

export const wetsuitsSlice = createSlice({
    name: 'wetsuits',
    initialState: {
        wetsuitsList: []
    },
    reducers: {

    },
    extraReducers:{
        [fetchWetsuits.fulfilled]: (state, action) => {
            state.wetsuitsList = action.payload;
        }
    }

})

export default wetsuitsSlice.reducer

export const selectWetsuits = state => {
    return state.wetsuits.wetsuitsList;
}
