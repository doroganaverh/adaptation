package ru.vasya.staff;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class Organization extends Staff {
    private String fullName;
    private String shortName;
    private String head;
    private String contacts;

    public Organization(){

    }

    public String getFullName() {
        return fullName;
    }

    @XmlElement
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    @XmlElement
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getHead() {
        return head;
    }

    @XmlElement
    public void setHead(String head) {
        this.head = head;
    }

    public String getContacts() {
        return contacts;
    }

    @XmlElement
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
