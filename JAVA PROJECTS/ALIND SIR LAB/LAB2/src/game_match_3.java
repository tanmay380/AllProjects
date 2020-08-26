import java.util.Random;

public class game_match_3 {
    public static int[][] mainarray = new int[9][5];

    public static void main(String[] args) {
        int row = 9, column = 5;
        int integer = 0;
        boolean same3 = true;

        Random random = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column ; j++) {
                mainarray[i][j] = random.nextInt(6);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column ; j++) {
                System.out.print(mainarray[i][j]+" ");
            }
            System.out.println();
        }
        while (same3) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column -2; j++) {
                    integer = mainarray[i][j];
                    if (integer == mainarray[i][j + 1] && integer == mainarray[i][j + 2]) {
                        if (i != 0) {
                            mainarray[i][j] = mainarray[i - 1][j];
                            mainarray[i][j + 1] = mainarray[i - 1][j + 1];
                            mainarray[i][j + 2] = mainarray[i - 1][j + 2];

                            for (int r=i;r>0;r--){

                                mainarray[r][j] = mainarray[r - 1][j];
                                mainarray[r][j + 1] = mainarray[r - 1][j + 1];
                                mainarray[r][j + 2] = mainarray[r - 1][j + 2];
                            }
                        }
                        mainarray[0][j] = random.nextInt(6);
                        mainarray[0][j + 1] = random.nextInt(6);
                        mainarray[0][j + 2] = random.nextInt(6);
//                        System.out.println("row int");
                        same3 = true;

                    } /*else if (i!=row-1) {
                        if (integer == mainarray[i][j + 1] && integer == mainarray[i + 1][j]) {
                            if (i != 0 && i!=1) {
                                mainarray[i][j] = mainarray[i - 2][j];
                                mainarray[i][j + 1] = mainarray[i - 1][j + 1];
                                mainarray[i+1][j] = mainarray[i - 1][j + 2];
                            }else if(i==0){
                                mainarray[0][j] = random.nextInt(6);
                                mainarray[0][j + 1] = random.nextInt(6);
                                mainarray[1][j] = random.nextInt(6);
                            }else{//i=1
                                mainarray[i+1][j]=mainarray[i-1][j];
                                mainarray[i][j+1]=mainarray[i-1][j+1];
                                mainarray[i][j]=random.nextInt(6);
                            }

                            mainarray[1][j] = mainarray[0][j];
                            mainarray[0][j] = random.nextInt(6);
                            mainarray[0][j + 1] = random.nextInt(6);
                            System.out.println("row adjacent int");
                            same3 = true;
                        }

                    } */ //if you want adjacent checking too.. uncomment this like ((0,0),(0,1),(1,0))
                    else
                        same3 = false;

                }
            }

            for (int i = 0; i < row - 2; i++) {
                for (int j = 0; j < column; j++) {
                    integer = mainarray[i][j];
                    if (integer == mainarray[i + 1][j] && integer == mainarray[i + 2][j]) {
                        if (i == 0) {
                            mainarray[i][j] = random.nextInt(6);
                            mainarray[i + 1][j] = random.nextInt(6);
                            mainarray[i + 2][j] = random.nextInt(6);
                        } else if (i == 1) {
                            mainarray[i][j] = random.nextInt(6);
                            mainarray[i + 1][j] = random.nextInt(6);
                            mainarray[i + 2][j] = mainarray[i - 1][j];
                        } else if (i == 2) {
                            mainarray[i][j] = random.nextInt(6);
                            mainarray[i + 1][j] = mainarray[i - 2][j];
                            mainarray[i + 2][j] = mainarray[i - 1][j];
                        } else {
                            mainarray[i][j] = mainarray[i - 3][j];
                            mainarray[i + 1][j] = mainarray[i - 2][j];
                            mainarray[i + 2][j] = mainarray[i - 1][j];
                            for (int r = i; r >= 3; r--) {
                                mainarray[r][j] = mainarray[r - 3][j];
                            }
                        }
                            mainarray[0][j] = random.nextInt(6);
                            mainarray[1][j] = random.nextInt(6);
                            mainarray[2][j] = random.nextInt(6);

//                        System.out.println("coloumn int");
                        same3 = true;

                    } else
                        same3 = false;
                }
            }


        }
        System.out.println("final showing ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(mainarray[i][j] + " ");
            }
            System.out.println();
        }
    }
}

