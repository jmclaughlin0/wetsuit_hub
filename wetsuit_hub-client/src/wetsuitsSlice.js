import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import {allWetsuitsURL} from "./URLS";

export const fetchWetsuits = createAsyncThunk( 'wetsuits/fetch', async(_,thunkAPI) =>  {
        const {order} = thunkAPI.getState()
        const response = await fetch(allWetsuitsURL,
            {method: 'GET', headers:{order}})

        const data = await response.json();

        return data;
    }
)

export const wetsuitsSlice = createSlice({
    name: 'wetsuits',
    initialState: {
        wetsuitsList: {},
        filter: '',
        filteredWetsuitsList: [{}]
    },
    reducers: {
        addWetsuit: (state, action) => {
            const newWetsuit = action.payload;
            state.wetsuitsList[newWetsuit.id] = newWetsuit;
        },

        getFilteredWetsuits: (state) => {
            if (state.filter === '') {
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

export const { addWetsuit, getFilteredWetsuits, changeFilter } = wetsuitsSlice.actions

export default wetsuitsSlice.reducer
