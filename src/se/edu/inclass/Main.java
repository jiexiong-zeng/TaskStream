package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);
        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        printDataWithStreams(tasksData);
        printDeadlinesWithStreams(tasksData);
        countDeadlinesWithStreams(tasksData);

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataWithStreams(ArrayList<Task> tasks) {
        System.out.println("Printing data using stream");
        tasks.stream()
                .forEach(System.out::println);
    }


    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }


    public static void printDeadlinesWithStreams(ArrayList<Task> tasks) {
        System.out.println("Printing deadline using stream");
        tasks.stream()
                .filter((task) -> task instanceof Deadline)
                .forEach(System.out::println);
    }


    private static void countDeadlinesWithStreams(ArrayList<Task> tasks) {
        System.out.println("Printing count with stream");
        int count = (int)tasks.stream()
                .filter((task) -> task instanceof Deadline)
                .count();
        System.out.println("Total number of deadlines: " + count);
    }

}
