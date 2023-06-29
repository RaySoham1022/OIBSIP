
// Importing Required Libraries from java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import java.util.Timer;
import java.util.TimerTask;

public class Online_Exam_System {
    public static void main(String[] args) {
        try {
            Login_Part SignIn_Section = new Login_Part();
            SignIn_Section.setSize(400, 180);
            SignIn_Section.setVisible(true);
            SignIn_Section.setLocation(400, 300);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "System Error", 0);
        }
    }
}

class Login_Part extends JFrame implements ActionListener {
    JButton Submission_Button;
    JPanel AreaLogin;
    JLabel User_Name, Password;
    JTextField User_Name_TextField, Password_TextField;

    Login_Part() {
        User_Name = new JLabel();
        User_Name.setText("Enter Your Name :");
        User_Name_TextField = new JTextField(15);
        Password = new JLabel();
        Password.setText("Enter Password (Default 12345):");
        Password_TextField = new JPasswordField(10);
        Submission_Button = new JButton("Start Exam");
        Submission_Button.setBounds(150, 100, 50, 80);
        AreaLogin = new JPanel(new GridLayout(3, 1));
        AreaLogin.add(User_Name);
        AreaLogin.add(User_Name_TextField);
        AreaLogin.add(Password);
        AreaLogin.add(Password_TextField);
        AreaLogin.add(Submission_Button);
        add(AreaLogin, BorderLayout.CENTER);
        Submission_Button.addActionListener(this);
        setTitle("Player Details");
    }

    public void actionPerformed(ActionEvent event) {
        String User_Value = User_Name_TextField.getText();
        String Password_Value = Password_TextField.getText();
        if (Password_Value.equals("")) {
            Password_TextField.setText("Please Provide Password");
            actionPerformed(event);
        } else {
            new Begin_Online_Test(User_Value);
        }
    }
}

class Begin_Online_Test extends JFrame implements ActionListener {
    JLabel Time_Section, Question_Choice;
    JRadioButton Answer_Choice[] = new JRadioButton[6];
    JButton Save_Next, Review_Later;
    ButtonGroup Button_Group;
    int Correct_Answer = 0, Present_Question = 0, x_coord = 1, y_coord = 1, Now = 0;
    int Review_Array[] = new int[10];
    Timer Ticker = new Timer();

    Begin_Online_Test(String User_Name) {
        super(User_Name);
        Time_Section = new JLabel();
        Question_Choice = new JLabel();
        add(Time_Section);
        add(Question_Choice);
        Button_Group = new ButtonGroup();
        for (int Button_No = 1; Button_No <= 4; Button_No++) {
            Answer_Choice[Button_No] = new JRadioButton();
            add(Answer_Choice[Button_No]);
            Button_Group.add(Answer_Choice[Button_No]);
        }
        Save_Next = new JButton("Save and Next ");
        Review_Later = new JButton("Review Later");
        Save_Next.addActionListener(this);
        Review_Later.addActionListener(this);
        add(Save_Next);
        add(Review_Later);
        Assign_Questions();
        Question_Choice.setBounds(30, 40, 450, 20);
        Time_Section.setBounds(20, 20, 450, 20);
        Answer_Choice[1].setBounds(50, 80, 300, 20);
        Answer_Choice[2].setBounds(50, 110, 300, 20);
        Answer_Choice[3].setBounds(50, 140, 300, 20);
        Answer_Choice[4].setBounds(50, 170, 300, 20);
        Save_Next.setBounds(95, 240, 140, 30);
        Review_Later.setBounds(270, 240, 150, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 200);
        setVisible(true);
        setSize(600, 350);

        Ticker.scheduleAtFixedRate(new TimerTask() {
            int Total_Time = 100;

            public void run() {
                Time_Section.setText("Remaining Time Left : " + Total_Time + " 's");
                Total_Time = Total_Time - 1;
                if (Total_Time < 0) {
                    Ticker.cancel();
                    Time_Section.setText("Time OUT !");
                }
            }
        }, 0, 1000);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == Save_Next) {
            if (Authenticate_Answer()) {
                Correct_Answer = Correct_Answer + 1;
            }
            Present_Question = Present_Question + 1;
            Assign_Questions();
            if (Present_Question == 9) {
                Save_Next.setEnabled(false);
                Review_Later.setText("See Result");
            }
        }
        if (event.getActionCommand().equals("Review Question Later")) {
            JButton Review = new JButton("Review Qno " + x_coord);
            Review.setBounds(480, 20 + (30 * x_coord), 100, 30);
            add(Review);
            Review.addActionListener(this);
            Review_Array[x_coord] = Present_Question;
            x_coord = x_coord + 1;
            Present_Question = Present_Question + 1;
            Assign_Questions();
            if (Present_Question == 9) {
                Review_Later.setText("See Result");
            }
            setVisible(false);
            setVisible(true);
        }

        for (int Initial = 0, y_coord = 1; Initial < x_coord; Initial++, y_coord++) {
            if (event.getActionCommand().equals("Review Qno " + y_coord)) {
                if (Authenticate_Answer()) {
                    Correct_Answer = Correct_Answer + 1;
                }
                Now = Present_Question;
                Present_Question = Review_Array[y_coord];
                Assign_Questions();
                ((JButton) event.getSource()).setEnabled(false);
                Present_Question = Now;
            }
        }

        if (event.getActionCommand().equals("See Result")) {
            if (Authenticate_Answer()) {
                Correct_Answer = Correct_Answer + 1;
            }
            Present_Question = Present_Question + 1;
            JOptionPane.showMessageDialog(this, "Total Correct Answers are :" + Correct_Answer);
            System.exit(0);
        }
    }

    private void Assign_Questions() {
        Answer_Choice[4].setSelected(true);
        switch (Present_Question) {
            case 0:
                Question_Choice.setText("Qn 1: Who is the First Prime Minister of India :");
                Answer_Choice[1].setText("Narendra Modi");
                Answer_Choice[2].setText("Jawharlal Nehru");
                Answer_Choice[3].setText("Indira Gandhi");
                Answer_Choice[4].setText("Manmohan Singh");
                break;
            case 1:
                Question_Choice.setText("Qn 2: What is the Capital of USA :");
                Answer_Choice[1].setText("New York");
                Answer_Choice[2].setText("Washington DC");
                Answer_Choice[3].setText("California");
                Answer_Choice[4].setText("Florida");
                break;
            case 2:
                Question_Choice.setText("Qn 3: In which year the First Battle of Panipat took place :");
                Answer_Choice[1].setText("1757");
                Answer_Choice[2].setText("1556");
                Answer_Choice[3].setText("1526");
                Answer_Choice[4].setText("1947");
                break;
            case 3:
                Question_Choice.setText("Qn 4: What is the Symbol of Sodium :");
                Answer_Choice[1].setText("Na");
                Answer_Choice[2].setText("K");
                Answer_Choice[3].setText("Ca");
                Answer_Choice[4].setText("So");
                break;
            case 4:
                Question_Choice.setText("Qn 5: Who discovered the Laws of Motion :");
                Answer_Choice[1].setText("Nikola Tesla");
                Answer_Choice[2].setText("Charles Babbage");
                Answer_Choice[3].setText("Isaac Newton");
                Answer_Choice[4].setText("Jagadish Chandra Bose");
                break;
            case 5:
                Question_Choice.setText("Qn 6: Where is the Victoria Memorial located :");
                Answer_Choice[1].setText("Mumbai");
                Answer_Choice[2].setText("Shimla");
                Answer_Choice[3].setText("Delhi");
                Answer_Choice[4].setText("Kolkata");
                break;
            case 6:
                Question_Choice.setText("Qn 7: Who fought for implementing Widow Remarriage Act :");
                Answer_Choice[1].setText("Dayanand Saraswati");
                Answer_Choice[2].setText("Iswarchandra Vidyasagar");
                Answer_Choice[3].setText("Mother Teressa");
                Answer_Choice[4].setText("Lala Lajpat Rai");
                break;
            case 7:
                Question_Choice.setText("Qn 8: Under whose leadership India won World Cup in 2011 :");
                Answer_Choice[1].setText("Sourav Ganguly");
                Answer_Choice[2].setText("Virat Kohli");
                Answer_Choice[3].setText("Rohit Sharma");
                Answer_Choice[4].setText("MS Dhoni");
                break;
            case 8:
                Question_Choice.setText("Qn 9: Which is the first film of Amitabh Bachchan :");
                Answer_Choice[1].setText("Sholay");
                Answer_Choice[2].setText("Deewar");
                Answer_Choice[3].setText("Saat Hindustani");
                Answer_Choice[4].setText("Don");
                break;
            case 9:
                Question_Choice.setText(
                        "Qn 10: Which place is famous for Biriyani :");
                Answer_Choice[1].setText("Chennai");
                Answer_Choice[2].setText("Amritsar");
                Answer_Choice[3].setText("Hyderabad");
                Answer_Choice[4].setText("Ahmedabad");
                break;

            default:
                break;
        }

        Question_Choice.setBounds(30, 40, 450, 20);
    }

    boolean Authenticate_Answer() {
        switch (Present_Question) {
            case 0:
                return (Answer_Choice[2].isSelected());
            case 1:
                return (Answer_Choice[2].isSelected());
            case 2:
                return (Answer_Choice[3].isSelected());
            case 3:
                return (Answer_Choice[1].isSelected());
            case 4:
                return (Answer_Choice[3].isSelected());
            case 5:
                return (Answer_Choice[4].isSelected());
            case 6:
                return (Answer_Choice[2].isSelected());
            case 7:
                return (Answer_Choice[4].isSelected());
            case 8:
                return (Answer_Choice[3].isSelected());
            case 9:
                return (Answer_Choice[3].isSelected());

            default:
                return false;
        }
    }
}

