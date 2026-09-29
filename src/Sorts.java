public class Sorts<T extends Comparable<T>> {

    public void insertionSort(T[] vetor) {

        for (int i = 1; i < vetor.length; i++) {

            T eleito = vetor[i];

            int j = i - 1;

            while (j >= 0 && eleito.compareTo(vetor[j]) < 0) {

                vetor[j + 1] = vetor[j];

                j--;
            }

            vetor[j + 1] = eleito;
        }
    }
}