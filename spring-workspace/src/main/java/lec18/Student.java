package lec18;

public class Student {
    private String name;
    private int score;

    public Student(String n, int s) {
        this.name = n ;
        this.score = s ;
    }

    public void getInfo() {
        System.out.println("--getInfo() method activate--");
        System.out.println("Name  : " + name);
        System.out.println("score : " + score);
    }
    // getter & setter
    // getter & setter를 쓰는 이유는 안전장치를 삽입하는 형태일 때 사용한다.
    // 예를 들어 score가 수정하고자 하는 점수가 50점보다 크면 수정하고, 그렇지 않으면 <조건>
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if(score > 50) {
            this.score = score;
        } else {
            System.out.println("You can't modify");
        }
    }
}

