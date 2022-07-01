# world of zuul游戏

# 项目介绍

​	world of zuul 是一个基于字符界面的探险游戏，在老师所给的基本案例的基础之上，我们进行了基于SpringBoot+vue的开发。在增加游戏功能的同时，以小组的形式进一步掌握该实践课程内容。

# 开发目标

## 软件架构设计

### 后端技术

|    技术    |     说明     |                        官网                         |
| :--------: | :----------: | :-------------------------------------------------: |
| SpringBoot | 容器+MVC框架 |       https://spring.io/projects/spring-boot        |
|  MyBatis   |   ORM框架    |   http://www.mybatis.org/mybatis-3/zh/index.html    |
|   MySQL    |    数据库    |               https://www.mysql.com/                |
|  MongoDB   | NoSql数据库  | [https://www.mongodb.com](https://www.mongodb.com/) |

### 前端技术

|    技术    |     说明     |                         官网                          |
| :--------: | :----------: | :---------------------------------------------------: |
|    Vue     |   前端框架   |                  https://vuejs.org/                   |
| Vue-router |   路由框架   |               https://router.vuejs.org/               |
|  Element   |  前端UI框架  | [https://element.eleme.io](https://element.eleme.io/) |
|   Axios    | 前端HTTP框架 |            https://github.com/axios/axios             |

## 开发工具

|      工具       |       说明        |
| :-------------: | :---------------: |
|      IDEA       |      开发IDE      |
|    Studio 3T    | MongoDB可视化工具 |
| MySQL Workbench |  MySQL可视化工具  |
|      Figma      |   原型设计工具    |
|     Typora      |  Markdown编辑器   |
|     Github      |   项目托管平台    |

## 功能扩展设计

+ 扩展房间功能，每个房间随机生成任意数量的物件，物件有描述和重量值，玩家进入一个房间后，可以通过“look”命令查看当前房间的信息以及房间内的所有物品信息。
+ 在游戏中实现一个“back”命令，玩家输入该命令后把玩家带回上一个房间。
+ 在游戏中增加具有传输功能的房间，每当玩家进入房间，会被随机地传输到另一个房间。
+ 在游戏中新建一个独立的Player类来表示玩家：
  + 保存玩家的姓名等基本信息，保存当前所在房间；
  + 玩家可以随身携带任意数量物件，但总重量不能超过某个上限值；
  + 新命令“take”和"drop"，玩家可以拾取房间内的指定物品或丢弃身上携带的某件物品；
  + 在房间内随机增加物品，例如魔法饼干，能够利用“use”命令进行使用，增加玩家负重能力。

+ 增加图形化用户界面，可以通过图形化界面执行游戏功能。
+ 增加数据库功能，保存游戏状态和用户设置。

# 分配开发任务

## 小组成员分工

|      姓名      | 代码开发 |       github管理       |    文档编写    |
| :------------: | :------: | :--------------------: | :------------: |
| 戴芯瑜（队长） | 前端开发 | 发布issue，编写actions |    实验报告    |
|     李文丽     | 前端开发 |                        |                |
|     张婉柔     | 后端开发 |        分支管理        |                |
|     罗雯欣     | 后端开发 |      编写actions       | REPORT.md；PPT;实验报告 |

## 里程碑设置

​	通过里程碑页面能够看到里程碑的到期时间和完成百分比，可以对一段时间的工作情况进行检测跟踪小组进度。

​    本小组创建了四个里程碑，其中Milestone Test是为了学习和测试Milestone和issue的功能。其余三个里程碑则正式投入使用。通过里程碑我们将实践的开发任务分为了三个部分：构思与项目框架构建、项目功能开发与项目迭代优化与测试。

​    其中项目功能开发为实践项目的主要部分，其中会发布**每日的功能开发任务**供小组成员提交以跟踪功能开发进度。**构思及优化里程碑则不发布每日issue不指定委托人，而是发布功能或模块的小组issue，当完成后由组长进行关闭。**


## issue发布

​	通过发布issue将里程碑目标进行细化，给出小组成员的每日任务。

​	Issue使得项目成员可以在进行开发的 GitHub 上跟踪工作，同时issue不限人员留言，可以在发布issue以及提交之后留下自己的疑问便于讨论。

​	Issue首先被分为两类：一类是小组issue主要包括整体功能的构思或测试。这类issue由小组成员共同完成，在任务完成之后由涉及成员提交相关结果，之后组长在确认之后进行关闭。


​	另一类issue为日常任务。主要管理小组成员每天的任务分配。在每周讨论之后，由组长发出一周每日的日常任务。与上类不同的是，这类issue会分配受托人，并且给出task标签标记为日常任务


# 分支开发与集成

## 分支设置

+ master 分支（主分支）——稳定版本。除了从publish分支进行merge，不接受任何其它修改。在master分支上合并提交都必须有Tag， Tag标签用于标识每次生产发布操作。
+ develop 分支（开发分支）——提测版本。该分支主要作为集成测试分支，所有的测试环境部署该分支进行测试。同时也保持着最新版本的代码，与feature不断merge以支持之后的feature分支获得最新代码。
+ feature分支（特性分支）——功能分支。该分支主要用于功能开发，该分支每次都从develop分支拉取创建，开发完成完提测合并到develop分支，之后便可以将分支删除以保证分支的简洁。
+ publish 分支（发布分支）——发布新版本分支类型。都是测试验证通过后从develop分支拉取创建，用于预生产环境、生产环境发布。对于最终确定的版本可以发布Tag通过Actions配置自动发布打包放置在publish分支中。

## 分支管理

* 初始化。首先组长在master分支的基础上分出develop、publish分支。之后在develop分支的基础上选择第一个功能分出feature分支。
* 开发。小组在本地进行开发，在自测没有问题之后，发起merge到feature分支请求，并给出commit，标签为feat（feature缩写）。
* 合并。将一个功能开发完全时，就发起请求，将feature分支的内容merge到develop分支中，在完成合并之后就将feature分支进行删除。
* 测试。在每一次push到feature分支上时，都能够通过actions进行代码的格式检测和运行测试，只有完成测试时才能够push到GitHub，否则将会被退回。
* 上线。通过git发布tag，能够使用actions自动打包到publish分支上，使得publish分支始终保持一个最新版本。在完成打包之后就可以拉出一个新的feature功能分支。

# 自动规范检查与测试

## 自动规范检查代码格式

参考博客：https://lindexi.blog.csdn.net/article/details/110531566?spm=1001.2101.3001.6650.4&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-4-110531566-blog-119813756.pc_relevant_paycolumn_v3&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-4-110531566-blog-119813756.pc_relevant_paycolumn_v3&utm_relevant_index=9

+ 命名，设置脚本的触发时机
+ 指定运行在哪个设备上，并将当前分支的最新代码拉下来，对代码配置指定Java环境
+ 安装dotnet format工具，安装dotnet环境，如果出现格式问题则使用编码规范工具尝试修复代码文件夹里面的文本文件的编码
+ 利用机器人账号进行commit，在此代码审核后推送到当前分支

## 自动规范测试

​	在命名与下载代码配置环境处与之前相同，在此不再赘述。

+ 之后首先要与关系型数据库相连，通过指定版本与端口等信息还原本地运行环境。
+ 再与非关系型数据库MongoDB相连，同样需要输入端口，指定数据库等参数。
+ 进行maven自动化测试检查

# 自动打包与发布

参考博客：https://www.cnblogs.com/ixysy/p/12720134.html

https://blog.csdn.net/qq_41091006/article/details/124703709

​	命名、连接数据库同自动规范测试，在此不再赘述。

+ 采用Maven进行打包，将打包好的Jar包放到staging 文件夹
+ 通过git命令给出版本号触发自动发布
+ 将版本号与jar包结合放入发布分支中
