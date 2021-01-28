package co.za.entelect.relationship.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserProfile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private boolean termsAndConditions;

    @Column(unique = true, nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String password;

    public UserProfile()
    {
    }

    public UserProfile(String password, String firstname, String lastname, String emailAddress,
                       LocalDate dateOfBirth, boolean termsAndConditions)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.termsAndConditions = termsAndConditions;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Long getId()
    {
        return id;
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


    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getEmailAddress());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile user = (UserProfile) o;
        return getEmailAddress().equals(user.getEmailAddress());
    }

    @Override
    public String toString()
    {
        return "UserProfile{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", termsAndConditions=" + termsAndConditions +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
