import pageStore from "../../stores/page";
import {RiSunLine, RiMoonLine} from "react-icons/ri"

export const Header = () => {
    return (
        <div className="w-full border-b-2 border-gray-200 dark:border-gray-800 py-5 flex items-center">
            <div className="text-3xl font-bold px-6 text-primary dark:text-gray-200 w-72">
                ToostCloud
            </div>
            <div className="w-full ml-10 flex items-center">
                <input className="w-1/3 bg-gray-100 dark:bg-gray-700 px-4 py-3 shadow-sm focus:shadow-md rounded-2xl focus:outline-none" />

                <div className="flex-grow" />
                <div className="mr-14 flex items-center">
                    <button className="text-2xl text-gray-800 dark:text-gray-200 cursor-pointer" onClick={() => pageStore.toggleTheme()}>
                        {pageStore.darkTheme ? <RiSunLine /> : <RiMoonLine />}
                    </button>
                </div>
            </div>
        </div>
    )
}