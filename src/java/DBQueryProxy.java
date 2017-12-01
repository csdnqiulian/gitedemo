import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class DBQueryProxy implements InvocationHandler{

	
	   // 代理目标对象  
    private Object target;  
  
    // 创建目标对象的代理对象  
    public  Object createProxyInstance(Object target) {  
        // 代理的目标对象  
        this.target = target;  
        // 创建代理对象  
        // 1、定义代理类的类加载器  
        // 2、代理类要实现的接口列表  
        // 3、 指派方法调用的调用处理程序  
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this);  
    }
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		 System.out.println("在调用之前，我要干点啥呢？");  
		  
        System.out.println("Method:" + method);  
        Object returnValue = method.invoke(target, args);  
        System.out.println("在调用之后，我要干点啥呢？");  
        return returnValue;  
	}

}
