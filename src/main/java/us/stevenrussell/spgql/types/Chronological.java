package us.stevenrussell.spgql.types;

import java.time.LocalDate;

public interface Chronological {
    public LocalDate getCreated();
    public LocalDate getUpdated();
}
