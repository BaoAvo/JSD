public class Validator {
    private final Dialog dialog = new Dialog();
    private final MessageConstants messageConstants = new MessageConstants();
    public boolean validateInputPatron(String name, String dob, String email, String phone) {
        if (name.isEmpty() || dob.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            dialog.showError(messageConstants.MESSAGE_FIELD_EMPTY_FAIL);
            return false;
        }
        return true;
    }

    public boolean validateDate(String dob) {
        return dob.matches(messageConstants.PATTERN_DATE_VALIDATOR);
    }

    public boolean validateEmail(String email) {
        return email.matches(messageConstants.PATTERN_EMAIL_VALIDATOR);
    }

    public boolean validatePhone(String phone) {
        return phone.matches(messageConstants.PATTERN_PHONE_VALIDATOR);
    }
}
