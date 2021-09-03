import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import {allWetsuitsURL, kidsWetsuitsURL, mensWetsuitsURL, scrapeWetsuitsURL, womensWetsuitsURL} from "./URLS";

export const fetchWetsuits = createAsyncThunk( 'wetsuits/fetch', async() =>  {
        const response = await fetch(allWetsuitsURL,
            {method: 'GET'})

    return await response.json();
    }
)

export const scrapeWetsuits = createAsyncThunk( 'wetsuits/scrape', async() =>  {
        const response = await fetch(scrapeWetsuitsURL,
            {method: 'POST'})

        const data = await response.json();

        alert("")

        return data;
    }
)

export const fetchMensWetsuits = createAsyncThunk( 'wetsuits/fetchMens', async() =>  {
        const response = await fetch(mensWetsuitsURL,
            {method: 'GET'})

    return await response.json();
    }
)

export const fetchWomensWetsuits = createAsyncThunk( 'wetsuits/fetchWomens', async() =>  {
        const response = await fetch(womensWetsuitsURL,
            {method: 'GET'})

    return await response.json();
    }
)

export const fetchKidsWetsuits = createAsyncThunk( 'wetsuits/fetchKids', async() =>  {
        const response = await fetch(kidsWetsuitsURL,
            {method: 'GET'})

        return await response.json();
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
                    filteredWetsuitsList: state.wetsuitsList.filter(wetsuit => wetsuit.name.toLowerCase().includes(state.filter.toLowerCase()))
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
        },
        [fetchMensWetsuits.fulfilled]: (state, action) => {
            state.wetsuitsList = action.payload;
            state.filteredWetsuitsList = action.payload;
        },
        [fetchWomensWetsuits.fulfilled]: (state, action) => {
            state.wetsuitsList = action.payload;
            state.filteredWetsuitsList = action.payload;
        },
        [fetchKidsWetsuits.fulfilled]: (state, action) => {
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