package co.za.entelect.relationship.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class SecurityData implements Serializable
{
    @Id
    @GeneratedValue
    private Long id;
    private String emailAddress;
    private String password;

    @OneToOne(mappedBy = "securityData")
    private RegisteredUser registeredUser;


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

    public void setRegisteredUser(RegisteredUser registeredUser)
    {
        this.registeredUser = registeredUser;
    }

    public SecurityData()
    {
    }

    public SecurityData(String emailAddress, String password)
    {
        this.emailAddress = emailAddress;
        this.password = password;
    }



    @Override
    public String toString()
    {
        return "SecurityData{" +
                "emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}