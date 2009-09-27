package a.b.c;
import   java.io.*;   
  public   class   ThreadTest{   
        public   static   void   main(String   args[])throws   Exception{   
        Process   p=Runtime.getRuntime().exec("tasklist");   
                System.out.println(p);   
          
        BufferedReader   bw=new   BufferedReader(new   InputStreamReader(p.getInputStream()));   
       // Thread.currentThread().sleep(10000);   
        System.out.println(bw.readLine());   
                          System.out.println(bw.readLine());   
        System.out.println(bw.readLine());   
        System.out.println(bw.readLine());   
        System.out.println(bw.readLine());   
        System.out.println(bw.readLine());   
        }   
  } 