package esc.dc.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Chalani {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String chalaniNumber;

    private String chalaniDate;

    public String englishChalaniDate;

    public String praSamWaChaNo;

    private String letterDate;

    public String englishLetterDate;

    public String subject;
    public String address;
    public String faat;
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
            name = "chalani_chalani_person",
            joinColumns = @JoinColumn(name = "chalani_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    @JsonManagedReference
    private ChalaniPerson chalaniPerson;


    @NotNull
    private int regionId;

    public Chalani() {
    }

    public Chalani(String chalaniNumber, String chalaniDate, String englishChalaniDate, String praSamWaChaNo, String letterDate, String englishLetterDate, String subject, String address, String faat, String signature, boolean recivType, String imagePath, MultipartFile file, Office office, ChalaniPerson chalaniPerson, @NotNull int regionId) {
        this.chalaniNumber = chalaniNumber;
        this.chalaniDate = chalaniDate;
        this.englishChalaniDate = englishChalaniDate;
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
        this.chalaniPerson = chalaniPerson;
        this.regionId = regionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEnglishChalaniDate() {
        return englishChalaniDate;
    }

    public void setEnglishChalaniDate(String englishChalaniDate) {
        this.englishChalaniDate = englishChalaniDate;
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

    public ChalaniPerson getChalaniPerson() {
        return chalaniPerson;
    }

    public void setChalaniPerson(ChalaniPerson chalaniPerson) {
        this.chalaniPerson = chalaniPerson;
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
        return "Chalani{" +
                "id=" + id +
                ", chalaniNumber='" + chalaniNumber + '\'' +
                ", chalaniDate='" + chalaniDate + '\'' +
                ", englishChalaniDate='" + englishChalaniDate + '\'' +
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
                ", chalaniPerson=" + chalaniPerson +
                ", regionId=" + regionId +
                '}';
    }
}
