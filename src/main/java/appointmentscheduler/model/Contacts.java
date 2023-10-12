package appointmentscheduler.model;

public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Gets the contact id.
     *
     * @return the contact id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Gets the contact name.
     *
     * @return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Gets the email of the contact.
     *
     * @return the email of the contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns a string representation of the contact.
     *
     * @return a string representation of the contact
     */
    @Override
    public String toString() {
        return getContactName() + " (ID: " + getContactId() + ")";
    }
}
