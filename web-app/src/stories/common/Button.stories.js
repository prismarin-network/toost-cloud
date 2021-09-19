import React from 'react'
import {BaseButton} from "@/components/common/BaseButton";

export default {
    title: 'Common/Button',
    component: BaseButton,
    argTypes: {
        disabled: false,
        click: { actions: "clicked" },
    }
}

const Template = (args) => <BaseButton {...args}>Button</BaseButton>;

export const Default = Template.bind({});
Default.args = {
    disabled: false,
};

export const Disabled = Template.bind({});
Disabled.args = {
    disabled: true,
};