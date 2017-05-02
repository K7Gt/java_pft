package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by Golem on 01.05.2017.
 */

@Entity
@Table(name = "mantis_user_table")
public class Users {
    @Id
    @Column(name = "id")
    private int id = 0;

    @Column(name = "username")
//    @Type(type = "text")
    private String name;

    @Column(name = "email")
//    @Type(type = "text")
    private String eMail;

    @Transient
    private String password;

    public String getName() {
        return name;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public Users withId(int id){
        this.id = id;
        return this;
    }

    public Users withName(String name){
        this.name = name;
        return this;
    }

    public Users withEmail(String eMail){
        this.eMail = eMail;
        return this;
    }

    public Users withPassword(String password){
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (id != users.id) return false;
        if (name != null ? !name.equals(users.name) : users.name != null) return false;
        return eMail != null ? eMail.equals(users.eMail) : users.eMail == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        return result;
    }
}
