package ictgradschool.project.utilities;

public class RandomPassword {
    public static String generateRandomPassword(){
        String pw = "";

        String[] allAvailableChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".split("");

        for(int i = 0; i < 12; i++){
            int random = (int)(Math.random()*allAvailableChar.length);
            pw += allAvailableChar[random];
        }

        return pw;
    }
}
