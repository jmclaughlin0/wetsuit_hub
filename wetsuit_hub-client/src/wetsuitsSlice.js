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
        wetsuitsList: [],
        filter: '',
        filteredWetsuitsList: []
    },
    reducers: {
        getFilteredWetsuits: (state) => {
            if (state.filter === "") {
                return({...state,
                    filteredWetsuitsList: state.wetsuitsList})
            } else {
                return ({
                    ...state,
                    filteredWetsuitsList: state.wetsuitsList.filter(meal => meal.name.toLowerCase().includes(state.filter.toLowerCase()))
                })
            }
            },
        changeFilter: (state, action) => {
                state.filter = action.payload
            }
    },
    extraReducers:{
        [fetchWetsuits.fulfilled]: (state, action) => {
            state.wetsuitsList = action.payload;
            state.filteredWetsuitsList = action.payload;
        }
    }

})

export const { getFilteredWetsuits, changeFilter } = wetsuitsSlice.actions

export default wetsuitsSlice.reducer

export const selectWetsuits = state => {
    return state.wetsuits.filteredWetsuitsList;
}

export const selectFilter = state => {
    return state.wetsuits.filter;
}