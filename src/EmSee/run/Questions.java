package EmSee.run;

public class Questions {
    public Questions(){}

    public boolean YesOrNo(String question) {
        boolean toReturn = false;
        System.out.println(question +"\n(y/n)");
        while (true){
            String answer = Main.scanner.nextLine();
            if(answer.equals("y")) {
                toReturn=true;
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("answer with y/n");
            }
        }
        return toReturn;
    }

    public String string(String question) {
        boolean toReturn = false;
        System.out.println(question);
        return Main.scanner.nextLine();
    }
}
