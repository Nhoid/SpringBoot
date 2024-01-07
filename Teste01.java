import Classes.PrintTask;
import Classes.user;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Teste01
{
    public static void main(String[] args)
    {
        PrintTask task1 = new PrintTask("Task1");
        PrintTask task2 = new PrintTask("Task2");
        PrintTask task3 = new PrintTask("Task3");
        PrintTask task4 = new PrintTask("Task4");
        System.out.println("Começando o executor");
        ExecutorService exe = Executors.newCachedThreadPool();

        exe.execute(task1);
        exe.execute(task2);
        exe.execute(task3);

        exe.shutdown();

        System.out.println("Fim da main");
    }
}
