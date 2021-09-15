import {TestComponent} from "../components/TestComponent";
import React from "react";

export default {
    title: 'TestComponent',
    component: TestComponent,
};

const Template = (args) => <TestComponent {...args} />;

export const Primary = Template.bind({});