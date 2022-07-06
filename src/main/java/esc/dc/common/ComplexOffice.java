package esc.dc.common;

import esc.dc.Model.Office;

public class ComplexOffice {
    private final long id;
    private final Office office;

    public long getId() {
        return id;
    }

    public Office getOffice() {
        return office;
    }

    public ComplexOffice(long id, Office office) {
        this.id = id;
        this.office = office;
    }
}
