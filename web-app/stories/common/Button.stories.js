import React from 'react'
import { Button } from "../../components/common/Button";

export default {
    title: 'Common/Button',
    component: Button,
    argTypes: {
        disabled: false,
        click: { actions: "clicked" },
    }
}

const Template = (args) => <Button {...args}>Button</Button>;

export const Default = Template.bind({});
Default.args = {
    disabled: false,
    classes: 'bg-red-300'
};

export const Disabled = Template.bind({});
Disabled.args = {
    disabled: true,
};