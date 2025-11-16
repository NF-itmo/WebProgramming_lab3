import { useEffect } from "react";
import Table from "../../components/table/Table";
import { useAppDispatch, useAppSelector } from "../../store/hooks/redux";
import { fromArray } from "../../store/slices/pointSlice";
import { getHistory } from "./api/getHistory";
import useError from "../error/useError";

const History = () => {
    const dispatch = useAppDispatch();
    const { points } = useAppSelector((state) => state.points);
    const { token } = useAppSelector((state) => state.token);
    const { showError } = useError();

    useEffect(
        () => {
            getHistory({
                start: 0,
                length: 10,
                token: token,
                onSuccess: (data) => {
                    dispatch(fromArray(data))
                },
                onError: (descr) => showError(descr)
            })
        }, [token]
    )
    
    return (
        <Table
            tableData={
                points.map((elem) => ({
                    timestamp: elem.timestamp * 1000,
                    x: elem.x,
                    y: elem.y,
                    r: elem.r,
                    isHitted: elem.isHitted
                }))
            }
        />
    )
}

export default History;