package swt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;

public class EmployeeController {

    private AllEmployee dieBeschaeftigten = new AllEmployee();
    private String currentUserID;

    private void load() {
        String[][] importTable;
        try {
            importTable = CsvLoader.load("WorkData");
            for (int i = 0; i < importTable.length; i++) {
                if (importTable[i][0].equalsIgnoreCase("w")) {
                    dieBeschaeftigten.createWorker(importTable[i][1], importTable[i][2], importTable[i][3], importTable[i][4]);
                } else if (importTable[i][0].equalsIgnoreCase("d")) {
                    dieBeschaeftigten.addDayToWorker(importTable[i][1], importTable[i][2], importTable[i][3], importTable[i][4], importTable[i][5], importTable[i][6], importTable[i][7]);
                } else if (importTable[i][0].equalsIgnoreCase("v")) {
                    dieBeschaeftigten.addVacationToWorker(importTable[i][1], importTable[i][2], importTable[i][3], importTable[i][4]);
                } else if (importTable[i][0].equalsIgnoreCase("f")) {
                    dieBeschaeftigten.addFlexTimeToWorker(importTable[i][1], importTable[i][2], importTable[i][3], importTable[i][4]);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("File not availible");
        }

    }

    private void export() {
        ArrayList<String[]> exportListTable = new ArrayList<>();
        for (Worker value : dieBeschaeftigten.getEmployeeList().values()) {

            exportListTable.add(new String[]{"w", value.getId(), value.getFirstName(), value.getLastName(), value.getDepartment(), "", "", "", "", ""});

            for (CalenderDay tag : dieBeschaeftigten.getEmployeeList().get(value.getId()).getTage().values()) {
                exportListTable.add(new String[]{"d", value.getId(), tag.getCalenderDay().toString(), tag.getStart().toString(), tag.getEnd().toString(), tag.getBreakMin() + "", tag.getTargetMin() + "", tag.getComment(), tag.getAbsence() + "", ""});

            }
            for (Holiday tag : dieBeschaeftigten.getEmployeeList().get(value.getId()).getUrlaubsAnfragen().values()) {
                exportListTable.add(new String[]{"v", value.getId(), tag.getDate().toString(), tag.getMin() + "", Boolean.toString(tag.getApproved()), "", "", "", "", ""});

            }
            for (FlexTime tag : dieBeschaeftigten.getEmployeeList().get(value.getId()).getFlexTimeAnfragen().values()) {
                exportListTable.add(new String[]{"f", value.getId(), tag.getDate().toString(), tag.getMin() + "", Boolean.toString(tag.getApproved()), "", "", "", "", ""});

            }
        }

        String[][] exportArray2D = new String[exportListTable.size()][10];
        int counter = 0;
        for (String[] row : exportListTable) {
            exportArray2D[counter] = row;
            counter++;
        }
        try {
            CsvLoader.save("WorkData", exportArray2D);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.print(currentUserID + " ,you submitted ");
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
    void btnFlextimeRequestClicked(ActionEvent event) {
        try {
            dieBeschaeftigten.addFlexTimeToWorker(currentUserID, dpFlextimeBegin.getValue().toString(), flexMin.getText(), "false");
            export();
            lblFlextimeError.setVisible(false);
        } catch (Exception e) {
            lblFlextimeError.setVisible(true);
        }
        System.out.println("your flextime");
    }

    @FXML
    void btnSaveClicked(ActionEvent event) {
        try {
            dieBeschaeftigten.addDayToWorker(currentUserID, dpTimeDate.getValue().toString(), tfTimeBegin.getText() + ":00", tfTimeEnd.getText() + ":00", pauseMin.getText(), "Created by GUI from " + currentUserID, "n");
            lblArbeitzeitError.setVisible(false);
            export();
        } catch (Exception e) {
            lblArbeitzeitError.setVisible(true);
        }
        System.out.println("your work-time");
    }

    @FXML
    void btnUrlaubRequestClicked(ActionEvent event) {
        try {
            dieBeschaeftigten.addVacationToWorker(currentUserID, dpUrlaubBegin.getValue().toString(), vacationMin.getText(), "false");
            export();
            lblUrlaubError.setVisible(false);
        } catch (Exception e) {
            lblUrlaubError.setVisible(true);
        }
        System.out.println("your vacation request");
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
