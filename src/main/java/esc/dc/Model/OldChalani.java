package esc.dc.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OldChalani {

    @Id
    private int id;

    private String prakar;
    private String chalaniNumber;
    private String chalaniDate;
    private String saal;
    private String letterDate;
    private String officePerson;
    private String address;
    private String subject;
    private String faat;
    private int regionId;

    public OldChalani() {
    }

    public OldChalani(int id, String prakar, String chalaniNumber, String chalaniDate, String saal, String letterDate, String officePerson, String address, String subject, String faat, int regionId) {
        this.id = id;
        this.prakar = prakar;
        this.chalaniNumber = chalaniNumber;
        this.chalaniDate = chalaniDate;
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

    public String getChalaniNumber() {
        return chalaniNumber;
    }

    public void setChalaniNumber(String chalaniNumber) {
        this.chalaniNumber = chalaniNumber;
    }

    public String getChalaniDate() {
        return chalaniDate;
    }

    public void setChalaniDate(String chalaniDate) {
        this.chalaniDate = chalaniDate;
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
        return "OldChalani{" +
                "id=" + id +
                ", prakar='" + prakar + '\'' +
                ", chalaniNumber='" + chalaniNumber + '\'' +
                ", chalaniDate='" + chalaniDate + '\'' +
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
