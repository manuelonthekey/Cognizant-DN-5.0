import java.lang.reflect.Method;
public class Q39_ReflectionInJava {
    public void printSecret() { System.out.println("Secret Method Invoked!"); }
    
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("Q39_ReflectionInJava");
            Object obj = clazz.getDeclaredConstructor().newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("Method found: " + method.getName());
                if (method.getName().equals("printSecret")) {
                    method.invoke(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}