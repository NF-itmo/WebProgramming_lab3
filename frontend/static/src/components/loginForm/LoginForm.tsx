import { FormEventHandler, useState } from "react";
import styles from "./LoginForm.module.css"
import TextInput from "../../UI/inputs/textInput/TextInput";
import ButtonPrimary from "../../UI/buttons/buttonPrimary/ButtonPrimary";
import ButtonSecondary from "../../UI/buttons/buttonSecondary/ButtonSecondary";

type props = {
    onLoginHandler?: (login: string, password: string) => void,
    onRegisterHandler?: (login: string, password: string) => void,
};

const LoginForm = (
    {
        onLoginHandler = (login, password) => {},
        onRegisterHandler = (login, password) => {},
    }: props
) => {
    const [getLogin, setLogin] = useState<string>("");
    const [getPassword, setPassword] = useState<string>("");

    return (
        <div className={styles['form-container']}>
            <TextInput
                inputGroupName="login"
                welcomeText="Логин"
                onChangeHandler={(value) => setLogin(value)}
            />
            <TextInput
                inputGroupName="password"
                welcomeText="Пароль"
                onChangeHandler={(value) => setPassword(value)}
            />
            <ButtonPrimary
                type="button"
                text="Войти"
                onClick={() => onLoginHandler(getLogin, getPassword)}
            />
            <ButtonSecondary
                type="button"
                text="Зарегистрироваться"
                onClick={() => onRegisterHandler(getLogin, getPassword)}
            />
        </div>
    )
}

export default LoginForm;