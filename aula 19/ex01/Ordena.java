public class Ordena {   
    public void ordenaNumerosCrescentes(int vetor[]) {   
        // SEM código -> não irá ordenar o vetor: código propositalmente vazio para o teste falhar (VERMELHO)

        //Reescreva um código rápido para que a funcionalidade seja aprovada nos testes já criados (VERDE)
        /*
        int rascunho;
        if (vetor[0] > vetor[1]) {   
            rascunho = vetor[1];
            vetor[1] = vetor[0];
            vetor[0] = rascunho;
        }
        */

        // Avance o ciclo do TDD, melhorando o código em pequenos avanços(REFATORAR), repetindo o ciclo até chegar ao código final da funcionalidade
        int iA, iB, iT;
        for (iA = 1; iA < vetor.length; iA++) {   
            for (iB = vetor.length - 1; iB >= iA; iB--) {   
                if (vetor[iB - 1] > vetor[iB]) {   
                    iT = vetor[iB - 1];
                    vetor[iB - 1] = vetor[iB];
                    vetor[iB] = iT;
                }
            }
        }
    }

    // public void ordenaNumerosDecrescentes(int vetor[]) {   
    //     // Não há código -> não irá ordenar o vetor: código propositalmente vazio para o teste falhar (VERMELHO)
    // }
}
