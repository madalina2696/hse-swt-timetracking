package swt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;

public class SupervisorController {
    private boolean firstTimeVaction = true;
    private boolean firstTimeFlexTime = true;
    int goThroughVacations = 0;
    int goThroughFlextimes = 0;
    private AllWorker dieBeschaeftigten = new AllWorker();
    private ArrayList<HolidayAndWorker> urlaubsListe = new ArrayList<>();
    private ArrayList<FlexTimeAndWorker> flextimeListe = new ArrayList<>();

    private void load() {
        String[][] importTable;
        try {
            importTable = CsvLoader.load("WorkData"); // lädt die test datei also alle eintragungen, die gemacht wurden
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
        System.out.println("File has been loaded");
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
        System.out.print("Exported! List has been updated. ");
    }

    public SupervisorController() throws IOException {

        load();
        for (Worker worker : dieBeschaeftigten.getEmployeeList().values()) {
            for (Holiday tag : dieBeschaeftigten.getEmployeeList().get(worker.getId()).getUrlaubsAnfragen().values()) {
                if (!tag.getApproved()) {
                    urlaubsListe.add(new HolidayAndWorker(worker.getId(), tag));
                }
            }
        }
        for (Worker worker : dieBeschaeftigten.getEmployeeList().values()) {
            for (FlexTime tag : dieBeschaeftigten.getEmployeeList().get(worker.getId()).getFlexTimeAnfragen().values()) {
                if (!tag.getApproved()) {
                    flextimeListe.add(new FlexTimeAndWorker(worker.getId(), tag));
                }
            }
        }
    }

    @FXML
    private Button btnFlextimeAnfrageAblehnen;

    @FXML
    private Button btnFlextimeAnfrageAkzeptieren;

    @FXML
    private Button btnUrlaubsanfrageAblehnen;

    @FXML
    private Button btnUrlaubsanfrageAkzeptieren;

    @FXML
    private Label lblFlextimeAnfragenEmp1;

    @FXML
    private Label lblFlextimeAnfragenEmp1Begin;

    @FXML
    private Label lblFlextimeAnfragenEmp1Min;

    @FXML
    private Label lblUrlaubsanfragenEmp1;

    @FXML
    private Label lblUrlaubsanfragenEmp1Begin;

    @FXML
    private Label lblUrlaubsanfragenEmp1Min;


    @FXML
    void btnFlextimeAnfrageAblehnenClicked(ActionEvent event) {

        dieBeschaeftigten.deleteFlextime(flextimeListe.get(goThroughFlextimes).getId(), flextimeListe.get(goThroughFlextimes).getFlextime().getDate());
        export();
        flextimeListe.clear();
        for (Worker worker : dieBeschaeftigten.getEmployeeList().values()) {
            for (FlexTime tag : dieBeschaeftigten.getEmployeeList().get(worker.getId()).getFlexTimeAnfragen().values()) {
                if (!tag.getApproved()) {
                    flextimeListe.add(new FlexTimeAndWorker(worker.getId(), tag));
                }
            }
        }
        goThroughFlextimes++;
        if (goThroughFlextimes >= flextimeListe.size()) {
            goThroughFlextimes = 0;
        }
        try {
            lblFlextimeAnfragenEmp1.setText(dieBeschaeftigten.getWorker(flextimeListe.get(goThroughFlextimes).getId()).getFirstName() + ' ' + dieBeschaeftigten.getWorker(flextimeListe.get(goThroughFlextimes).getId()).getLastName() + " (" + flextimeListe.get(goThroughFlextimes).getId() + ')');
            lblFlextimeAnfragenEmp1Begin.setText(flextimeListe.get(goThroughFlextimes).getFlextime().getDate().toString());
            lblFlextimeAnfragenEmp1Min.setText(flextimeListe.get(goThroughFlextimes).getFlextime().getMin() + "");
            System.out.println("Request denied!");
        } catch (Exception e) {
            lblFlextimeAnfragenEmp1.setText("");
            lblFlextimeAnfragenEmp1Begin.setText("");
            lblFlextimeAnfragenEmp1Min.setText("");
            btnFlextimeAnfrageAkzeptieren.setDisable(true);
            btnFlextimeAnfrageAblehnen.setDisable(true);
        }

    }

    @FXML
    void btnFlextimeAnfrageAkzeptierenClicked(ActionEvent event) {
        if (firstTimeFlexTime) {
            firstTimeFlexTime = false;
            try {
                lblFlextimeAnfragenEmp1.setText(dieBeschaeftigten.getWorker(flextimeListe.get(goThroughFlextimes).getId()).getFirstName() + ' ' + dieBeschaeftigten.getWorker(flextimeListe.get(goThroughFlextimes).getId()).getLastName() + " (" + flextimeListe.get(goThroughFlextimes).getId() + ')');
                lblFlextimeAnfragenEmp1Begin.setText(flextimeListe.get(goThroughFlextimes).getFlextime().getDate().toString());
                lblFlextimeAnfragenEmp1Min.setText(flextimeListe.get(goThroughFlextimes).getFlextime().getMin() + "");
                btnFlextimeAnfrageAkzeptieren.setText("Akzeptieren");
                btnFlextimeAnfrageAblehnen.setVisible(true);


            } catch (Exception e) {
                lblFlextimeAnfragenEmp1.setText("");
                lblFlextimeAnfragenEmp1Begin.setText("");
                lblFlextimeAnfragenEmp1Min.setText("");
                btnFlextimeAnfrageAkzeptieren.setDisable(true);
                btnFlextimeAnfrageAblehnen.setDisable(true);
            }
        } else {
            try {
                dieBeschaeftigten.addFlexTimeToWorker(flextimeListe.get(goThroughFlextimes).getId(), flextimeListe.get(0).getFlextime().getDate().toString(), flextimeListe.get(0).getFlextime().getMin() + "", "true");
                export();
                flextimeListe.clear();
                for (Worker worker : dieBeschaeftigten.getEmployeeList().values()) {
                    for (FlexTime tag : dieBeschaeftigten.getEmployeeList().get(worker.getId()).getFlexTimeAnfragen().values()) {
                        if (!tag.getApproved()) {
                            flextimeListe.add(new FlexTimeAndWorker(worker.getId(), tag));
                        }
                    }
                }
                goThroughFlextimes++;
                if (goThroughFlextimes >= flextimeListe.size()) {
                    goThroughFlextimes = 0;
                }
                dieBeschaeftigten.addFlexTimeToWorker(flextimeListe.get(goThroughFlextimes).getId(), flextimeListe.get(0).getFlextime().getDate().toString(), flextimeListe.get(0).getFlextime().getMin() + "", "true");
                lblFlextimeAnfragenEmp1Begin.setText(flextimeListe.get(goThroughFlextimes).getFlextime().getDate().toString());
                lblFlextimeAnfragenEmp1Min.setText(flextimeListe.get(goThroughFlextimes).getFlextime().getMin() + "");
            } catch (Exception e) {
                lblFlextimeAnfragenEmp1.setText("");
                lblFlextimeAnfragenEmp1Begin.setText("");
                lblFlextimeAnfragenEmp1Min.setText("");
                btnFlextimeAnfrageAkzeptieren.setDisable(true);
                btnFlextimeAnfrageAblehnen.setDisable(true);
            }
        }
        System.out.println("Request granted!");

    }

    @FXML
    void btnMonatszeitenAblehnen(ActionEvent event) {

    }

    @FXML
    void btnMonatszeitenAkzeptierenClicked(ActionEvent event) {

    }

    @FXML
    void btnUrlaubsanfrageAblehnenClicked(ActionEvent event) {


        dieBeschaeftigten.deleteVacation(urlaubsListe.get(goThroughVacations).getId(), urlaubsListe.get(goThroughVacations).getHoliday().getDate());
        export();
        urlaubsListe.clear();
        for (Worker worker : dieBeschaeftigten.getEmployeeList().values()) {
            for (Holiday tag : dieBeschaeftigten.getEmployeeList().get(worker.getId()).getUrlaubsAnfragen().values()) {
                if (!tag.getApproved()) {
                    urlaubsListe.add(new HolidayAndWorker(worker.getId(), tag));
                }
            }
        }
        goThroughVacations++;
        if (goThroughVacations >= urlaubsListe.size()) {
            goThroughVacations = 0;
        }
        try {
            lblUrlaubsanfragenEmp1.setText(dieBeschaeftigten.getWorker(urlaubsListe.get(goThroughVacations).getId()).getFirstName() + ' ' + dieBeschaeftigten.getWorker(urlaubsListe.get(goThroughVacations).getId()).getLastName() + " (" + urlaubsListe.get(goThroughVacations).getId() + ')');
            lblUrlaubsanfragenEmp1Begin.setText(urlaubsListe.get(goThroughVacations).getHoliday().getDate().toString());
            lblUrlaubsanfragenEmp1Min.setText(urlaubsListe.get(goThroughVacations).getHoliday().getMin() + "");

        } catch (Exception e) {
            lblUrlaubsanfragenEmp1.setText("");
            lblUrlaubsanfragenEmp1Begin.setText("");
            lblUrlaubsanfragenEmp1Min.setText("");
            btnUrlaubsanfrageAblehnen.setDisable(true);
            btnUrlaubsanfrageAkzeptieren.setDisable(true);
        }
        System.out.println("Request denied!");
    }


    @FXML
    void btnUrlaubsanfrageAkzeptierenClicked(ActionEvent event) {
        if (firstTimeVaction) {
            firstTimeVaction = false;
            try {
                lblUrlaubsanfragenEmp1.setText(dieBeschaeftigten.getWorker(urlaubsListe.get(goThroughVacations).getId()).getFirstName() + ' ' + dieBeschaeftigten.getWorker(urlaubsListe.get(goThroughVacations).getId()).getLastName() + " (" + urlaubsListe.get(goThroughVacations).getId() + ')');
                lblUrlaubsanfragenEmp1Begin.setText(urlaubsListe.get(goThroughVacations).getHoliday().getDate().toString());
                lblUrlaubsanfragenEmp1Min.setText(urlaubsListe.get(goThroughVacations).getHoliday().getMin() + "");
                btnUrlaubsanfrageAkzeptieren.setText("Akzeptieren");
                btnUrlaubsanfrageAblehnen.setVisible(true);
            } catch (Exception e) {
                lblUrlaubsanfragenEmp1.setText("");
                lblUrlaubsanfragenEmp1Begin.setText("");
                lblUrlaubsanfragenEmp1Min.setText("");
                btnUrlaubsanfrageAblehnen.setDisable(true);
                btnUrlaubsanfrageAkzeptieren.setDisable(true);

            }
        } else {
            try {
                dieBeschaeftigten.addVacationToWorker(urlaubsListe.get(goThroughVacations).getId(), urlaubsListe.get(0).getHoliday().getDate().toString(), urlaubsListe.get(0).getHoliday().getMin() + "", "true");
                export();
                urlaubsListe.clear();
                for (Worker worker : dieBeschaeftigten.getEmployeeList().values()) {
                    for (Holiday tag : dieBeschaeftigten.getEmployeeList().get(worker.getId()).getUrlaubsAnfragen().values()) {
                        if (!tag.getApproved()) {
                            urlaubsListe.add(new HolidayAndWorker(worker.getId(), tag));
                        }
                    }
                }
                goThroughVacations++;
                if (goThroughVacations >= urlaubsListe.size()) {
                    goThroughVacations = 0;
                }
                lblUrlaubsanfragenEmp1.setText(dieBeschaeftigten.getWorker(urlaubsListe.get(goThroughVacations).getId()).getFirstName() + ' ' + dieBeschaeftigten.getWorker(urlaubsListe.get(goThroughVacations).getId()).getLastName() + " (" + urlaubsListe.get(goThroughVacations).getId() + ')');
                lblUrlaubsanfragenEmp1Begin.setText(urlaubsListe.get(goThroughVacations).getHoliday().getDate().toString());
                lblUrlaubsanfragenEmp1Min.setText(urlaubsListe.get(goThroughVacations).getHoliday().getMin() + "");

            } catch (Exception e) {
                lblUrlaubsanfragenEmp1.setText("");
                lblUrlaubsanfragenEmp1Begin.setText("");
                lblUrlaubsanfragenEmp1Min.setText("");
                btnUrlaubsanfrageAblehnen.setDisable(true);
                btnUrlaubsanfrageAkzeptieren.setDisable(true);
            }
        }
        System.out.println("Request granted!");
    }


    @FXML
    private TextField uebersichtName;


    @FXML
    private TextArea bigField;


    @FXML
    void showTimes(ActionEvent event) {
        StringBuilder sb = new StringBuilder();
        try {
            Worker value = dieBeschaeftigten.getWorker(uebersichtName.getText());
            for (CalenderDay tag : dieBeschaeftigten.getEmployeeList().get(value.getId()).getTage().values()) {
                sb.append(tag.getCalenderDay());
                sb.append("     ");
                sb.append(tag.getStart());
                sb.append("     ");
                sb.append(tag.getEnd());
                sb.append("     ");
                sb.append(tag.getTargetMin());
                sb.append("     ");
                sb.append(tag.getBreakMin());
                sb.append("\n");

            }
            bigField.setText(sb.toString());
        } catch (Exception e) {
            bigField.setText("Not found");
        }
    }


}




