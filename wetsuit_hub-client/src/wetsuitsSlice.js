import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import {allWetsuitsURL, scrapeWetsuitsURL} from "./URLS";

export const fetchWetsuits = createAsyncThunk( 'wetsuits/fetch', async(yourData) =>  {
    const array = yourData.toString().split("/")

    const gender = array[0]
    const thickness = array[1]
    const zipper = array[2]

    const response = await fetch(allWetsuitsURL + "?g=" + gender + "&t=" + thickness + "&z=" + zipper ,
            {method: 'GET'
                })

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

export const wetsuitsSlice = createSlice({
    name: 'wetsuits',
    initialState: {
        wetsuitsList: [],
        filter: '',
        filteredWetsuitsList: [{name:"Wetsuits are still being loaded - Please wait...", imageAddress: "https://www.clipartmax.com/middle/m2i8A0G6m2i8G6K9_computer-icons-wave-vector-craft-magnets-clip-art-waves-icon-png/"}]

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