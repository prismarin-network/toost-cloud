import React from 'react';
import {InputLabel} from "../../../components/common/input/InputLabel";

export default {
    title: 'Common/Input/Label',
    component: InputLabel,
};

const Template = (args) => <InputLabel {...args} /> ;

export const Default = Template.bind({});
Default.args = {
    label: "Default",
    type: "text",
    placeholder: "",
    classes: "",
};
