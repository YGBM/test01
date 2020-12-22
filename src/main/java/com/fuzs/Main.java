package com.fuzs;

/**
 * 二 配置环境变量

打开配置文件 ： vi ~/.bash_profile 并填写下文内容
添加配置：
  # jdk1.6
  export JAVA_6_HOME=`/usr/libexec/java_home -v 1.6`
  # jdk1.8
  export JAVA_8_HOME=`/usr/libexec/java_home -v 1.8`
  
  # 默认 jdk 使用1.8版本
  export JAVA_HOME=$JAVA_8_HOME
 ...
  #切换 jdk 版本
  alias jdk6="export JAVA_HOME=$JAVA_6_HOME"
  alias jdk8="export JAVA_HOME=$JAVA_8_HOME"
3、切换环境：在终端输入jdk6 即可使用jkd1.6环境。输入jdk8即可使用jdk1.8.

作者：刍一束
链接：https://www.jianshu.com/p/92d03ef038b1
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
// -XX:+UseSerialGC -XX:-UseCompressedOops -Xms10m -Xmx10m
public class Main {
    public static void main(String[] args){
        Son son = new Son();
    }
}

class Father{}

class Son extends Father{
    private static final String COUNTRY="US";
}