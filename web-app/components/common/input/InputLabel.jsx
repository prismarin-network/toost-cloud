export const InputLabel = ({label, type, placeholder, classes}) => {

    const classValue = [
        classes,
        "w-52 bg-gray-100 dark:bg-gray-700 px-4 py-3 shadow-sm focus:shadow-md rounded-2xl focus:outline-none"
    ]
    return (
        <label className="flex flex-col justify-start space-y-1">
            <span className="pl-1 text-gray-600">{label}</span>
            <input type={type} placeholder={placeholder}
                   className={classValue.join(" ")}  />
        </label>

    )
}