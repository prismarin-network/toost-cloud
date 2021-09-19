import axios from "axios"

let api = axios.create({
    baseURL: process.env.NEXT_PUBLIC_API_URL,
    timeout: 3000,
    headers: {'Content-Type': 'application/json'}
});

export default api