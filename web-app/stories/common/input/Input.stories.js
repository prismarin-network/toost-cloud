import React from 'react';
import {Input} from "../../../components/common/input/Input";

export default {
    title: 'Common/Input/Base',
    component: Input,
};

const Template = (args) => <Input {...args} /> ;

export const Default = Template.bind({});
Default.args = {
    type: "text",
    placeholder: "",
    classes: ""
};
