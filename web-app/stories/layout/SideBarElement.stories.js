import React from 'react';
import {SideBarElement} from "../../components/layout/SideBarElement";
import { RiHome3Line } from "react-icons/ri"

export default {
    title: 'Layout/SideBarElement',
    component: SideBarElement,
    argTypes: {
        selected: false,
    }
};

const Template = (args) =>  <div className="w-64"><SideBarElement {...args} /></div>;

export const Default = Template.bind({});
Default.args = {
    label: "Home",
    icon: <RiHome3Line />,
    selected: true,
};
