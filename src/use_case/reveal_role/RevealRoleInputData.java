package use_case.reveal_role;

public class RevealRoleInputData {

    final private String userName;

    public RevealRoleInputData(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

}
