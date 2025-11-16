import styles from "./TextInput.module.css"

type props = {
    inputGroupName: string,
    welcomeText?: string,
    defaultValue?: string,
    onChangeHandler?: (value: string) => void
}


const TextInput = (
    {
        inputGroupName,
        welcomeText = "",
        defaultValue = "",
        onChangeHandler = (value: string) => {}
    }: props
) => {
    return (
        <input
            id={inputGroupName}
            name={inputGroupName}
            defaultValue={defaultValue} 
            placeholder={welcomeText}
            onChange={
                (e) => onChangeHandler(e.target.value)
            }
            className={styles['text-input']}
            required
        >
        </input>
    )
}

export default TextInput;