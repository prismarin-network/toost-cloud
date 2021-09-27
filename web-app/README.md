# Toost cloud Web app Project

This project uses NextJS, the react framework for production.
If you want to learn more about NextJS, please visit its website: https://nextjs.org/

## Setup

```bash
# install dependencies
$ npm ci

# serve with hot reload
$ npm run dev

# build for production and launch server
$ npm run build
$ npm run start

# for component development
$ npm run storybook
$ npm run build-storybook

# to run tests
$ npm run test

# to check linting
$ npm run lint
$ npm run lint -- --fix
```

## Storybook

### What is Storybook
Storybook is a service we use to develop and design our components.
It allows us to design the components without a backend. In order to start Storybook execute:

`$ npm run storybook`

### Creating a story
When creating a new component please create a corresponding story in the `/stories` directory.

Example:
```bash
/src/components/common/Button.jsx
/src/stories/common/Button.stories.js
```

We want to mirror our components folder in the stories folder. This is to ensure it is easy to follow and navigate both.

You begin each story by importing the component at the top. Afterwards you define a default
export function defining the component and the arg types for the props. This is to use Storybook controls.
Afterwards you create a default template that define how the component is used. This default template can then
be edited for different story version. (See example code below)

```js
// ExampleComponent.stories.js

// Import your Component
import React from 'react';
import { ExampleComponent } from "./ExampleComponent";

// Export a default function
// This is where you define your components and what props are needed
// All other argTypes can be found here: https://storybook.js.org/docs/react/essentials/controls#annotation
export default {
    title: 'Guides/Example Component',
    component: ExampleComponent,
    argTypes: {
        // Add a color selection
        color: { control: 'color' },
        // Add a select dropdown (Can be removed and set in the React Component)
        size: { control: { type: 'select', options: ['text-sm', 'text-lg', 'text-2xl'] } },
        // Actions can be used to mock functions. More info: https://storybook.js.org/docs/react/essentials/actions
        testFunction: { action: "testFunction" }
    },
};

// Create a default Template
const Template = (args) => <ExampleComponent {...args} />;

// For each version of the Component create a new story
export const Default = Template.bind({});
// You can change the args and props to modify the story here
Default.args = {
    label: 'Test Text',
    color: 'black'
};
```

More helpful links: [Storybook Getting started guide](https://storybook.js.org/docs/react/writing-stories/introduction)