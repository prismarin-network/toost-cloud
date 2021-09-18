import 'tailwindcss/tailwind.css'
import { themes } from '@storybook/theming';

export const parameters = {
  actions: { argTypesRegex: "^on[A-Z].*" },
  controls: {
    matchers: {
      color: /(background|color)$/i,
      date: /Date$/,
    },
  },
  options: {
    storySort: {
      method: 'alphabetical',
      order: ['Guides', ['Introduction']],
    },
  },
  darkMode: {
    current: 'dark'
  }
}
