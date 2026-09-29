public class Candidato implements Comparable<Candidato> {

    private String nome;
    private float nota;

    public Candidato(String nome, float nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public float getNota() {
        return nota;
    }

    @Override
    public int compareTo(Candidato outro) {


        int comparacaoNota = Float.compare(outro.nota, this.nota);

        if (comparacaoNota != 0) {
            return comparacaoNota;
        }


        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public String toString() {
        return nome + " - " + String.format("%.1f", nota);
    }
}