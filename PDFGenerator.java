import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

public class PDFGenerator {
    public static void generatePayslip(Employee emp) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Payslip_" + emp.getId() + ".pdf"));
            document.open();

            document.add(new Paragraph("Employee Payslip", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("ID: " + emp.getId()));
            document.add(new Paragraph("Name: " + emp.getName()));
            document.add(new Paragraph("Department: " + emp.getDepartment()));
            document.add(new Paragraph("Base Salary: " + emp.getBaseSalary()));
            document.add(new Paragraph("Bonus: " + emp.getBonus()));
            document.add(new Paragraph("Deductions: " + emp.getDeductions()));
            double netSalary = emp.getBaseSalary() + emp.getBonus() - emp.getDeductions();
            document.add(new Paragraph("Net Salary: " + netSalary));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
