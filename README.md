# ThinkingInJava
Thinking In Java Source Code

#### 1、文件过滤器
实现FilenameFilter接口来对文件做出过滤

#### 2、关与给匿名类需要传入final参数问题
匿名内部类编译时，会单独生成一个class文件，该类持有外部类的引用，传入的参数都会自动复制一份。那么传入的参数 与 内部类实际使用的参数就不是相同的一个参数了，
为了避免出现问题，所以规定匿名内部类使用的外部参数一定要是final类型的。

#### 3、文件工具类 Directory.java
该文件工具类可根据正则表达式返回文件夹或者文件树目录下的所有符合条件的文件集合

#### 4、文件输入输出流
这里的输入输出的概念要以程序本身为对象进行考虑，输入是指输入到程序当中，输出是指程序输出到别的地方 
##### 4.1 输入流数据源 
字节数组、String对象、文件、管道(暂时没有接触过)、一个由其他种类的流组成的序列、网络
每一个数据源都有其对应的子类
ByteArrayInputStream(字节数组，字节数组即代表内存缓冲区，允许将内存缓冲区当作inputstream使用)
StringBufferInputStream(将string转换成为inputStream, 底层使用stringBuffer)
FileInputStream
PipedInputStream
SequenceInputStream（将两个或者多个inputstream当作一个inputstream）
FilterInputStream

任何自inputstream 或者reader派生而来的类都有read方法，用于读取单个字节或者字节数组

##### 4.2 输出流
任何来自outputstream或者writer派生而来的类都有write方法，用于写单个字节或者字节数组
ByteArrayOutputStream
FileOutputStream
PipedOutputStream
FilterOuputStream

##### 4.3 输入输出流当中的装饰器模式
在一般的装饰器模式当中，一般是被装饰类与装饰类都实现同一个接口，而装饰类当中一般会持有被装饰类的一个实例，
该实例一般是在装饰类的构造函数中传入。

输入输出流当中的装饰者模式与以上有些不同，FilterInputStream是所有装饰器类的基类、FilterInputStream继承inputStream，
而且FilterInputStream中会持有一个inputStream对象，其实这个inputStream对象就是被修饰的对象，装饰类继承FilterInputStream,
在各自的构造函数当中，会把被装饰器对象(inputStream对象)传进来。由于是inputStream对象，所以所有的inputStream的派生类
都可以被装饰。

装饰器类 DataInputStream  BufferInputStream(防止每次读取时候都进行写的操作) 
LineNumberInputStream(跟踪输入流当中的行号，set或者get) PushbackInputStream
以上列举的装饰器类都是在已经存在的流之上进行操作的

##### 4.4 我们输入中使用的 System.out.print操作的实现
所有的方法实际上是通过PrintStream当中的write方式实现的，而这个write方式实际上是对一个outStream对象进行修改
```$xslt
    private void write(String s) {
        try {
            synchronized (this) {
                ensureOpen(); //确认outputStream对象不为null
                textOut.write(s);
                textOut.flushBuffer();
                charOut.flushBuffer();
                if (autoFlush && (s.indexOf('\n') >= 0))
                    out.flush();
            }
        }
        catch (InterruptedIOException x) {
            Thread.currentThread().interrupt();
        }
        catch (IOException x) {
            trouble = true;
        }
    }
```

这个输出字符串 主要是对两个对象进行操作，一个是BufferedWriter textOut， 一个是OutputStreamWriter charOut, 
平时我们调用的System.out中的out对象，是一个PrintStream对象， PrintStream我们知道了是继承FilterStream的，
也是个装饰类，具体也是对FilterOutputStream中持有的outputStream对象进行操作，具体怎么实现的，我们看一下out的

首先在System当中有out的初始化：
```$xslt
FileOutputStream fdOut = new FileOutputStream(FileDescriptor.out);
setOut0(newPrintStream(fdOut, props.getProperty("sun.stdout.encoding")));
```
其中FileDescriptor.out对象是这样的：
`public static final FileDescriptor out = standardStream(1);`
其中：
```$xslt
    private static FileDescriptor standardStream(int fd) {
        FileDescriptor desc = new FileDescriptor();
        desc.handle = set(fd);
        return desc;
    }
```
可以看到outputStream描述符是1，这里是作为out的初始化

看到这里就差不多了，我们可以自己写一个System的out实现简单的输出：
```$xslt
FileOutputStream fileOutputStream = new FileOutputStream(FileDescriptor.out);
Properties myPro = new Properties();
PrintStream myOut = new PrintStream(new BufferedOutputStream(fileOutputStream, 128), true);;
myOut.println("Test");

//假如我关掉的话，后面就再也不会输出了
try {
    fileOutputStream.close();
    myOut.close();
} catch (IOException e) {
    e.printStackTrace();
}
System.out.println("这里是不会输出的");
```










