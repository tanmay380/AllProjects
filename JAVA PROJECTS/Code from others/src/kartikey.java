import java.util.*;

class kartikey {
    static int matrix[][];
    static int n;
    static int m;
    static boolean vis[][];
    static boolean again;

    static int score;
    static void swap(int row1, int col1, int row2, int col2) {
        int temp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = temp;
    }

    static void printMatrix() {
        System.out.print("  ");
        for (int i = 0; i < m; i++) {
            System.out.print(" A" + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("B" + (i + 1) + "  ");
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int cellNum(int row, int col) {
        return row * n + col;
    }

    static int getRandomNumber() {
        int min = 1;
        int max = 5;
        return (int) ((Math.random() * (max - min)) + min);
    }

    static void processQueue(Queue<Integer> queue) {
        Iterator<Integer> itr = queue.iterator();
        while (itr.hasNext()) {
            int cur = itr.next();
            vis[cur / n][cur % m] = true;
        }
        while (!queue.isEmpty()) {
            int remove = queue.remove();
            int row = remove / n;
            int col = remove % m;

            score += 100;

            vis[row][col] = true;

            // Check left
            if (col - 1 >= 0 && !vis[row][col - 1] && matrix[row][col - 1] == matrix[row][col]) {
                queue.add(cellNum(row, col - 1));
            }
            // Check right
            if (col + 1 < m && !vis[row][col + 1] && matrix[row][col + 1] == matrix[row][col]) {
                queue.add(cellNum(row, col + 1));
            }
            // Check top
            if (row - 1 >= 0 && !vis[row - 1][col] && matrix[row - 1][col] == matrix[row][col]) {
                queue.add(cellNum(row - 1, col));
            }
            // Check bottom
            if (row + 1 < n && !vis[row + 1][col] && matrix[row + 1][col] == matrix[row][col]) {
                queue.add(cellNum(row + 1, col));
            }
        }
    }

    static void solve() {
        do {

            again = false;
            vis = new boolean[n][m];
            for (int row = 0; row < n; row++) {
                Queue<Integer> queue = new LinkedList<>();

                int prevColor = -1;

                for (int col = 0; col < m; col++) {
                    int curColor = matrix[row][col];

                    if (col != 0 && curColor != prevColor) {
                        if (queue.size() >= 3) {
                            // Process the queue
                            processQueue(queue);
                        }
                        queue = new LinkedList<>();
                    }
                    queue.add(cellNum(row, col));
                    // Check top
                    if (row - 1 >= 0 && !vis[row - 1][col] && matrix[row - 1][col] == matrix[row][col]) {
                        queue.add(cellNum(row - 1, col));
                        if (row - 2 >= 0 && !vis[row - 2][col] && matrix[row - 2][col] == matrix[row][col]) {
                            queue.add(cellNum(row - 2, col));
                        }
                    }
                    // Check bottom
                    if (row + 1 < n && !vis[row + 1][col] && matrix[row + 1][col] == matrix[row][col]) {
                        queue.add(cellNum(row + 1, col));
                        if (row + 2 < n && !vis[row + 2][col] && matrix[row + 2][col] == matrix[row][col]) {
                            queue.add(cellNum(row + 2, col));
                        }
                    }
                    prevColor = curColor;
                }
                if (queue.size() >= 3) {
                    processQueue(queue);
                }
                queue = new LinkedList<>();
            }
            for (int col = 0; col < m; col++) {
                Queue<Integer> queue = new LinkedList<>();
                for (int row = n - 1; row >= 0; row--) {
                    if (!vis[row][col]) {
                        queue.add(matrix[row][col]);
                    }
                }
                int size = queue.size();
                for (int row = n - 1; row >= 0 && !queue.isEmpty(); row--) {
                    matrix[row][col] = queue.remove();
                }
                for (int row = n - size - 1; row >= 0; row--) {
                    matrix[row][col] = getRandomNumber();
                }
            }
            first: for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    if (vis[row][col]) {
                        again = true;
                        break first;
                    }
                }
            }
        } while (again);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        boolean run = true;
        score = 0;
        do {
            System.out.println("\nInitial Matrix");
            printMatrix();

            solve();

            System.out.println("Final matrix after checking initially");
            printMatrix();
            System.out.println("\nYour current score is " + score + "\n");
            System.out.println("Input 1 to continue or 0 to stop");
            int res = sc.nextInt();
            if (res == 0) {
                run = false;
                break;
            }
            run = true;
            System.out.println("Input two positions to swap to increase your score\nExample A1B1 A2B2 in one line");

            String instruction = sc.nextLine();
            instruction.trim();
            boolean valid = true;
            while (valid) {
                String pos1 = instruction.split(" ")[0];
                String pos2 = instruction.split(" ")[1];

                int row1 = Character.getNumericValue(pos1.charAt(1)) - 1;
                int col1 = Character.getNumericValue(pos1.charAt(3)) - 1;

                int row2 = Character.getNumericValue(pos2.charAt(1)) - 1;
                int col2 = Character.getNumericValue(pos2.charAt(3)) - 1;

                if ((row1 == row2 && col1 == col2 + 1) || (row1 == row2 && col1 == col2 - 1)
                        || (col1 == col2 && row1 == row2 - 1) || (col1 == col2 && row1 == row2 + 1)) {
                    valid = false;
                    swap(row1, col1, row2, col2);
                } else {
                    System.out.println("You have not input advacent cells try again");
                    valid = true;
                }
            }
        } while (run);
    }
}