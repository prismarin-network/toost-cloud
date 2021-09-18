import React from 'react';
import SidebarLayout from "../../layout/SideBarLayout";

export default {
    title: 'Layout/SideBarLayout',
    component: SidebarLayout,
    argTypes: {
        dark: true,
    }
};

const Template = (args) =>  <div className={args.dark ? "dark" : ""}><SidebarLayout>Test Content</SidebarLayout></div>;

export const Default = Template.bind({});