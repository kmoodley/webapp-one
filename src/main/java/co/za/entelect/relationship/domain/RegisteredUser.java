package co.za.entelect.relationship.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class RegisteredUser
{
    @Id
    @GeneratedValue
    private Long id;

    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private boolean termsAndConditions;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn( name = "security_data_id" )
    private SecurityData securityData;

    private RegisteredUser()
    {
    }

    public RegisteredUser(String password, String firstname, String lastname, String emailAddress,
                          LocalDate dateOfBirth, boolean termsAndConditions)
    {
        securityData = new SecurityData(emailAddress, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.termsAndConditions = termsAndConditions;
    }

    public RegisteredUser(String emailAddress, String password, boolean termsAndConditions)
    {
        securityData = new SecurityData(emailAddress, password);
        this.termsAndConditions = termsAndConditions;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isTermsAndConditions()
    {
        return termsAndConditions;
    }

    public void setTermsAndConditions(boolean termsAndConditions)
    {
        this.termsAndConditions = termsAndConditions;
    }

    public void setSecurityData(SecurityData securityData)
    {
        this.securityData = securityData;
    }

    private SecurityData getSecurityData()
    {
        return securityData;
    }

    @Override
    public String toString()
    {
        return "RegisteredUser{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", termsAndConditions=" + termsAndConditions +
                ", securityData=" + securityData +
                '}';
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(securityData.getEmailAddress());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisteredUser user = (RegisteredUser) o;
        return securityData.getEmailAddress().equals(user.getSecurityData().getEmailAddress()) ;
    }
}
