<!-- ![](https://img.shields.io/badge/%E7%8A%B6%E6%80%81-%E7%BB%B4%E6%8A%A4%E4%B8%AD-brightgreen) -->
<!--![](https://img.shields.io/badge/%E7%8A%B6%E6%80%81-%E5%B0%B1%E7%BB%AA-orange)-->

![](https://img.shields.io/badge/%E7%8A%B6%E6%80%81-%E7%BB%B4%E6%8A%A4%E4%B8%AD-brightgreen)
![](https://img.shields.io/badge/spring-2.4.2-green)

<h2>内容列表</h2>

- [Git 规范](#git-规范)
    - [Git 分支命名](#git-分支命名)
- [Git Commit Message 格式](#git-commit-message-格式)
    - [type 提交类型：](#type-提交类型)
    - [subject 提交描述](#subject-提交描述)
- [相关仓库](#相关仓库)
- [维护者](#维护者)
- [如何贡献](#如何贡献)
    - [贡献者](#贡献者)
- [快速上手](#快速上手)
    - [特别感谢：](#特别感谢)
- [使用许可](#使用许可)

## Git 规范

### Git 分支命名

-   `masin`：主分支，负责记录上线版本的迭代，该分支代码与线上代码是完全一致的。
-   `develop`：开发分支，该分支记录相对稳定的版本，所有的 feature 分支和 bugfix 分支都从该分支创建。其它分支为短期分支，其完成功能开发之后需要删除
-   `feature/*`：特性（功能）分支，用于开发新的功能，不同的功能创建不同的功能分支，功能分支开发完成并自测通过之后，需要合并到 develop 分支，之后删除该分支。
-   `bugfix/*`：bug 修复分支，用于修复不紧急的 bug，普通 bug 均需要创建 bugfix 分支开发，开发完成自测没问题后合并到 develop 分支后，删除该分支。
-   `release/*`
    ：发布分支，用于代码上线准备，该分支从 develop 分支创建，创建之后由测试发布到测试环境进行测试，测试过程中发现 bug 需要在该 release 分支上进行 bug 修复，所有 bug 修复完后，上线之前，需要合并该 release 分支到 masin 分支和 develop 分支。
-   `hotfix/`\*：紧急 bug 修复分支，该分支只有在紧急情况下使用，从 masin 分支创建，用于紧急修复线上 bug，修复完成后，需要合并该分支到 masin 分支以便上线，同时需要再合并到 develop 分支。

## Git Commit Message 格式

<h2>type : subject</h2>

### type 提交类型：

-   `revert:` 回滚代码
-   `feat:` 新特性
-   `fix:` 错误修复
-   `improvement:` 功能改进
-   `docs:` 文档修改
-   `perf:`性能修改
-   `test:`测试
-   `refactor:` 代码重构
-   `build:` 其他修改, 比如构建流程, 依赖管理

### subject 提交描述

**对应内容是 commit 目的的简短描述，不超过 50 个字符**

---

## 维护者

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

---

## 快速上手

环境尽量请按开头的环境标签配置好，否则可能会出现各种问题。

安装依赖：





---

### 特别感谢：

[IntelliJ_IDEA](https://www.jetbrains.com/zh-cn/idea/) 是一款在各方面最大限度地提高开发人员生产力的 IDE，适用于Java平台语言。

<code><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA_icon.png?_ga=2.177568869.1594575276.1678373380-1840121525.1678373377&_gl=1*jqpgqc*_ga*MTg0MDEyMTUyNS4xNjc4MzczMzc3*_ga_9J976DJZ68*MTY3ODM3MzM3Ny4xLjEuMTY3ODM3MzUwOS4wLjAuMA.." style="width:200px; "/></code>

[Spring Boot](https://spring.io/)Spring Boot 是一个用于快速构建基于 Spring 框架的应用程序的工具。它通过自动配置和约定优于配置的原则，简化了 Spring 应用程序的开发流程。Spring Boot 可以快速地创建独立的、生产级别的 Spring 应用程序，而不需要过多的配置和样板代码。它还提供了一组开箱即用的功能，如嵌入式 Web 服务器、健康检查、安全性、监控和管理等。

通过 Spring Boot，开发人员可以更加专注于业务逻辑，而不是配置和部署问题。它还支持各种集成，包括 JPA、JDBC、NoSQL 数据库、消息队列、缓存等等。Spring Boot 还拥有一个庞大的社区，开发人员可以很容易地获取帮助和支持。总之，Spring Boot 是一个功能强大、易于使用、高效、可扩展的框架，可以让开发人员更加专注于业务，快速构建高质量的应用程序。

<code><img src="https://4.bp.blogspot.com/-ou-a_Aa1t7A/W6IhNc3Q0gI/AAAAAAAAD6Y/pwh44arKiuM_NBqB1H7Pz4-7QhUxAgZkACLcBGAs/s1600/spring-boot-logo.png" style="width:200px; "/></code>

[MySQL8.0](https://www.mysql.com/cn/)MySQL 8.0是一种流行的关系型数据库管理系统，是MySQL系列的最新版本。它是一个开源的、可靠的、高性能的数据库，广泛应用于Web应用程序、企业应用程序、云平台等各种场景。

<code><img src="https://upload.wikimedia.org/wikipedia/zh/thumb/6/62/MySQL.svg/1200px-MySQL.svg.png" style="width:200px; "/></code>

[Redis](https://redis.io/)Redis以内存作为数据存储介质，可以实现超高速读写操作，同时支持将数据持久化到硬盘上，以便在重启后可以快速地恢复数据。此外，Redis还提供了许多高级功能，例如事务处理、Lua脚本、发布/订阅等，以及对分布式环境下的数据存储和缓存提供了完整的支持。

<code><img src="https://www.stackery.io/assets/images/posts/redis-cache-cluster-support/featured.svg" style="width:200px; " /></code>

## 使用许可

[Apache License 2.0](LICENSE) © Richard Littauer
