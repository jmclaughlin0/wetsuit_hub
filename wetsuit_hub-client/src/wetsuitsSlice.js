import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import {allWetsuitsURL, numberPagesURL, scrapeWetsuitsURL} from "./URLS";

export const fetchWetsuits = createAsyncThunk( 'wetsuits/fetch', async(yourData) =>  {
    const array = yourData.toString().split("/")

    const gender = array[0]
    const thickness = array[1]
    const zipper = array[2]
    const page = array[3]
    const order = array[4]

    const response = await fetch(allWetsuitsURL + "?g=" + gender + "&t=" + thickness + "&z=" + zipper + "&p=" + page + "&o=" + order ,
            {method: 'GET'
                })

    return await response.json();
    }
)

export const fetchNumberPages = createAsyncThunk( 'pages/fetch', async(yourData) =>  {
        const array = yourData.toString().split("/")

        const gender = array[0]
        const thickness = array[1]
        const zipper = array[2]
        const page = array[3]

        const response = await fetch(numberPagesURL + "?g=" + gender + "&t=" + thickness + "&z=" + zipper + "&p=" + page,
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
        filteredWetsuitsList: [{name:"Wetsuits are still being loaded - Please wait...", price: "N/A"}],
        thickness: '',
        gender: '',
        numberPages: 1,
        order: "PA"
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
            },
        changeGender: (state, action) => {
            state.gender = action.payload
            },
        changeThickness: (state, action) => {
            state.thickness = action.payload
            },


    },
    extraReducers:{
        [fetchWetsuits.fulfilled]: (state, action) => {
            state.wetsuitsList = action.payload;
            state.filteredWetsuitsList = action.payload;
        },
        [fetchNumberPages.fulfilled]: (state, action) => {
            state.numberPages = action.payload;
        }
        }

})

export const {getFilteredWetsuits, changeFilter, changeGender, changeThickness} = wetsuitsSlice.actions

export default wetsuitsSlice.reducer

export const selectWetsuits = state => {
    return state.wetsuits.filteredWetsuitsList;
}

export const selectFilter = state => {
    return state.wetsuits.filter;
}

export const selectGender = state => {
    return state.wetsuits.gender;
}

export const selectThickness = state => {
    return state.wetsuits.thickness;
}

export const selectPages = state => {
    return state.wetsuits.numberPages;
}
