package hello.core.member;

/**
 * Member 클래스는 3가지 속성
 * 1. id
 * 2. name
 * 3. grade
 */
public class Member {

    private Long id;
    private String name;
    private Grade grade;

    /* 생성자 생성 */
    /* 단축키는 command + n */
    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    /* Getter, Setter 생성 */
    /* 데이터를 가져오고 뽑기 위해서 생성 */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
