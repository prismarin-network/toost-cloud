import 'tailwindcss/tailwind.css'

function App({ Component, pageProps }) {
  return (
      <div className="">
          <Component {...pageProps} />
      </div>
  )
}

export default App
