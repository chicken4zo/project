package kr.or.bit.dto;

public class Member {
    private String id;
    private String password;
    private String address;
    private int birth;
    private String name;

    public Member() {
    }

    public Member(String id, String password, String name, String address, int birth) {
        this.id = id;
        this.password = password;
        this.address = address;
        this.birth = birth;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", birth=" + birth +
                ", name='" + name + '\'' +
                '}';
    }
}
