package gui;

import application.WriteSettings;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import structures.TaskSettings;

import java.io.File;

public class Toolbar {
    private Stage primaryStage;
    private MenuBar bar;

    private MainGUI parent;

    public Toolbar(Stage primaryStage, MainGUI parent){
        this.primaryStage = primaryStage;
        this.parent = parent;
        this.makeBar();
    }

    public MenuBar getBar(){
        return this.bar;
    }

    private void makeBar(){
        Menu file = new Menu("File");
        MenuItem newTaskButton = new MenuItem("New Task");
        newTaskButton.setOnAction(evt -> {
            this.parent.getTaskList().addToTaskList("Unnamed");
        });
        MenuItem saveTasks = new MenuItem("Save");
        //TODO: IMPLEMENT SAVE BUTTON FUNCTIONALITY
        saveTasks.setOnAction(evt -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter(
                    "DAT file(*.dat)", "*.dat"
            );
            fileChooser.getExtensionFilters().add(ext);
            fileChooser.setTitle("Save");
            File saveFile = fileChooser.showSaveDialog(primaryStage);
            if(saveFile != null){
                WriteSettings ws = new WriteSettings();
                ws.serializeObject(new TaskSettings(parent.getTaskList()),
                        saveFile.getAbsolutePath());
            }
        });

        MenuItem loadTasks = new MenuItem("Load");
        //TODO: IMPLEMENT LOAD BUTTON FUNCTIONALITY
        MenuItem exitButton = new MenuItem("Exit");
        exitButton.setOnAction(evt -> {
            //TODO: IMPLEMENT RUNNING WHILE CLOSED MECHANISM
            System.exit(0);
        });
        file.getItems().addAll(newTaskButton, saveTasks, loadTasks, exitButton);

        Menu edit = new Menu("Edit");
        MenuItem sortTasks = new MenuItem("Sort Alphabetically");
        sortTasks.setOnAction(evt -> {
            this.parent.getTaskList().sortAlphabetically();
        });
        edit.getItems().add(sortTasks);

        Menu help = new Menu("Help");

        // Add Menu items here

        this.bar = new MenuBar(file, edit, help);
    }
}
