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
