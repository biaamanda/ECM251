package br.maua;

// Exercício 1: ordenação decrescente (enunciado em OrdenaTest.java)

public class Ordena {

    // Bubble sort do slide 28, com a comparação invertida:
    // troca quando o anterior é MENOR que o atual
    public void ordenaNumerosDecrescentes(int iVet[]) {
        int iA, iB, iT;
        for (iA = 1; iA < iVet.length; iA++) {
            for (iB = iVet.length - 1; iB >= iA; iB--) {
                if (iVet[iB - 1] < iVet[iB]) {
                    iT = iVet[iB - 1];
                    iVet[iB - 1] = iVet[iB];
                    iVet[iB] = iT;
                }
            }
        }
    }
}
