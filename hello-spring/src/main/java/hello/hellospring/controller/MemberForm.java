package hello.hellospring.controller;

public class MemberForm {
    private String name;
    //createMemberForm.html의 form 중 name= 속성과 매칭이 되어서 들어온다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
