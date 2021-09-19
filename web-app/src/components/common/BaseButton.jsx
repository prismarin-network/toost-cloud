export const BaseButton = ({ classes, type, disabled, onClick, children }) => {

    let classList;
    classList = [
        classes,
        "py-2 px-6 font-semibold rounded-lg shadow-md focus:outline-none focus:ring-2 focus:ring-opacity-75 cursor-pointer",
    ];

    if (disabled) {
        classList.push("text-white bg-gray-300 cursor-not-allowed shadow-sm")
    } else if (type === "secondary") {
        classList.push("text-white bg-gray-400 hover:bg-gray-600 focus:ring-gray-300")
    } else if (type === "success") {
        classList.push("text-white bg-green-500 hover:bg-green-700 focus:ring-green-400")
    } else if (type === "danger") {
        classList.push("text-white bg-red-500 hover:bg-red-700 focus:ring-red-400")
    } else if (type === "primary") {
        classList.push("text-white bg-indigo-500 hover:bg-indigo-700 focus:ring-indigo-400")
    } else {
        classList.push("shadow-none text-white")
    }

    return (
        <button onClick={onClick} className={classList.join(" ")}>
            { children }
        </button>
    )
}