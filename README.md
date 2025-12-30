# Algorithm Practice

一个系统化的算法学习和练习Java项目，涵盖LeetCode热门面试题150道，包含完善的数据结构实现和详细的算法题目解答。

## 📊 进度统计

<!-- PROGRESS_START -->
![总数](https://img.shields.io/badge/完成进度-23%2F150-red)
![简单](https://img.shields.io/badge/简单-9%2F40-green)
![中等](https://img.shields.io/badge/中等-12%2F92-orange)
![困难](https://img.shields.io/badge/困难-2%2F18-red)
<!-- PROGRESS_END -->


## 🔧 技术栈

- **Java 21** - 最新LTS版本，支持现代Java特性
- **JUnit 5 (Jupiter)** - 单元测试框架
- **Lombok 1.18.30** - 简化样板代码
- **Maven** - 项目构建和依赖管理

## 📚 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── org/
│   │       └── example/
│   │           ├── dataStructure/   # 数据结构定义
│   │           │   ├── ListNode.java       # 链表节点
│   │           │   ├── Node.java           # 普通节点
│   │           │   ├── QuadTreeNode.java   # 四叉树节点
│   │           │   ├── TreeNode.java       # 二叉树节点
│   │           │   └── Trie.java           # 前缀树
│   │           ├── solutions/       # 题目解答
│   │           │   └── TopInterview150.java
│   │           └── utils/           # 工具类
│   │               └── ReadmeUpdater.java  # README自动更新工具
│   └── resources/
│       └── docs/
│           └── TopInterview150.md   # 热门面试题150题清单
└── test/
    └── java/
        └── org/
            └── example/
                └── solutions/       # 单元测试
                    └── TopInterview150Test.java
```

## 📦 依赖

- **lombok**: 1.18.30 (编译时注解处理器，简化代码)
- **junit-jupiter**: 5.10.0 (单元测试框架)

## 🚀 快速开始

### 前置要求

- JDK 21 或更高版本
- Maven 3.6+

### 克隆项目

```bash
git clone <repository-url>
cd algorithm-practice
```

### 编译项目

```bash
mvn clean compile
```

### 运行测试

```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=TopInterview150Test

# 运行特定测试方法
mvn test -Dtest=TopInterview150Test#testMethodName
```

### 更新README进度

```bash
# 使用工具类自动更新README中的进度统计
mvn exec:java -Dexec.mainClass="org.example.utils.ReadmeUpdater"
```

## 📋 题目清单

详细的题目列表请查看：
- [热门面试题150题](src/main/resources/problemsList/TopInterview150.md)

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

