import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import utils.JTableUtils;

    public class MainFrame {
        private JFrame mainFrame = new JFrame();
        public final int defaultButtonHeight = 25;
        public final int defaultButtonWidth = 150;
        public int[][] defaultArray = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        public int inputRows = 3;
        public int inputColumns = 3;
        public JTable inputTable = new JTable(inputRows, inputColumns);
        public JTable outputTable = new JTable(3, 3){
            @Override
            public boolean isCellEditable(int r, int c){
                return false;
            }
        };

        public JFileChooser jfc = new JFileChooser();
        public void readInputFileToTable(){
            int returnValue = jfc.showOpenDialog(null);
            if(returnValue ==JFileChooser.APPROVE_OPTION) {
                File userFile = jfc.getSelectedFile();
                try {
                    int [][] userArray = FileSolution.readFile(userFile.getAbsolutePath());
                    inputRows = userArray.length;
                    inputColumns = userArray[0].length;
                    JTableUtils.writeArrayToJTable(inputTable, userArray);
                } catch (Exception e) {
                    System.err.println("Error while reading file");
                }
            }
        }

        public void outputSolutionArray() {
            try{
                int[][] solutionArray = JTableUtils.readIntMatrixFromJTable(inputTable);
                solutionArray = Solution.solution(solutionArray);
                JTableUtils.writeArrayToJTable(outputTable, solutionArray);
            } catch (Exception e) {
                System.err.println("Error while writing array");
            }

        }
        public void outputToFile(){
            int returnValue = jfc.showOpenDialog(null);
            if(returnValue ==JFileChooser.APPROVE_OPTION) {
                File ansFile = jfc.getSelectedFile();
                try {
                    int[][] ansArray = JTableUtils.readIntMatrixFromJTable(outputTable);
                    FileSolution.writeFile(ansArray, ansFile.getAbsolutePath());
                } catch (Exception e) {
                    System.err.println("Error while reading file");
                }
            }
        }
        public JButton initJButton(String text){
            JButton returnButton = new JButton(text);
            returnButton.setMaximumSize(new Dimension(defaultButtonWidth, defaultButtonHeight));
            returnButton.setActionCommand(text);
            returnButton.addActionListener(new ButtonClickListener());
            return returnButton;
        }
        private class ButtonClickListener implements ActionListener {
            public void actionPerformed(ActionEvent evt) {
                String command = evt.getActionCommand();

                switch (command) {
                    case "Добавить столбец":
                        JTableUtils.resizeJTable(inputTable, inputRows, ++inputColumns);
                        break;
                    case "Добавить строку":
                        JTableUtils.resizeJTable(inputTable, ++inputRows, inputColumns);
                        break;
                    case "Удалить столбец":
                        JTableUtils.resizeJTable(inputTable, inputRows, --inputColumns);
                        break;
                    case "Удалить строку":
                        JTableUtils.resizeJTable(inputTable, --inputRows, inputColumns);
                        break;
                    case "Считать из файла":
                        readInputFileToTable();
                        break;
                    case "Старт":
                        outputSolutionArray();
                        break;
                    case "Записать в файл":
                        outputToFile();
                }
            }
        }
        public void setMainFrame(){
            mainFrame.setSize(500, 700);
            mainFrame.setLayout(new GridLayout(5, 1));



            JTableUtils.writeArrayToJTable(inputTable, defaultArray);
            JTableUtils.writeArrayToJTable(outputTable, defaultArray);


            JButton addColumn = initJButton("Добавить столбец");
            JButton addRow = initJButton("Добавить строку");
            JButton deleteColumn = initJButton("Удалить столбец");
            JButton deleteRow = initJButton("Удалить строку");
            Box buttonBox = new Box(BoxLayout.Y_AXIS);
            Box appendRowButtons = new Box(BoxLayout.X_AXIS);
            Box deleteRowButtons = new Box(BoxLayout.X_AXIS);

            JButton addArrayFromFile = initJButton("Считать из файла");
            JButton outputSolutionArray = initJButton("Старт");
            JButton writeArrayToFile = initJButton("Записать в файл");
            Box inputFunctions = new Box(BoxLayout.Y_AXIS);
            addArrayFromFile.setAlignmentX(Component.CENTER_ALIGNMENT);
            outputSolutionArray.setAlignmentX(Component.CENTER_ALIGNMENT);
            writeArrayToFile.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPanel downPanel = new JPanel();
            downPanel.add(writeArrayToFile);

            appendRowButtons.add(addColumn);
            appendRowButtons.add(addRow);
            deleteRowButtons.add(deleteColumn);
            deleteRowButtons.add(deleteRow);
            buttonBox.add(appendRowButtons);
            buttonBox.add(deleteRowButtons);
            inputFunctions.add(addArrayFromFile);
            inputFunctions.add(outputSolutionArray);
            JScrollPane inputScrollTable = new JScrollPane(inputTable);
            JScrollPane outputScrollTable = new JScrollPane(outputTable);

            mainFrame.add(inputScrollTable);
            mainFrame.add(buttonBox);
            mainFrame.add(inputFunctions);
            mainFrame.add(outputScrollTable);
            mainFrame.add(downPanel);
            mainFrame.setVisible(true);
        }
        public MainFrame() {
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setMainFrame();
        }
    }

