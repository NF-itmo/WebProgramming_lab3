import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { createLocalStorageMiddleware } from '../middleware/localStorageMiddleware';

type tokenState = {
    token: string;
}

const initialState: tokenState = {
    token: JSON.parse(
        localStorage.getItem('token') || "{token: ''}"   
    )["token"]
};

export const tokenSlice = createSlice({
    name: 'token',
    initialState,
    reducers: {
        setToken: (state, action: PayloadAction<tokenState>) => {
            state.token = action.payload.token;
        }
    },
});

export const { setToken } = tokenSlice.actions;
export default tokenSlice.reducer;
export const tokenLocalStorageMiddleware = createLocalStorageMiddleware(setToken, 'token');