package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);
        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        printdeadlinesWithLambdas(tasksData);
        ArrayList<Task> filteredList = filterTaskbyString(tasksData,"2");
        printDeadlines(filteredList);
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

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printdeadlinesWithLambdas(ArrayList<Task> tasksData) {
        tasksData.stream()
                .filter(task -> task instanceof Deadline)
                .sorted((a, b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);

    }


    public static ArrayList<Task> filterTaskbyString(ArrayList<Task> tasksData , String filterStream) {
        ArrayList<Task> filteredList;
        filteredList = (ArrayList<Task>) tasksData.stream()
                .filter(task -> task.getDescription().contains(filterStream))
                .collect(Collectors.toList());

        return filteredList;
    }

}
