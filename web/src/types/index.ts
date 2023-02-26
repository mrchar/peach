export interface Account {
    number: string,
    name: string,
    user: User | null,
}

export interface User {
    name: string,
    gender: string,
    birthday: Date,
    phoneNumber: string,
    emailAddress: string
}