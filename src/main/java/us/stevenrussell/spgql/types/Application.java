package us.stevenrussell.spgql.types;

import java.time.LocalDate;
import java.util.List;

public class Application {
    private long id;
    private String name;
    private String displayName;
    private String description;
    private Role provisionerRole;
    private boolean isDeleted;
    private LocalDate created;
    private LocalDate updated;
    private List<Entitlement> entitlements;

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

    public Role getProvisionerRole() {
        return provisionerRole;
    }

    public void setProvisionerRole(Role provisionerRole) {
        this.provisionerRole = provisionerRole;
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

    public List<Entitlement> getEntitlements() {
        return entitlements;
    }

    public void setEntitlements(List<Entitlement> entitlements) {
        this.entitlements = entitlements;
    }
}
