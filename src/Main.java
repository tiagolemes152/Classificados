import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String caminhoCSV = "candidatos.csv";

        List<Candidato> candidatos = carregarCSV(caminhoCSV);

        Candidato[] vetor = candidatos.toArray(new Candidato[0]);

        System.out.println("Ordenação Antiga");

        mostrarCandidatos(vetor);

        Sorts<Candidato> sorts = new Sorts<>();

        sorts.insertionSort(vetor);

        System.out.println();
        System.out.println(" Atual ");

        mostrarCandidatos(vetor);
    }

    private static List<Candidato> carregarCSV(String caminho) {

        List<Candidato> candidatos = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {

            String linha;
            boolean primeiraLinha = true;

            while ((linha = leitor.readLine()) != null) {

                if (linha.trim().isEmpty()) {
                    continue;
                }

                String delimitador;

                if (linha.contains(";")) {
                    delimitador = ";";
                } else {
                    delimitador = ",";
                }

                String[] dados = linha.split(delimitador);

                if (dados.length < 2) {
                    continue;
                }

                String nome = dados[0].trim();
                String notaTexto = dados[1].trim();

                // Ignora cabeçalho
                if (primeiraLinha && !ehNumero(notaTexto)) {
                    primeiraLinha = false;
                    continue;
                }

                primeiraLinha = false;

                try {

                    float nota = Float.parseFloat(
                            notaTexto.replace(",", ".")
                    );

                    candidatos.add(new Candidato(nome, nota));

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Linha ignorada: " + linha
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Erro ao ler o arquivo CSV: " + e.getMessage()
            );
        }

        return candidatos;
    }

    private static boolean ehNumero(String valor) {

        try {
            Float.parseFloat(valor.replace(",", "."));
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void mostrarCandidatos(Candidato[] candidatos) {

        for (int i = 0; i < candidatos.length; i++) {

            System.out.println(
                    (i + 1) + ". " + candidatos[i]
            );
        }
    }
}