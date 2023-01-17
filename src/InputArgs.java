public class InputArgs {

    public String inputF;
    public String outputF;
    public String[] args;
    public boolean gui = false;

    public InputArgs(String[] args) {
        this.args = args;
    }

    public boolean makeArguments(){
        inputF = null;
        outputF = null;
        if(args.length > 0){
            for (int i=0; i<args.length; i+=2){
                if(args[i].equals("-g") || args[i].equals("--gui")){
                    gui = true;
                    i--;
                }
                else if (args[i].equals("-i") || args[i].equals("--input")){
                    inputF = args[i+1];

                } else if (args[i].equals("-o") || args[i].equals("--output")){
                    outputF = args[i+1];
                }
                else{
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
