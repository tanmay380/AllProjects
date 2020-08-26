import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class quickselect {
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static int kelement(int[] arr, int low, int high, int k) {
        int pi = partition(arr, low, high);
        if (pi == k) {
            return arr[pi];
        } else if (pi < k) {
            return kelement(arr, pi + 1, high, k);
        } else {
            return kelement(arr, low, pi - 1, k);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());//no of times loops will run
    while (testcase-->0) {
        int size = Integer.parseInt(br.readLine());//no of elements in the array

        int[] arraycopy = new int[size];


        String[] input = br.readLine().trim().split(" ");


        for (int i = 0; i < size; i++) {
            arraycopy[i] = Integer.parseInt(input[i]);
        }
        int kelemn=Integer.parseInt(br.readLine());
        StringBuffer stringBuffer=new StringBuffer();

        stringBuffer.append(kelement(arraycopy, 0, size - 1, kelemn - 1));
        stringBuffer.append("\n");
        System.out.print(stringBuffer);
    }
        ;
    }
}

