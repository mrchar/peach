/**
 * 账户信息
 */
export interface Account {
    number: string, // 账户编号
    name: string, // 账户名称
    user: User | null, // 账户所有者的信息
}

/**
 * 用户信息
 */
export interface User {
    name: string, // 用户名称
    gender: string, // 用户性别
    birthday: string  // 用户生日
    phoneNumber: string, // 手机号码
    emailAddress: string // 电子邮箱地址
}

export interface Profile extends User {
    accountNumber: string,
    accountName: string
}