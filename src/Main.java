import java.awt.*;
import java.util.Locale;

public class Main {

    public static InputArgs parseCmdArgs(String[] args){
        InputArgs cmd = new InputArgs(args);
        if(!cmd.makeArguments()){
            return null;
        }
        return cmd;
    }

    public static void main(String[] args) {

        InputArgs userData = parseCmdArgs(args);
        if(userData != null) {
            if(!userData.gui){
                if(userData.inputF != null){
                    try {
                        int[][] readySolution = Solution.solution(FileSolution.readFile(userData.inputF));
                        FileSolution.writeFile(readySolution, userData.outputF);
                    }
                    catch (Exception e){
                        System.err.println("Haven't got input file");
                    }
                }
                else {
                    System.err.println("Haven't got input file");
                }
            } else if (userData.gui) {
                new MainFrame();
            }
        }
        else{
            System.err.println("Arguments error!");
        }

    }
}