package com.rout.hibernate.image.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Student {

    @Id
    private Long id;

    private String name;

    private String profilePicName;

    @Lob
    private byte[] profilePic;

    public Long getId() {
        return id;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public Student setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
        return this;
    }

    public String getProfilePicName() {
        return profilePicName;
    }

    public Student setProfilePicName(String profilePicName) {
        this.profilePicName = profilePicName;
        return this;
    }
}
