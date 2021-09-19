import React from 'react';
import {FileUploadModal} from "@/components/files/FileUploadModal";

export default {
    title: 'Files/FileUploadModal',
    component: FileUploadModal,
    argTypes: {
        isOpen: true,
        toggleModal: { actions: "toggleModal" },
    }
};

const Template = (args) =>  <div className="h-screen w-full bg-gray-300"><FileUploadModal {...args} /></div>;

export const Default = Template.bind({});
Default.args = {
    isOpen: true,
};
