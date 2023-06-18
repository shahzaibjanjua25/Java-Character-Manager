/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asif2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

// Person class
class Person implements Serializable {
    protected String name;
    protected String address;

    public Person() {
        String x = "Person construct is called";
    }

    public Person(String name) {
        this.name = name;
        String x = "Person construct 2 is called" + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // A method that will be overridden by sub-class
    public String myMethod(String t) {
        return t + " hello";
    }
}

// Student class inheriting from Person
class Student extends Person implements Serializable {
    private String school;
    private int gradeLevel;

    public Student() {
        super();
        String x = "Student construct is called";
    }

    public Student(String name, String school, int gradeLevel) {
        super(name);
        this.school = school;
        this.gradeLevel = gradeLevel;
        String x = "Student construct 2 is called" + name + school + gradeLevel;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    // Override the myMethod() from the parent class
    @Override
    public String myMethod(String t) {
        return t + " from Student";
    }
}

// InternationalStudent class inheriting from Student
class InternationalStudent extends Student implements Serializable {
    private String country;

    public InternationalStudent() {
        super();
        String x = "InternationalStudent construct is called";
    }

    public InternationalStudent(String name, String school, int gradeLevel, String country) {
        super(name, school, gradeLevel);
        this.country = country;
        String x = "InternationalStudent construct 2 is called" + name + school + gradeLevel + country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Override the myMethod() from the parent class
    @Override
    public String myMethod(String t) {
        return t + " from InternationalStudent";
    }
}

// Teacher class inheriting from Person
class Teacher extends Person implements Serializable {
    private String subject1;
    private String subject2;

    public Teacher() {
        super();
        String x = "Teacher construct is called";
    }

    public Teacher(String name, String subject1, String subject2) {
        super(name);
        this.subject1 = subject1;
        this.subject2 = subject2;
        String x = "Teacher construct 2 is called" + name + subject1 + subject2;
    }

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    // Override the myMethod() from the parent class
    @Override
    public String myMethod(String t) {
        return t + " from Teacher";
    }
}

// Main class for the GUI
public class CharacterCreationGUI extends JFrame {
    private static final int MAX_CHARACTERS = 100;
    private static final String DATA_FILE = "character_data.txt";

    private ArrayList<Person> characters = new ArrayList<>();

    // GUI components
    private JTextField nameField;
    private JComboBox<String> characterTypeComboBox;
    private JTextArea characterDetailsTextArea;
    private JTextField addressField; // New address field

    public CharacterCreationGUI() {
        super("Character Creation");

        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Initialize GUI components
        nameField = new JTextField(20);
        characterTypeComboBox = new JComboBox<>(new String[] { "Student", "International Student", "Teacher" });
        characterDetailsTextArea = new JTextArea(10, 30);
        characterDetailsTextArea.setEditable(false);
        addressField = new JTextField(20); // New address field

        // Create character button
        JButton createButton = new JButton("Create Character");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createCharacter();
            }
        });

        // Modify character button
        JButton modifyButton = new JButton("Modify Character");
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modifyCharacter();
            }
        });

        // Delete character button
        JButton deleteButton = new JButton("Delete Character");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCharacter();
            }
        });

        // View character button
        JButton viewButton = new JButton("View Character");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewCharacter();
            }
        });

        // Layout GUI components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Address:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Character Type:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(characterTypeComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(createButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(modifyButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(deleteButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(viewButton, gbc);

        JPanel characterDetailsPanel = new JPanel(new BorderLayout());
        characterDetailsPanel.setBorder(BorderFactory.createTitledBorder("Character Details"));
        characterDetailsPanel.add(new JScrollPane(characterDetailsTextArea), BorderLayout.CENTER);

        // Add main panel and character details panel to the frame
        add(mainPanel, BorderLayout.NORTH);
        add(characterDetailsPanel, BorderLayout.CENTER);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);

        // Load character data from file
        loadData();
    }

    // Create a new character
    private void createCharacter() {
        String name = nameField.getText();
        String address = addressField.getText();
        String characterType = (String) characterTypeComboBox.getSelectedItem();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name for the character.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (address.isEmpty()) { // Validate address field
            JOptionPane.showMessageDialog(this, "Please enter an address for the character.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create character based on the selected character type
        if (characterType.equals("Student")) {
            Student student = new Student();
            student.setName(name);
            student.setAddress(address);
            characters.add(student);
            saveData();
        } else if (characterType.equals("International Student")) {
            String school = JOptionPane.showInputDialog(this, "Enter the school for the international student:");
            if (school == null || school.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a school for the international student.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String gradeLevelString = JOptionPane.showInputDialog(this,
                    "Enter the grade level for the international student:");
            if (gradeLevelString == null || gradeLevelString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a grade level for the international student.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int gradeLevel;
            try {
                gradeLevel = Integer.parseInt(gradeLevelString);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid grade level. Please enter a numeric value.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            InternationalStudent internationalStudent = new InternationalStudent();
            internationalStudent.setName(name);
            internationalStudent.setAddress(address);
            internationalStudent.setSchool(school);
            internationalStudent.setGradeLevel(gradeLevel);
            characters.add(internationalStudent);
            saveData();
        } else if (characterType.equals("Teacher")) {
            String subject1 = JOptionPane.showInputDialog(this, "Enter the first subject for the teacher:");
            if (subject1 == null || subject1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the first subject for the teacher.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String subject2 = JOptionPane.showInputDialog(this, "Enter the second subject for the teacher:");
            if (subject2 == null || subject2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the second subject for the teacher.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setAddress(address);
            teacher.setSubject1(subject1);
            teacher.setSubject2(subject2);
            characters.add(teacher);
            saveData();
        }

        JOptionPane.showMessageDialog(this, "Character created successfully.", "Success",
                JOptionPane.INFORMATION_MESSAGE);
        clearFields();
    }

    // Modify an existing character
    private void modifyCharacter() {
        if (characters.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No characters available to modify.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] characterOptions = characters.toArray();
        Object selectedCharacter = JOptionPane.showInputDialog(this, "Select a character to modify:", "Modify Character",
                JOptionPane.PLAIN_MESSAGE, null, characterOptions, characterOptions[0]);

        if (selectedCharacter == null) {
            return;
        }

        String newName = JOptionPane.showInputDialog(this, "Enter the new name for the character:");
        if (newName == null || newName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a new name for the character.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newAddress = JOptionPane.showInputDialog(this, "Enter the new address for the character:"); // Modify address field
        if (newAddress == null || newAddress.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a new address for the character.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the selected character with the new name and address
        Person selectedPerson = (Person) selectedCharacter;
        selectedPerson.setName(newName);
        selectedPerson.setAddress(newAddress);
        saveData();
        JOptionPane.showMessageDialog(this, "Character modified successfully.", "Success",
                JOptionPane.INFORMATION_MESSAGE);
        clearFields();
    }

    // Delete an existing character
    private void deleteCharacter() {
        if (characters.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No characters available to delete.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] characterOptions = characters.toArray();
        Object selectedCharacter = JOptionPane.showInputDialog(this, "Select a character to delete:", "Delete Character",
                JOptionPane.PLAIN_MESSAGE, null, characterOptions, characterOptions[0]);

        if (selectedCharacter == null) {
            return;
        }

        characters.remove(selectedCharacter);
        saveData();
        JOptionPane.showMessageDialog(this, "Character deleted successfully.", "Success",
                JOptionPane.INFORMATION_MESSAGE);
        clearFields();
    }

    // View details of an existing character
    private void viewCharacter() {
        if (characters.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No characters available to view.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] characterOptions = characters.toArray();
        Object selectedCharacter = JOptionPane.showInputDialog(this, "Select a character to view:", "View Character",
                JOptionPane.PLAIN_MESSAGE, null, characterOptions, characterOptions[0]);

        if (selectedCharacter == null) {
            return;
        }

        // Display the details of the selected character
        Person selectedPerson = (Person) selectedCharacter;
        String details = "Name: " + selectedPerson.getName() + "\nAddress: " + selectedPerson.getAddress()
                + "\nType: " + selectedPerson.getClass().getSimpleName() + "\nDetails: "
                + selectedPerson.myMethod("Hello");
        characterDetailsTextArea.setText(details);
    }

    // Clear the input fields
    private void clearFields() {
        nameField.setText("");
        addressField.setText("");
    }

    // Save character data to file
    private void saveData() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            outputStream.writeObject(characters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load character data from file
    private void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            characters = (ArrayList<Person>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CharacterCreationGUI();
            }
        });
    }
}

