import React from 'react';
import {Modal} from "@/components/common/Modal";

export default {
    title: 'Common/Modal',
    component: Modal,
    argTypes: {
        isOpen: true,
        toggleModal: { actions: "toggleModal" },
    }
};

const Template = (args) => <div className="h-screen w-full bg-gray-300"> <p className="text-2xl font-bold text-center">Test Text</p> <Modal {...args}> Test Test </Modal></div>;

export const Default = Template.bind({});
Default.args = {
    isOpen: true,
};
