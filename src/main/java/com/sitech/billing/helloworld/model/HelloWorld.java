package com.sitech.billing.helloworld.model;

public class HelloWorld {
    private Integer id;
    private String name;
    private String description;
    private String developers;
    private String contributors;
    private String company;
    private String tel;
    private String eMail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevelopers() {
        return developers;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public String getContributors() {
        return contributors;
    }

    public void setContributors(String contributors) {
        this.contributors = contributors;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "HelloWorld{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", developers='" + developers + '\'' +
                ", contributors='" + contributors + '\'' +
                ", company='" + company + '\'' +
                ", tel='" + tel + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
