import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.nio.file.Paths;
import java.nio.file.*;

class TheMap {
    public static HashMap<String, String> mp = new HashMap<>();

    public static void setMap(String key, String value) {
        mp.put(key, value);
    }
}

// class writeToFile {
//     public static void write(Map<String, String> map, String outputFile) {
//         File file = new File("results.txt");
//         BufferedWriter bf = null;
//         try {
//             bf = new BufferedWriter(new FileWriter(file));
//             for (Map.Entry<String, String> entry : TheMap.mp.entrySet()) {
//                 bf.write(entry.getKey() + ":" + entry.getValue());
//                 bf.newLine();
//             }
//         } catch (IOException e) {
//             System.out.println(e);
//         } finally {
//             try {
//                 System.out.println("File Written Successfully");
//                 bf.close();
//             } catch (Exception e) {
//                 e.getStackTrace();
//             }
//         }
//     }
// }

class Task extends Thread {
    private String tx;
    private String hexValue;
    public Map<String, String> mp = new HashMap<>();

    public static String convertToHexString(String hexValue) {
        StringBuilder stringB = new StringBuilder();
        char[] charArray = hexValue.toCharArray();
        for (char c : charArray) {
            String charToHex = Integer.toHexString(c);
            stringB.append(charToHex);
        }
        return stringB.toString();
    }

    public Task(String text) {
        this.tx = text;
    }

    @Override
    public void run() {
        System.out.println("Converting " + tx + " to HEX");
        hexValue = convertToHexString(tx);
        TheMap.setMap(tx.toString(), hexValue.toString());
    }

    public String getValue() {
        return hexValue;
    }

}

public class task2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            Path fiile = Paths.get("sample2.txt");
            int count = (int) Files.lines(fiile).count();

            int i = 0;
            while (i < count) {
                try {
                    String line1 = Files.readAllLines(Paths.get("sample2.txt")).get(i).replaceAll("\\s+", "");
                    Task task1 = new Task(line1);
                    executorService.execute(task1);
                } catch (Exception e) {
                    System.out.println("File end");
                    break;
                }
                try {
                    String line2 = Files.readAllLines(Paths.get("sample2.txt")).get(i + 1).replaceAll("\\s+", "");
                    Task task2 = new Task(line2);
                    executorService.execute(task2);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("File end");
                    break;
                }
                try {
                    String line3 = Files.readAllLines(Paths.get("sample2.txt")).get(i + 2).replaceAll("\\s+", "");
                    Task task3 = new Task(line3);
                    executorService.execute(task3);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("File end");
                    break;
                }
                try {
                    String line4 = Files.readAllLines(Paths.get("sample2.txt")).get(i + 3).replaceAll("\\s+", "");
                    Task task4 = new Task(line4);
                    executorService.execute(task4);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("File end");
                    break;

                }
                try {
                    String line5 = Files.readAllLines(Paths.get("sample2.txt")).get(i + 4).replaceAll("\\s+", "");
                    Task task5 = new Task(line5);
                    executorService.execute(task5);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("File end");
                    break;
                }
                i += 5;
            }
            executorService.shutdown();
            TheMap.mp.forEach((key, value) -> {
                System.out.println(key + ": " + value);
            });
            File file = new File("results.txt");
            BufferedWriter bf = new BufferedWriter(new FileWriter(file));
            try {
                TheMap.mp.forEach((key, value) -> {
                    try {
                        bf.write(key + ": " + value);
                        bf.newLine();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    System.out.println("File Written Successfully");
                    bf.close();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.getStackTrace();
        }
    }
}
