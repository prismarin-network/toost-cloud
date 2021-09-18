export const Input = ({type, placeholder, classes}) => {

    const classValue = [
        classes,
        "w-26 bg-gray-100 dark:bg-gray-700 px-4 py-3 shadow-sm focus:shadow-md rounded-2xl focus:outline-none"

    ]
    return (
        <input type={type} placeholder={placeholder}
               className={classValue.join(" ")}  />
    )
}