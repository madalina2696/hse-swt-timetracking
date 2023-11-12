package timetracking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EmployeeController {

    public List<Employee> employees = new ArrayList<>();
    private String currentUserID;

    private void load() {
        String[][] importTable;
        try {
            importTable = CsvLoader.load("WorkData");
            for (int i = 0; i < importTable.length; i++) {
                if (importTable[i][0].equalsIgnoreCase("w")) {
                    Employee newEmployee = new Employee(importTable[i][1], importTable[i][2], importTable[i][3],
                            importTable[i][4]);
                    employees.add(newEmployee);
                } else if (importTable[i][0].equalsIgnoreCase("d")) {
                    Employee employee = findEmployeeById(importTable[i][1]);
                    if (employee != null) {
                        employee.addDayToWorker(importTable[i][2], importTable[i][3], importTable[i][4],
                                importTable[i][5], importTable[i][6], importTable[i][7]);
                    }
                } else if (importTable[i][0].equalsIgnoreCase("v")) {
                    Employee employee = findEmployeeById(importTable[i][1]);
                    if (employee != null) {
                        employee.addVacationToWorker(importTable[i][2], importTable[i][3], importTable[i][4]);
                    }
                } else if (importTable[i][0].equalsIgnoreCase("f")) {
                    Employee employee = findEmployeeById(importTable[i][1]);
                    if (employee != null) {
                        employee.addFlexTimeToWorker(importTable[i][2], importTable[i][3], importTable[i][4]);
                    }
                } else {
                    System.err.println("Error in load() in EmployeeController.java: Unknown record type '"
                            + importTable[i][0] + "'");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File not available");
        }
    }

    private Employee createEmployeeFromData(String[] data) {
        // Assuming Employee constructor takes four parameters
        return new Employee(data[1], data[2], data[3], data[4]);
    }

    private Employee findEmployeeById(String id) {
        // Search for the employee with the given ID in the list
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    private void export() {
        /*
         * ArrayList<String[]> exportListTable = new ArrayList<>();
         * for (Worker value : dieBeschaeftigten.getEmployeeList().values()) {
         * exportListTable.add(new String[] { "w", value.getId(), value.getFirstName(),
         * value.getLastName(),
         * value.getDepartment(), "", "", "", "", "" });
         * for (CalenderDay tag :
         * dieBeschaeftigten.getEmployeeList().get(value.getId()).getTage().values()) {
         * exportListTable.add(new String[] { "d", value.getId(),
         * tag.getCalenderDay().toString(),
         * tag.getBegin().toString(), tag.getEnd().toString(), tag.getBreakMinute() +
         * "",
         * tag.getTargetMinute() + "", tag.getComment(), tag.getAbsence() + "", "" });
         * }
         * for (Holiday tag :
         * dieBeschaeftigten.getEmployeeList().get(value.getId()).getUrlaubsAnfragen().
         * values()) {
         * exportListTable.add(new String[] { "v", value.getId(),
         * tag.getDate().toString(), tag.getMinute() + "",
         * Boolean.toString(tag.getApproved()), "", "", "", "", "" });
         * }
         * for (FlexTime tag :
         * dieBeschaeftigten.getEmployeeList().get(value.getId()).getFlexTimeAnfragen().
         * values()) {
         * exportListTable.add(new String[] { "f", value.getId(),
         * tag.getDate().toString(), tag.getMinute() + "",
         * Boolean.toString(tag.getApproved()), "", "", "", "", "" });
         * }
         * }
         * String[][] exportArray2D = new String[exportListTable.size()][10];
         * int counter = 0;
         * for (String[] row : exportListTable) {
         * exportArray2D[counter] = row;
         * counter++;
         * }
         * try {
         * CsvLoader.save("WorkData", exportArray2D);
         * } catch (IOException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         * System.out.print(currentUserID + " ,you submitted ");
         */
    }

    public EmployeeController() throws IOException {
        currentUserID = CsvLoader.load("LastLogIn")[0][0];
        load();
    }

    @FXML
    private DatePicker dpFlextimeBegin;

    @FXML
    private DatePicker dpTimeDate;

    @FXML
    private DatePicker dpUrlaubBegin;

    @FXML
    private Label lblArbeitzeitError;

    @FXML
    private Label lblFlextimeError;

    @FXML
    private Label lblUrlaubError;
    @FXML
    private TextField pauseMin;

    @FXML
    private TextField tfTimeBegin;

    @FXML
    private TextField tfTimeEnd;

    @FXML
    private TextField vacationMin;
    @FXML
    private TextField sollMin;

    @FXML
    private TextField flexMin;

    @FXML
    void buttonFlextimeRequestClicked(ActionEvent event) {
        /*
         * try {
         * dieBeschaeftigten.addFlexTimeToWorker(currentUserID,
         * dpFlextimeBegin.getValue().toString(),
         * flexMin.getText(), "false");
         * export();
         * lblFlextimeError.setVisible(false);
         * } catch (Exception e) {
         * lblFlextimeError.setVisible(true);
         * }
         * System.out.println("your flextime");
         */
    }

    @FXML
    void buttonSaveClicked(ActionEvent event) {
        /*
         * try {
         * dieBeschaeftigten.addDayToWorker(currentUserID,
         * dpTimeDate.getValue().toString(),
         * tfTimeBegin.getText() + ":00", tfTimeEnd.getText() + ":00",
         * pauseMin.getText(),
         * "Created by GUI from " + currentUserID, "n");
         * lblArbeitzeitError.setVisible(false);
         * export();
         * } catch (Exception e) {
         * lblArbeitzeitError.setVisible(true);
         * }
         * System.out.println("your work-time");
         */
    }

    @FXML
    void buttonUrlaubRequestClicked(ActionEvent event) {
        /*
         * try {
         * dieBeschaeftigten.addVacationToWorker(currentUserID,
         * dpUrlaubBegin.getValue().toString(),
         * vacationMin.getText(), "false");
         * export();
         * lblUrlaubError.setVisible(false);
         * } catch (Exception e) {
         * lblUrlaubError.setVisible(true);
         * }
         * System.out.println("your vacation request");
         */
    }

    @FXML
    void dpFlextimeBeginSelected(ActionEvent event) {

    }

    @FXML
    void dpTimeDateSelected(ActionEvent event) {

    }

    @FXML
    void dpUrlaubBeginSelected(ActionEvent event) {

    }
}