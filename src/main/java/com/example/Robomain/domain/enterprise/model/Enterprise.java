package com.example.Robomain.domain.enterprise.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Enterprise domain entity — the top-level organizational unit.
 * Pure Java, no JPA/Spring. Business rules enforced via factory + methods.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {

    private UUID id;
    private String enterpriseName;
    private String enterpriseCode;
    private String enterpriseMail;
    private String enterprisePhone;
    private String enterpriseAddress;
    @Builder.Default
    private int facilityTotal = 0;
    private UUID businessClassificationId;
    private Date createdAt;
    private Date updatedAt;

    public static Enterprise create(String name, String code, String mail, String phone, String address) {
        if (name == null || name.isBlank()) throw new ValidationException("Enterprise name cannot be blank");
        if (mail == null || !mail.contains("@")) throw new ValidationException("Invalid enterprise email: " + mail);
        return Enterprise.builder()
                .id(UUID.randomUUID())
                .enterpriseName(name)
                .enterpriseCode(code)
                .enterpriseMail(mail)
                .enterprisePhone(phone)
                .enterpriseAddress(address)
                .facilityTotal(0)
                .build();
    }

    public void update(String name, String code, String mail, String phone, String address) {
        if (name != null && !name.isBlank()) this.enterpriseName = name;
        if (code != null) this.enterpriseCode = code;
        if (mail != null) this.enterpriseMail = mail;
        if (phone != null) this.enterprisePhone = phone;
        if (address != null) this.enterpriseAddress = address;
    }

    /** Called when a new facility is added to this enterprise. */
    public void incrementFacilityTotal() {
        this.facilityTotal++;
    }

    /** Called when a facility is removed from this enterprise. */
    public void decrementFacilityTotal() {
        if (this.facilityTotal > 0) this.facilityTotal--;
    }
}
