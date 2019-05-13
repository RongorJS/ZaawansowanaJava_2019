package JavaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Controller {
    private FuturePaymentRepository futurePaymentRepository = new FuturePaymentRepository();
    private PastPaymentRepository pastPaymentRepository = new PastPaymentRepository();
    ObservableList<String> typeOptions = FXCollections.observableArrayList(Categories.Ubrania_Obuwie.toString(),
            Categories.Rachunki.toString(),Categories.Zywnosc.toString(),Categories.Uzywki.toString(),Categories.Chemia_SrodkiCzystosci.toString(),
            Categories.Transport.toString(),Categories.Rozrywka.toString(),Categories.Sprzet.toString(),Categories.Kosmetyki.toString());
    private DrawingClass drawingClass = new DrawingClass(pastPaymentRepository.getRepo(),futurePaymentRepository.getRepo());
    private int ID =0,pastID=0;
    private ObservableList<PieChart.Data> pieChartData;

    @FXML
    private TableView<FuturePayment> mainPaymentsTable;
    @FXML
    private TableColumn<FuturePayment, Integer> idCol;
    @FXML
    private TableColumn<FuturePayment, String> nameCol;
    @FXML
    private TableColumn<FuturePayment, Float> priceCol;
    @FXML
    private TableColumn<FuturePayment, Categories> typeCol;
    @FXML
    private TableColumn<FuturePayment, String> descCol;

    @FXML
    private TableView<PastPayment> pastPaymentsTable;
    @FXML
    private TableColumn<PastPayment, Integer> idColumn;
    @FXML
    private TableColumn<PastPayment, String> nameColumn;
    @FXML
    private TableColumn<PastPayment, Float> priceColumn;
    @FXML
    private TableColumn<PastPayment, Categories> typeColumn;
    @FXML
    private TableColumn<PastPayment, String> descColumn;
    @FXML
    private TableColumn<PastPayment, Date> dateColumn;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField descTextField;
    @FXML
    private TextField finalDescTextField;
    @FXML
    private Tab mainTab;
    @FXML
    private Tab tableTab;
    @FXML
    private Tab chartsTab;
    @FXML
    private DatePicker datePicker;
    @FXML
    private CheckBox percentCheckBox;
    @FXML
    private CheckBox numberCheckBox;
    @FXML
    private CheckBox percentCheckBox2;
    @FXML
    private CheckBox numberCheckBox2;
    @FXML
    private DatePicker from;
    @FXML
    private DatePicker to;
    @FXML
    private ToggleButton toggleChartsMode;
    @FXML
    private Label Od,Do;

    @FXML
    private PieChart pieChart;

    @FXML
    void deslectCheckBoxes(){
        if(!chartsTab.isSelected()) {
            percentCheckBox.setSelected(false);
            percentCheckBox.setDisable(false);
            numberCheckBox.setSelected(false);
            numberCheckBox.setDisable(false);
            numberCheckBox2.setSelected(false);
            percentCheckBox2.setSelected(false);
            from.setDisable(true);to.setDisable(true);
            from.setValue(null);to.setValue(null);
            pieChartData.clear();

        }

    }

    @FXML
    void initializeFuturePaymentsChartWindow(){
        if(toggleChartsMode.isSelected()){
            toggleChartsMode.setStyle("-fx-background-color: #84ff96");

            percentCheckBox.setDisable(true);
            percentCheckBox.setSelected(false);
            percentCheckBox2.setDisable(false);

            numberCheckBox.setDisable(true);
            numberCheckBox.setSelected(false);
            numberCheckBox2.setDisable(false);

            from.setDisable(true);
            to.setDisable(true);
            from.setOpacity(0);
            to.setOpacity(0);
            Od.setOpacity(0);
            Do.setOpacity(0);

            if(pieChartData != null){
                pieChartData.clear();
            }
        }
        else{

            toggleChartsMode.setStyle("");

            percentCheckBox2.setSelected(false);
            percentCheckBox2.setDisable(true);
            percentCheckBox.setDisable(false);

            numberCheckBox2.setSelected(false);
            numberCheckBox2.setDisable(true);
            numberCheckBox.setDisable(false);

            from.setDisable(false);
            to.setDisable(false);
            from.setOpacity(1);
            to.setOpacity(1);
            Od.setOpacity(1);
            Do.setOpacity(1);

            if(pieChartData != null)
            pieChartData.clear();
        }
    }

    @FXML
    void drawFuturePaymentsChart(){
        Short one = 1,two=2,three=3;
        if(percentCheckBox2.isSelected()){
            numberCheckBox2.setSelected(false);
            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.DOWN);
                float sum = drawingClass.ReturnTypeFuturePayments(Categories.Ubrania_Obuwie)+
                        drawingClass.ReturnTypeFuturePayments(Categories.Rachunki)+
                        drawingClass.ReturnTypeFuturePayments(Categories.Zywnosc)+
                        drawingClass.ReturnTypeFuturePayments(Categories.Uzywki)+
                        drawingClass.ReturnTypeFuturePayments(Categories.Chemia_SrodkiCzystosci)+
                        drawingClass.ReturnTypeFuturePayments(Categories.Transport)+
                        drawingClass.ReturnTypeFuturePayments(Categories.Rozrywka)+
                        drawingClass.ReturnTypeFuturePayments(Categories.Sprzet)+
                        drawingClass.ReturnTypeFuturePayments(Categories.Kosmetyki);
                float percentOne = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypeFuturePayments(Categories.Ubrania_Obuwie)))/sum)*100;
                float percentTwo = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypeFuturePayments(Categories.Rachunki)))/sum)*100;
                float percentThree = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypeFuturePayments(Categories.Zywnosc)))/sum)*100;
                float percentFour = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypeFuturePayments(Categories.Uzywki)))/sum)*100;
                float percentFive = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypeFuturePayments(Categories.Chemia_SrodkiCzystosci)))/sum)*100;
                float percentSix = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypeFuturePayments(Categories.Transport)))/sum)*100;
                float percentSeven = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypeFuturePayments(Categories.Rozrywka)))/sum)*100;
                float percentEight = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypeFuturePayments(Categories.Sprzet)))/sum)*100;
                float percentNine = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypeFuturePayments(Categories.Kosmetyki)))/sum)*100;
                pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Ubrania_Obuwie" + df.format(percentOne) + "%",percentOne),
                        new PieChart.Data("Rachunki " + df.format(percentTwo) + "%",percentTwo),
                        new PieChart.Data("Zywnosc " + df.format(percentThree) + "%",percentThree),
                        new PieChart.Data("Uzywki " + df.format(percentThree) + "%",percentFour),
                        new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(percentThree) + "%",percentFive),
                        new PieChart.Data("Transport " + df.format(percentThree) + "%",percentSix),
                        new PieChart.Data("Rozrywka " + df.format(percentThree) + "%",percentSeven),
                        new PieChart.Data("Sprzet " + df.format(percentThree) + "%",percentEight),
                        new PieChart.Data("Kosmetyki " + df.format(percentThree) + "%",percentNine));
                pieChart.setData(pieChartData);
        }
        else {
            if(pieChartData != null)
                pieChartData.clear();
        }
    }

    @FXML
    void drawFuturePaymentsChart2(){
        Short one = 1,two=2,three=3;
        if(numberCheckBox2.isSelected()){

            percentCheckBox2.setSelected(false);
            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.DOWN);

                pieChartData = FXCollections.observableArrayList();
                pieChartData.add(new PieChart.Data("Ubrania_Obuwie " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Ubrania_Obuwie)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Ubrania_Obuwie)));
                pieChartData.add(new PieChart.Data("Rachunki " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Rachunki)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Rachunki)));
                pieChartData.add(new PieChart.Data("Zywnosc " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Zywnosc)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Zywnosc)));
                pieChartData.add(new PieChart.Data("Uzywki " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Uzywki)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Uzywki)));
                pieChartData.add(new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Chemia_SrodkiCzystosci)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Chemia_SrodkiCzystosci)));
                pieChartData.add(new PieChart.Data("Transport " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Transport)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Transport)));
                pieChartData.add(new PieChart.Data("Rozrywka " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Rozrywka)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Rozrywka)));
                pieChartData.add(new PieChart.Data("Sprzet " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Sprzet)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Sprzet)));
                pieChartData.add(new PieChart.Data("Kosmetyki " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Kosmetyki)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Kosmetyki)));
                pieChart.setData(pieChartData);
        }
        else {
            if(pieChartData != null)
                pieChartData.clear();
        }
    }


    @FXML
        //AMMOUNT
    void drawPastPaymentsChart(){
        Short one = 1,two=2,three=3;
        if(numberCheckBox.isSelected()){
            from.setDisable(false);to.setDisable(false);
            percentCheckBox.setSelected(false);
            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.DOWN);

            if((from.getEditor().getText().length() == 0 && to.getEditor().getText().length() == 0) || (from.getEditor().getText().length() != 0 && to.getEditor().getText().length() == 0)
                    || (from.getEditor().getText().length() == 0 && to.getEditor().getText().length() != 0)){
            pieChartData = FXCollections.observableArrayList();
            pieChartData.add(new PieChart.Data("Ubrania_Obuwie " + df.format(drawingClass.returnSumOfPayments(Categories.Ubrania_Obuwie)) +"PLN",drawingClass.returnSumOfPayments(Categories.Ubrania_Obuwie)));
            pieChartData.add(new PieChart.Data("Rachunki " + df.format(drawingClass.returnSumOfPayments(Categories.Rachunki)) +"PLN",drawingClass.returnSumOfPayments(Categories.Rachunki)));
            pieChartData.add(new PieChart.Data("Zywnosc " + df.format(drawingClass.returnSumOfPayments(Categories.Zywnosc)) +"PLN",drawingClass.returnSumOfPayments(Categories.Zywnosc)));
            pieChartData.add(new PieChart.Data("Uzywki " + df.format(drawingClass.returnSumOfPayments(Categories.Uzywki)) +"PLN",drawingClass.returnSumOfPayments(Categories.Uzywki)));
            pieChartData.add(new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(drawingClass.returnSumOfPayments(Categories.Chemia_SrodkiCzystosci)) +"PLN",drawingClass.returnSumOfPayments(Categories.Chemia_SrodkiCzystosci)));
            pieChartData.add(new PieChart.Data("Transport " + df.format(drawingClass.returnSumOfPayments(Categories.Transport)) +"PLN",drawingClass.returnSumOfPayments(Categories.Transport)));
            pieChartData.add(new PieChart.Data("Rozrywka " + df.format(drawingClass.returnSumOfPayments(Categories.Rozrywka)) +"PLN",drawingClass.returnSumOfPayments(Categories.Rozrywka)));
            pieChartData.add(new PieChart.Data("Sprzet " + df.format(drawingClass.returnSumOfPayments(Categories.Sprzet)) +"PLN",drawingClass.returnSumOfPayments(Categories.Sprzet)));
            pieChartData.add(new PieChart.Data("Kosmetyki " + df.format(drawingClass.returnSumOfPayments(Categories.Kosmetyki)) +"PLN",drawingClass.returnSumOfPayments(Categories.Kosmetyki)));
            pieChart.setData(pieChartData);}

            if(from.getEditor().getText().length() != 0 && to.getEditor().getText().length() != 0){
                LocalDate date = from.getValue();
                int fromMonth = date.getMonthValue(); int fromDay = date.getDayOfMonth();
                LocalDate date2 = to.getValue();
                int toMonth = date2.getMonthValue(); int toDay = date2.getDayOfMonth();

                float sumOne = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Ubrania_Obuwie,fromMonth,toMonth,fromDay,toDay);
                float sumTwo = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Rachunki,fromMonth,toMonth,fromDay,toDay);
                float sumThree = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Zywnosc,fromMonth,toMonth,fromDay,toDay);
                float sumFour = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Uzywki,fromMonth,toMonth,fromDay,toDay);
                float sumFive = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Chemia_SrodkiCzystosci,fromMonth,toMonth,fromDay,toDay);
                float sumSix = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Transport,fromMonth,toMonth,fromDay,toDay);
                float sumSeven = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Rozrywka,fromMonth,toMonth,fromDay,toDay);
                float sumEight = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Sprzet,fromMonth,toMonth,fromDay,toDay);
                float sumNine = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Kosmetyki,fromMonth,toMonth,fromDay,toDay);

                pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Ubrania_Obuwie " + df.format(sumOne) + "PLN",sumOne),
                        new PieChart.Data("Rachunki " + df.format(sumTwo) + "PLN",sumTwo),
                        new PieChart.Data("Zywnosc " + df.format(sumThree) + "PLN",sumThree),
                        new PieChart.Data("Uzywki " + df.format(sumFour) + "PLN",sumFour),
                        new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(sumFive) + "PLN",sumFive),
                        new PieChart.Data("Transport " + df.format(sumSix) + "PLN",sumSix),
                        new PieChart.Data("Rozrywka " + df.format(sumSeven) + "PLN",sumSeven),
                        new PieChart.Data("Sprzet " + df.format(sumEight) + "PLN",sumEight),
                        new PieChart.Data("Kosmetyki " + df.format(sumNine) + "PLN",sumNine));
                pieChart.setData(pieChartData);

            }

            }
        else {
            pieChartData.clear();
            from.setDisable(true);to.setDisable(true);
            from.setValue(null);to.setValue(null);
        }

   }
   @FXML
   void Update(){
        if(numberCheckBox.isSelected())
        drawPastPaymentsChart();
        else
        drawPastPaymentsChart2();
    }

   @FXML
       //PERCENT
   void drawPastPaymentsChart2(){
       Short one = 1,two=2,three=3;
       if(percentCheckBox.isSelected()){
            from.setDisable(false);to.setDisable(false);
            numberCheckBox.setSelected(false);
           DecimalFormat df = new DecimalFormat("##.##");
           df.setRoundingMode(RoundingMode.DOWN);

           if((from.getEditor().getText().length() == 0 && to.getEditor().getText().length() == 0) || (from.getEditor().getText().length() != 0 && to.getEditor().getText().length() == 0)
           || (from.getEditor().getText().length() == 0 && to.getEditor().getText().length() != 0)){

               float sum = drawingClass.ReturnTypePastPayments(Categories.Ubrania_Obuwie)+
                       drawingClass.ReturnTypePastPayments(Categories.Rachunki)+drawingClass.ReturnTypePastPayments(Categories.Zywnosc)
                       +drawingClass.ReturnTypePastPayments(Categories.Uzywki)+drawingClass.ReturnTypePastPayments(Categories.Chemia_SrodkiCzystosci)+
                       drawingClass.ReturnTypePastPayments(Categories.Transport)+drawingClass.ReturnTypePastPayments(Categories.Rozrywka)+
                       drawingClass.ReturnTypePastPayments(Categories.Sprzet)+drawingClass.ReturnTypePastPayments(Categories.Kosmetyki);

               float percentOne = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypePastPayments(Categories.Ubrania_Obuwie)))/sum)*100;
               float percentTwo = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypePastPayments(Categories.Rachunki)))/sum)*100;
               float percentThree = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypePastPayments(Categories.Zywnosc)))/sum)*100;
               float percentFour = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypePastPayments(Categories.Uzywki)))/sum)*100;
               float percentFive = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypePastPayments(Categories.Chemia_SrodkiCzystosci)))/sum)*100;
               float percentSix = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypePastPayments(Categories.Transport)))/sum)*100;
               float percentSeven = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypePastPayments(Categories.Rozrywka)))/sum)*100;
               float percentEight = (Float.parseFloat(Integer.toString(drawingClass.ReturnTypePastPayments(Categories.Sprzet)))/sum)*100;
               float percentNine= (Float.parseFloat(Integer.toString(drawingClass.ReturnTypePastPayments(Categories.Kosmetyki)))/sum)*100;

           pieChartData = FXCollections.observableArrayList(
                   new PieChart.Data("Ubrania_Obuwie " + df.format(percentOne) + "%",percentOne),
                   new PieChart.Data("Rachunki " + df.format(percentTwo) + "%",percentTwo),
                   new PieChart.Data("Zywnosc " + df.format(percentThree) + "%",percentThree),
                   new PieChart.Data("Uzywki " + df.format(percentFour) + "%",percentFour),
                   new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(percentFive) + "%",percentFive),
                   new PieChart.Data("Transport " + df.format(percentSix) + "%",percentSix),
                   new PieChart.Data("Rozrywka " + df.format(percentSeven) + "%",percentSeven),
                   new PieChart.Data("Sprzet " + df.format(percentEight) + "%",percentEight),
                   new PieChart.Data("Kosmetyki " + df.format(percentNine) + "%",percentNine));
           pieChart.setData(pieChartData);
           }


           if(from.getEditor().getText().length() != 0 && to.getEditor().getText().length() != 0){
               LocalDate date = from.getValue();
               int fromMonth = date.getMonthValue(); int fromDay = date.getDayOfMonth();
               LocalDate date2 = to.getValue();
               int toMonth = date2.getMonthValue(); int toDay = date2.getDayOfMonth();

               float ones = drawingClass.countPaymentsBetweenDates(Categories.Ubrania_Obuwie,fromMonth,toMonth,fromDay,toDay);
               float twos = drawingClass.countPaymentsBetweenDates(Categories.Rachunki,fromMonth,toMonth,fromDay,toDay);
               float threes = drawingClass.countPaymentsBetweenDates(Categories.Zywnosc,fromMonth,toMonth,fromDay,toDay);
               float fours = drawingClass.countPaymentsBetweenDates(Categories.Uzywki,fromMonth,toMonth,fromDay,toDay);
               float fives = drawingClass.countPaymentsBetweenDates(Categories.Chemia_SrodkiCzystosci,fromMonth,toMonth,fromDay,toDay);
               float sixes = drawingClass.countPaymentsBetweenDates(Categories.Transport,fromMonth,toMonth,fromDay,toDay);
               float sevens = drawingClass.countPaymentsBetweenDates(Categories.Rozrywka,fromMonth,toMonth,fromDay,toDay);
               float eights = drawingClass.countPaymentsBetweenDates(Categories.Sprzet,fromMonth,toMonth,fromDay,toDay);
               float nines = drawingClass.countPaymentsBetweenDates(Categories.Kosmetyki,fromMonth,toMonth,fromDay,toDay);
               float sumOTT = ones+twos+threes+fours+fives+sixes+sevens+eights+nines;
               ones=(ones/sumOTT)*100;
               twos=(twos/sumOTT)*100;
               threes=(threes/sumOTT)*100;
               fours=(fours/sumOTT)*100;
               fives=(fives/sumOTT)*100;
               sixes=(sixes/sumOTT)*100;
               sevens=(sevens/sumOTT)*100;
               eights=(eights/sumOTT)*100;
               nines=(nines/sumOTT)*100;

               pieChartData = FXCollections.observableArrayList(
                       new PieChart.Data("Ubrania_Obuwie " + df.format(ones) + "%",ones),
                       new PieChart.Data("Rachunki " + df.format(twos) + "%",twos),
                       new PieChart.Data("Zywnosc " + df.format(threes) + "%",threes),
                       new PieChart.Data("Uzywki " + df.format(fours) + "%",fours),
                       new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(fives) + "%",fives),
                       new PieChart.Data("Transport " + df.format(sixes) + "%",sixes),
                       new PieChart.Data("Rozrywka " + df.format(sevens) + "%",sevens),
                       new PieChart.Data("Sprzet " + df.format(eights) + "%",eights),
                       new PieChart.Data("Kosmetyki " + df.format(nines) + "%",nines));
               pieChart.setData(pieChartData);

           }
       }
       else {
           pieChartData.clear();
           from.setDisable(true);to.setDisable(true);
           from.setValue(null);to.setValue(null);
       }

   }

    @FXML
    void openTabEvent() {
        if (tableTab.isSelected()) {
            ObservableList<PastPayment> pastPaymentList = FXCollections.observableArrayList(pastPaymentRepository.getRepo());
            pastPaymentsTable.setItems(pastPaymentList);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        }
    }

    @FXML
    void openMainTabEvent() {
        if (mainTab.isSelected()) {
            ObservableList<FuturePayment> futurePaymentsList = FXCollections.observableArrayList(futurePaymentRepository.getRepo());
            mainPaymentsTable.setItems(futurePaymentsList);
            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        }
    }

    @FXML
    void initialize(){
        choiceBox.setItems((typeOptions));
    }

    @FXML
    void addFuturePaymentClick(ActionEvent actionEvent){
        Categories choiceBoxResult = null;
        if(choiceBox.getValue().toString() == "Ubrania_Obuwie") choiceBoxResult = Categories.Ubrania_Obuwie;
        if(choiceBox.getValue().toString() == "Rachunki") choiceBoxResult = Categories.Rachunki;
        if(choiceBox.getValue().toString() == "Zywnosc") choiceBoxResult = Categories.Zywnosc;
        if(choiceBox.getValue().toString() == "Uzywki") choiceBoxResult = Categories.Uzywki;
        if(choiceBox.getValue().toString() == "Chemia_SrodkiCzystosci") choiceBoxResult = Categories.Chemia_SrodkiCzystosci;
        if(choiceBox.getValue().toString() == "Transport") choiceBoxResult = Categories.Transport;
        if(choiceBox.getValue().toString() == "Rozrywka") choiceBoxResult = Categories.Rozrywka;
        if(choiceBox.getValue().toString() == "Sprzet") choiceBoxResult = Categories.Sprzet;
        if(choiceBox.getValue().toString() == "Kosmetyki") choiceBoxResult = Categories.Kosmetyki;

        if(/*choiceBoxResult != null &&*/ nameTextField.getText() != null && priceTextField.getText() != null && descTextField.getText() != null){
        FuturePayment futurePayment = new FuturePayment(ID,nameTextField.getText(), Float.parseFloat(priceTextField.getText()),
                choiceBoxResult,descTextField.getText());
        futurePaymentRepository.AddToRepo(futurePayment);
        nameTextField.clear();priceTextField.clear();descTextField.clear();choiceBox.setValue(null);
        ID++;
        chartsTab.setDisable(false);
        }
        openMainTabEvent();
    }

    @FXML
    void finishPaymentClick(ActionEvent actionEvent){
        if(mainPaymentsTable.getSelectionModel().getSelectedItem() != null && datePicker.getValue() != null){
        FuturePayment temp = mainPaymentsTable.getSelectionModel().getSelectedItem();
        Date date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        PastPayment pastPayment;
            if(finalDescTextField.getText() != null){
                pastPayment = new PastPayment(pastID,temp.getName(), temp.getPrice(),temp.getType(),finalDescTextField.getText(),date);
            }
            else{
                pastPayment = new PastPayment(pastID,temp.getName(), temp.getPrice(),temp.getType(),temp.getDescription(),date);
            }
            pastPaymentRepository.AddToRepo(pastPayment);
            futurePaymentRepository.DeletePayment(temp.getID());
            openMainTabEvent();
            finalDescTextField.clear();
            datePicker.getEditor().clear();
            pastID++;
            chartsTab.setDisable(false);
        }
    }

     public Controller() { }
}