export const SideBarElement = ({ label, icon, selected }) => {

    return (
        <li className={`flex items-center w-full my-2 rounded-r-3xl px-6 py-3 cursor-pointer text-gray-600 dark:text-gray-400 text-lg font-semibold ${selected ? "bg-blue-100 text-blue-600 dark:bg-coolGray-800" : "hover:bg-gray-200 dark:hover:bg-gray-800"}`}>
            { icon }
            <span className="ml-4">
                { label }
            </span>
        </li>
    )
}