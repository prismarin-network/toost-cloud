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

const Template = (args) =>  <FileUploadModal {...args} />;

export const Default = Template.bind({});
Default.args = {
    isOpen: true,
};
