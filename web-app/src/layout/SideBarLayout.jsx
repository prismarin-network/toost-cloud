import {SideBar} from "@/components/layout/SideBar";
import {Header} from "@/components/layout/Header";
import {Toolbar} from "@/components/layout/Toolbar";

export default function SidebarLayout({ children }) {
    return (
        <div className="min-h-screen bg-white dark:bg-gray-900">
            <Header />
            <div className="flex">
                <SideBar />
                <main className="ml-72 w-full p-4 mr-10">
                    <Toolbar title="My files" />
                    {children}
                </main>
            </div>
        </div>
    )
}