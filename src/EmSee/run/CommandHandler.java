package EmSee.run;

public class CommandHandler {
    public CommandHandler(){}

    String commandList ="--Commands--\n" +
            "?/help: Displays a list of all commands.\n" +
            "exit: terminates the program.\n"
            ;
    public void RunCommand(String command) {
        String[] args = command.split(" ");
        if(args[0].equals("help")||args[0].equals("?")){System.out.println(commandList);}
        else if(args[0].equals("exit")){System. exit(0);}
        else if(args[0].equals("add_sale")){Main.databaseHandler.addSale();}
        else{System.out.println("This command does not exist, type \"?\" for a list of all commands");}
        System.out.println("What is next?");
    }
}
