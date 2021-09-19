export const Toolbar = ({ title, middle, right }) => {
    return (
        <div className="border-b-2 border-gray-200 dark:border-gray-800 text-gray-100 py-2 mb-3 flex items-center">
            <h1 className="text-2xl text-gray-600 dark:text-gray-400 font-semibold">
                { title }
            </h1>
            <div className="flex-grow">
                { middle }
            </div>
            <div>
                { right }
            </div>
        </div>
    )
}