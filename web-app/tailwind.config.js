const colors = require('tailwindcss/colors')

module.exports = {
  mode: 'jit',
  purge: [
      './pages/**/*.{js,ts,jsx,tsx}',
      './components/**/*.{js,ts,jsx,tsx}',
      './layout/**/*.{js,ts,jsx,tsx}'
  ],
  darkMode: 'class', // or 'media' or 'class'
  theme: {
    extend: {
        colors: {
            primary: '#007ACC',
            secondary: '#009FDE',
            light: "#DEF2FF",
            accent: "#DCA11C",
            gray: colors.gray,
            coolGray: colors.coolGray,
        },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
