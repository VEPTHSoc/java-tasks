import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task extends Thread{

    private int number;
    public Task(int number){
        this.number = number;
    }
    public void run(){
        System.out.println("Task" + number + "Started");
        for(int i= 0; i<= 10; i++){
            System.out.println("T" + number);
        }

        System.out.println("Task"+ number + "Done");
    }
} 

public class task1 { 
    public static void main(String[] args) {
       ExecutorService executorService = Executors.newFixedThreadPool(3);
       executorService.execute(new Task(1));
       executorService.execute(new Task(2));
       executorService.shutdown();
    }


   
}
