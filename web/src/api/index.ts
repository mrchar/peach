export interface LoginParams {
    name: string,
    password: string
}

export interface Account {
    number: string,
    name: string,
}

export const login = async (params: LoginParams): Promise<Account> => {
    return {number: "0001", name: params.name}
}

export const register = async (params: LoginParams): Promise<Account> => {
    return {number: "0001", name: params.name}
}