# Exercício 3 – Criando Threads em Java

## Exemplo 6 – `SimpleThreadExample` (1 Thread)

**Saída:**

```
Missao: Gerar um bilhao de numeros aleatorios!
- Criando 1 Thread(s) para isso!
- Iniciando Thread Principal...
- Encerrando o processamento...
Missao cumprida em 47.42 segundos!
```

**Análise:**

- Apesar da mensagem "Criando 1 Thread(s)", nenhuma thread nova é criada. O `main` chama `run()` diretamente, como um método comum, e todo o trabalho é feito pela **Thread Principal** (`main`), que a JVM cria ao iniciar o programa.
- O processamento é linear: um único núcleo da CPU executa os 1 bilhão de sorteios com `Math.pow()`, um depois do outro, enquanto os outros núcleos ficam ociosos.
- O tempo medido (47,42 s) é o tempo total do programa, porque o `main` só imprime "Missao cumprida" depois que o laço termina.
- Esse valor é a referência para comparar com as versões com várias threads.

## Exemplo 7 – `MultipleThreadExample` (pela IDE)

**Saída:**

```
-> Thread iniciada (main)...
-> Criando +1 Thread(s) para isso:
----> Thread criada (Thread-0)!
-> Thread finalizada (main) em 0.00s!
----> Iniciando Thread (Thread-0)...
----> Encerrando Thread (Thread-0)!
----> Missao da Thread (Thread-0) cumprida em 47.45s!
```

**Análise:**

- Aqui uma thread nova é criada de fato. O `main` chama **`start()`**, que pede à JVM e ao SO uma nova thread (`Thread-0`) e executa o `run()` nela. Ficam duas threads: `main` e `Thread-0`.
- A mensagem `Thread finalizada (main) em 0.00s` aparece **antes** da `Thread-0` começar a trabalhar. O `main` só dispara a thread e segue em frente, sem esperar por ela (não há `join()`). Por isso o tempo do `main` (0,00 s) não mede o processamento; o que vale é o tempo da `Thread-0`.
- O programa continua rodando mesmo depois que o `main` termina, porque a JVM só encerra quando todas as threads (não-daemon) acabam.
- O tempo da `Thread-0` (47,45 s) é praticamente igual ao do Exemplo 6 (47,42 s). Com uma thread só, o trabalho continua sequencial, num único núcleo: criar a thread muda **quem** executa, não a velocidade.

## Como o número de threads é definido

A quantidade de threads é lida na linha 21 do `main`:

```java
final int threads = args.length == 0 ? 1 : Integer.valueOf(args[0]);
```

- **Com argumento** (`java MultipleThreadExample.java 2`): `args[0]` vale `"2"` e `Integer.valueOf` converte o texto para o número 2.
- **Sem argumento** (pelo botão "Run" da IDE ou `java MultipleThreadExample.java`): `args.length == 0` e o programa usa o valor padrão `1`.

Para testar os Exemplos 9 a 11 **pela IDE**, que não passa argumento, troque o valor padrão `1` pelo número de threads desejado (`X`), como mostra a linha comentada 22:

```java
//final int threads = args.length == 0 ? X : Integer.valueOf(args[0]);
```

O restante do código se ajusta sozinho: o laço `for` cria `threads` objetos e cada um gera `TOTAL_NUMEROS / threads` números.

## Exemplo 8 – `java MultipleThreadExample.java`

**Saída:**

```
Missao: Gerar um bilhao de numeros aleatorios usando Threads!
-> Thread iniciada (main)...
-> Criando +1 Thread(s) para isso:
----> Thread criada (Thread-0)!
----> Iniciando Thread (Thread-0)...
-> Thread finalizada (main) em 0.00s!
----> Encerrando Thread (Thread-0)!
----> Missao da Thread (Thread-0) cumprida em 50.53s!
```

**Análise:**

- **Linha usada** (sem alteração): sem argumento, vale o valor padrão `1`.
  ```java
  final int threads = args.length == 0 ? 1 : Integer.valueOf(args[0]);
  ```
- No console, o comando `java MultipleThreadExample.java` compila o arquivo em memória e já o executa, sem precisar do `javac` (modo _source-file_, disponível desde o Java 11).
- Sem argumento, `args.length == 0` e o programa usa **N = 1**. O resultado é o mesmo do Exemplo 7: uma única `Thread-0` fazendo todo o trabalho, em cerca de 50 s.
- Aqui, `Iniciando Thread (Thread-0)` apareceu **antes** de `Thread finalizada (main)`; no Exemplo 7 foi o contrário. O código é o mesmo, mas a ordem muda. Depois do `start()`, as duas threads correm de forma independente, e quem decide qual imprime primeiro é o **escalonador (scheduler) do SO**.
- O tempo (50,53 s) ficou um pouco acima do medido na IDE (47,45 s). Essa diferença é variação normal entre execuções: depende da carga do computador naquele momento, da temperatura e da frequência da CPU (turbo).

## Exemplo 9 – `java MultipleThreadExample.java 1`

**Saída:**

```
Missao: Gerar um bilhao de numeros aleatorios usando Threads!
-> Thread iniciada (main)...
-> Criando +1 Thread(s) para isso:
----> Thread criada (Thread-0)!
----> Iniciando Thread (Thread-0)...
-> Thread finalizada (main) em 0.00s!
----> Encerrando Thread (Thread-0)!
----> Missao da Thread (Thread-0) cumprida em 50.20s!
```

**Análise:**

- **Linha usada** (sem alteração): o valor padrão continua `1`. Pelo console, o `1` vem do argumento.
  ```java
  final int threads = args.length == 0 ? 1 : Integer.valueOf(args[0]);
  ```
- O argumento `1` chega em `args[0]` e é convertido com `Integer.valueOf(args[0])`, então N = 1.
- É exatamente o mesmo caso do Exemplo 8. O tempo (50,20 s contra 50,53 s) confirma que passar `1` ou não passar argumento dá no mesmo.
- Com uma thread só não existe paralelismo: um núcleo trabalha e os outros ficam ociosos.

## Exemplo 10 – `java MultipleThreadExample.java 2`

**Saída:**

```
Missao: Gerar um bilhao de numeros aleatorios usando Threads!
-> Thread iniciada (main)...
-> Criando +2 Thread(s) para isso:
----> Thread criada (Thread-0)!
----> Thread criada (Thread-1)!
----> Iniciando Thread (Thread-0)...
-> Thread finalizada (main) em 0.00s!
----> Iniciando Thread (Thread-1)...
----> Encerrando Thread (Thread-1)!
----> Missao da Thread (Thread-1) cumprida em 30.43s!

----> Encerrando Thread (Thread-0)!
----> Missao da Thread (Thread-0) cumprida em 30.59s!
```

**Análise:**

- **Linha alterada** para testar pela IDE (sem argumento): o valor padrão passa de `1` para `2`.
  ```java
  final int threads = args.length == 0 ? 2 : Integer.valueOf(args[0]);
  ```
  Pelo console não é preciso alterar nada: basta passar `2` como argumento.
- São criadas duas threads (`Thread-0` e `Thread-1`), cada uma gerando 500 milhões de números (1 bilhão / 2).
- As duas rodam **em paralelo**, em núcleos diferentes da CPU. Por isso cada uma leva cerca de 30 s, e o programa inteiro também leva cerca de 30 s, não 60 s. O tempo total é o da thread mais lenta, não a soma dos tempos.
- A `Thread-1` foi iniciada depois e mesmo assim terminou **antes** da `Thread-0`. A ordem de término não é garantida e depende do escalonador.
- O tempo caiu de cerca de 50 s para cerca de 30,6 s, um ganho (_speedup_) de **1,64×**. O ganho ideal seria 2×. Ele não é perfeito por três motivos:
  - Quando mais núcleos trabalham ao mesmo tempo, a CPU reduz a frequência de cada um (o turbo diminui para controlar a temperatura e o consumo).
  - Os núcleos disputam recursos compartilhados, como o cache e o barramento de memória.
  - Criar e escalonar as threads tem um custo.

## Exemplo 11 – `java MultipleThreadExample.java 3`

**Saída:**

```
Missao: Gerar um bilhao de numeros aleatorios usando Threads!
-> Thread iniciada (main)...
-> Criando +3 Thread(s) para isso:
----> Thread criada (Thread-0)!
----> Thread criada (Thread-1)!
----> Thread criada (Thread-2)!
----> Iniciando Thread (Thread-0)...
----> Iniciando Thread (Thread-1)...
----> Iniciando Thread (Thread-2)...
-> Thread finalizada (main) em 0.00s!
----> Encerrando Thread (Thread-0)!
----> Missao da Thread (Thread-0) cumprida em 17.92s!

----> Encerrando Thread (Thread-1)!
----> Missao da Thread (Thread-1) cumprida em 18.14s!

----> Encerrando Thread (Thread-2)!
----> Missao da Thread (Thread-2) cumprida em 18.26s!
```

**Análise:**

- **Linha alterada** para testar pela IDE (sem argumento): o valor padrão passa de `1` para `3`.
  ```java
  final int threads = args.length == 0 ? 3 : Integer.valueOf(args[0]);
  ```
- São três threads, cada uma gerando 333.333.333 números. Como `1_000_000_000 / 3` é divisão inteira e 3 × 333.333.333 = 999.999.999, o programa gera **um número a menos** que 1 bilhão (diferença desprezível).
- As três rodam em paralelo e terminam em cerca de 18 s. O tempo total caiu para cerca de 18,3 s, um _speedup_ de **2,75×** em relação a uma thread (o ideal seria 3×).
- Nesta execução as threads terminaram na mesma ordem em que foram criadas, mas isso foi coincidência: no Exemplo 10, a ordem foi outra.

## Comparação dos tempos

Execuções feitas num Intel Core i7-1355U (10 núcleos, 12 threads lógicas).

| N (threads)             | Números por Thread | Tempo por Thread (s)  | Tempo total (s) | Speedup |
| ----------------------- | ------------------ | --------------------- | --------------- | ------- |
| 1 (SimpleThreadExample) | 1.000.000.000      | 47,42                 | 47,42           | —       |
| 1 (IDE, Exemplo 7)      | 1.000.000.000      | 47,45                 | 47,45           | —       |
| 1 (console, Exemplo 8)  | 1.000.000.000      | 50,53                 | 50,53           | 1,00×   |
| 1 (console, Exemplo 9)  | 1.000.000.000      | 50,20                 | 50,20           | 1,00×   |
| 2 (Exemplo 10)          | 500.000.000        | 30,43 / 30,59         | 30,59           | 1,64×   |
| 3 (Exemplo 11)          | 333.333.333        | 17,92 / 18,14 / 18,26 | 18,26           | 2,75×   |

O _speedup_ foi calculado em relação às execuções com N = 1 no console, que é onde rodaram os Exemplos 10 e 11.

## Conclusão

- Executar `run()` diretamente (Exemplo 6) não cria thread nenhuma. Só `start()` cria uma nova linha de execução.
- Dividir o trabalho em N threads reduz o tempo total, porque núcleos diferentes processam partes do trabalho **ao mesmo tempo** (execução paralela).
- O ganho é menor que N (1,64× com 2 threads e 2,75× com 3) por causa da redução de frequência da CPU com vários núcleos ativos, da disputa por recursos compartilhados e do custo de criar as threads.
- O `main` não espera as threads (não há `join()`), por isso o tempo que ele imprime (0,00 s) não representa o processamento. Para medir o tempo total no próprio programa, seria preciso chamar `join()` em cada thread antes de calcular o tempo.
- A ordem em que as threads começam e terminam não é determinística: quem decide é o escalonador do SO.
