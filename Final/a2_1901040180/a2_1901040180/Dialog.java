import javax.swing.*;

public class Dialog {
    public void showError(String message) {
        JOptionPane.showConfirmDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showInformation(String message) {
        JOptionPane.showConfirmDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
