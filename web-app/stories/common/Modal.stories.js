import React from 'react';
import  { Modal } from "../../components/common/Modal";

export default {
    title: 'Common/Modal',
    component: Modal,
    argTypes: {
        isOpen: true,
        closeModal: { actions: "closeModal" },
        openModal: { actions: "openModal" },
    }
};

const Template = (args) => <Modal {...args} />;

export const Default = Template.bind({});
Default.args = {
    isOpen: true,
};
