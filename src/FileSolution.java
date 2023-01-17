import java.io.*;
import java.util.ArrayList;

public class FileSolution {

    public static int[] stringArrayToIntArray(String[] userList){
        int[] ans = new int[userList.length];
        for(int i = 0; i < userList.length; i++){
            ans[i] = Integer.parseInt(userList[i].trim());
        }
        return ans;
    }
    public static int countLines(String userFile) throws Exception{
        int i = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(userFile))) {
            for(String line; (line = br.readLine()) != null; ) {
                i++;
            }
        }
        return i;
    }
    public static int[][] readFile(String userFile){
        try {
            int[][] ans = new int[countLines(userFile)][];
            int i = 0;
            BufferedReader br = new BufferedReader(new FileReader(userFile));
            for(String line; (line = br.readLine())!=null; i++)

                {
                    ans[i] = (stringArrayToIntArray(line.split(",")));
                }


            return ans;
        }
        catch (Exception e){
            return null;
        }
    }

    public static void writeFile(int[][] answerArray, String pathToOutput) {
        File outputFile = new File(pathToOutput);
        try {
            outputFile.createNewFile();
        }
        catch (IOException e){
            System.err.println("Error while creating file");
        }

        try{
            FileOutputStream fw = new FileOutputStream(outputFile);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fw));
            for(int i = 0; i < answerArray.length; i++){
                for(int j = 0; j < answerArray[i].length; j++){
                    bw.append(answerArray[i][j] + " ");
                }
                bw.newLine();
            }
            bw.close();
        }
        catch (FileNotFoundException e){
            System.err.println("Error while creating FileOutputStream");
        }
        catch (IOException e){
            System.err.println("Error while editing file");
        }
    }
}
