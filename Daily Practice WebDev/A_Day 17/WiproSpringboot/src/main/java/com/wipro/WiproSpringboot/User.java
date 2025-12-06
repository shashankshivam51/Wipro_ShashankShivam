package com.wipro.WiproSpringboot;

public class User {

    private String name;
    private String password;
    private String eid;
    private int salary;

    public User() {}

    public User(String name, String password, String eid, int salary) {
        this.name = name;
        this.password = password;
        this.eid = eid;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", password=" + password 
               + ", eid=" + eid + ", salary=" + salary + "]";
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEid() { return eid; }
    public void setEid(String eid) { this.eid = eid; }

    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
}
