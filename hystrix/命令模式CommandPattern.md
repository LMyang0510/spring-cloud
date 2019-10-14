## 命令模式
### 简介
    百度百科：在软件系统中，“行为请求者”与“行为实现者”通常呈现一种“紧耦合”，但在某些场合，
             比如要对行为进行“记录、撤销/重做、事务”等处理，这种无法抵御变化的紧耦合是不合适的。
             将一组行为抽象为对象，实现二者之间的松耦合。这就是命令模式（Command Pattern）。
    
    菜鸟教程：命令模式（Command Pattern）是一种数据驱动的设计模式，它属于行为型模式。
             请求以命令的形式包裹在对象中，并传给调用对象。调用对象寻找可以处理该命令的合适的对象，
             并把该命令传给相应的对象，该对象执行命令。
             
    意图：    将一个请求封装成一个对象，从而使您可以用不同的请求对客户进行参数化。
    
    主要解决：在软件系统中，行为请求者与行为实现者通常是一种紧耦合的关系，但某些场合，
             比如需要对行为进行记录、撤销或重做、事务等处理时，这种无法抵御变化的紧耦合的设计就不太合适。
             
    何时使用：在某些场合，比如要对行为进行"记录、撤销/重做、事务"等处理，这种无法抵御变化的紧耦合是不合适的。
             在这种情况下，如何将"行为请求者"与"行为实现者"解耦？将一组行为抽象为对象，可以实现二者之间的松耦合。
             
    如何解决：通过调用者调用接受者执行命令，顺序：调用者→接受者→命令。
    
    关键代码：定义三个角色：1、received 真正的命令执行对象 2、Command 3、invoker 使用命令对象的入口
    
    优点：    1、降低了系统耦合度。 2、新的命令可以很容易添加到系统中去。
    
    缺点：    使用命令模式可能会导致某些系统有过多的具体命令类。
    
    使用场景：认为是命令的地方都可以使用命令模式，比如：1、GUI 中每一个按钮都是一条命令。 2、模拟 CMD。
    
    注意事项：系统需要支持命令的撤销(Undo)操作和恢复(Redo)操作，也可以考虑使用命令模式，见命令模式的扩展。
    
### 模式结构
    Command：        定义命令的接口，声明执行的方法。
    ConcreteCommand：命令接口实现对象，是“虚”的实现；通常会持有接收者，并调用接收者的功能来完成命令要执行的操作。
    Receiver：       接收者，真正执行命令的对象。任何类都可能成为一个接收者，只要它能够实现命令要求实现的相应功能。
    Invoker：        要求命令对象执行请求，通常会持有命令对象，可以持有很多的命令对象。
                     这个是客户端真正触发命令并要求命令执行相应操作的地方，也就是说相当于使用命令对象的入口。
    Client：         创建具体的命令对象，并且设置命令对象的接收者。注意这个不是我们常规意义上的客户端，而是在组装命令对象和接收者，
                     或许，把这个Client称为装配者会更好理解，因为真正使用命令的客户端是从Invoker来触发执行。
![演示图](https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=84dd4058e71190ef15f69a8daf72f673/574e9258d109b3de9d6a74ddccbf6c81810a4ca9.jpg)

 ### 实现
    我们首先创建作为命令的接口 Order，然后创建作为请求的 Stock 类。
    实体命令类 BuyStock 和 SellStock，实现了 Order 接口，将执行实际的命令处理。创建作为调用对象的类 Broker，它接受订单并能下订单。
    Broker 对象使用命令模式，基于命令的类型确定哪个对象执行哪个命令。CommandPatternDemo，我们的演示类使用 Broker 类来演示命令模式。
![演示图](https://www.runoob.com/wp-content/uploads/2014/08/command_pattern_uml_diagram.jpg)

#### 创建一个命令接口 Order.java
```java
public interface Order {
   void execute();
}
```

#### 创建一个请求类 Stock.java
```java
public class Stock {
   
   private String name = "ABC";
   private int quantity = 10;
 
   public void buy(){
      System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
   }
   public void sell(){
      System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] sold");
   }
}
```

#### 创建实现了 Order 接口的实体类 BuyStock.java 和 SellStock.java
```java
public class BuyStock implements Order {
   private Stock abcStock;
 
   public BuyStock(Stock abcStock){
      this.abcStock = abcStock;
   }
 
   public void execute() {
      abcStock.buy();
   }
}
public class SellStock implements Order {
   private Stock abcStock;
 
   public SellStock(Stock abcStock){
      this.abcStock = abcStock;
   }
 
   public void execute() {
      abcStock.sell();
   }
}
```

#### 创建命令调用类 Broker.java
```java
public class Broker {
   private List<Order> orderList = new ArrayList<Order>(); 
 
   public void takeOrder(Order order){
      orderList.add(order);      
   }
 
   public void placeOrders(){
      for (Order order : orderList) {
         order.execute();
      }
      orderList.clear();
   }
}
```

#### 使用 Broker 类来接受并执行命令 CommandPatternDemo.java
```java
public class CommandPatternDemo {
   public static void main(String[] args) {
      Stock abcStock = new Stock();
 
      BuyStock buyStockOrder = new BuyStock(abcStock);
      SellStock sellStockOrder = new SellStock(abcStock);
 
      Broker broker = new Broker();
      broker.takeOrder(buyStockOrder);
      broker.takeOrder(sellStockOrder);
 
      broker.placeOrders();
   }
}
```