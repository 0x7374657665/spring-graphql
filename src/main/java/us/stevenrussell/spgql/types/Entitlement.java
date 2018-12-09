package us.stevenrussell.spgql.types;

import java.time.LocalDate;

public class Entitlement implements Chronological {
    private long id;
    private String name;
    private String displayName;
    private String description;
    private String businessUnit;
    private boolean restricted;
    private long parentApplicationId;
    private boolean isDeleted;
    private LocalDate created;
    private LocalDate updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public long getParentApplicationId() {
        return parentApplicationId;
    }

    public void setParentApplicationId(long parentApplicationId) {
        this.parentApplicationId = parentApplicationId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }
}
