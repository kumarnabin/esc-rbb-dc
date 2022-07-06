package esc.dc.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OldDarta {

    @Id
    private int id;

    private String prakar;
    private String dartaNumber;
    private String dartaDate;
    private String saal;
    private String letterDate;
    private String officePerson;
    private String address;
    private String subject;
    private String faat;
    private int regionId;

    public OldDarta() {
    }

    public OldDarta(int id, String prakar, String dartaNumber, String dartaDate, String saal, String letterDate, String officePerson, String address, String subject, String faat, int regionId) {
        this.id = id;
        this.prakar = prakar;
        this.dartaNumber = dartaNumber;
        this.dartaDate = dartaDate;
        this.saal = saal;
        this.letterDate = letterDate;
        this.officePerson = officePerson;
        this.address = address;
        this.subject = subject;
        this.faat = faat;
        this.regionId = regionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrakar() {
        return prakar;
    }

    public void setPrakar(String prakar) {
        this.prakar = prakar;
    }

    public String getDartaNumber() {
        return dartaNumber;
    }

    public void setDartaNumber(String dartaNumber) {
        this.dartaNumber = dartaNumber;
    }

    public String getDartaDate() {
        return dartaDate;
    }

    public void setDartaDate(String dartaDate) {
        this.dartaDate = dartaDate;
    }

    public String getSaal() {
        return saal;
    }

    public void setSaal(String saal) {
        this.saal = saal;
    }

    public String getLetterDate() {
        return letterDate;
    }

    public void setLetterDate(String letterDate) {
        this.letterDate = letterDate;
    }

    public String getOfficePerson() {
        return officePerson;
    }

    public void setOfficePerson(String officePerson) {
        this.officePerson = officePerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFaat() {
        return faat;
    }

    public void setFaat(String faat) {
        this.faat = faat;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "OldDarta{" +
                "id=" + id +
                ", prakar='" + prakar + '\'' +
                ", dartaNumber='" + dartaNumber + '\'' +
                ", dartaDate='" + dartaDate + '\'' +
                ", saal='" + saal + '\'' +
                ", letterDate='" + letterDate + '\'' +
                ", officePerson='" + officePerson + '\'' +
                ", address='" + address + '\'' +
                ", subject='" + subject + '\'' +
                ", faat='" + faat + '\'' +
                ", regionId=" + regionId +
                '}';
    }
}
