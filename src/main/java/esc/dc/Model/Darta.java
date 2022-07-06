package esc.dc.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Darta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String dartaNumber;


    private String dartaDate;

    public String englishDartaDate;

    public String praSamWaChaNo;

    private String letterDate;

    public String englishLetterDate;

    public String subject;
    public String address;
    public String faat;

    //signature is used as letter cha.no.
    public String signature;
    public boolean recivType;

    @Column(name="image_path")
    private String imagePath;

    @Transient
    private MultipartFile file;

    @ManyToOne
    @JoinColumn(name = "office_id",referencedColumnName = "id")
    @JsonManagedReference
    private Office office;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "darta_darta_Person",
            joinColumns = @JoinColumn(name = "darta_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    @JsonManagedReference
    private DartaPerson dartaPerson;


    @NotNull
    private int regionId;

    public Darta() {
    }

    public Darta(String dartaNumber, String dartaDate, String englishDartaDate, String praSamWaChaNo, String letterDate, String englishLetterDate, String subject, String address, String faat, String signature, boolean recivType, String imagePath, MultipartFile file, Office office, DartaPerson dartaPerson, @NotNull int regionId) {
        this.dartaNumber = dartaNumber;
        this.dartaDate = dartaDate;
        this.englishDartaDate = englishDartaDate;
        this.praSamWaChaNo = praSamWaChaNo;
        this.letterDate = letterDate;
        this.englishLetterDate = englishLetterDate;
        this.subject = subject;
        this.address = address;
        this.faat = faat;
        this.signature = signature;
        this.recivType = recivType;
        this.imagePath = imagePath;
        this.file = file;
        this.office = office;
        this.dartaPerson = dartaPerson;
        this.regionId = regionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPraSamWaChaNo() {
        return praSamWaChaNo;
    }

    public void setPraSamWaChaNo(String praSamWaChaNo) {
        this.praSamWaChaNo = praSamWaChaNo;
    }

    public String getLetterDate() {
        return letterDate;
    }

    public void setLetterDate(String letterDate) {
        this.letterDate = letterDate;
    }

    public String getEnglishDartaDate() {
        return englishDartaDate;
    }

    public void setEnglishDartaDate(String englishDartaDate) {
        this.englishDartaDate = englishDartaDate;
    }

    public String getEnglishLetterDate() {
        return englishLetterDate;
    }

    public void setEnglishLetterDate(String englishLetterDate) {
        this.englishLetterDate = englishLetterDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFaat() {
        return faat;
    }

    public void setFaat(String faat) {
        this.faat = faat;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isRecivType() {
        return recivType;
    }

    public void setRecivType(boolean recivType) {
        this.recivType = recivType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public DartaPerson getDartaPerson() {
        return dartaPerson;
    }

    public void setDartaPerson(DartaPerson dartaPerson) {
        this.dartaPerson = dartaPerson;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getFormattedDartaType() {
        return this.recivType?"Person":"Office";
    }

    @Override
    public String toString() {
        return "Darta{" +
                "id=" + id +
                ", dartaNumber='" + dartaNumber + '\'' +
                ", dartaDate='" + dartaDate + '\'' +
                ", englishDartaDate='" + englishDartaDate + '\'' +
                ", praSamWaChaNo='" + praSamWaChaNo + '\'' +
                ", letterDate='" + letterDate + '\'' +
                ", englishLetterDate='" + englishLetterDate + '\'' +
                ", subject='" + subject + '\'' +
                ", address='" + address + '\'' +
                ", faat='" + faat + '\'' +
                ", signature='" + signature + '\'' +
                ", recivType=" + recivType +
                ", imagePath='" + imagePath + '\'' +
                ", file=" + file +
                ", office=" + office +
                ", dartaPerson=" + dartaPerson +
                ", regionId=" + regionId +
                '}';
    }
}
