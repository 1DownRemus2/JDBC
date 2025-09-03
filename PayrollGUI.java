import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class PayrollGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Payroll System");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel l1 = new JLabel("Name:");
        l1.setBounds(30, 30, 100, 30);
        JTextField tf1 = new JTextField();
        tf1.setBounds(130, 30, 200, 30);

        JLabel l2 = new JLabel("Dept:");
        l2.setBounds(30, 70, 100, 30);
        JTextField tf2 = new JTextField();
        tf2.setBounds(130, 70, 200, 30);

        JLabel l3 = new JLabel("Base Salary:");
        l3.setBounds(30, 110, 100, 30);
        JTextField tf3 = new JTextField();
        tf3.setBounds(130, 110, 200, 30);

        JLabel l4 = new JLabel("Bonus:");
        l4.setBounds(30, 150, 100, 30);
        JTextField tf4 = new JTextField();
        tf4.setBounds(130, 150, 200, 30);

        JLabel l5 = new JLabel("Deductions:");
        l5.setBounds(30, 190, 100, 30);
        JTextField tf5 = new JTextField();
        tf5.setBounds(130, 190, 200, 30);

        JButton addBtn = new JButton("Add Employee");
        addBtn.setBounds(100, 240, 180, 30);

        JButton viewBtn = new JButton("View Employees");
        viewBtn.setBounds(100, 280, 180, 30);

        JButton pdfBtn = new JButton("Generate Payslip");
        pdfBtn.setBounds(100, 320, 180, 30);

        JTextField idField = new JTextField("Enter ID");
        idField.setBounds(130, 360, 100, 30);

        frame.add(l1);
        frame.add(tf1);
        frame.add(l2);
        frame.add(tf2);
        frame.add(l3);
        frame.add(tf3);
        frame.add(l4);
        frame.add(tf4);
        frame.add(l5);
        frame.add(tf5);
        frame.add(addBtn);
        frame.add(viewBtn);
        frame.add(pdfBtn);
        frame.add(idField);

        frame.setVisible(true);

        // Button actions
        addBtn.addActionListener(e -> {
            String name = tf1.getText();
            String dept = tf2.getText();
            double base = Double.parseDouble(tf3.getText());
            double bonus = Double.parseDouble(tf4.getText());
            double deduct = Double.parseDouble(tf5.getText());
            Employee emp = new Employee(name, dept, base, bonus, deduct);
            EmployeeDAO.addEmployee(emp);
            JOptionPane.showMessageDialog(frame, "Employee added successfully!");
        });

        viewBtn.addActionListener(e -> {
            List<Employee> employees = EmployeeDAO.getAllEmployees();
            StringBuilder sb = new StringBuilder();
            for (Employee emp : employees) {
                double net = emp.getBaseSalary() + emp.getBonus() - emp.getDeductions();
                sb.append(emp.getId()).append(" - ").append(emp.getName()).append(" - ₹").append(net).append("\n");
            }
            JOptionPane.showMessageDialog(frame, sb.toString(), "All Employees", JOptionPane.INFORMATION_MESSAGE);
        });

        pdfBtn.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            Employee emp = EmployeeDAO.getEmployeeById(id);
            if (emp != null) {
                PDFGenerator.generatePayslip(emp);
                JOptionPane.showMessageDialog(frame, "Payslip generated for ID " + id);
            } else {
                JOptionPane.showMessageDialog(frame, "Employee not found");
            }
        });
    }
}
