
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

    private String car_make;
    private String phone;
    private String car_number;


    public User(Long id, String firstName, String lastName, Integer age, String car_make, String phone, String car_number) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

        this.car_make = car_make;
        this.phone = phone;
        this.car_number=car_number;
    }
//
    public User(String firstName, String lastName, Integer age, String car_make, String phone, String car_number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.car_make = car_make;
        this.phone = phone;
        this.car_number=car_number;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCar_make() {
        return car_make;
    }

    public void setCar_make(String car_make) {
        this.car_make = car_make;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    @Override
    public String toString() {
        return "User " +
                 + id +":"+
                " firstName=" + firstName +
                ", lastName=" + lastName +
                ", age=" + age +
                ", car_make=" + car_make+
                ", phone=" + phone+
                ", car_number=" + car_number + '\n';
    }
}
