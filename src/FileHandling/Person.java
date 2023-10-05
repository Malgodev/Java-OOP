package FileHandling;

public abstract class Person {
    private Name name;
    private Address address;
    private String phoneNum, email;

    public void setName(String firstName, String midName, String lastName) {
        name.setFirstName(firstName);
        name.setMidName(midName);
        name.setLastName(lastName);
    }

    public void setAddress(String homeNo, String street, String city) {
        address.setHomeNo(homeNo);
        address.setStreet(street);
        address.setCity(city);
    }

    public String getName() {
        return String.valueOf(name);
    }

    public String getAddress() {
        return String.valueOf(address);
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }
}