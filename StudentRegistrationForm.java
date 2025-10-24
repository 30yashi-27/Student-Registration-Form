import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    String name, email, course;
    int age;

    Student(String name, int age, String email, String course) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    
    public String toString() {
        return name + " (" + age + ") - " + course + " - " + email;
    }
}

public class StudentRegistrationForm extends JFrame implements ActionListener {
    JTextField nameField, ageField, emailField;
    JComboBox<String> courseBox;
    JButton registerButton, clearButton;
    JTextArea displayArea;

    ArrayList<Student> students = new ArrayList<>();

    public StudentRegistrationForm() {
        setTitle("Student Registration Form");
        setSize(400, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Course:"));
        String[] courses = {"Java", "Python", "C++", "Web Development"};
        courseBox = new JComboBox<>(courses);
        formPanel.add(courseBox);

        registerButton = new JButton("Register");
        clearButton = new JButton("Clear");
        formPanel.add(registerButton);
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.NORTH);

        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        registerButton.addActionListener(this);
        clearButton.addActionListener(e -> clearForm());

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String ageText = ageField.getText();
        String email = emailField.getText();
        String course = (String) courseBox.getSelectedItem();

        if (name.isEmpty() || ageText.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            Student s = new Student(name, age, email, course);
            students.add(s);
            displayArea.append("✅ Registered: " + s + "\n");
            clearForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid age!");
        }
    }

    private void clearForm() {
        nameField.setText("");
        ageField.setText("");
        emailField.setText("");
        courseBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new StudentRegistrationForm();
    }
}
