# 桃子 Peach

我将使用全栈技术实现尽可能多的功能，以验证我有能力实现这些功能，并将这个项目作为实现这些功能的演示示例。

## 技术栈

现在正是验证最新技术栈的最佳时期，Vue3 框架和工具链已经趋于稳定，越来越多的项目已经开始向 Vue3+TypeScript 迁移，借助 Componsition Api，Vue3 可以更好的实现代码复用。

正在此时，SpringBoot3 和 SpringFramework6 也已经正式发布，越来越多的 Spring 库也相继发布了适配 SpringBoot3 的版本，SpringAuthorizationServer 也已经发布了正式版，这说明 Spring 进入了一个新的时代。

同时在 Spring 的推动下，Java17 将成为下一个代替 Java8 的默认版本，所以是时候以崭新的姿态开始新的开发了。

需要注意的是，我将尽可能避免使用 SpringCloud 下的组件，因为我们发现，微服务架构正在趋向于使用编排工具实现 SpringCloud 的功能。显而易见的是 SpringCloud 只适用于 Java 的技术栈，虽然 Java 依然是最流行的网络服务开发技术，但是我并不想因为这个原因放弃使用其他编程语言的可能性。很多优秀的框架何苦在未来很有可能会代替 Java 中的最佳实践，比如使用 Casdoor 实现认证中心显然比使用 SpringAuthorizationServer 实现认证中心要简单的多。

### 前端技术栈

- Vite
- Vue3
- TypeScript
- TailwindCSS
- ElementPlus

### 后端技术栈

- Java17
- Gradle
- Spring6
- SpringBoot3
- Liquibase
- SpringDataJPA

### 服务编排

- Docker Compose

## 看板

| 领域     | 功能     | 计划中 | 开发中 | 已完成 |
| -------- | -------- | ------ | ------ | ------ |
| 基础功能 | 注册登录 | x      |        |        |
|          | 用户管理 | x      |        |        |
|          | 角色管理 | x      |        |        |
|          | 权限管理 | x      |        |        |
| 公共功能 | 即时通讯 | x      |        |        |
|          | 视频直播 | x      |        |        |
| 业务功能 | 订单管理 | x      |        |        |
|          | 商品管理 | x      |        |        |
|          | 快递管理 | x      |        |        |

## 迭代计划

| 阶段   | 版本号 | 功能     | 计划工时 | 已用工时 | 开始时间 | 完成时间 |
| ------ | ------ | -------- | -------- | -------- | -------- | -------- |
| 发布前 | V0.1   | 注册登录 | 8        |          |          |          |
|        | V0.2   | 用户管理 | 8        |          |          |          |
|        |        | 角色管理 | 8        |          |          |          |
|        | V0.3   | 即时通讯 | 16       |          |          |          |