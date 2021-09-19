const colors = require('tailwindcss/colors')

module.exports = {
  mode: 'jit',
  purge: [
      './src/pages/**/*.{js,ts,jsx,tsx,css}',
      './src/components/**/*.{js,ts,jsx,tsx,css}',
      './src/layout/**/*.{js,ts,jsx,tsx,css}',
      './src/stories/**/*.{js,ts,jsx,tsx,css}'
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
