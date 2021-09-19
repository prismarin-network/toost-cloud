import {Modal} from "../common/Modal";
import {useState} from "react";
import {useDropzone} from 'react-dropzone';
import {RiFolderAddLine, RiCloseLine} from 'react-icons/ri'

export const FileUploadModal = ({ isOpen }) => {
    const [files, setFiles] = useState([]);
    const [loading, setLoading] = useState(false)

    const {getRootProps, getInputProps} = useDropzone({
        noKeyboard: true,
        accept: 'image/*',
        onDrop: acceptedFiles => {
            setLoading(true)
            acceptedFiles.map(file => {
                files.push(file)
                Object.assign(file, { preview: URL.createObjectURL(file) })
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
        <Modal title="Upload File:" isOpen={isOpen} classes="max-w-3xl">
            {loading && (
                <h1>Test</h1>
            )}
            <header {...getRootProps({className: 'dropzone'})}>
                <div className="border-dashed border-2 border-gray-400 py-12 flex flex-col justify-center items-center my-4">
                    <input {...getInputProps()} />
                    <p className="mb-3 font-semibold text-gray-900 flex flex-wrap justify-center">
                        <span>Drag and drop your</span>&nbsp;<span>files anywhere or</span>
                    </p>
                    <button id="button"
                            className="mt-2 rounded-sm px-3 py-1 bg-gray-200 hover:bg-gray-300 focus:shadow-outline focus:outline-none">
                        Upload a file
                    </button>
                </div>
            </header>
            <div>
                <h4 className="text-md font-semibold">
                    Files:
                </h4>
                <div className="w-full grid grid-cols-5 gap-4 my-2">
                    {files.map(file => (
                        <div key={file.name} className="w-32 h-32 overflow-hidden relative rounded-lg">
                            <div className="absolute h-full w-full opacity-0 hover:opacity-100 transition duration-300 ease-in-out">
                                <div className="bg-blue-400 opacity-70 h-full w-full" />
                                <RiCloseLine onClick={() => removeImage(file.name)} className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 text-6xl text-gray-900 cursor-pointer" />
                            </div>
                            <img className="object-cover w-full h-full" key={file.name} src={file.preview} alt={file.name}/>
                        </div>
                    ))}
                </div>
                {files.length === 0 && (
                    <div className="text-center text-gray-400 mb-10">
                        <RiFolderAddLine className="block mx-auto text-8xl" />
                        <h1 className="text-xl my-2">
                            You have not uploaded any files yet.
                        </h1>
                    </div>
                )}
            </div>
            <footer>

            </footer>
        </Modal>
    )
}