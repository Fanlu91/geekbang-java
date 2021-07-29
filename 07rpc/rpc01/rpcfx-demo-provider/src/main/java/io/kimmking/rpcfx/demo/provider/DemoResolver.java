package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
//        System.out.println("context.getBeanDefinitionNames() ===>> " + String.join(",", applicationContext.getBeanDefinitionNames()));
        return this.applicationContext.getBean(serviceClass);
    }

    @Override
    public Object resolve(Class<?> clazz) {
        return this.applicationContext.getBean(clazz);
    }

    // 改成泛型和反射
//    @Override
//    public Object resolve(String serviceClass) throws IOException, ClassNotFoundException {
//
//        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(serviceClass.getBytes()));;
//        Object object = objectInputStream.readObject();
//        objectInputStream.close();
//        return this.applicationContext.getBean(object.getClass());
//    }
}
