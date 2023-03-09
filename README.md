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
[@951294416](https://github.com/951294416)
[@ice-chenyi](https://github.com/ice-chenyi)


## 如何贡献

非常欢迎你的加入！[提一个 Issue](https://github.com/mason369/future/issues) 或者提交一个
Pull Request。

Progressive-Tune 遵循 [Contributor Covenant](http://contributor-covenant.org/version/1/3/0/) 行为规范。

### 贡献者

<!-- readme: collaborators,contributors -start -->
<!-- readme: collaborators,contributors -end -->

**_感谢以上参与项目的人_**

---

## 快速上手

环境尽量请按开头的环境标签配置好，否则可能会出现各种问题。

安装依赖：





---

### 特别感谢：

[IntelliJ WebStorm](https://zh.wikipedia.org/zh-hans/IntelliJ_IDEA) 是一款在各方面最大限度地提高开发人员生产力的 IDE，适用于前端平台语言。

<code><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/WebStorm_icon.png?_gl=1*10616q8*_ga*MTEwMzE4MDQwOS4xNjU0NzQ0NjIw*_ga_9J976DJZ68*MTY1NTA5NzcyOC4yLjEuMTY1NTA5ODE3Ni42MA..&_ga=2.237879491.294686240.1655097729-1103180409.1654744620" style="width:200px; "/></code>

[Node.js](https://nodejs.org/en/) 对一些特殊用例进行优化，提供替代的 API，使得 V8 在非浏览器环境下运行得更好，V8 引擎执行 Javascript 的速度非常快，性能非常好，基于 Chrome
JavaScript 运行时建立的平台， 用于方便地搭建响应速度快、易于扩展的网络应用。

<code><img src="https://nodejs.org/static/images/logo.svg" style="width:200px; "/></code>

[Visual Studio Code](https://code.visualstudio.com/)在 Windows、macOS 和 Linux 上运行的独立源代码编辑器。
JavaScript 和 Web 开发人员的最佳选择，具有几乎可支持任何编程语言的扩展。

<code><img src="https://visualstudio.microsoft.com/wp-content/uploads/2019/09/vs-code-responsive-01-1.png" style="width:200px; "/></code>

[Vue.js](https://cn.vuejs.org/)前端先进的渐进式 JavaScript 框架,易学易用，性能出色，适用场景丰富的 Web 前端框架。

<code><img src="https://cn.vuejs.org/logo.svg" style="width:200px; " /></code>

## 使用许可

[Apache License 2.0](LICENSE) © Richard Littauer
