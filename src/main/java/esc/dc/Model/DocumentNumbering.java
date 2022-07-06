package esc.dc.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="document_numbering")
public class DocumentNumbering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String field;

    @ManyToOne
    @JoinColumn(name = "numbering_factor_id",referencedColumnName = "id")
    @JsonManagedReference
    private NumberingFactor numberingFactor;

    @Column(name = "first_number_amt")
    private int firstNumberAmt;

    @Column(name = "first_number_starting_no")
    private int firstNumberStartingNo;

    @Column(name = "first_number_increasing_factor")
    private int firstNumberIncreasingFactor;

    @Column(name = "first_word")
    private String firstWord;

    @Column(name = "second_number_amt")
    private int secondNumberAmt;

    @Column(name = "second_number_starting_no")
    private int secondNumberStartingNo;

    @Column(name = "second_number_increasing_factor")
    private int secondNumberIncreasingFactor;

    @Column(name = "second_word")
    private String secondWord;

    @Column(name = "display_no")
    private String displayNo;

    public String getFormattedStatusType() {
        return this.status?"Enabled":"Disabled";
    }

    public boolean status;


    @NotNull
    private int regionId;

    public DocumentNumbering() {
    }

    public DocumentNumbering(String field, NumberingFactor numberingFactor, int firstNumberAmt, int firstNumberStartingNo, int firstNumberIncreasingFactor, String firstWord, int secondNumberAmt, int secondNumberStartingNo, int secondNumberIncreasingFactor, String secondWord, String displayNo, boolean status, @NotNull int regionId) {
        this.field = field;
        this.numberingFactor = numberingFactor;
        this.firstNumberAmt = firstNumberAmt;
        this.firstNumberStartingNo = firstNumberStartingNo;
        this.firstNumberIncreasingFactor = firstNumberIncreasingFactor;
        this.firstWord = firstWord;
        this.secondNumberAmt = secondNumberAmt;
        this.secondNumberStartingNo = secondNumberStartingNo;
        this.secondNumberIncreasingFactor = secondNumberIncreasingFactor;
        this.secondWord = secondWord;
        this.displayNo = displayNo;
        this.status = status;
        this.regionId = regionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public NumberingFactor getNumberingFactor() {
        return numberingFactor;
    }

    public void setNumberingFactor(NumberingFactor numberingFactor) {
        this.numberingFactor = numberingFactor;
    }

    public int getFirstNumberAmt() {
        return firstNumberAmt;
    }

    public void setFirstNumberAmt(int firstNumberAmt) {
        this.firstNumberAmt = firstNumberAmt;
    }

    public int getFirstNumberStartingNo() {
        return firstNumberStartingNo;
    }

    public void setFirstNumberStartingNo(int firstNumberStartingNo) {
        this.firstNumberStartingNo = firstNumberStartingNo;
    }

    public int getFirstNumberIncreasingFactor() {
        return firstNumberIncreasingFactor;
    }

    public void setFirstNumberIncreasingFactor(int firstNumberIncreasingFactor) {
        this.firstNumberIncreasingFactor = firstNumberIncreasingFactor;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }

    public int getSecondNumberAmt() {
        return secondNumberAmt;
    }

    public void setSecondNumberAmt(int secondNumberAmt) {
        this.secondNumberAmt = secondNumberAmt;
    }

    public int getSecondNumberStartingNo() {
        return secondNumberStartingNo;
    }

    public void setSecondNumberStartingNo(int secondNumberStartingNo) {
        this.secondNumberStartingNo = secondNumberStartingNo;
    }

    public int getSecondNumberIncreasingFactor() {
        return secondNumberIncreasingFactor;
    }

    public void setSecondNumberIncreasingFactor(int secondNumberIncreasingFactor) {
        this.secondNumberIncreasingFactor = secondNumberIncreasingFactor;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }

    public String getDisplayNo() {
        return displayNo;
    }

    public void setDisplayNo(String displayNo) {
        this.displayNo = displayNo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "DocumentNumbering{" +
                "id=" + id +
                ", field='" + field + '\'' +
                ", numberingFactor=" + numberingFactor +
                ", firstNumberAmt=" + firstNumberAmt +
                ", firstNumberStartingNo=" + firstNumberStartingNo +
                ", firstNumberIncreasingFactor=" + firstNumberIncreasingFactor +
                ", firstWord='" + firstWord + '\'' +
                ", secondNumberAmt=" + secondNumberAmt +
                ", secondNumberStartingNo=" + secondNumberStartingNo +
                ", secondNumberIncreasingFactor=" + secondNumberIncreasingFactor +
                ", secondWord='" + secondWord + '\'' +
                ", displayNo='" + displayNo + '\'' +
                ", status=" + status +
                ", regionId=" + regionId +
                '}';
    }
}
