import React from 'react';
import {SideBar} from "@/components/layout/SideBar";

export default {
    title: 'Layout/SideBar',
    component: SideBar,
};

const Template = (args) =>  <SideBar {...args} />;

export const Default = Template.bind({});
