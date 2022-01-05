import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import {allWetsuitsURL, numberPagesURL, scrapeWetsuitsURL, stateOfUpdateURL} from "./URLS";

export const fetchWetsuits = createAsyncThunk( 'wetsuits/fetch', async(yourData) =>  {
    const array = yourData.toString().split("/")

    const gender = array[0]
    const thickness = array[1]
    const zipper = array[2]
    const page = array[3]
    const order = array[4]
    const search = array[5]
    const hood = array[6]
    const size = array[7]

    const response = await fetch(allWetsuitsURL + "?g=" + gender + "&t=" + thickness + "&z=" + zipper + "&p=" + page + "&o=" + order + "&s=" + search + "&h=" + hood + "&d=" + size,
            {method: 'GET',
                mode: 'cors',
                cache: 'no-cache',
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json'
                }
                })

    return await response.json();
    }
)

export const fetchNumberPages = createAsyncThunk('pages/fetch', async(yourData) =>  {
        const array = yourData.toString().split("/")

        const gender = array[0]
        const thickness = array[1]
        const zipper = array[2]
        const search = array[3]
        const hood = array[4]
        const size = array[5]

        const response = await fetch(numberPagesURL + "?g=" + gender + "&t=" + thickness + "&z=" + zipper + "&s=" + search + "&h=" + hood+ "&d=" + size,
            {method: 'GET',
                mode: 'cors',
                cache: 'no-cache',
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json'
                }
            })

        return await response.json();
    }
)

export const scrapeWetsuits = createAsyncThunk('wetsuits/scrape', async() =>  {
        const response = await fetch(scrapeWetsuitsURL,
            {method: 'POST',
                mode: 'cors'})

        const data = await response.json();

        alert("")

        return data;
    }
)


export const stateOfUpdate = createAsyncThunk('update/state', async() =>  {
        const response = await fetch(stateOfUpdateURL,
            {method: 'GET',
                mode: 'cors'})

    return await response.text();
    }
)

export const wetsuitsLoading = [{name:"Wetsuits are being loaded - Please wait...", price: "N/A"}];

export const wetsuitsSlice = createSlice({
    name: 'wetsuits',
    initialState: {
        wetsuitsList: wetsuitsLoading,
        thickness: '',
        gender: '',
        numberPages: 1,
        updateInProgress: "completed",
        order: "PA",
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
        },
        [stateOfUpdate.fulfilled]: (state, action) => {
            state.updateInProgress = action.payload;
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

export const selectUpdateInProgress = state => {
    return state.wetsuits.updateInProgress;
}
