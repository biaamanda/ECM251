// Classe "Checksum.java" -- reaproveitada da aula 18 (Ex01 e Ex03).
//
// calcularChecksum(): Soma e Complemento de 2 (o que a P3 2025 pediu).
//   soma-se (modulo 256) o valor de cada caractere; ao final, complemento de 2
//   da soma. Somando o checksum aos dados, o resultado (mod 256) e zero.
//
// calcularCRC(): CRC-32 bit a bit (polinomio reverso 0xEDB88320). Nao e usado
//   pela P3 2025, mas fica pronto caso a prova de 2026 peca CRC no lugar do CS.

public class Checksum {
    private static final long POLINOMIO_CRC32 = 0xEDB88320L;

    public Checksum() {
    }

    // Soma e Complemento de 2 -- retorna um valor de 0 a 255
    public int calcularChecksum(char[] caracteres) {
        int soma = 0;
        for (char c : caracteres) {
            soma = (soma + (c & 0xFF)) & 0xFF;      // soma modulo 256 (1 byte)
        }
        return (~soma + 1) & 0xFF;                   // complemento de 2
    }

    // CRC-32 -- retorna um valor de 32 bits (dentro de um long)
    public long calcularCRC(char[] caracteres) {
        long crc = 0xFFFFFFFFL;
        for (char c : caracteres) {
            crc ^= (c & 0xFF);
            for (int i = 0; i < 8; i++) {
                if ((crc & 1L) != 0L) {
                    crc = (crc >>> 1) ^ POLINOMIO_CRC32;
                } else {
                    crc = crc >>> 1;
                }
            }
        }
        return (crc ^ 0xFFFFFFFFL) & 0xFFFFFFFFL;
    }
}
