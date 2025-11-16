import { registerReq } from "./api/registerReq";
import { loginReq } from "./api/loginReq";
import LoginFormComponent from "../../components/loginForm/LoginForm"
import { useNavigate } from "react-router-dom";
import { useAppDispatch } from "../../store/hooks/redux";
import { setToken } from "../../store/slices/tokenSlice";
import useError from "../error/useError";

const LoginForm = () => {
    const navigate = useNavigate();
    const dispatch = useAppDispatch();
    const { showError } = useError();
    
    return (
        <LoginFormComponent
            onRegisterHandler={
                (login, password) => {
                    registerReq(
                        login,
                        password,
                        (token) => {
                            dispatch(setToken({token: token}))
                            navigate("/")
                        },
                        (descr) => showError(descr)
                    )
                    
                }
            }
            onLoginHandler={
                (login, password) => {
                    loginReq(
                        login,
                        password,
                        (token) => {
                            dispatch(setToken({token: token}))
                            navigate("/")
                        },
                        (descr) => showError(descr)
                    )
                }
            }
        />
    )
}

export default LoginForm;