import axios from "axios"
axios.defaults.baseURL = "http://localhost:8080/api/"
axios.defaults.withCredentials=true

export interface LoginParams {
    name: string,
    password: string
}

export interface Account {
    number: string,
    name: string,
}

export const login = async (params: LoginParams): Promise<Account> => {
    await axios.post("/login", params)
    return {number: "0001", name: params.name}
}

export const register = async (params: LoginParams): Promise<Account> => {
    return await axios.post("/register", params) as Account
}