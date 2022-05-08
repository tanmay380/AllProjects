import java.util.ArrayList;

public class intersectionarray {
    public static void main(String[] args) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();

        arr1.add(1);
        arr1.add(2);
        arr1.add(2);
        arr1.add(2);
        arr1.add(3);
        arr1.add(4);

        arr2.add(2);
        arr2.add(2);
        arr2.add(3);
        arr2.add(3);

        int i = 0, j = 0;
        ArrayList<Integer> ans = new ArrayList<>();

        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i) == arr2.get(j)) {
                ans.add(arr1.get(i));
                i++;
                j++;
            } else if (arr1.get(i) < arr2.get(j)) {
                i++;
            } else {
                j++;
            }
        }

        System.out.println(ans);

    }
}
