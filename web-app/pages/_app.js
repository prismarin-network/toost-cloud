import 'tailwindcss/tailwind.css'
import {observer} from "mobx-react-lite";
import pageStore from "../stores/page";

const App = observer(({Component, pageProps}) => {
    return (
      <div className={pageStore.darkTheme ? "dark" : ""}>
          <Component {...pageProps} />
      </div>
  )
})

export default App
