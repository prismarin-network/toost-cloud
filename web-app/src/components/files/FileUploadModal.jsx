import {Modal} from "@/components/common/Modal";
import {useState} from "react";
import {useDropzone} from 'react-dropzone';
import {RiCloseLine, RiFile3Line} from 'react-icons/ri'
import {FcAddImage} from 'react-icons/fc'
import {BaseButton} from "@/components/common/BaseButton";

export const FileUploadModal = ({ isOpen, toggleModal }) => {
    const [files, setFiles] = useState([]);
    const [loading, setLoading] = useState(false)

    const {getRootProps, getInputProps} = useDropzone({
        noKeyboard: true,
        onDrop: acceptedFiles => {
            setLoading(true)
            acceptedFiles.map(file => {
                files.push(file)
                if (file.type.startsWith('image')) {
                    Object.assign(file, { preview: URL.createObjectURL(file) })
                }
            })
            setLoading(false)
        }
    });

    const removeImage = (fileName) => {
        setFiles(files.filter(file => {
            if (file.name === fileName) {
                URL.revokeObjectURL(file.preview);
            }
            return file.name !== fileName
        }))
    }

    return (
        <Modal title="Upload:" toggleModal={() => toggleModal} isOpen={isOpen} classes="max-w-3xl">
            {loading && (
                <h1>Test</h1>
            )}
            <header {...getRootProps({className: 'dropzone'})}>
                <div className="border-dashed border-2 border-gray-400 py-12 flex flex-col justify-center items-center my-4">
                    <input {...getInputProps()} />
                    <p className="mb-3 font-semibold text-gray-900 flex flex-wrap justify-center">
                        <span>Drag and drop your</span>&nbsp;<span>files anywhere or</span>
                    </p>
                    <BaseButton type="secondary" classes="text-sm focus:ring-0 shadow-none">
                        Upload a file
                    </BaseButton>
                </div>
            </header>
            <div>
                <h4 className="text-lg font-semibold">
                    Files:
                </h4>
                <div className="w-full grid grid-cols-5 gap-4 my-2">
                    {files.map(file => (
                        <div key={file.name} className="w-32 overflow-hidden text-center">
                            <div className="w-32 h-32 overflow-hidden relative rounded-lg">
                                <div className="absolute z-20 h-full w-full opacity-0 hover:opacity-100 transition duration-300 ease-in-out">
                                    <div className="bg-blue-400 opacity-70 h-full w-full" />
                                    <RiCloseLine onClick={() => removeImage(file.name)} className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 text-6xl text-white cursor-pointer" />
                                </div>
                                {file.type.startsWith("image") ? (
                                    <img className="object-cover w-full h-full" key={file.name} src={file.preview} alt={file.name}/>
                                ): (
                                    <div className="w-full h-full bg-red-500">
                                        <RiFile3Line className="absolute z-10 top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 text-6xl text-white cursor-pointer" />
                                    </div>
                                )}
                            </div>
                            <a className="text-gray-500 text-sm font-semibold">
                                {file.name}
                            </a>
                        </div>
                    ))}
                </div>
                {files.length === 0 && (
                    <div className="text-center mb-10">
                        <FcAddImage className="block mx-auto text-8xl" />
                        <h1 className="text-xl my-2 text-gray-400">
                            You have not uploaded any files yet.
                        </h1>
                    </div>
                )}
            </div>
            <footer className="flex items-center justify-end pt-4">
                <BaseButton onClick={toggleModal}>
                    Cancel
                </BaseButton>
                <BaseButton disabled={!files.length > 0} type="primary" classes="ml-1">
                    Upload
                </BaseButton>
            </footer>
        </Modal>
    )
}