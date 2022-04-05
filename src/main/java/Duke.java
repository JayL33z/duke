import ui.Ui;
import storage.Storage;
import exception.DukeException;

import commands.Command;
import commands.AddCommand;
import commands.DeleteCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import commands.MarkCommand;
import commands.UnknownCommand;
import commands.ByeCommand;
import commands.ViewCommand;
import commands.FindCommand;


import tasklist.Task;
import tasklist.TaskList;
import tasklist.Todo;
import tasklist.Event;
import tasklist.Deadline;

import parser.Parser;



public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage); //Object variables in Java always point to the real object in the memory heap. A mutable object’s value can be changed when it is passed to a method.
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}