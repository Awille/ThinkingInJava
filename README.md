# ThinkingInJava
Thinking In Java Source Code

#### 1、文件过滤器
实现FilenameFilter接口来对文件做出过滤

#### 2、关与给匿名类需要传入final参数问题
匿名内部类编译时，会单独生成一个class文件，该类持有外部类的引用，传入的参数都会自动复制一份。那么传入的参数 与 内部类实际使用的参数就不是相同的一个参数了，
为了避免出现问题，所以规定匿名内部类使用的外部参数一定要是final类型的。


