import { RiImage2Line, RiHome3Line, RiSettings2Line, RiFolder3Line } from "react-icons/ri"
import {SideBarElement} from "./SideBarElement";

export const SideBar = () => {
    return (
        <div className="fixed h-screen w-64 py-4">
            <ul>
                <SideBarElement selected label="Home" icon={<RiHome3Line />} />
                <SideBarElement label="Albums" icon={<RiFolder3Line />} />
                <SideBarElement label="Photos" icon={<RiImage2Line />} />
                <SideBarElement label="Settings" icon={<RiSettings2Line />} />
            </ul>
        </div>
    )
}