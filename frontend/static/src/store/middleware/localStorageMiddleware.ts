import { ActionCreatorWithPayload, Middleware } from '@reduxjs/toolkit';

export const createLocalStorageMiddleware = <T>(
  actionCreator: ActionCreatorWithPayload<T>,
  itemName: string
): Middleware => {
  const localStorageMiddleware: Middleware = store => next => action => {
    const result = next(action);
    
    if (actionCreator.match(action)) {
      const value =
        typeof action.payload === 'string'
          ? action.payload
          : JSON.stringify(action.payload);

      localStorage.setItem(itemName, value);
    }

    return result;
  };

  return localStorageMiddleware;
};
