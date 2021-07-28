import { createSlice } from '@reduxjs/toolkit'

export const orderSlice = createSlice({
    name: 'order',
    initialState: {
        value:
            "price_up"

    },
    reducers: {
        setOrder: (state, action) => {
            state.value = action.payload;
        }

    }
})

export const { setOrder } = orderSlice.actions

export default orderSlice.reducer

export const selectOrder = state => state.order.value