public class testCounter {
   public static void main(String[]args) {
   
   BasicCounter basic = new BasicCounter();
   
   System.out.println(basic.value());
   basic.up();
   System.out.println(basic.value());
   basic.down();
   System.out.println(basic.value());
   for (int i = 0; i < 10; i++) {
   basic.up(); }
   System.out.println(basic.value());
   basic.reset();
   System.out.println(basic.value());
   
   System.out.println("SQUARE");
   
   SquareCounter square = new SquareCounter();
   
   System.out.println(square.value());
   square.up();
   System.out.println(square.value());
   square.down();
   System.out.println(square.value());
   for (int i = 0; i < 3; i++) {
   square.up(); }
   System.out.println(square.value());
   square.reset();
   square.down();
   System.out.println(square.value());
   
   System.out.println("FLEXIBLE");
   FlexibleCounter flex = new FlexibleCounter(-10,3);
   
   System.out.println(flex.value());
   flex.up();
   System.out.println(flex.value());
   flex.down();
   System.out.println(flex.value());
   for (int i = 0; i < 10; i++) {
   flex.up(); }
   System.out.println(flex.value());
   flex.reset();
   System.out.println(flex.value());
   }
}