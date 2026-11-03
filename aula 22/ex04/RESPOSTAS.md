# Exercício 4

> O que acontece se na linha de comando digitada via console: `java MultipleThreadExample.java N`, o valor de N for aumentando? Há um limite pragmático para N? Justifique!

## Ambiente dos testes

- **Processador:** Intel Core i7-1355U, com **10 núcleos físicos** e **12 processadores lógicos**. É uma arquitetura híbrida:
  - 2 núcleos de desempenho (*P-cores*), cada um com Hyper-Threading, totalizando 4 processadores lógicos;
  - 8 núcleos de eficiência (*E-cores*), mais lentos.
- **Java:** 25.
- **Como foi medido:** cada N foi executado com `java MultipleThreadExample N`. O tempo total foi medido no relógio, do início ao fim do processo, porque o `main` não espera as threads e não mostra esse tempo.

## Testes

| N | Números por Thread | Tempo por Thread (s) | Tempo total (s) | Speedup |
|---:|---:|---:|---:|---:|
| 1 | 1.000.000.000 | 50,20 | ≈ 50,9 | 1,0× |
| 2 | 500.000.000 | 30,43 – 30,59 | ≈ 31,4 | 1,6× |
| 3 | 333.333.333 | 17,92 – 18,26 | ≈ 19,1 | 2,7× |
| 4 | 250.000.000 | 14,08 – 14,36 | ≈ 14,6 | 3,5× |
| 6 | 166.666.666 | 11,83 – 12,06 | ≈ 12,5 | 4,1× |
| 8 | 125.000.000 | 7,78 – 7,97 | ≈ 8,2 | 6,2× |
| 10 | 100.000.000 | 6,20 – 6,61 | ≈ 6,9 | 7,4× |
| 12 | 83.333.333 | 4,26 – 6,92 | ≈ 7,2 | 7,1× |
| 16 | 62.500.000 | 4,12 – 5,91 | ≈ 6,2 | 8,2× |
| 24 | 41.666.666 | 4,66 – 5,55 | ≈ 5,9 | 8,6× |
| 48 | 20.833.333 | 1,63 – 4,64 | ≈ 6,0 | 8,5× |
| 100 | 10.000.000 | 0,53 – 2,51 | ≈ 6,2 | 8,2× |
| 1.000 | 1.000.000 | 0,05 – 6,54 | ≈ 9,9 | 5,1× |
| 10.000 | 100.000 | 0,00 – 0,41 | ≈ 11,0 | 4,6× |

*Speedup* = tempo total com N = 1 ÷ tempo total com N threads.

**Variação entre execuções:** os tempos variaram bastante ao repetir o mesmo N. N = 3 levou 19 s numa execução e 28 s em outra; N = 4 levou 20 s, 19 s e 15 s. A tabela usa as execuções mais rápidas. Num notebook, a frequência da CPU muda o tempo todo: o turbo sobe e desce conforme a temperatura e o limite de consumo de energia, e outros programas também disputam a CPU. Por isso os números servem para ver a **tendência**, não como valores exatos.

## Resposta: o que acontece quando N aumenta?

O comportamento tem três fases:

**1. De N = 1 até ≈ 10–12: o tempo cai bastante.**
Cada nova thread ocupa um núcleo que estava ocioso, e o trabalho é dividido em partes menores que rodam **em paralelo de verdade**. O tempo total caiu de cerca de 50 s para cerca de 7 s.

O ganho é menor que N (com 10 threads, 7,4× em vez de 10×) por três motivos:
- quanto mais núcleos ativos, menor a frequência de cada um;
- os *E-cores* são mais lentos que os *P-cores*;
- os núcleos disputam o cache e a memória.

**2. De N ≈ 12 até ≈ 100: o tempo para de cair (estabiliza em cerca de 6 s).**
Com mais threads do que os **12 processadores lógicos**, não existem núcleos livres para as threads extras. O SO passa a **revezar** as threads nos mesmos núcleos (execução **concorrente**, por fatias de tempo, o *quantum*), e o trabalho total continua o mesmo. Por isso:
- o tempo total não melhora;
- a diferença entre a thread mais rápida e a mais lenta aumenta (com N = 48, de 1,6 s a 4,6 s): algumas threads pegam *P-cores* ou recebem mais fatias de tempo do escalonador, outras ficam esperando na fila.

**3. De N ≈ 1.000 em diante: o tempo volta a piorar.**
Com N = 1.000 o tempo total subiu para cerca de 10 s, e com N = 10.000 para cerca de 11 s, sendo que cada thread trabalha menos de 0,5 s. O que domina agora é o **custo de gerenciar as threads**, não o cálculo:
- **Criação:** cada `new MultipleThreadExample(...).start()` pede ao SO uma thread nativa, com pilha de memória própria. Com 10.000 threads, o `main` passa boa parte do tempo só criando threads, uma de cada vez.
- **Troca de contexto:** o SO salva e restaura o estado de cada thread a cada revezamento. Com milhares de threads, essas trocas consomem tempo de CPU que não é cálculo.
- **Saída no console:** cada thread imprime 4 linhas, e o `System.out` é sincronizado (uma thread por vez). Com 10.000 threads são cerca de 40.000 linhas disputando o mesmo recurso.

## Há um limite pragmático para N?

**Sim.** O limite pragmático (útil) é aproximadamente o **número de processadores lógicos da máquina**, que neste computador é 12. Acima disso não há ganho de desempenho, e com N muito alto o programa fica **mais lento**.

Esse número pode ser obtido no próprio programa:

```java
int n = Runtime.getRuntime().availableProcessors(); // 12 nesta máquina
```

Além do limite de desempenho, existem limites em que o programa **deixa de funcionar**. Os quatro primeiros foram testados:

| N | O que acontece | Motivo |
|---|---|---|
| `0` | `ArithmeticException: / by zero` | `TOTAL_NUMEROS / threads` divide por zero (linha 23) |
| negativo (`-1`) | Imprime "Criando +-1 Thread(s)" e termina sem fazer nada | O `for (i = 0; i < -1; ...)` não executa nenhuma vez |
| texto (`abc`) | `NumberFormatException` | `Integer.valueOf("abc")` não consegue converter (linha 21) |
| `2147483648` ou mais | `NumberFormatException` | Ultrapassa o maior `int` do Java (`Integer.MAX_VALUE` = 2.147.483.647) |
| maior que 1 bilhão | Cada thread gera **0 números** | `1_000_000_000 / N` dá 0 na divisão inteira: as threads são criadas mas não fazem nada |
| muito grande, com threads vivas ao mesmo tempo | `OutOfMemoryError: unable to create native thread` (não testado) | Cada thread reserva memória para a sua pilha, e o SO limita quantas threads um processo pode ter |

Quando N não divide 1 bilhão exatamente, a divisão inteira faz alguns números se perderem. Com N = 6, por exemplo, são gerados 6 × 166.666.666 = 999.999.996 números (4 a menos). A diferença é desprezível, mas mostra que o código não distribui o resto da divisão.

## Justificativa

- Threads só aceleram um programa quando existem **núcleos livres** para executá-las ao mesmo tempo (**paralelismo**). Até o número de processadores lógicos, cada thread nova ganha um núcleo, e o tempo cai.
- Acima disso, as threads passam a **competir** pelos mesmos núcleos (**concorrência**). O SO, por meio do escalonador, alterna entre elas por fatias de tempo, e o trabalho total não diminui.
- Cada thread tem um custo: criação, memória para a pilha e trocas de contexto. Com poucas threads esse custo é desprezível; com milhares, ele passa a dominar o tempo total.
- Por isso, na prática, N deve ficar perto do número de processadores lógicos. É o que fazem os *pools* de threads do Java (`Executors.newFixedThreadPool(n)`), que reutilizam um número fixo de threads em vez de criar uma para cada tarefa.
