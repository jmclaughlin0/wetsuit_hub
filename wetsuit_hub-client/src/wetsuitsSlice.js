import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import {allWetsuitsURL, numberPagesURL, scrapeWetsuitsURL} from "./URLS";

export const fetchWetsuits = createAsyncThunk( 'wetsuits/fetch', async(yourData) =>  {
    const array = yourData.toString().split("/")

    const gender = array[0]
    const thickness = array[1]
    const zipper = array[2]
    const page = array[3]
    const order = array[4]
    const search = array[5]
    const hood = array[6]

    const response = await fetch(allWetsuitsURL + "?g=" + gender + "&t=" + thickness + "&z=" + zipper + "&p=" + page + "&o=" + order + "&s=" + search + "&h=" + hood,
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
        const search = array[3]
        const hood = array[4]

        const response = await fetch(numberPagesURL + "?g=" + gender + "&t=" + thickness + "&z=" + zipper + "&s=" + search + "&h=" + hood,
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
        wetsuitsList: [{name:"Wetsuits are still being loaded - Please wait...", price: "N/A"}],
        thickness: '',
        gender: '',
        numberPages: 1,
        order: "PA"
    },
    reducers: {
        changeGender: (state, action) => {
            state.gender = action.payload
            },
        changeThickness: (state, action) => {
            state.thickness = action.payload
            }
    },
    extraReducers:{
        [fetchWetsuits.fulfilled]: (state, action) => {
            state.wetsuitsList = action.payload;
        },
        [fetchNumberPages.fulfilled]: (state, action) => {
            state.numberPages = action.payload;
        }
        }

})

export const {getFilteredWetsuits, changeGender, changeThickness} = wetsuitsSlice.actions

export default wetsuitsSlice.reducer

export const selectWetsuits = state => {
    return state.wetsuits.wetsuitsList;
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
