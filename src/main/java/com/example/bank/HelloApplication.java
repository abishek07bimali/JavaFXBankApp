package com.example.bank;

import connection.ConnectionClass;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Random;

public class HelloApplication extends Application {
    static Label Fname, Lname, Address, phone, Anum, Balance, withdraw, Calcu, intersest,rateI,getid;
    static TextField input, inputLnam, BAmount, Anumber, inputAdd, Phone, WAmount, iAmo, cAmo,rateP;
    static int num = 0;
//    static int getid;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
        stage.setTitle("Bank Management system");
    }

    private Parent createContent() {
        GridPane root = new GridPane();
        root.setPrefSize(630, 600);
        root.setHgap(20);
        root.setVgap(10);
        root.setPadding(new Insets(10));

        Fname = new Label("Customer First name ");
        Fname.setFont(Font.font(14));
        root.add(Fname, 0, 0);

        Lname = new Label("Customer Last name ");
        Lname.setFont(Font.font(14));
        root.add(Lname, 0, 1);

        Address = new Label("Customer Address ");
        Address.setFont(Font.font(14));
        root.add(Address, 0, 2);

        phone = new Label("Phone Number ");
        phone.setFont(Font.font(14));
        root.add(phone, 0, 3);

        Anum = new Label("Account  Number ");
        Anum.setFont(Font.font(14));
        root.add(Anum, 0, 4);

        Balance = new Label("Balance Amount ");
        Balance.setFont(Font.font(14));
        root.add(Balance, 0, 5);

        withdraw = new Label("Withdraw / Deposit ");
        withdraw.setFont(Font.font(14));
        root.add(withdraw, 0, 6);

        rateI = new Label("Rate per Month ");
        rateI.setFont(Font.font(14));
        root.add(rateI, 0, 7);

        intersest = new Label("Interest Months ");
        intersest.setFont(Font.font(14));
        root.add(intersest, 0, 10);

        Calcu = new Label("Calculated Interest ");
        Calcu.setFont(Font.font(14));
        root.add(Calcu, 0, 11);

        getid = new Label();
        getid.setFont(Font.font(14));
        root.add(getid, 2, 13);

        input = new TextField();
        input.setFont(Font.font(18));
        root.add(input, 1, 0);

        inputLnam = new TextField();
        inputLnam.setFont(Font.font(18));
        root.add(inputLnam, 1, 1);

        inputAdd = new TextField();
        inputAdd.setFont(Font.font(18));
        root.add(inputAdd, 1, 2);

        Phone = new TextField();
        Phone.setFont(Font.font(18));
        root.add(Phone, 1, 3);

        Anumber = new TextField();
        Anumber.setEditable(false);
        Anumber.setFont(Font.font(18));
        root.add(Anumber, 1, 4);

        BAmount = new TextField();
        BAmount.setEditable(false);
        BAmount.setFont(Font.font(18));
        root.add(BAmount, 1, 5);

        WAmount = new TextField();
        WAmount.setFont(Font.font(18));
        root.add(WAmount, 1, 6);

        rateP = new TextField();
        rateP.setFont(Font.font(18));
        root.add(rateP, 1, 7);

        iAmo = new TextField();
        iAmo.setFont(Font.font(18));
        root.add(iAmo, 1, 10);

        cAmo = new TextField();
        cAmo.setEditable(false);
        cAmo.setFont(Font.font(18));
        root.add(cAmo, 1, 11);




        Button button = new Button("Search Customer");
        button.setFont(Font.font(18));
        root.add(button, 2, 0);
        button.setMinWidth(200);
        button.setMaxWidth(200);

        Button button1 = new Button("Clear Field");
        button1.setFont(Font.font(18));
        root.add(button1, 2, 1);
        button1.setMinWidth(200);
        button1.setMaxWidth(200);

        Button button2 = new Button("Next Customer");
        button2.setFont(Font.font(18));
        root.add(button2, 2, 2);
        button2.setMinWidth(200);
        button2.setMaxWidth(200);

        Button button3 = new Button("Previous Customer");
        button3.setFont(Font.font(18));
        root.add(button3, 2, 3);
        button3.setMinWidth(200);
        button3.setMaxWidth(200);

        Button button4 = new Button("Update Customer");
        button4.setFont(Font.font(18));
        root.add(button4, 2, 4);
        button4.setMinWidth(200);
        button4.setMaxWidth(200);

        Button button5 = new Button("Open Account");
        button5.setFont(Font.font(18));
        root.add(button5, 2, 5);
        button5.setMinWidth(200);
        button5.setMaxWidth(200);

        Button button6 = new Button("Deposit Amount");
        button6.setFont(Font.font(18));
        root.add(button6, 2, 6);
        button6.setMinWidth(200);
        button6.setMaxWidth(200);

        Button button7 = new Button("Withdraw Amount");
        button7.setFont(Font.font(18));
        root.add(button7, 2, 7);
        button7.setMinWidth(200);
        button7.setMaxWidth(200);

        Button button8 = new Button("Calculate Interest");
        button8.setFont(Font.font(18));
        root.add(button8, 2, 8);
        button8.setMinWidth(200);
        button8.setMaxWidth(200);


        button.setOnAction(e -> {
            search();
        });
        button1.setOnAction(e -> {
            clearField();
        });
        button2.setOnAction(e -> {
                nextButton();
        });
        button3.setOnAction(e -> {
            previousButton();
        });
        button4.setOnAction(e -> {
            updateAcount();
            search();
        });
        button5.setOnAction(e -> {
            insertData();
//            clearField();
        });
        button6.setOnAction(e -> {
            deposit();
            WAmount.setText("");
            search();
        });
        button7.setOnAction(e -> {
            withdraw();
            WAmount.setText("");
            search();
        });
        button8.setOnAction(e -> {
            calcuInt();
        });

        return root;

    }

    static void insertData(){
        if(input.getText().isEmpty() || inputLnam.getText().isEmpty()||
                inputAdd.getText().isEmpty()|| Phone.getText().isEmpty()||rateP.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please fill all required field!!").showAndWait();

        }
        else {
            try{
                String sql = "select * from customer where firstname like ? and lastname like ? and phone like ? ";
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setString(1, input.getText());
                    ps.setString(2, inputLnam.getText());
                    ps.setString(3, Phone.getText());
//                    ps.setString(4, inputAdd.getText());
                    ResultSet rs = connectionClass.retrieve(ps);
                    if(rs.next()){
                        new Alert(Alert.AlertType.ERROR, "User already registered!").showAndWait();
                    }
                    else {
                        String sqlq = "insert into customer(firstname,lastname,account,address,phone,balance,rate) values('" + input.getText() + "'," +
                                "'" + inputLnam.getText() + "'," +
                                "'" + acountNum() + "'," +
                                "'" + inputAdd.getText() + "'," +
                                "'" + Phone.getText() + "'," +
                                "'" + 00.000 + "'," +
                                "'"+rateP.getText()+"' )";
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(sqlq);
                            new Alert(Alert.AlertType.ERROR, "Account has been created!!").showAndWait();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    static void search() {
        if(input.getText().isEmpty() || inputLnam.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter the Firstname and Lastname!!").showAndWait();
        }
        else {
            String sql = "select * from customer where firstname like ? and lastname like ? ";
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, input.getText());
                ps.setString(2, inputLnam.getText());
                ResultSet rs = connectionClass.retrieve(ps);
                if (!rs.next()) {
                    new Alert(Alert.AlertType.ERROR, "Record not found!!").showAndWait();
                }
                else {
                        input.setText(rs.getString("firstname"));
                        inputLnam.setText(rs.getString("lastname"));
                        inputAdd.setText(rs.getString("address"));
                        Phone.setText(rs.getString("phone"));
                        Anumber.setText(rs.getString("account"));
                        BAmount.setText(rs.getString("balance"));
                        rateP.setText(rs.getString("rate"));
                        num= Integer.parseInt((rs.getString("id")));
//                    System.out.println("hello");
                    }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static int withdraw() {
        if (WAmount.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please enter the amount to be withdraw!!!").showAndWait();
        }
        else {
//            int A=Integer.parseInt(BAmount.getText());
//            int B= Integer.parseInt(WAmount.getText());
//            System.out.println(value);
//            if (A-B>=0) {
                float balance = Float.parseFloat(BAmount.getText());
                float finalBalance = balance - Float.parseFloat(WAmount.getText());
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String sql = "Update customer Set balance=? where account=?";
                try {
                    PreparedStatement ps = connectionClass.connection.prepareStatement(sql);
                    ps = connectionClass.connection.prepareStatement(sql);
                    ps.setFloat(1, (finalBalance));
                    ps.setString(2, Anumber.getText());
                    new Alert(Alert.AlertType.INFORMATION, "Amount has been withdrew!!!").showAndWait();
                    return connectionClass.manipulate(ps);
                } catch (SQLException e) {
                    System.out.println("Error " + e);
                    return 0;
                }
//            }
//            else {
//                new Alert(Alert.AlertType.ERROR, "You don't have enough balance!!").showAndWait();
//
//            }
        }

        return -1;
    }


    public static int deposit(){
        if (WAmount.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please enter the amount to be deposited!!!").showAndWait();
        }
        else{
            float balance = Float.parseFloat(BAmount.getText());
            float finalBalance = balance + Float.parseFloat(WAmount.getText());
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String sql = "Update customer Set balance=? where account=?";
            try {
                PreparedStatement ps = connectionClass.connection.prepareStatement(sql);
                ps = connectionClass.connection.prepareStatement(sql);
                ps.setFloat(1, (finalBalance));
                ps.setString(2, Anumber.getText());
                new Alert(Alert.AlertType.INFORMATION, "Amount has been deposited!!!").showAndWait();
                return connectionClass.manipulate(ps);

            } catch (SQLException e) {
                System.out.println("Error " + e);
                return 0;
            }

        }
        return -1;
    }

    static int updateAcount(){
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "Update customer Set firstname=?, lastname=?,address=?,phone=? where account=?";
        try{
            PreparedStatement ps = connectionClass.connection.prepareStatement(sql);
            ps = connectionClass.connection.prepareStatement(sql);
            ps.setString(1, input.getText());
            ps.setString(2, inputLnam.getText());
            ps.setString(3, inputAdd.getText());
            ps.setString(4, Phone.getText());
            ps.setString(5, Anumber.getText());
            return connectionClass.manipulate(ps);

        } catch(SQLException e){
            System.out.println("Error " + e);
            return 0;
        }
}

   static  void clearField(){
        input.setText("");
        inputLnam.setText("");
        inputAdd.setText("");
        Anumber.setText("");
        BAmount.setText("");
        Phone.setText("");
        iAmo.setText("");
        cAmo.setText("");
        rateP.setText("");

    }
    static int acountNum(){
        Random rand = new Random();
        int num = rand.nextInt(9000000) + 1000000;
        return num;
    }

    static  void calcuInt() {
        if (BAmount.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "please search the account first!!").showAndWait();
        } else {
            if(iAmo.getText().isEmpty()){
                new Alert(Alert.AlertType.ERROR, "please enter the months!!").showAndWait();
            }
            else {
                int months = Integer.parseInt(iAmo.getText());
                float P = Float.parseFloat(BAmount.getText());
                int rateper = Integer.parseInt(rateP.getText());


                float finalInterest = (P * months * rateper) / (12 * 100);
                cAmo.setText(String.valueOf(finalInterest));
            }
        }
    }

    static void nextButton() {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("select * from customer");
                rs.first();
                input.setText(rs.getString("firstname"));
                inputLnam.setText(rs.getString("lastname"));
                inputAdd.setText(rs.getString("address"));
                Phone.setText(rs.getString("phone"));
                Anumber.setText(rs.getString("account"));
                BAmount.setText(rs.getString("balance"));
                rateP.setText(rs.getString("rate"));


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    static  void previousButton(){
        try {
//            Label i=getid;
            int i=1;
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql="select * from customer where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, String.valueOf(3));
            ResultSet rs = connectionClass.retrieve(ps);
            if(rs.next()) {
                input.setText(rs.getString("firstname"));
                inputLnam.setText(rs.getString("lastname"));
                inputAdd.setText(rs.getString("address"));
                Phone.setText(rs.getString("phone"));
                Anumber.setText(rs.getString("account"));
                BAmount.setText(rs.getString("balance"));
                rateP.setText(rs.getString("rate"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}