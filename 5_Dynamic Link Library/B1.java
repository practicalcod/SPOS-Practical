import java.io.*;
import java.util.*;

class B1{
	static{
		System.loadLibrary("B1");
	}
	private native int add(int a,int b);
	private native int sub(int a,int b);	
	private native int mult(int a,int b);	
	private native int div(int a,int b);
	public static void main(String [] args){
		Scanner sc= new Scanner (System.in);
		int a,b,ch;
		System.out.println("\n enter value of a");
		a=sc.nextInt();
		System.out.println("\n enter value of b");
		b=sc.nextInt();
		do{
			System.out.println("\n enter your choice");
			ch=sc.nextInt();
			switch(ch){
				case 1: new B1().add(a,b);
				break;
				case 2:new B1().sub(a,b);
				break;
				case 3: new B1().mult(a,b);
				break;
				case 4:new B1().div(a,b);
				break;
				default:System.out.println("your choice is wrong");
			}
		}while(ch<5);
	}
	
}


//cd Documents/
//ls
//javac B1.java
//		ls
//javac -h . -classpath . B1.java
//		ls
//gcc -shared -FPIC -I/usr/lib/jvm/java-11-openjdk-amd64/include -I/usr/lib/jvm/java-11-openjdk-amd64/include/linux B1.c -o libB1.so
//java -Djava.library.path=. B1






// cd Documents/
// ls
// javac B1.java
// ls
// javac -h . -classpath . B1.java
// ls
// gcc -shared -FPIC -I/usr/lib/jvm/java-11-openjdk-amd64/include -I/usr/lib/jvm/java-11-openjdk-amd64/include/linux B1.c -o libB1.so
// java -Djava.library.path=. B1