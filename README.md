<!-- ![](https://img.shields.io/badge/%E7%8A%B6%E6%80%81-%E7%BB%B4%E6%8A%A4%E4%B8%AD-brightgreen) -->
<!--![](https://img.shields.io/badge/%E7%8A%B6%E6%80%81-%E5%B0%B1%E7%BB%AA-orange)-->

![](https://img.shields.io/badge/%E7%8A%B6%E6%80%81-%E7%BB%B4%E6%8A%A4%E4%B8%AD-brightgreen)
![](https://img.shields.io/badge/spring-2.4.2-green)

<h2>内容列表</h2>

[TOC]
# FutureBack_end - 后端项目

## 一、项目概述
本项目是一个模仿Discord和Telegram的纯后端项目，旨在构建一个功能强大、安全可靠的通信与协作平台后端服务。通过运用多种先进技术，包括Java、Spring Boot、Spring Security、Lombok、mybatis注解开发、MySQL、JWT、redis二级缓存以及端到端加密技术，为用户提供高效、安全的通信体验，如实时消息传递、用户管理、群组管理等功能。

## 二、技术栈
- **Java 100.0%**：作为项目的主要开发语言，充分发挥其面向对象编程的优势，构建稳定、高效的后端逻辑。
- **Spring Boot**：用于快速搭建项目框架，自动配置各种组件，简化项目的初始化和开发过程，提高开发效率，使开发者能够专注于业务逻辑实现。
- **Spring Security**：保障系统的安全性，实现用户认证、授权和权限管理等功能，防止非法访问和数据泄露，确保系统资源的安全使用。
- **Lombok**：通过注解简化Java代码编写，自动生成常用方法，减少样板代码，提高代码的简洁性和可读性，同时降低代码维护成本。
- **MyBatis注解开发**：方便与MySQL数据库进行交互，高效执行SQL语句，实现数据的持久化操作，确保数据的存储、查询、更新和删除等功能的准确实现。
- **MySQL 8.0**：作为关系型数据库，存储系统的各类数据，如用户信息、消息记录、群组信息等，保证数据的完整性、一致性和持久性。
- **JWT（JSON Web Tokens）**：用于用户认证和授权，用户登录成功后获取JWT Token，在后续请求中携带Token进行身份验证，实现无状态认证，提高系统的可扩展性和安全性。
- **Redis二级缓存**：利用Redis的高性能缓存特性，将频繁访问的数据缓存到Redis中，减少数据库查询压力，提高系统响应速度，优化用户体验，同时支持数据的持久化存储。
- **端到端加密技术**：确保用户通信内容的安全性，只有通信双方能够解密和读取消息，防止信息在传输过程中被窃取或篡改，有效保护用户隐私。（还是实践中）

## 三、核心功能
1. **用户管理**
    - 用户注册与登录（JWT认证）：用户可通过注册功能创建账号，注册时需提供必要信息，如用户名、密码、邮箱等。登录时采用JWT认证机制，确保用户身份安全，成功登录后获取Token用于后续请求验证。
    - 用户信息管理：用户可修改个人信息，如头像、昵称、密码等，同时系统管理员可对用户信息进行全面管理，包括查询、禁用、启用等操作，确保用户信息的安全和合规。
    - 基于角色的权限控制：定义了不同的用户角色，如普通用户、管理员等，每个角色具有不同的操作权限。普通用户可以进行基本的通信操作，如发送消息、加入群组等；管理员则拥有对用户、群组、消息等的全面管理权限。
2. **消息管理**
    - 实时消息传递：支持用户之间的实时消息发送和接收，确保消息的及时性和准确性，采用高效的消息推送机制，提高通信效率。
    - 消息存储与查询：所有消息记录将被存储到数据库中，用户可以查询历史消息记录，方便回顾和查找重要信息。
    - 消息加密与解密（端到端加密技术）：在消息传输过程中，对消息内容进行端到端加密，只有发送方和接收方能够解密和查看消息，确保通信内容的安全性和隐私性。
3. **群组管理**
    - 群组创建与管理：用户可以创建群组，并邀请其他用户加入。群主和管理员可以对群组进行管理，如设置群组名称、头像、公告等信息，管理群组成员，包括添加、移除成员等操作。
    - 群组聊天功能：群组成员可以在群组内进行实时聊天，发送文字、图片、文件等多种类型的消息，实现群组内的高效协作和沟通。
4. **好友管理（可选功能）**
    - 好友添加与删除：用户可以搜索并添加其他用户为好友，建立好友关系后，方便进行一对一的通信和互动。同时，用户可以删除好友关系。
    - 好友列表展示：展示用户的好友列表，方便用户快速找到好友并进行聊天或其他操作。

## 四、项目结构
- **.github**：存放与项目相关的GitHub配置文件，如工作流程文件、问题模板等，有助于规范项目的GitHub管理流程。
- **.mvn/wrapper**：包含Maven Wrapper相关文件，确保项目在不同环境下能正确使用Maven构建，方便项目的依赖管理和构建操作。
- **src**：项目的源代码目录，包含业务逻辑、数据访问、控制器等代码。
    - **main/java**：存放Java源文件，按照功能模块划分包结构，使代码结构清晰、易于维护。
    - **main/resources**：包含配置文件，如Spring配置文件、MyBatis配置文件、数据库连接配置文件等，以及静态资源文件，如图片、样式文件等（如果有）。
- **.gitignore**：指定不被Git版本控制系统跟踪的文件和目录，避免不必要的文件被纳入版本管理，保持项目仓库的整洁。
- **README.md**：项目的说明文档，提供项目的概述、技术栈、功能、使用方法等信息，方便其他开发者了解和使用项目。
- **mvnw**及**mvnw.cmd**：Maven Wrapper脚本，用于在未安装Maven的环境中执行项目构建命令，提高项目的可移植性。
- **pom.xml**：Maven项目对象模型文件，用于管理项目依赖关系、插件配置等，确保项目所需的库和工具能够正确引入。

## 五、Git规范
1. **Git分支命名**
    - **masin**：主分支，负责记录上线版本的迭代，该分支代码与线上代码是完全一致的，是项目的稳定版本发布分支。
    - **develop**：开发分支，记录相对稳定的版本，所有的特性（feature）分支和bug修复（bugfix）分支都从该分支创建，是项目的主要开发集成分支。
    - **feature/***：特性（功能）分支，用于开发新的功能，不同的功能创建不同的功能分支，功能分支开发完成并自测通过之后，需要合并到develop分支，之后删除该分支，以保持项目分支的清晰和整洁。
    - **bugfix/***：bug修复分支，用于修复不紧急的bug，普通bug均需要创建bugfix分支开发，开发完成自测没问题后合并到develop分支后，删除该分支，便于问题的跟踪和解决。
    - **release/***：发布分支，用于代码上线准备，该分支从develop分支创建，创建之后由测试发布到测试环境进行测试，测试过程中发现bug需要在该release分支上进行bug修复，所有bug修复完后，上线之前，需要合并该release分支到masin分支和develop分支，确保上线版本的稳定性和准确性。
    - **hotfix/***：紧急bug修复分支，该分支只有在紧急情况下使用，从masin分支创建，用于紧急修复线上bug，修复完成后，需要合并该分支到masin分支以便上线，同时需要再合并到develop分支，及时修复线上问题并同步到开发主线。
2. **Git Commit Message格式**
    - **type : subject**
        - **type提交类型**：
            - **revert**: 回滚代码
            - **feat**: 新特性
            - **fix**: 错误修复
            - **improvement**: 功能改进
            - **docs**: 文档修改
            - **perf**: 性能修改
            - **test**: 测试
            - **refactor**: 代码重构
            - **build**: 其他修改, 比如构建流程, 依赖管理
        - **subject提交描述**：对应内容是commit目的的简短描述，不超过50个字符，简洁明了地说明本次提交的目的。

## 六、快速上手
1. **环境准备**
    - 确保安装JDK 8或更高版本，保证Java运行环境正常。
    - 安装Maven 3.6及以上版本，用于项目构建和依赖管理。
    - 安装MySQL 8.0数据库，并创建相应的数据库和表结构（可参考项目中的数据库初始化脚本，如有）。
    - 安装Redis并启动Redis服务，用于缓存数据和实现一些高级功能，如消息队列（如果有使用）等。
2. **项目安装与运行**
    - 克隆项目代码到本地：`git clone https://github.com/Futureluxe/FutureBack_end.git`。
    - 进入项目目录：`cd FutureBack_end`。
    - 使用Maven构建项目：`mvn clean install`。
    - 运行项目：可以通过在IDE中运行主类（通常是包含main方法的类）启动项目，或者使用Maven命令`mvn spring-boot:run`启动。项目启动后，默认监听在本地的某个端口（如8080），可通过浏览器或其他工具访问接口进行测试（如`http://localhost:8080/api-docs`可查看接口文档，若使用Swagger生成文档）。


## 七、维护者与贡献者

### 维护者

[@daoxuan233](https://github.com/daoxuan233)
</br>
[@951294416](https://github.com/951294416)
</br>
[@ice-chenyi](https://github.com/ice-chenyi)


## 如何贡献

非常欢迎你的加入！[提一个 Issue](https://github.com/Futureluxe/FutureBack_end/issues) 或者提交一个
Pull Request。

Progressive-Tune 遵循 [Contributor Covenant](http://contributor-covenant.org/version/1/3/0/) 行为规范。

### 贡献者

<!-- readme: collaborators,contributors -start -->
<table>
<tr>
    <td align="center">
        <a href="https://github.com/daoxuan233">
            <img src="https://avatars.githubusercontent.com/u/102936237?v=4" width="100;" alt="daoxuan233"/>
            <br />
            <sub><b>DaoXuan</b></sub>
        </a>
    </td></tr>
</table>
<!-- readme: collaborators,contributors -end -->

**_感谢以上参与项目的人_**


## 八、特别感谢

[IntelliJ_IDEA](https://www.jetbrains.com/zh-cn/idea/) 是一款在各方面最大限度地提高开发人员生产力的 IDE，适用于Java平台语言。

<code><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA_icon.png?_ga=2.177568869.1594575276.1678373380-1840121525.1678373377&_gl=1*jqpgqc*_ga*MTg0MDEyMTUyNS4xNjc4MzczMzc3*_ga_9J976DJZ68*MTY3ODM3MzM3Ny4xLjEuMTY3ODM3MzUwOS4wLjAuMA.." style="width:200px; "/></code>

[Spring Boot](https://spring.io/)Spring Boot 是一个用于快速构建基于 Spring 框架的应用程序的工具。它通过自动配置和约定优于配置的原则，简化了 Spring 应用程序的开发流程。Spring Boot 可以快速地创建独立的、生产级别的 Spring 应用程序，而不需要过多的配置和样板代码。它还提供了一组开箱即用的功能，如嵌入式 Web 服务器、健康检查、安全性、监控和管理等。

通过 Spring Boot，开发人员可以更加专注于业务逻辑，而不是配置和部署问题。它还支持各种集成，包括 JPA、JDBC、NoSQL 数据库、消息队列、缓存等等。Spring Boot 还拥有一个庞大的社区，开发人员可以很容易地获取帮助和支持。总之，Spring Boot 是一个功能强大、易于使用、高效、可扩展的框架，可以让开发人员更加专注于业务，快速构建高质量的应用程序。

<code><img src="https://4.bp.blogspot.com/-ou-a_Aa1t7A/W6IhNc3Q0gI/AAAAAAAAD6Y/pwh44arKiuM_NBqB1H7Pz4-7QhUxAgZkACLcBGAs/s1600/spring-boot-logo.png" style="width:200px; "/></code>

[MySQL8.0](https://www.mysql.com/cn/)MySQL 8.0是一种流行的关系型数据库管理系统，是MySQL系列的最新版本。它是一个开源的、可靠的、高性能的数据库，广泛应用于Web应用程序、企业应用程序、云平台等各种场景。

<code><img src="https://upload.wikimedia.org/wikipedia/zh/thumb/6/62/MySQL.svg/1200px-MySQL.svg.png" style="width:200px; "/></code>

[Redis](https://redis.io/)Redis以内存作为数据存储介质，可以实现超高速读写操作，同时支持将数据持久化到硬盘上，以便在重启后可以快速地恢复数据。此外，Redis还提供了许多高级功能，例如事务处理、Lua脚本、发布/订阅等，以及对分布式环境下的数据存储和缓存提供了完整的支持。

<code><img src="https://www.stackery.io/assets/images/posts/redis-cache-cluster-support/featured.svg" style="width:200px; " /></code>

## 九、使用许可

[Apache License 2.0](LICENSE) © Richard Littauer
