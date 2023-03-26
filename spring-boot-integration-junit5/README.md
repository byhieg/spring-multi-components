# 说明
springboot集成junit5

- 使用mockio方式，这种方式不需要启动spring容器，对象的方法执行都采用mock的结果。
- 不使用mockio方式，这种方式以测试的方式启动spring容器。controller测试方法使用TestRestTemplate或者MOCKMVC。其他等方法都使用Junit5 + Spring

每个源码包中的类，相对的测试方法都在test目录下。

## 数据源测试

数据源测试采用两种方式。

- 测试方法直接加载实际代码中mysql数据源测试
- 测试方法采用h2数据库，每个测试方法执行时，都初始化h2数据库。为了隔离测试和实际代码，h2数据库的测试类加载单独的测试环境的application

