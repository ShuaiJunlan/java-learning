 ### JDK代理和cglib代理

> JDK实现动态代理需要实现类通过接口定义业务方法，对于没有接口的类，如何实现动态代理呢，这就需要CGLib了。CGLib采用了非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。JDK动态代理与CGLib动态代理均是实现Spring AOP的基础。

#### JDK Proxy

 * JDK动态代理的原理是根据定义好的规则，用传入的接口创建一个新类，这就是为什么采用动态代理时为什么只能用接口引用指向代理，而不能用传入的类引用执行动态类。

JDK 动态生成的代理类具有几个特点：

- 继承 Proxy 类，并实现了在Proxy.newProxyInstance()中提供的接口数组。
- 命名方式为 $ProxyN，其中N会慢慢增加，一开始是 $Proxy1，接下来是$Proxy2...
- 有一个参数为 InvocationHandler 的构造函数。这个从 Proxy.newProxyInstance() 函数内部的clazz.getConstructor(new Class[] { InvocationHandler.class }) 可以看出。

Java 实现动态代理的缺点：因为 Java 的单继承特性（每个代理类都继承了 Proxy 类），只能针对接口创建代理类，不能针对类创建代理类。

#### CGlib

 * CGLib采用的是用创建一个继承实现类的子类，用asm库动态修改子类的代码来实现的，所以可以用传入的类引用执行代理类
 * CGLib创建的动态代理对象性能比JDK创建的动态代理对象的性能高不少，但是CGLib在创建代理对象时所花费的时间却比JDK多得多，所以对于单例的对象，因为无需频繁创建对象，用CGLib合适，反之，使用JDK方式要更为合适一些。同时，由于CGLib由于是采用动态创建子类的方法，对于final方法，无法进行代理

 #### References
 * [cglib 教程 - 使用cglib实现动态代理](https://www.jianshu.com/p/e983ecf3e7a5)