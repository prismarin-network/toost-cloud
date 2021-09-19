import React from 'react';
import {FileUploadModal} from "../../components/files/FileUploadModal";

export default {
    title: 'Files/FileUploadModal',
    component: FileUploadModal,
    argTypes: {
        isOpen: true,
        closeModal: { actions: "closeModal" },
        openModal: { actions: "openModal" },
    }
};

const Template = (args) =>  <FileUploadModal {...args} />;

export const Default = Template.bind({});
Default.args = {
    isOpen: true,
};
