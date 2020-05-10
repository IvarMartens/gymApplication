package codewithivar.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;



public class ControllerHome {

    @FXML
    private Button repMax, volume , plateCalculator, goToHome , calculateVolume , calculateRepMax , calculatePlates;

    @FXML
    private Label labelYourVolumeIs, labelResult , labelError , labelError2, labelYourRepMaxIs ,labelPlates;

    @FXML
    private TextField textFieldReps , textFieldWeight;

    @FXML
    private RadioButton fifteenRadioButton , twentyRadioButton;


    // handling stage switch
    @FXML
    private void handleButtonAction (ActionEvent event) throws Exception {
        Stage stage ;
        Parent root ;

        if(event.getSource() == repMax){
            stage = (Stage) repMax.getScene().getWindow();
            root = FXMLLoader.load(getClass().getClassLoader().getResource("codewithivar/FXML/oneRepMax.fxml"));
        }
        else if(event.getSource() == volume){
            stage = (Stage) volume.getScene().getWindow();
            root = FXMLLoader.load(getClass().getClassLoader().getResource("codewithivar/FXML/volume.fxml"));
        }
        else if (event.getSource() == plateCalculator){
            stage = (Stage) plateCalculator.getScene().getWindow();
            root = FXMLLoader.load(getClass().getClassLoader().getResource("codewithivar/FXML/plateCalculator.fxml"));
        }
        else {
            stage = (Stage) goToHome.getScene().getWindow();
            root = FXMLLoader.load(getClass().getClassLoader().getResource("codewithivar/FXML/home.fxml"));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // calculation for one rep max and volume

    // Lukte niet om beide onAction events samen te voegen
    // Kreeg dan constant een nullpointer expression
    public void onActionResult(ActionEvent event) {

        if ((event.getSource() == calculateVolume || event.getSource() == calculateRepMax ) && !textFieldWeight.getText().trim().isEmpty() && !textFieldReps.getText().trim().isEmpty()) {

            labelError.setWrapText(true);
            labelError2.setWrapText(true);

            //setting boolean for numeric testing
            boolean numericTest = false;

            //setting a constant for calculation rep max
            double repMaxConstant =  (1.0 / 30);

            //trying if text field input is numeric
            try {
                double weightTest = Double.parseDouble(textFieldWeight.getText());
                double repTest = Double.parseDouble(textFieldReps.getText());
                numericTest = true;
            }
            //error if text field input is not numeric
            catch (NumberFormatException e) {
                labelError.setVisible(true);
                labelError.setText("error* please enter a number");
            }

            // parsing text field input in to a double
            if (numericTest == true) {
                double weight = Double.parseDouble(textFieldWeight.getText());
                double reps = Double.parseDouble(textFieldReps.getText());

                if (weight % 2.5 != 0) {
                    labelError.setVisible(true);
                    labelError.setText("error* please enter a valid weight");
                    labelError2.setVisible(false);
                }
                // error if both values aren't valid
                else if (weight > 550 && reps > 20) {
                    labelError.setVisible(true);
                    labelError2.setVisible(true);
                    labelError.setText("error* please enter a weight lower than 550Kg");
                    labelError2.setText("error* please enter a repcount lower than 20");
                }
                // error if weight is to high
                else if (weight > 550) {
                    labelError.setVisible(true);
                    labelError.setText("error* please enter a weight lower than 550Kg");
                    labelError2.setVisible(false);
                }
                // error if reps is to high
                else if (reps > 20) {
                    labelError.setVisible(true);
                    labelError.setText("error* please enter a repcount lower than 20");
                    labelError2.setVisible(false);
                }
                // calculating and showing result
                else {
                    //volume
                    if(event.getSource() == calculateVolume) {
                        double result = weight * reps;
                        labelYourVolumeIs.setVisible(true);
                        labelError2.setVisible(false);
                        labelError.setVisible(false);
                        labelResult.setText(result + "Kg");
                    }
                    // one rep max
                    if (event.getSource() == calculateRepMax){
                        double result = (double) Math.round((weight * reps * repMaxConstant) + weight) ;
                        labelYourRepMaxIs.setVisible(true);
                        labelError2.setVisible(false);
                        labelError.setVisible(false);
                        labelResult.setText(result + "Kg");
                    }

                }
            }
        }
    }

    // plates per side calculation
    public void onActionResultPlates(ActionEvent event) {
        if (event.getSource() == calculatePlates && !textFieldWeight.getText().trim().isEmpty() && (fifteenRadioButton.isSelected() || twentyRadioButton.isSelected())){

            labelError.setWrapText(true);
            labelError2.setWrapText(true);
            labelPlates.setWrapText(true);

            // setting variables
            byte bar = 20;
            byte sidesOfBar = 2;
            byte amountOfTwentyKgPlates = 0;
            byte amountOfFifteenKgPlates = 0;
            byte amountOfTenKgPlates = 0;
            byte amountOfFiveKgPlates = 0;
            byte amountOfTwoPointFiveKgPlates = 0;
            byte amountOfOnePointTwentyFiveKgPlates = 0;

            boolean numericPlates = false;

            //trying if text field input is numeric
            try {
                double weightTest = Double.parseDouble(textFieldWeight.getText());
                numericPlates = true;
            }
            //error if text field input is not numeric
            catch (NumberFormatException e) {
                labelError.setVisible(true);
                labelError.setText("error* please enter a number");
            }

            if (numericPlates == true){
                double weight = Double.parseDouble(textFieldWeight.getText());

                // error for invalid weight
                if (weight % 2.5 != 0) {
                    labelError.setVisible(true);
                    labelError.setText("error* please enter a valid weight");
                    labelError2.setVisible(false);
                }
                //error if weight is to high and both buttons are selected
                else if (weight > 550 && fifteenRadioButton.isSelected() && twentyRadioButton.isSelected()){
                    labelError.setVisible(true);
                    labelError2.setVisible(true);
                    labelError.setText("error* please enter a weight lower than 550Kg");
                    labelError2.setText("error* please select only one bar weight");
                }
                // error if weight is to high
                else if (weight > 550){
                    labelError.setVisible(true);
                    labelError.setText("error* please enter a weight lower than 550Kg");
                    labelError2.setVisible(false);
                }
                //error if both buttons are selected
                else if ( fifteenRadioButton.isSelected() && twentyRadioButton.isSelected()) {
                    labelError.setVisible(true);
                    labelError.setText("error* please select only one bar weight");
                    labelError2.setVisible(false);
                }
                //calculation
                else{
                    //bar is 20 by default set to 15 if that button is selected
                    if(fifteenRadioButton.isSelected()){
                        bar = 15;
                    }
                    //calculating what the total weight of the plates are on one side
                    float weightOfPlatesPerSide = (float) ((weight - bar) / sidesOfBar);

                    // getting right amount of the plates
                    while (weightOfPlatesPerSide >= 20 ){
                        amountOfTwentyKgPlates ++;
                        weightOfPlatesPerSide = weightOfPlatesPerSide - 20;
                    }
                    while (weightOfPlatesPerSide >= 15 ){
                        amountOfFifteenKgPlates ++;
                        weightOfPlatesPerSide =weightOfPlatesPerSide - 15;
                    }
                    while (weightOfPlatesPerSide >= 10 ){
                        amountOfTenKgPlates ++;
                        weightOfPlatesPerSide =weightOfPlatesPerSide - 10;
                    }
                    while (weightOfPlatesPerSide >= 5 ){
                        amountOfFiveKgPlates ++;
                        weightOfPlatesPerSide =weightOfPlatesPerSide - 5;
                    }
                    while (weightOfPlatesPerSide >= 2.5 ){
                        amountOfTwoPointFiveKgPlates ++;
                        weightOfPlatesPerSide = (float) (weightOfPlatesPerSide - 2.5);
                    }
                    while (weightOfPlatesPerSide >= 1.25 ){
                        amountOfOnePointTwentyFiveKgPlates ++;
                        weightOfPlatesPerSide = (float) (weightOfPlatesPerSide - 1.25);
                    }

                    //displaying result
                    //geen betere manier kunnen bedenken om dit voor elkaar te kriigen
                    labelPlates.setText("You need " + "\r\n" +
                            "20 Kg -->   " + amountOfTwentyKgPlates + " plates" + "\r\n" +
                            "15 Kg -->   " + amountOfFifteenKgPlates + " plates" +"\r\n" +
                            "10 Kg -->   " + amountOfTenKgPlates + " plates"+ "\r\n" +
                            "5 Kg -->    " + amountOfFiveKgPlates + " plates" + "\r\n" +
                            "2.5 Kg -->  " + amountOfTwoPointFiveKgPlates + " plates"+  "\r\n" +
                            "1.25 Kg --> "+ amountOfOnePointTwentyFiveKgPlates + " plates"+  "\r\n" + "per side");
                    labelPlates.setVisible(true);

                }
            }
        }
    }
}




