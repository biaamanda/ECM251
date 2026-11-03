# Threads em Java (Java Threads)

>  Pesquisar, entender, estudar, resumir, exemplificar e dar aplicações, em PDF, o conceito de Threads em Java, também conhecida por Java Threads.

## O que é

Uma thread é uma linha de execução dentro de um programa. Todo programa Java começa com uma thread (a `main`), e pode criar outras para fazer várias tarefas ao mesmo tempo. As threads de um mesmo programa compartilham a memória (objetos e variáveis).

## Como criar

**1. Estendendo `Thread`:**

```java
class MinhaThread extends Thread {
    public void run() {
        System.out.println("Executando em outra thread");
    }
}

new MinhaThread().start();
```

**2. Implementando `Runnable`** (preferível, pois a classe continua livre para herdar de outra):

```java
class Tarefa implements Runnable {
    public void run() {
        System.out.println("Executando a tarefa");
    }
}

new Thread(new Tarefa()).start();
```

**3. Com lambda** (`Runnable` é uma interface funcional):

```java
new Thread(() -> System.out.println("Executando")).start();
```

Atenção: é `start()` que cria a nova thread. Chamar `run()` diretamente executa o código na thread atual.

## Exemplo

```java
public class Contadores {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> contar("A"));
        Thread b = new Thread(() -> contar("B"));
        a.start();
        b.start();
        a.join(); // espera a terminar
        b.join();
        System.out.println("Fim");
    }

    static void contar(String nome) {
        for (int i = 1; i <= 3; i++) {
            System.out.println(nome + " " + i);
            try { Thread.sleep(100); } catch (InterruptedException e) { }
        }
    }
}
```

A saída intercala `A` e `B`, porque as duas threads rodam ao mesmo tempo.

## Métodos úteis

| Método | Para que serve |
|---|---|
| `start()` | inicia a thread |
| `sleep(ms)` | pausa a thread atual |
| `join()` | espera outra thread terminar |
| `setDaemon(true)` | a thread não impede o programa de terminar |
| `interrupt()` | pede para a thread parar |

## Cuidados

Quando duas threads alteram o mesmo dado ao mesmo tempo, o resultado pode sair errado (condição de corrida). A palavra-chave `synchronized` faz com que apenas uma thread por vez execute aquele trecho:

```java
synchronized void incrementa() {
    contador++;
}
```

Em interfaces Swing, só a thread da interface (EDT) deve alterar componentes. Outras threads usam `SwingUtilities.invokeLater(...)`.

## Aplicações

- **Servidores**: uma thread por cliente, para atender vários ao mesmo tempo (o desafio desta aula).
- **Interfaces gráficas**: tarefas demoradas (rede, arquivos) rodam em outra thread para a janela não travar (o cliente dos exercícios 2 e 3 lê as respostas do servidor numa thread separada).
- Downloads, jogos, processamento paralelo de dados, tarefas agendadas.
