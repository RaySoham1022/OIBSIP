
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
            Login_Part Login_Section = new Login_Part();
            Login_Section.setSize(400, 180);
            Login_Section.setVisible(true);
            Login_Section.setLocation(400, 300);

        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, e.getMessage(), "System Error", 0);
        }
    }
}

class Login_Part extends JFrame implements ActionListener {
    JButton Submission_Button;
    JPanel Login_Panel;
    JLabel User_Name, Password;
    JTextField User_Name_TextField, Password_TextField;

    Login_Part() {
        User_Name = new JLabel();
        User_Name.setText("Enter Your UserName :");
        User_Name_TextField = new JTextField(15);
        Password = new JLabel();
        Password.setText("Enter Password :");
        Password_TextField = new JPasswordField(10);
        Submission_Button = new JButton("Login Now");
        Submission_Button.setBounds(150, 100, 50, 80);
        Login_Panel = new JPanel(new GridLayout(3, 1));
        Login_Panel.add(User_Name);
        Login_Panel.add(User_Name_TextField);
        Login_Panel.add(Password);
        Login_Panel.add(Password_TextField);
        Login_Panel.add(Submission_Button);
        add(Login_Panel, BorderLayout.CENTER);
        Submission_Button.addActionListener(this);
        setTitle("Login Details");
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
    JLabel Time_Section, Question_Section;
    JRadioButton Collection_Button[] = new JRadioButton[6];
    JButton Save_Next, Review_Later;
    ButtonGroup Button_Group;
    int Correct_Answer = 0, Current_Question = 0, x_coord = 1, y_coord = 1, Now = 0;
    int Review_Array[] = new int[10];
    Timer Ticker = new Timer();

    Begin_Online_Test(String User_Name) {
        super(User_Name);
        Time_Section = new JLabel();
        Question_Section = new JLabel();
        add(Time_Section);
        add(Question_Section);
        Button_Group = new ButtonGroup();
        for (int Button_No = 1; Button_No <= 4; Button_No++) {
            Collection_Button[Button_No] = new JRadioButton();
            add(Collection_Button[Button_No]);
            Button_Group.add(Collection_Button[Button_No]);
        }
        Save_Next = new JButton("Save and Next Qn");
        Review_Later = new JButton("Review Question Later");
        Save_Next.addActionListener(this);
        Review_Later.addActionListener(this);
        add(Save_Next);
        add(Review_Later);
        Assign_Questions();
        Question_Section.setBounds(30, 40, 450, 20);
        Time_Section.setBounds(20, 20, 450, 20);
        Collection_Button[1].setBounds(50, 80, 100, 20);
        Collection_Button[2].setBounds(50, 110, 100, 20);
        Collection_Button[3].setBounds(50, 140, 100, 20);
        Collection_Button[4].setBounds(50, 170, 100, 20);
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
            Current_Question = Current_Question + 1;
            Assign_Questions();
            if (Current_Question == 9) {
                Save_Next.setEnabled(false);
                Review_Later.setText("Get Result");
            }
        }
        if (event.getActionCommand().equals("Review Question Later")) {
            JButton Review = new JButton("Review Qno " + x_coord);
            Review.setBounds(480, 20 + (30 * x_coord), 100, 30);
            add(Review);
            Review.addActionListener(this);
            Review_Array[x_coord] = Current_Question;
            x_coord = x_coord + 1;
            Current_Question = Current_Question + 1;
            Assign_Questions();
            if (Current_Question == 9) {
                Review_Later.setText("Get Result");
            }
            setVisible(false);
            setVisible(true);
        }

        for (int Initial = 0, y_coord = 1; Initial < x_coord; Initial++, y_coord++) {
            if (event.getActionCommand().equals("Review Qno " + y_coord)) {
                if (Authenticate_Answer()) {
                    Correct_Answer = Correct_Answer + 1;
                }
                Now = Current_Question;
                Current_Question = Review_Array[y_coord];
                Assign_Questions();
                ((JButton) event.getSource()).setEnabled(false);
                Current_Question = Now;
            }
        }

        if (event.getActionCommand().equals("Get Result")) {
            if (Authenticate_Answer()) {
                Correct_Answer = Correct_Answer + 1;
            }
            Current_Question = Current_Question + 1;
            JOptionPane.showMessageDialog(this, "You Total No of Correct Answers are :" + Correct_Answer);
            System.exit(0);
        }
    }

    private void Assign_Questions() {
        Collection_Button[4].setSelected(true);
        switch (Current_Question) {
            case 0:
                Question_Section.setText("Qn 1: Who is Father of JAVA Programming Language :");
                Collection_Button[1].setText("Charles Babbage");
                Collection_Button[2].setText("James Gosling");
                Collection_Button[3].setText("M.P. JAVA");
                Collection_Button[4].setText("Priyanka Shinde");
                break;
            case 1:
                Question_Section.setText("Qn 2: Total Numbe of Primitive Data Types in JAVA :");
                Collection_Button[1].setText("6");
                Collection_Button[2].setText("7");
                Collection_Button[3].setText("8");
                Collection_Button[4].setText("9");
                break;
            case 2:
                Question_Section.setText("Qn 3: Where is Actual \'System\'' Class Defined :");
                Collection_Button[1].setText("java.lang.package");
                Collection_Button[2].setText("java.util.package");
                Collection_Button[3].setText("java.lo.package");
                Collection_Button[4].setText("java.utils.package");
                break;
            case 3:
                Question_Section.setText("Qn 4: Expected Created By Try-Catch Block is Caught in Which Block :");
                Collection_Button[1].setText("Catch");
                Collection_Button[2].setText("Throws");
                Collection_Button[3].setText("Final");
                Collection_Button[4].setText("Thrown");
                break;
            case 4:
                Question_Section.setText("Qn 5: Which of The Following  is Not an OOPS Concept in JAVA :");
                Collection_Button[1].setText("POLYMORPHISM");
                Collection_Button[2].setText("INHERITANCE");
                Collection_Button[3].setText("Compilation");
                Collection_Button[4].setText("ENCAPSULATION");
                break;
            case 5:
                Question_Section.setText("Qn 6: Identify The Infinite LOOP among The Following :");
                Collection_Button[1].setText("for ( ; ; )");
                Collection_Button[2].setText("for()i=0 ; i<10 ; i++");
                Collection_Button[3].setText("for(i=0 ; i<10 ; i++)");
                Collection_Button[4].setText("for(int=0 ; i++)");
                break;
            case 6:
                Question_Section.setText("Qn 7: When is Finalize() Method Called :");
                Collection_Button[1].setText("Before GarBage Collection");
                Collection_Button[2].setText("Before an Object goes out of Scope");
                Collection_Button[3].setText("When Variable Goes out of Scope");
                Collection_Button[4].setText("None");
                break;
            case 7:
                Question_Section.setText("Qn 8: What is Implicit return Type of Constructor :");
                Collection_Button[1].setText("No Return Type");
                Collection_Button[2].setText("A Class Object in which it is Defined");
                Collection_Button[3].setText("void");
                Collection_Button[4].setText("All of The Above");
                break;
            case 8:
                Question_Section.setText("Qn 9: Class at the Top of Exception Class is Known as :");
                Collection_Button[1].setText("AritmeticException");
                Collection_Button[2].setText("Throwable");
                Collection_Button[3].setText("Object");
                Collection_Button[4].setText("Console");
                break;
            case 9:
                Question_Section.setText(
                        "Qn 10: Which Provides Runtime Environment For JAVA Byte Code to be Executed on Machine :");
                Collection_Button[1].setText("JDK");
                Collection_Button[2].setText("JVM");
                Collection_Button[3].setText("JRE");
                Collection_Button[4].setText("JAVAC");
                break;

            default:
                break;
        }

        Question_Section.setBounds(30, 40, 450, 20);
    }

    boolean Authenticate_Answer() {
        switch (Current_Question) {
            case 0:
                return (Collection_Button[2].isSelected());
            case 1:
                return (Collection_Button[2].isSelected());
            case 2:
                return (Collection_Button[3].isSelected());
            case 3:
                return (Collection_Button[1].isSelected());
            case 4:
                return (Collection_Button[3].isSelected());
            case 5:
                return (Collection_Button[4].isSelected());
            case 6:
                return (Collection_Button[2].isSelected());
            case 7:
                return (Collection_Button[4].isSelected());
            case 8:
                return (Collection_Button[3].isSelected());
            case 9:
                return (Collection_Button[3].isSelected());

            default:
                return false;
        }
    }
}