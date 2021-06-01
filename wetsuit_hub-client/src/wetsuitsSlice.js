import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import {allWetsuitsURL} from "./URLS";

export const fetchWetsuits = createAsyncThunk( 'wetsuits/fetch', async() =>  {
        const response = await fetch(allWetsuitsURL,
            {method: 'GET'})

        const data = await response.json();

        return data;
    }
)

export const wetsuitsSlice = createSlice({
    name: 'wetsuits',
    initialState: {
        wetsuitsList: [{
                "name":"O'Neill HyperFreak",
                "Price": "£235",
                "webAddress": "https://www.youtube.com/watch?v=K4P7EML-H4U&ab_channel=MedSchoolInsidersMedSchoolInsiders",
                "size": "M",
                "id": "22710dbf-3fc2-4f7c-978d-0d2bdb85b825"
            },
            {
                "name":"O'Neill Psycho",
                "Price": "£25",
                "webAddress": "https://www.youtube.com/watch?v=K4P7EML-H4U&ab_channel=MedSchoolInsidersMedSchoolInsiders",
                "size": "MS",
                "id": "22710dhf-3fc2-4f7c-978d-0d2bdb85b825"
            }]
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
