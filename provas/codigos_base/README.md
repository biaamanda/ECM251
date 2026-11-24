# Códigos base — chat Cliente-Servidor (P4 ECM251)

Base comentada para adaptar na prova. Cada método tem um comentário dizendo **o que faz** e **quando manter**.
Esta versão já atende a **P4 2025** (a mais completa); para 2023/2024, é só **apagar** as partes marcadas.

| Arquivo | O que é |
|---|---|
| `SocketCliente.java` | "Embrulho" de UMA conexão (socket + entrada + saída). Usado pelos dois lados. **Não muda nunca.** |
| `Servidor.java` | Console. Aceita até 2 clientes, 1 thread por cliente, repassa as mensagens. |
| `Cliente.java` | Janela Swing + thread que fica ouvindo o servidor. |

Para gerar o .jar: veja o [README da pasta provas](../README.md) (Passos 1–8) e, se o `jar` não for reconhecido, a ferramenta [`../ferramentas/CriaJar.java`](../ferramentas/CriaJar.java).

---

## Etiquetas nos comentários

| Etiqueta | Significado |
|---|---|
| `[SEMPRE]` | manter em qualquer prova de chat |
| `[2024+]` | só se a prova pedir aviso de **"aguardando o segundo cliente" / "comunicação ativa"** |
| `[2025]` | só se a prova pedir **Cliente #1/#2** e que **o outro feche quando alguém sair** |

## Palavras de controle (servidor → cliente)

O texto tem que ser **idêntico** nos dois arquivos.

| Palavra | Quando o servidor manda | O cliente faz | Etiqueta |
|---|---|---|---|
| `SERVIDOR_CHEIO` | entrou um 3º cliente | avisa e fecha | `[SEMPRE]` |
| `AGUARDANDO` | só há 1 cliente | status "aguardando o segundo cliente" | `[2024+]` |
| `CONECTADO` | entrou o 2º (vai para os dois) | status "comunicação ativa" | `[2024+]` |
| `ID:1` / `ID:2` | logo que o cliente entra | põe "Cliente #n" no título e nos avisos | `[2025]` |
| `DESCONECTAR` | o outro cliente saiu | avisa e fecha | `[2025]` |
| *(null em `getMessage()`)* | o servidor caiu | avisa e fecha | `[2025]` |

---

## O que apagar para cada prova

### P4 2025 (9 casos) — usar como está
Nada a apagar.

### P4 2024 (8 casos) — apagar as partes `[2025]`
| Arquivo | Apagar |
|---|---|
| `Servidor.aguardarConexoes` | a linha `cliente.sendMsg("ID:" + numero);` |
| `Servidor.removerCliente` | o `for` com `DESCONECTAR` |
| `Cliente` (atributos) | `numero` e `saindo` (ou deixar, não atrapalha) |
| `Cliente.run` | o `if` do `ID:`, o `if` do `DESCONECTAR`, e trocar o final por `status("Conexão encerrada.");` |
| `Cliente.run` (textos) | tirar `"Cliente #" + numero` dos avisos de `AGUARDANDO` e `CONECTADO` |
| `Cliente` | o método `encerrar` e a linha `saindo = true;` do `sair()` |

### P4 2023 (7 casos) — apagar também as partes `[2024+]`
Tudo da 2024, mais:
| Arquivo | Apagar |
|---|---|
| `Servidor.aguardarConexoes` | o `if/else` com `AGUARDANDO` / `CONECTADO` (o `println` de "conectado" pode ficar) |
| `Cliente.run` | os `if` de `AGUARDANDO` e `CONECTADO` |

> Na dúvida, **deixar não atrapalha**: um aviso a mais na área de status não quebra nenhum caso de teste. O que **não pode** é apagar algo `[SEMPRE]`.

---

## Mapa: caso de teste → onde está no código

| Caso de teste (enunciado) | Onde |
|---|---|
| Cliente sem servidor → não fica aberto | `Cliente.conectar` (catch) |
| Servidor sobe, aguarda até 2 clientes | `Servidor.iniciar` |
| 2º servidor → não consegue executar | `Servidor.main` (catch) |
| Cliente 1 conectado / aguardando o segundo | `Servidor.aguardarConexoes` (`AGUARDANDO`) + `Cliente.run` |
| Cliente 2 → comunicação com o outro ativa | `Servidor.aguardarConexoes` (`CONECTADO`) + `Cliente.run` |
| 3º cliente → não fica aberto | `Servidor.recusarCliente` + `Cliente.run` (`SERVIDOR_CHEIO`) |
| Mensagem 1 ↔ 2 sem eco na origem | `Servidor.repassarMensagem` (pula o remetente) |
| Console do servidor com status e mensagens + remetente | `println` em `SocketCliente`, `aguardarConexoes`, `atenderCliente`, `removerCliente` |
| Um sai → o outro fecha / servidor cai → clientes fecham | `Servidor.removerCliente` (`DESCONECTAR`) + `Cliente.run` + `Cliente.encerrar` |

---

## Se a prova pedir algo diferente

| Pedido | O que mudar |
|---|---|
| Mais de 2 clientes (ex.: 3) | `MAX_CLIENTES` no Servidor; a mensagem do `SERVIDOR_CHEIO` no Cliente; o `else` do `CONECTADO` passa a ser `if (numero == MAX_CLIENTES)` |
| Outra porta | `PORT` no Servidor **e** no Cliente |
| Servidor só **ecoa** para o próprio cliente (Aula 21) | em `atenderCliente`, troque `repassarMensagem(...)` por `cliente.sendMsg(msg);` |
| Sem eco **nem no console do servidor** | apague o `println("<- Cliente ...")` de `atenderCliente` (confirme com o professor: conflita com "mostrar as mensagens no console") |
| Cliente em **console** em vez de janela | base do `Client` da Aula 22 (`Scanner(System.in)` + loop de envio) e uma `Thread` com o mesmo `while` do `run()` daqui |
| Servidor encerra quando o último cliente sai | em `removerCliente`, `if (clients.isEmpty()) System.exit(0);` |

---

## Testado
Compilado com `javac --release 8` e testado com clientes simulados (sem janela): `ID:1`+`AGUARDANDO`, `ID:2`+`CONECTADO`, `SERVIDOR_CHEIO`, mensagens sem eco, `DESCONECTAR` quando um sai, vaga liberada e `null` quando o servidor cai. **As janelas precisam ser testadas à mão.**
