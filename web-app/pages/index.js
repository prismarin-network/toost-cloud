import SidebarLayout from "../layout/SideBarLayout";
import {useEffect} from "react";
import authStore from "../stores/auth";

export default function Home() {

    useEffect(() => {
        authStore.login("admin", "admin123", (response) => {
            console.log(response)
        })
    }, [])

  return (
      <SidebarLayout>
          <div className="text-2xl dark:text-white">
              No content yet. View Storybook
          </div>
      </SidebarLayout>
  )
}